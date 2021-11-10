package com.lnko.controller.command;

import com.lnko.model.entity.User;
import com.lnko.model.service.UserService;
import com.lnko.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.math.BigDecimal;

public class Profile implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            UserService userService = new UserServiceImpl();
            HttpSession session = request.getSession();
            String login = session.getAttribute("login").toString();
            User user = userService.getUserByLogin(login);
            request.setAttribute("user", user);
        }

        if ("PUT".equalsIgnoreCase(request.getMethod())) {

            String balance = extractBody(request).replace("addToBalance: ","");
            BigDecimal addBalance = BigDecimal.valueOf(Long.parseLong(balance));

            HttpSession session = request.getSession();
            String login = session.getAttribute("login").toString();

            UserService userService = new UserServiceImpl();
            User user = userService.getUserByLogin(login);

            userService.refileBalance(user, addBalance);
        }

        return "/WEB-INF/user/profile.jsp";
    }

    private String extractBody(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
