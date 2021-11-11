package com.lnko.controller.command;

import com.lnko.model.dao.DaoFactory;
import com.lnko.util.ExtractBody;
import com.lnko.model.entity.Tariff;
import com.lnko.service.OrderService;
import com.lnko.service.TariffService;
import com.lnko.service.UserService;
import com.lnko.service.impl.OrderServiceImpl;
import com.lnko.service.impl.TariffServiceImpl;
import com.lnko.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddOrder implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            TariffService tariffService = new TariffServiceImpl();
            List<Tariff> tariffs = tariffService.getAllTariffs();
            request.setAttribute("tariffs", tariffs);
            return "/WEB-INF/user/addOrder.jsp";
        }

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String tariffsStr = ExtractBody.extractBody(request).replace("[", "")
                    .replace("]", "")
                    .replaceAll("\"", "");


            HttpSession session = request.getSession();
            String login = session.getAttribute("login").toString();

            UserService userService = new UserServiceImpl(DaoFactory.getInstance());
            Long userId = userService.getUserByLogin(login).getId();

            List<Long> tariffsIds = Stream.of(tariffsStr.split(","))
                    .map(String::trim)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());

            OrderService orderService = new OrderServiceImpl(DaoFactory.getInstance());
            orderService.saveNewOrders(tariffsIds, userId);
        }
        return "/WEB-INF/user/addOrder.jsp";
    }
}
