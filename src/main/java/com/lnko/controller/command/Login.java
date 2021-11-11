package com.lnko.controller.command;

import com.lnko.model.dao.DaoFactory;
import com.lnko.model.entity.Role;
import com.lnko.model.entity.User;
import com.lnko.service.UserService;
import com.lnko.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class Login implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return "/login.jsp";
        }

        if ("POST".equalsIgnoreCase(request.getMethod())) {

            String login = request.getParameter("username");
            String pass = request.getParameter("password");

            if (login == null || login.equals("") || pass == null || pass.equals("")) {
                return "redirect:/app/login";
            }

            if (CommandUtility.checkUserIsLogged(request, login)) {
                return "/";
            }

            UserService userService = new UserServiceImpl(DaoFactory.getInstance());
            User user = userService.getUserByLogin(login);
            String userPass = user.getPassword();

            if (!userPass.equals(pass)) {
                return "/login.jsp";
            }

            String userRole = user.getRole();

            if (userRole.equals(Role.ADMIN.toString())) {
                CommandUtility.setUserRole(request, Role.ADMIN.toString(), login);
                return "redirect:/app/admin/users";
            }
            if (userRole.equals(Role.USER.toString())) {
                CommandUtility.setUserRole(request, Role.USER.toString(), login);
                return "redirect:/app/user/profile";
            } else {
                CommandUtility.setUserRole(request, Role.UNKNOWN.toString(), login);
                return "/login.jsp";
            }
        }

        return "";
    }
}
