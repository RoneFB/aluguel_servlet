package br.gov.sp.fatec.projetoweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


import br.gov.sp.fatec.projetoweb.dao.AluguelDao;
import br.gov.sp.fatec.projetoweb.entity.Aluguel;

public class AluguelController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String idVen = req.getParameter("idVen");
		AluguelDao aluguelDAO = new AluguelDao();
		Aluguel aluguel = aluguelDAO.aluguelCompletebyID(id, idVen);
		ObjectMapper mapper = new ObjectMapper();
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.setStatus(200);
		PrintWriter out = resp.getWriter();
		
		String aluguelJson = mapper.writeValueAsString(aluguel);
		out.print(mapper.writeValueAsString(aluguelJson));
		out.print(aluguelJson);
		out.flush();
	}
	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper);
      /*  Aluguel aluguel = mapper.readValue(req.getReader(), Aluguel.class);
  
        AluguelDao aluguelDao = new AluguelDao();
        aluguelDao.save(aluguel);

        String aluguelJson = mapper.writeValueAsString(aluguel);*/
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.setStatus(201);
       /* String location = req.getServerName() + ":" + req.getServerPort() 
                + req.getContextPath() + "/aluguel?id=" + aluguel.getId();
        resp.setHeader("Location", location);
        PrintWriter out = resp.getWriter();
        out.print(aluguelJson);*/
        PrintWriter out = resp.getWriter();
        out.print("ok--"+mapper);
        out.flush();
    }
}
