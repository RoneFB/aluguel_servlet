package br.gov.sp.fatec.projetoweb.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter{
	 private ServletContext context;
    private String username = "admin";
    private String password = "password_dificil";
    private String realm = "PROTECTED";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		 this.context = filterConfig.getServletContext();
	        this.context.log("Filtro inicializado!");
	        this.username = filterConfig.getInitParameter("username");
	        this.password = filterConfig.getInitParameter("password");
	        String paramRealm = filterConfig.getInitParameter("realm");
	        if (paramRealm != null && paramRealm.length() > 0) {
	            this.realm = paramRealm;
	        }
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		 this.context.log("Filtro acessado!");
	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;

	        // Verifica se tem o header Authorization
	        String authHeader = request.getHeader("Authorization");
	        if (authHeader != null) {
	            // Divide o conte√∫do do header por espacos
	            StringTokenizer st = new StringTokenizer(authHeader);
	            // Se possui conte√∫do
	            if (st.hasMoreTokens()) {
	                String basic = st.nextToken();
	                // Verifica se possui o prefixo Basic
	                if (basic.equalsIgnoreCase("Basic")) {
	                    try {
	                        // Extrai as credenciais (Base64)
	                        String credentials = 
	                                new String(
	                                    Base64.getDecoder()
	                                        .decode(st.nextToken()));
	                        this.context.log("Credentials: " + credentials);
	                        // Separa as credenciais em usuario e senha
	                        Integer p = credentials.indexOf(":");
	                        if (p != -1) {
	                            String _username = 
	                                    credentials.substring(0, p).trim();
	                            String _password = 
	                                    credentials.substring(p + 1).trim();
	                            // Se nao bate com configuracao retorna erro
	                            if (!username.equals(_username) || 
	                                    !password.equals(_password)) {
	                            	
	                            	if(_username.equals("rone") && _password.equals("1234")) {
	                            		if(request.getMethod().equals("DELETE") ||
	                            				request.getMethod().equals("PATCH") || request.getMethod().equals("PUT")) {
	                            			/*Erro 403 N„o autorizado para o mÈtodo*/
	                            			forbidden(response, "Forbidden");
	                            			return;
	                            		}
	                            		chain.doFilter(req, res);
	                            	}else {
	                            		unauthorized(response, "Bad credentials");
	                            		return;
	                            	}
	                                
	                            }
	                            // Prossegue com a requisicao
	                            chain.doFilter(req, res);
	                        } else {
	                            unauthorized(response, 
	                                    "Invalid authentication token");
	                            return;
	                        }
	                    } catch (UnsupportedEncodingException e) {
	                        throw new Error("Couldn't retrieve authentication", e);
	                    }
	                }
	            }
	        } else {
	            unauthorized(response);
	            return;
	        }
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	private void forbidden(HttpServletResponse response, String message) throws IOException {
		response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
        response.sendError(403, message);
	}
	
	private void unauthorized(HttpServletResponse response, String message) 
            throws IOException {
        response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
        response.sendError(401, message);
    }

    private void unauthorized(HttpServletResponse response) throws IOException {
        unauthorized(response, "Unauthorized");
    }

}
