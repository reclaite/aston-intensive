package com.reclaite.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reclaite.model.User;
import com.reclaite.model.impl.UserImpl;
import com.reclaite.repository.UserRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/api/user")
public class UserServlet extends HttpServlet {

    private final UserRepository repository = new UserRepository();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String sessionToken = req.getSession().getId();
        User user = repository.get(sessionToken);

        try {
            if (user == null) {
                resp.getWriter().write(objectMapper.writeValueAsString("User not found"));
                resp.setStatus(404);
                return;
            }
            String json = objectMapper.writeValueAsString(user);
            resp.getWriter().write(json);
        } catch (IOException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Cannot write object into json", exception);
            resp.getWriter().write(objectMapper.writeValueAsString(new StatusResponse(500, "Error")));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            UserImpl user = objectMapper.readValue(req.getReader(), UserImpl.class);
            repository.update(user);
            resp.getWriter().write(objectMapper.writeValueAsString(new StatusResponse(200, "Successful!")));
        } catch (IOException exception) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Didn't find user by specified body", exception);
            resp.getWriter().write(objectMapper.writeValueAsString(new StatusResponse(500, "Error")));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            UserImpl user = objectMapper.readValue(req.getReader(), UserImpl.class);
            repository.add(user);
            resp.getWriter().write(objectMapper.writeValueAsString(new StatusResponse(200, "Successful!")));
        } catch (IOException exception) {
            Logger.getAnonymousLogger().log(Level.WARNING, "Didn't find user by specified body", exception);
            resp.getWriter().write(objectMapper.writeValueAsString(new StatusResponse(500, "Error")));
        }
    }
}
