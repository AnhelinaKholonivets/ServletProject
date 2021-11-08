package com.lnko.controller.command;

import com.lnko.model.entity.Role;
import com.lnko.model.entity.User;
import com.lnko.model.service.UserService;
import com.lnko.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class Login implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        String login = request.getParameter("username");
        String pass = request.getParameter("password");

        if( login == null || login.equals("") || pass == null || pass.equals("")  ){
            return "/login.jsp";
        }

        if (CommandUtility.checkUserIsLogged(request, login)) {
            return "/";
        }

        UserService userService = new UserServiceImpl();
        User user = userService.getUserByLogin(login);
        pass=user.getPassword();

        if(!user.getPassword().equals(pass)){
            return "/login.jsp";
        }

        String userRole = user.getRole();

        if (userRole.equals(Role.ADMIN.toString())) {
            CommandUtility.setUserRole(request, Role.ADMIN.toString(), login);
            return "redirect:/app/admin/";
        }
        if(userRole.equals(Role.USER.toString())) {
            CommandUtility.setUserRole(request,Role.USER.toString(), login);
            return "redirect:/app/user/";
        } else {
            CommandUtility.setUserRole(request, Role.UNKNOWN.toString(), login);
            return "/login.jsp";
        }
    }
}
