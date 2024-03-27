package com.example.servlets;

import com.example.dao.EntityOneDao;
import com.example.dao.impl.EntityOneDaoImpl;
import com.example.model.EntityOne;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EntityOneServlet extends HttpServlet {

    private EntityOneDao entity1DAO;

    @Override
    public void init() throws ServletException {
        super.init();
        entity1DAO = new EntityOneDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EntityOne> entities = entity1DAO.getAllEntities();
        String json = new Gson().toJson(entities);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        EntityOne entity1 = new EntityOne(name);
        entity1DAO.saveEntity(entity1);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        EntityOne entity1 = new EntityOne(id, name);
        entity1DAO.updateEntity(entity1);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        EntityOne entity1 = new EntityOne();
        entity1.setId(id);
        entity1DAO.deleteEntity(entity1);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
