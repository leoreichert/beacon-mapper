package br.furb.tcc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {

		String uri = request.getRequestURI();
		if (uri.endsWith("login") || uri.endsWith("efetuaLogin") || uri.contains("resources") || uri.contains("error")) {
			return true;
		}
		
		

		if (request.getSession().getAttribute("usuarioLogado") != null) {
			return true;
		}

		if (uri.contains("beacons")) {
			response.sendRedirect("../login");
		} else {
			response.sendRedirect("login");
		}
		return false;
	}
}