package com.lnko.controller.command;

import com.lnko.model.dao.DaoFactory;
import com.lnko.model.entity.User;
import com.lnko.service.UserService;
import com.lnko.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllUsers implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            UserService userService = new UserServiceImpl(DaoFactory.getInstance());

            String strPage = request.getParameter("page");
            int page;

            if (strPage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(strPage);
            }

            int size = 5;

            List<User> users = userService.getAllUsersPage(page, size);
            request.setAttribute("users", users);
            return "/WEB-INF/admin/allUsers.jsp";
        }

        if ("PUT".equalsIgnoreCase(request.getMethod())) {
            String user = request.getParameter("id");
            Long userId = Long.valueOf(user);

            UserService userService = new UserServiceImpl(DaoFactory.getInstance());
            userService.blockUser(userId);
        }

        return "";
    }
}
