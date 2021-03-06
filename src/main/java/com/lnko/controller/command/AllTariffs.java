package com.lnko.controller.command;

import com.lnko.model.dao.DaoFactory;
import com.lnko.model.entity.Role;
import com.lnko.model.entity.Tariff;
import com.lnko.service.TariffService;
import com.lnko.service.impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllTariffs implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            TariffService tariffService = new TariffServiceImpl(DaoFactory.getInstance());
            List<Tariff> tariffs = tariffService.getAllTariffs();

            request.setAttribute("tariffs", tariffs);

            HttpSession session = request.getSession();
            Object userRole = session.getAttribute("role");

            if (userRole.equals(Role.ADMIN.toString())) {
                return "/WEB-INF/admin/allTariffs.jsp";
            }
            if (userRole.equals(Role.USER.toString())) {
                return "/WEB-INF/user/allTariffs.jsp";
            }
        }

        if ("DELETE".equalsIgnoreCase(request.getMethod())) {
            String tariff = request.getParameter("id");
            Long tariffId = Long.valueOf(tariff);

            TariffService tariffService = new TariffServiceImpl(DaoFactory.getInstance());
            tariffService.deleteTariff(tariffId);
        }

     return "/";
    }
}
