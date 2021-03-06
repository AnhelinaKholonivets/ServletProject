package com.lnko.controller.command;

import com.lnko.model.dao.DaoFactory;
import com.lnko.model.entity.Order;
import com.lnko.model.entity.Role;
import com.lnko.model.entity.User;
import com.lnko.service.OrderService;
import com.lnko.service.UserService;
import com.lnko.service.impl.OrderServiceImpl;
import com.lnko.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllOrders implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        OrderService orderService = new OrderServiceImpl(DaoFactory.getInstance());
        List<Order> orders = null;

        HttpSession session = request.getSession();
        Object userRole = session.getAttribute("role");

        if (userRole.equals(Role.ADMIN.toString())) {
            orders = orderService.getAllOrders();
            request.setAttribute("orders", orders);
            return "/WEB-INF/admin/allOrders.jsp";
        }
        if (userRole.equals(Role.USER.toString())) {
            String login = session.getAttribute("login").toString();
            UserService userService = new UserServiceImpl(DaoFactory.getInstance());
            User user = userService.getUserByLogin(login);

            orders = orderService.getAllOrdersByUser(user);
            request.setAttribute("orders", orders);
            return "/WEB-INF/user/allOrders.jsp";
        }
        return "";
    }
}
