package com.abdullaevaziz.servlets;

import com.abdullaevaziz.DAO.DAO;
import com.abdullaevaziz.dto.ResponseResult;
import com.abdullaevaziz.model.User;
import com.abdullaevaziz.model.UserFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * • Написать сервлет FileServlet метод
 * POST который принимает id пользователя и файл,
 * добавляет информацию об этом файле в БД и
 * сохраняет файл на сервере Таблица UserFiles состоит из колонок:
 * id, filename, serverFilename, user_id.
 * Для одного пользователя файлы повторяться не могут.
 * Так как у разных пользователей названия файлов могут быть одинаковые,
 * то нужно на сервер их сохранять по следующему механизму:
 * 1. Принять файл на сервер
 * 2. Добавить информацию в БД об этом файле, кроме колонки serverFilename
 * 3. После того, как база присвоит id,
 * serverFilename должен быть равен "id.расширение, которое было у файла изначально"
 */
@WebServlet("/file_upload_servlet")
public class FileServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        PrintWriter writer = resp.getWriter();
        if (ServletFileUpload.isMultipartContent(req)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            String uploadPath = "C:\\users_files";
            File tempDir = new File(uploadPath);
            if (!tempDir.exists()) {
                tempDir.mkdirs();
            }
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> formItems = upload.parseRequest(req);
                if (formItems != null && formItems.size() > 0) {
                    FileItem itemRes = null;
                    long user_id = -1;
                    for (FileItem item : formItems) {
                        if (!item.isFormField()) {
                            itemRes = item;
                        } else {
                            String name = item.getFieldName();
                            String value = item.getString();
                            switch (name) {
                                case "user_id":
                                    user_id = Long.parseLong(value);
                            }
                        }
                    }

                    if (user_id != -1 && itemRes != null) {
                        try {
                            User user = (User) DAO.getObjectById(user_id,
                                    User.class);
                            if (user == null) {
                                resp.setStatus(400);
                                this.objectMapper.writeValue(resp.getWriter(),
                                        new ResponseResult<>("User не найден",
                                                null));
                                return;
                            }

                            String fileNameOriginal = itemRes.getName();
                            UserFile userFile = new UserFile(fileNameOriginal, user);
                            String fileExtension = fileNameOriginal.substring(fileNameOriginal.lastIndexOf("."));
                            String fileExtensionNew = fileExtension.replace(".", "");

                            try {

                                DAO.addObject(userFile);
                                String serverFilename = userFile.getId() + "." + fileExtensionNew;
                                userFile.setServerFilename(serverFilename);


                                DAO.updateObject(userFile);

                                itemRes.write(new File(tempDir, serverFilename));

                                this.objectMapper.writeValue(resp.getWriter(),
                                        new ResponseResult<>(null, userFile));

                            } catch (IllegalArgumentException e) {
                                resp.setStatus(400);
                                System.out.println(e.getClass());
                                e.printStackTrace();
                                this.objectMapper.writeValue(resp.getWriter(),
                                        new ResponseResult<>("Такой file для этого " +
                                                "usera уже существует в системе",
                                                null));
                            }
                        } catch (NumberFormatException e) {
                            resp.setStatus(400);
                            this.objectMapper.writeValue(resp.getWriter(),
                                    new ResponseResult<>("Некорректный формат id",
                                            null));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                writer.println(e.getMessage());
                resp.setStatus(400);
            }
        }
    }


    /**
     *•	Написать в FileServlet метод GET,
     * который для заданного id пользователя выдает список его загруженных на сервер файлов,
     *  а так же возвращает файл для заданного id пользователя и оригинального имени файла
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String userIdParam = req.getParameter("user_id");
        String filenameParam = req.getParameter("filename");

        if (userIdParam != null && filenameParam != null) {
            try {
                long idUser = Long.parseLong(userIdParam);
                User user = (User) DAO.getObjectById(idUser, User.class);
                if (user == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("User не найден", null));
                    return;
                }
                List<UserFile> userUserFileList = user.getUserFileSList();
                UserFile userFile =  userUserFileList.stream()
                        .filter(s -> filenameParam.equals(s.getFilename())).findFirst().orElse(null);
                if (userFile == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("FileName не найден", null));
                    return;
                }
                ServletContext cntx = req.getServletContext();
                File dir = new File("C:\\users_files");

                String stringServerFile = userFile.getServerFilename();
                File file = new File(dir + File.separator + stringServerFile);

                try (FileInputStream in = new FileInputStream(file);
                     OutputStream out = resp.getOutputStream()) {
                    resp.setContentLength((int) file.length());
                    long length = in.transferTo(out);
                    System.out.println("Bytes transferred: " + length);
                } catch (FileNotFoundException e) {
                    resp.getWriter().println("Incorrect file name");
                    resp.setStatus(400);
                } catch (IOException e) {
                    resp.getWriter().println("File Error!");
                    resp.setStatus(400);
                }

            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Некорректный формат id", null));
            }
        }
        else if (userIdParam != null) {
            try {
                long idUser = Long.parseLong(userIdParam);
                User user = (User) DAO.getObjectById(idUser, User.class);
                if (user == null) {
                    resp.setStatus(400);
                    this.objectMapper.writeValue(resp.getWriter(),
                            new ResponseResult<>("User не найден", null));

                }

                List<UserFile> userFiles = user.getUserFileSList();
                this.objectMapper.writeValue(resp.getWriter(), new ResponseResult<>(null, userFiles));
                DAO.closeOpenedSession();


            } catch (NumberFormatException e) {
                resp.setStatus(400);
                this.objectMapper.writeValue(resp.getWriter(),
                        new ResponseResult<>("Некорректный формат id", null));
            }
        }
    }
}