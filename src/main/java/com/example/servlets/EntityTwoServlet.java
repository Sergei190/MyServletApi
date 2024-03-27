package com.example.servlets;

import com.example.dao.EntityTwoDao;
import com.example.dao.impl.EntityTwoDaoImpl;
import com.example.model.EntityTwo;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EntityTwoServlet extends HttpServlet {

    private EntityTwoDao entityTwoDao;

    @Override
    public void init() throws ServletException {
        super.init();
        entityTwoDao = new EntityTwoDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EntityTwo> entities = entityTwoDao.getAllEntities();
        String json = new Gson().toJson(entities);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        EntityTwo entityTwo = new EntityTwo(name);
        entityTwoDao.saveEntity(entityTwo);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        EntityTwo entityTwo = new EntityTwo(id, name);
        entityTwoDao.updateEntity(entityTwo);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        EntityTwo entityTwo = new EntityTwo();
        entityTwo.setId(id);
        entityTwoDao.deleteEntity(entityTwo);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
