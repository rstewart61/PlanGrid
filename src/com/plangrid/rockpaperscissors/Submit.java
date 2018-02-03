package com.plangrid.rockpaperscissors;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Submit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String choiceString = request.getParameter("choice");
		if (choiceString == null) {
			throw new ServletException("Required parameter 'choice' missing");
		}
		Choice choice = null;
		try {
			choice = Choice.valueOf(choiceString);
		} catch (IllegalArgumentException e) {
			throw new ServletException("Unknown choice '" + choiceString + "'");
		}
		String sessionId = session.getId();
		Player player = RPSEngine.getPlayer(sessionId);
		Result result = RPSEngine.submit(player, choice);
		response.getWriter().append(result.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
