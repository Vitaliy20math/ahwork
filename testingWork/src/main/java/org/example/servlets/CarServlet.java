package org.example.servlets;

import org.example.dao.DaoCar;
import org.example.service.ServiceCar;
import org.example.service.ServiceCarImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet
public class CarServlet extends HttpServlet {
    ServiceCar serviceCar;

    public CarServlet() {
        serviceCar = new ServiceCarImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
