package com.lnko.controller.command;

import com.lnko.model.entity.User;
import com.lnko.model.service.UserService;
import com.lnko.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class Profile implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        UserService userService = new UserServiceImpl();
        User user = userService.getUserByLogin(request.getParameter("username"));
        request.setAttribute("user", user);
        return "/WEB-INF/user/profile.jsp";
    }
}
