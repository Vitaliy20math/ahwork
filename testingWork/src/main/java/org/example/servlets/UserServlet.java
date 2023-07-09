package org.example.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.exceptions.UncorrectJsonTransfer;
import org.example.model.User;
import org.example.service.ServiceUser;
import org.example.service.ServiceUserImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    ServiceUser serviceUser;
    ObjectMapper objectMapper;
    public UserServlet() {
        serviceUser = new ServiceUserImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        String id = req.getParameter("id");
        if (id != null) {
            User user = serviceUser.findUserById(Integer.parseInt(id));
            try {
                objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(user);
                resp.getWriter().write(json);
            } catch (UncorrectJsonTransfer e) {
                e.printStackTrace();
            }
        } else {
            List<User> users = serviceUser.getUsers();
            try {
                objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(users);
                resp.getWriter().write(json);
            } catch (UncorrectJsonTransfer e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = objectMapper.readValue(req.getReader(), User.class);
            if (user == null) {
                resp.sendError(resp.SC_BAD_REQUEST, "Invalid user data");
                return;
            }
            serviceUser.createUser(user);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(resp.getWriter(), user);

        } catch (UncorrectJsonTransfer uncorrectJsonTransfer) {
            uncorrectJsonTransfer.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        if (id != null) {
            try {
                serviceUser.deleteUser(serviceUser.findUserById(Integer.parseInt(id)));
                objectMapper = new ObjectMapper();
                List<User> list = serviceUser.getUsers();
                String json = objectMapper.writeValueAsString(list);
                resp.getWriter().write(json);
            } catch (UncorrectJsonTransfer | ClassCastException e) {
                e.printStackTrace();
            }
        } else {
            resp.sendError(resp.SC_BAD_REQUEST, "Not id for delete");
            return;
        }

    }
}
