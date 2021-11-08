package com.lnko.controller.command;

import com.lnko.model.entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOut implements Command{
    @Override
    public String execute(HttpServletRequest request) {
            HttpSession session = request.getSession(false);
            session.invalidate();
            CommandUtility.setUserRole(request, Role.UNKNOWN.toString(), "guest");
            return "redirect:/app/login";
        }
    }

