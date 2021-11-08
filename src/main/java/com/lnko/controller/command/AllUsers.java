package com.lnko.controller.command;

import com.lnko.model.entity.User;
import com.lnko.model.service.UserService;
import com.lnko.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllUsers implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        UserService userService = new UserServiceImpl();
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        return "/WEB-INF/admin/allUsers.jsp";
    }
}
