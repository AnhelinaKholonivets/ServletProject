package com.lnko.controller.command;

import com.lnko.model.entity.Tariff;
import com.lnko.model.service.OrderService;
import com.lnko.model.service.TariffService;
import com.lnko.model.service.UserService;
import com.lnko.model.service.impl.OrderServiceImpl;
import com.lnko.model.service.impl.TariffServiceImpl;
import com.lnko.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
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
            String tariffsStr = extractBody(request).replace("[", "")
                    .replace("]", "")
                    .replaceAll("\"", "");

            HttpSession session = request.getSession();
            String login = session.getAttribute("login").toString();

            UserService userService = new UserServiceImpl();
            Long userId = userService.getUserByLogin(login).getId();

            List<Long> tariffsIds = Stream.of(tariffsStr.split(","))
                    .map(String::trim)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());

            OrderService orderService = new OrderServiceImpl();
            orderService.saveNewOrders(tariffsIds, userId);
        }
        return "/WEB-INF/user/addOrder.jsp";
    }

    private String extractBody(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
