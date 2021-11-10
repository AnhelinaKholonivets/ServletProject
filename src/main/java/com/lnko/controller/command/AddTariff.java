package com.lnko.controller.command;

import com.lnko.model.entity.Product;
import com.lnko.model.entity.Tariff;
import com.lnko.model.service.ProductService;
import com.lnko.model.service.TariffService;
import com.lnko.model.service.impl.ProductServiceImpl;
import com.lnko.model.service.impl.TariffServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public class AddTariff implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            ProductService productService = new ProductServiceImpl();
            List<Product> products = productService.getAllProducts();
            request.setAttribute("products", products);
            return "/WEB-INF/admin/addTariff.jsp";
        }

        if ("POST".equalsIgnoreCase(request.getMethod())) {

            Tariff tariff = new Tariff();
            tariff.setName(request.getParameter("tariff"));
            Product product = new Product();
            product.setId(new Long(request.getParameter("product")));
            tariff.setProduct(product);
            tariff.setPrice(new BigDecimal(request.getParameter("price")));

            TariffService tariffService = new TariffServiceImpl();
            tariffService.saveTariff(tariff);
            return "redirect:/admin/tariffs";
        }
        return "";
    }
}
