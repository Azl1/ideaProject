package filter;


import DAO.DAO;
import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie[] cookies = request.getCookies();
        String valueHash = null;
        String valueUserId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("hash")) {
                    valueHash = cookie.getValue();
                }
                if (cookie.getName().equals("userId")) {
                    valueUserId = cookie.getValue();
                }
            }
        }

        System.out.println(request.getRequestURI());

        //URL Запроса/переадресации на Servlet входа
        String userRequest = request.getContextPath() + "/user";
        //Если сессия ранее создана
        boolean loginRequest = request.getRequestURI().contains(userRequest)
                && request.getMethod().equals("GET");
        boolean registerRequest = request.getRequestURI().contains(userRequest)
                && request.getMethod().equals("POST");
        //Если запрос пришел со страницы с входом или сессия не пуста даем добро следовать дальше
        //Если нет ридерект на страницу входа
        if (request.getRequestURI().equals(request.getContextPath() + "/login.html")
                || request.getRequestURI().equals(request.getContextPath() + "/registration.html")
                || request.getRequestURI().endsWith("js")
                || loginRequest
                || registerRequest
                || (valueHash != null && valueUserId != null
                && DAO.getObjectByParams(
                new String[]{"hash", "id"},
                new Object[]{valueHash, Long.parseLong(valueUserId)},
                User.class) != null)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.html");
        }
    }

    @Override
    public void destroy() {

    }
}
