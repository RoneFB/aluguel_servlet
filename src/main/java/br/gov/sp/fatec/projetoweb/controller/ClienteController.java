package br.gov.sp.fatec.projetoweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.sp.fatec.projetoweb.dao.AluguelDao;
import br.gov.sp.fatec.projetoweb.dao.ClienteDao;
import br.gov.sp.fatec.projetoweb.entity.Aluguel;
import br.gov.sp.fatec.projetoweb.entity.Cliente;

public class ClienteController extends HttpServlet{
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		ClienteDao clienteDao = new ClienteDao();
		Cliente cliente = clienteDao.searchById(id);
		ObjectMapper mapper = new ObjectMapper();
		
		String clienteJson = mapper.writeValueAsString(cliente);
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.setStatus(200);
		PrintWriter out = resp.getWriter();
		
		out.print(clienteJson);
		out.flush();
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		 ObjectMapper mapper = new ObjectMapper();
	        Cliente cliente = mapper.readValue(req.getReader(), Cliente.class);
	  
	        ClienteDao clienteDao = new ClienteDao();
	        clienteDao.save(cliente);

	        String clienteJson = mapper.writeValueAsString(cliente);
	        resp.setContentType("application/json");
	        resp.setCharacterEncoding("UTF-8");

	        resp.setStatus(201);
	        String location = req.getServerName() + ":" + req.getServerPort() 
	                + req.getContextPath() + "/cliente?id=" + cliente.getId();
	        resp.setHeader("Location", location);
	        PrintWriter out = resp.getWriter();
	        out.print(clienteJson);
	        out.flush();
	}
}
