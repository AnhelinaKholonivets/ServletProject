package com.lnko.controller.filters;

import com.lnko.model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) servletRequest;

        HttpSession session = req.getSession();

        String currentUri = req.getRequestURI().replace("/app", "");
        Object userRole = session.getAttribute("role");

        if (userRole == null || userRole.equals("")) {
            userRole = "";
        }

        if (checkAccess(currentUri, getAccessList(userRole.toString()))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletRequest.getRequestDispatcher("/").forward(servletRequest, servletResponse);
        }
    }

    private boolean checkAccess(String uri, List<String> accessList) {
        return accessList.contains(uri);
    }

    private List<String> getAccessList(String role) {
        List<String> adminAccess = Arrays.asList("/admin/tariffs", "/admin/orders", "/admin/users", "/", "/home", "admin/tariffs/addTariff");
        List<String> userAccess = Arrays.asList("/user/tariffs", "/user/orders", "/user/profile", "/", "/home", "/user/orders/addOrder");
        List<String> unknownAccess = Arrays.asList("/login", "/", "/home");

        if (role.equals(Role.ADMIN.toString())) {
            return adminAccess;
        }
        if (role.equals(Role.USER.toString())) {
            return userAccess;
        }
        return unknownAccess;
    }

    @Override
    public void destroy() {

    }
}
