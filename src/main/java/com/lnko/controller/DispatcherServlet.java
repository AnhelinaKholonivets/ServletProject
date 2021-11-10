package com.lnko.controller;

import com.lnko.controller.command.*;
import com.lnko.model.service.impl.LowBalanceException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    private final Map<String, Command> commands = new HashMap<>();

    public void init(ServletConfig servletConfig) {

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

        commands.put("login", new Login());
        commands.put("logout", new LogOut());
        commands.put("admin/users", new AllUsers());
        commands.put("admin/users/addUser", new AddUser());
        commands.put("admin/tariffs", new AllTariffs());
        commands.put("admin/tariffs/addTariff", new AddTariff());
        commands.put("admin/orders", new AllOrders());
        commands.put("user/tariffs", new AllTariffs());
        commands.put("user/orders", new AllOrders());
        commands.put("user/orders/addOrder", new AddOrder());
        commands.put("user/profile", new Profile());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String path = request.getRequestURI();
            path = path.replaceAll(".*/app/", "");
            Command command = commands.getOrDefault(path, (r) -> "/index.jsp");
            String page = command.execute(request);
            if (page.contains("redirect:")) {
                response.sendRedirect(page.replace("redirect:/", "/"));
            } else {
                request.getRequestDispatcher(page).forward(request, response);
            }
        } catch (LowBalanceException e) {
            response.sendRedirect("WEB-INF/user/profile.jsp?LowBalanceError");
        } catch (RuntimeException e) {
            response.sendRedirect("WEB-INF/error.jsp");
        }
    }
}
