package dk.hao.example1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.hao.AnalyzeRequest;


//@WebServlet("/ApiServlet")
public class ApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.println("ContextPath: " + request.getContextPath());
		out.println("<br />ServletPath: " + request.getServletPath());
		out.println("<br />PathInfo: " + request.getPathInfo());
		out.println("<br /><br />");
		
		AnalyzeRequest analyze = new AnalyzeRequest(request.getPathInfo());
		
		switch(analyze.getLevel())
		{
		case MatchElevId:
			out.println("Match på Elev + " + analyze.getId()); 
			break;
		case MatchElev:
			out.println("Match på Elev");
			break;
		case MatchNo:
			out.println("Ingen match");
			break;
			
			
		}
		
	}


}
