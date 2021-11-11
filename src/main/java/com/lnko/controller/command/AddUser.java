package com.lnko.controller.command;

import com.lnko.model.dao.DaoFactory;
import com.lnko.model.entity.Role;
import com.lnko.model.entity.User;
import com.lnko.service.UserService;
import com.lnko.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class AddUser implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return "/WEB-INF/admin/addUser.jsp";
        }

        if ("POST".equalsIgnoreCase(request.getMethod())) {

            String email = request.getParameter("email");
            String pass = request.getParameter("password");
            if (email == null || email.equals("") || pass == null || pass.equals("")) {
                return "/WEB-INF/admin/addUser.jsp";
            }

            User user = new User();
            user.setFirstName(request.getParameter("firstName"));
            user.setLastName(request.getParameter("lastName"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setBalance(new BigDecimal(request.getParameter("balance")));
            user.setBlocked(false);
            user.setRole(Role.USER.toString());

            UserService userService = new UserServiceImpl(DaoFactory.getInstance());
            userService.saveNewUser(user);
        }

        return "redirect:/app/admin/users";
    }
}
