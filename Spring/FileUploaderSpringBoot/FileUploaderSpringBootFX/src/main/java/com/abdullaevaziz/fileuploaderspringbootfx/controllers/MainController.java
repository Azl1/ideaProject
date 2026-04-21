package com.abdullaevaziz.fileuploaderspringbootfx.controllers;


import com.abdullaevaziz.fileuploaderspringbootfx.App;
import com.abdullaevaziz.fileuploaderspringbootfx.model.User;
import com.abdullaevaziz.fileuploaderspringbootfx.model.UserFile;
import com.abdullaevaziz.fileuploaderspringbootfx.model.UserFileType;
import com.abdullaevaziz.fileuploaderspringbootfx.retrofit.UserFileRepository;
import com.abdullaevaziz.fileuploaderspringbootfx.retrofit.UserRepository;
import com.abdullaevaziz.fileuploaderspringbootfx.util.Constants;
import com.abdullaevaziz.fileuploaderspringbootfx.util.Util;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class MainController implements ControllerData<User> {

    //Клиент
    @FXML
    public Label getAbsolutePathLabelClient;
    //Сервер
    @FXML
    public Label getPathLabelServer;
    @FXML // Клиент
    public ListView<UserFile> getAbsolutePathListViewClient;
    @FXML // Сервер
    public ListView<UserFile> getPathListViewServer;
    @FXML
    public Button buttonChoseUser;
    @FXML
    public Button buttonExit;
    private Preferences preferences = Preferences.userNodeForPackage(App.class);
    private String token = preferences.get(Constants.PREFERENCE_KEY_TOKEN, null);
    private UserFileRepository userFileRepository = new UserFileRepository(token);
    private UserRepository userRepository = new UserRepository(token);
    private User user;
    private String currentPathServer;
    private String currentPathClient;

    //сюда приходит когда юзер и когда админ передает юзера
    @FXML
    public void initialize() {
        System.out.println("huy");
        try {
            String role = Util.getRole(token);
            if (!role.equals("ROLE_ADMIN")) {
                long userId = preferences.getLong(Constants.PREFERENCE_KEY_ID, -1);
                this.user = userRepository.getUserId(userId);
                System.out.println("initialize " + user.getUserType() + " " + user);
                buttonExit.setVisible(true);
                buttonChoseUser.setVisible(false);
                init();
            }

        } catch (IOException e) {
            e.printStackTrace();
            App.showAlert("ERROR", "Ошибка обращения к серверу", Alert.AlertType.ERROR);
        }
    }

    //сюда приходит только когда админ выбирает юзера
    @Override
    public void initData(User value) throws IOException {
        user = value;
        buttonExit.setVisible(false);
        init();
    }

    private void init() throws IOException {
        String userBasePath = String.valueOf(user.getId());
        currentPathServer = userBasePath;
        currentPathClient = "C:\\";

        getPathLabelServer.setText("\\" + userBasePath);
        getAbsolutePathLabelClient.setText("C:\\");

        ContextMenu contextMenu = new ContextMenu();
        MenuItem renameItem = new MenuItem("Переименовать");
        MenuItem deleteItem = new MenuItem("Удалить");
        contextMenu.getItems().addAll(renameItem, deleteItem);

        List<UserFile> informationFilesClient = new ArrayList<>(Arrays.stream(new File("C:\\").listFiles())
                .map(x -> new UserFile(x.getPath(), x.isDirectory() ? UserFileType.DIR : UserFileType.FILE)).toList());
        informationFilesClient.add(0, new UserFile("...", UserFileType.RETURN));
        this.getAbsolutePathListViewClient.setItems(FXCollections.observableList(informationFilesClient));
/**
 *                                                    Client
 * -----------------------------------------------------------------------------------------------------------------------------------------
 */
        this.getAbsolutePathListViewClient.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        UserFile userFile = getAbsolutePathListViewClient.getSelectionModel().getSelectedItem();

                        if (userFile == null) {
                            App.showAlert("INFO", "User file не выбран",
                                    Alert.AlertType.INFORMATION);
                            return;
                        }

                        if (userFile.getUserFileType() == UserFileType.RETURN) {
                            File currentDir = new File(currentPathClient);
                            File parentDir = currentDir.getParentFile();

                            if (parentDir != null) {
                                currentPathClient = parentDir.getAbsolutePath();
                            } else {
                                currentPathClient = "C:\\";
                            }
                            System.out.println("CurrentServerPathClient: " + currentPathClient);
                            List<UserFile> informationFilesClient = new ArrayList<>(Arrays.stream(new File(currentPathClient).listFiles())
                                    .map(x -> new UserFile(x.getPath(), x.isDirectory() ? UserFileType.DIR : UserFileType.FILE)).toList());
                            informationFilesClient.add(0, new UserFile("...", UserFileType.RETURN));
                            getAbsolutePathListViewClient.setItems(FXCollections.observableList(informationFilesClient));
                            getAbsolutePathLabelClient.setText(userFile.getPath().replace("/", "\\"));

                            getAbsolutePathLabelClient.setText(currentPathClient.replace("/", "\\"));
                            return;
                        }

                        if (userFile.getUserFileType() == UserFileType.FILE) {
                            App.showAlert("INFO", "Нельзя открыть файл: " + userFile, Alert.AlertType.INFORMATION);
                            return;
                        }

                        if (userFile.getUserFileType() == UserFileType.DIR) {
                            String getPathSelect = userFile.getPath().replace("\\", "/");

                            System.out.println("Папка клиента  getPathSelect " + getPathSelect);
                            currentPathClient = getPathSelect;
                            List<UserFile> informationFilesClient = new ArrayList<>(Arrays.stream(new File(currentPathClient).listFiles())
                                    .map(x -> new UserFile(x.getPath(), x.isDirectory() ? UserFileType.DIR : UserFileType.FILE)).toList());
                            informationFilesClient.add(0, new UserFile("...", UserFileType.RETURN));
                            getAbsolutePathListViewClient.setItems(FXCollections.observableList(informationFilesClient));
                            getAbsolutePathLabelClient.setText(userFile.getPath().replace("/", "\\"));

                        }
                    }
                }
            }
        });

        getAbsolutePathListViewClient.setOnContextMenuRequested(event -> {
            if (getAbsolutePathListViewClient.getSelectionModel().getSelectedItem() != null) {
                contextMenu.show(getAbsolutePathListViewClient, event.getScreenX(), event.getScreenY());
                UserFile userFile = getAbsolutePathListViewClient.getSelectionModel().getSelectedItem();


                renameItem.setOnAction(e -> {
                    UserFile userFileSelected = getAbsolutePathListViewClient.getSelectionModel().getSelectedItem();
                    if (userFileSelected == null) return;
                    String oldPath = userFileSelected.getPath();
                    File oldFile = new File(oldPath);

                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Переименование");
                    dialog.setHeaderText("Новое имя файла");
                    dialog.showAndWait().ifPresent(newName -> {
                        if (!newName.isBlank()) {
                            File newFile = new File(oldFile.getParentFile(), newName);
                            if (newFile.exists()) {
                                App.showAlert("Ошибка", "Файл с таким именем уже существует", Alert.AlertType.ERROR);
                                return;
                            }
                            boolean renamed = oldFile.renameTo(newFile);
                            refreshListViewClient();
                        }
                    });
                    contextMenu.hide();
                });


                deleteItem.setOnAction(e -> {
                    UserFile userFileSelected = getAbsolutePathListViewClient.getSelectionModel().getSelectedItem();
                    if (userFileSelected == null) return;
                    String filePath = userFileSelected.getPath();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Удаление");
                    alert.setHeaderText("Удалить файл?");
                    alert.setContentText(filePath);
                    alert.showAndWait().ifPresent(result -> {
                        if (result == ButtonType.OK) {
                            try {
                                boolean deleted = deleteRecursively(filePath);
                                if (deleted) {
                                    File currentDir = new File(currentPathClient);
                                    List<UserFile> files = new ArrayList<>();
                                    File[] listFiles = currentDir.listFiles();
                                    if (listFiles != null) {
                                        for (File f : listFiles) {
                                            files.add(new UserFile(f.getPath(), f.isDirectory() ? UserFileType.DIR : UserFileType.FILE));
                                        }
                                    }
                                    getAbsolutePathListViewClient.getItems().remove(userFileSelected);
                                    getAbsolutePathLabelClient.setText(currentPathClient);
                                }

                            } catch (Exception ex) {
                                ex.printStackTrace();
                                App.showAlert("Error!", ex.getMessage(), Alert.AlertType.ERROR);
                            }
                        }

                    });
                    contextMenu.hide();
                });

            }
        });

/**
 *                                                    Server
 * --------------------------------------------------------------------------------------------------------------------------------------------------------
 */
        List<UserFile> informationFilesServer = new UserFileRepository(token).getInformationFiles(userBasePath);
        informationFilesServer.add(0, new UserFile("...", UserFileType.RETURN));
        this.getPathListViewServer.setItems(FXCollections.observableList(informationFilesServer));
        this.getPathListViewServer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        UserFile userFile = getPathListViewServer.getSelectionModel().getSelectedItem();

                        if (userFile == null) {
                            App.showAlert("INFO", "User file не выбран",
                                    Alert.AlertType.INFORMATION);
                            return;
                        }

                        if (userFile.getUserFileType() == UserFileType.RETURN) {
                            try {
                                String[] parts = currentPathServer.split("/");
                                if (parts.length > 1) {
                                    currentPathServer = String.join("/", Arrays.copyOf(parts, parts.length - 1));
                                } else {
                                    currentPathServer = String.valueOf(user.getId());
                                }

                                List<UserFile> informationFiles = userFileRepository.getInformationFiles(currentPathServer);
                                informationFiles.add(0, new UserFile("...", UserFileType.RETURN));
                                getPathListViewServer.setItems(FXCollections.observableList(informationFiles));
                                //refreshCurrentPathWithPath(currentServerPath);

                                getPathLabelServer.setText(currentPathServer.replace("/", "\\"));
                            } catch (IOException e) {
                                App.showAlert("ERROR", e.getMessage(), Alert.AlertType.ERROR);
                            }
                            return;
                        }

                        if (userFile.getUserFileType() == UserFileType.FILE) {
                            App.showAlert("INFO", "Нельзя открыть файл: " + userFile, Alert.AlertType.INFORMATION);
                            return;
                        }

                        if (userFile.getUserFileType() == UserFileType.DIR) {
                            String getPathSelect = userFile.getPath().replace("\\", "/");
                            System.out.println("Папка клиента  getPathSelect " + getPathSelect);
                            currentPathServer = getPathSelect;
                            try {
                                List<UserFile> files = userFileRepository.getInformationFiles(getPathSelect);
                                files.add(0, new UserFile("...", UserFileType.RETURN));
                                getPathListViewServer.setItems(FXCollections.observableList(files));
                                getPathLabelServer.setText(userFile.getPath().replace("/", "\\"));

                            } catch (IOException e) {
                                App.showAlert("ERROR", e.getMessage(), Alert.AlertType.ERROR);
                            }
                        }
                    }
                }
            }
        });

        getPathListViewServer.setOnContextMenuRequested(event -> {
            if (getPathListViewServer.getSelectionModel().getSelectedItem() != null) {
                contextMenu.show(getPathListViewServer, event.getScreenX(), event.getScreenY());
                UserFile userFile = getPathListViewServer.getSelectionModel().getSelectedItem();
                String oldPath = userFile.getPath().replace("\\", "/");


                renameItem.setOnAction(e -> {
                    UserFile userFileSelected = getPathListViewServer.getSelectionModel().getSelectedItem();
                    if (userFileSelected == null) return;
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Переименование");
                    dialog.setHeaderText("Новое имя файла");
                    dialog.showAndWait().ifPresent(newName -> {
                        if (!newName.isBlank()) {
                            try {
                                System.out.println(user.getId());
                                System.out.println(newName);
                                userFileRepository.put(oldPath, newName);

                                List<UserFile> files = userFileRepository.getInformationFiles(String.valueOf(user.getId()));
                                files.add(0, new UserFile("...", UserFileType.RETURN));
                                getPathListViewServer.setItems(FXCollections.observableList(files));
                                getAbsolutePathLabelClient.setText(String.valueOf(user.getId()));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                                App.showAlert("Error!", ex.getMessage(), Alert.AlertType.ERROR);
                            }
                        }
                    });
                    contextMenu.hide();
                });


                deleteItem.setOnAction(e -> {
                    UserFile userFileSelected = getPathListViewServer.getSelectionModel().getSelectedItem();
                    String filePath = userFileSelected.getPath().replace("\\", "/");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Удаление");
                    alert.setHeaderText("Удалить файл?");
                    //alert.setContentText();
                    alert.showAndWait().ifPresent(result -> {
                        if (result == ButtonType.OK) {
                            try {
                                System.out.println("Удаление файла: " + filePath);
                                String res = userFileRepository.delete(filePath);
                                getPathListViewServer.getItems().remove(userFileSelected);

                                String currentPath = currentPathServer;
                                if (currentPath == null || currentPath.isEmpty()) {
                                    currentPath = String.valueOf(user.getId());
                                }
                                List<UserFile> files = userFileRepository.getInformationFiles(currentPath);
                                files.add(0, new UserFile("...", UserFileType.RETURN));
                                getPathListViewServer.setItems(FXCollections.observableList(files));

                                if (currentPath.equals(String.valueOf(user.getId()))) {
                                    getPathLabelServer.setText("\\" + currentPath);
                                } else {
                                    getPathLabelServer.setText(currentPath.replace("/", "\\"));
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                                App.showAlert("Error!", ex.getMessage(), Alert.AlertType.ERROR);
                            }
                        }
                    });
                    contextMenu.hide();
                });
            }
        });
    }

    public void refreshListViewServer(String path) {
        try {
            List<UserFile> files = userFileRepository.getInformationFiles(path);
            files.add(0, new UserFile("...", UserFileType.RETURN));
            getPathListViewServer.setItems(FXCollections.observableList(files));
            getAbsolutePathLabelClient.setText("C:\\" + user.getId());
            if (path.equals(String.valueOf(user.getId()))) {
                getPathLabelServer.setText("\\" + path);
            } else {
                getPathLabelServer.setText(path.replace("/", "\\"));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            App.showAlert("Error!", ex.getMessage(), Alert.AlertType.ERROR);
            preferences.remove(Constants.PREFERENCE_KEY_ID);
            preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
            preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
            preferences.remove(Constants.PREFERENCE_KEY_TOKEN);
        }
    }

    private void refreshListViewClient() {
        File currentDir = new File(currentPathClient);
        List<UserFile> files = new ArrayList<>();
        File[] listFiles = currentDir.listFiles();
        if (listFiles != null) {
            for (File f : listFiles) {
                files.add(new UserFile(f.getPath(), f.isDirectory() ? UserFileType.DIR : UserFileType.FILE));
            }
        }
        files.add(0, new UserFile("...", UserFileType.RETURN));
        getAbsolutePathListViewClient.setItems(FXCollections.observableList(files));
        getAbsolutePathLabelClient.setText(currentPathClient);
    }


    @FXML
    public void addFolderButtonClient(ActionEvent actionEvent) {
        try {

            App.openWindowAndWait("addPathClient.fxml", "Add path info", currentPathClient);
            currentPathClient = currentPathClient.replace("/", "\\");
            refreshListViewClient();

        } catch (IOException e) {
            e.printStackTrace();
            preferences.remove(Constants.PREFERENCE_KEY_ID);
            preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
            preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
            preferences.remove(Constants.PREFERENCE_KEY_TOKEN);
        }
    }

    @FXML
    public void addFolderButtonServer(ActionEvent actionEvent) {
        try {
            App.openWindowAndWait("addPathServer.fxml", "Add path info", currentPathServer);
            refreshListViewServer(currentPathServer);

        } catch (IOException e) {
            e.printStackTrace();
            preferences.remove(Constants.PREFERENCE_KEY_ID);
            preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
            preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
            preferences.remove(Constants.PREFERENCE_KEY_TOKEN);
        }
    }

    @FXML
    public void exit(ActionEvent actionEvent) throws IOException {
        preferences.remove(Constants.PREFERENCE_KEY_ID);
        preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
        preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
        preferences.remove(Constants.PREFERENCE_KEY_TOKEN);

        App.closeWindow(actionEvent);
        App.openWindow("auth.fxml", "Authorization info", null);
    }

    @FXML
    public void backToUsersButton(ActionEvent actionEvent) {
        try {
            App.openWindow("choseUser.fxml", "Authorization info", null);
            App.closeWindow(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
            preferences.remove(Constants.PREFERENCE_KEY_ID);
            preferences.remove(Constants.PREFERENCE_KEY_LOGIN);
            preferences.remove(Constants.PREFERENCE_KEY_PASSWORD);
            preferences.remove(Constants.PREFERENCE_KEY_TOKEN);
        }
    }

    private boolean deleteRecursively(String path) {
        File file = new File(path);
        if (file == null || !file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    deleteRecursively(child.getPath());
                }
            }
        }
        return file.delete();
    }

    /**
     * На сервер
     */
    @FXML
    public void moveToServer(ActionEvent event) {
        File tempZip = null;
        try {
            UserFile selected = getAbsolutePathListViewClient.getSelectionModel().getSelectedItem();
            if (selected == null) {
                App.showAlert("Error", "Выберите файл или папку", Alert.AlertType.ERROR);
                return;
            }

            File sourceFile = new File(selected.getPath());
            if (sourceFile.isDirectory()) {
                if (sourceFile.listFiles() == null || sourceFile.listFiles().length == 0) {
                    App.showAlert("Warning", "Папка пуста! Нечего загружать.", Alert.AlertType.WARNING);
                    return;
                }
                tempZip = createZipArchive(sourceFile);
                boolean success = userFileRepository.uploadFolder(currentPathServer, tempZip);
                if (success) {
                    App.showAlert("Success","Папка '" + sourceFile.getName() + "' загружена", Alert.AlertType.INFORMATION);
                }
            } else {
                boolean success = userFileRepository.uploadFile(currentPathServer, sourceFile);
                if (success) {
                    App.showAlert("Success",
                            "Файл '" + sourceFile.getName() + "' загружен", Alert.AlertType.INFORMATION);
                }
            }
            refreshListViewServer(currentPathServer);
        } catch (Exception e) {
            e.printStackTrace();
            App.showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        } finally {
            if (tempZip != null && tempZip.exists()) {
                boolean deleted = tempZip.delete();
                if (deleted) {
                    System.out.println("Временный ZIP удален: " + tempZip.getAbsolutePath());
                }
            }
        }
    }

    /**
     * На клиент
     */
    @FXML
    public void moveToClient(ActionEvent event) {
        try {
            UserFile selected = this.getPathListViewServer.getSelectionModel().getSelectedItem();
            if (selected == null) {
                App.showAlert("Error!", "Выберите файл или папку", Alert.AlertType.ERROR);
                return;
            }
            FileChooser fileChooser = new FileChooser();
            if (selected.getUserFileType() == UserFileType.DIR) {
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("ZIP files", "*.zip"));
                fileChooser.setInitialFileName(selected + ".zip");

                File saveFile = fileChooser.showSaveDialog(null);
                if (saveFile != null) {
                    String serverPath = selected.getPath().replace("\\", "/");
                    new UserFileRepository(token).downloadFileZip(serverPath, saveFile);
                    refreshListViewServer(currentPathServer);
                    App.showAlert("Info!", "Сохранено: " + saveFile.getName(), Alert.AlertType.INFORMATION);
                }
            } else {
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All files (*.*)", "*.*"));
                fileChooser.setInitialFileName(selected.toString());

                File saveFile = fileChooser.showSaveDialog(null);
                if (saveFile != null) {
                    String serverPath = selected.getPath().replace("\\", "/");
                    new UserFileRepository(token).downloadFile(serverPath, saveFile);
                    refreshListViewServer(currentPathServer);
                    App.showAlert("Info!", "Сохранено: " + saveFile.getName(), Alert.AlertType.INFORMATION);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            App.showAlert("Error!", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public File createZipArchive(File sourceFile) throws IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        File zipFile = new File(tempDir, sourceFile.getName() + ".zip");

        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            if (sourceFile.isDirectory()) {
                zipFolder(sourceFile, sourceFile.getName(), zos);
            } else {
                zipFile(sourceFile, sourceFile.getName(), zos);
            }
        }
        return zipFile;
    }

    public void zipFolder(File folder, String parentPath, ZipOutputStream zos) throws IOException {
        File[] files = folder.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                zipFolder(file, parentPath + "/" + file.getName(), zos);
            } else {
                zipFile(file, parentPath + "/" + file.getName(), zos);
            }
        }
    }

    private void zipFile(File file, String entryName, ZipOutputStream zos) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            ZipEntry entry = new ZipEntry(entryName);
            zos.putNextEntry(entry);

            byte[] buffer = new byte[4096];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
        }
    }
}