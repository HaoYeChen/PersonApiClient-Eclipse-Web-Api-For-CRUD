package dk.hao.example2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import dk.hao.AnalyzeRequest;
import dk.hao.Elev;


//@WebServlet("/ApiServlet")
public class ApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		PrintWriter out = response.getWriter();
		
		Elev elev1 = new Elev(1001, "Andreas 1", 23, "Cykle i sne");
		elev1.addSkill("gå");
		elev1.addSkill("Kravle");
		
		Elev elev2 = new Elev(1002, "Andreas 2", 23, "Gå i sne");
		elev2.addSkill("Spise");
		elev2.addSkill("Drik smeltevand");
		
		Elev elev3 = new Elev(1003, "Martin", 59, "Cykle baglæns");
		elev3.addSkill("Vælte");
		elev3.addSkill("Programmere");
		
		ArrayList<Elev> elever = new ArrayList<Elev>();
		elever.add(elev1);
		elever.add(elev2);
		elever.add(elev3);
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		AnalyzeRequest analyze = new AnalyzeRequest(request.getPathInfo());
		
		switch(analyze.getLevel())
		{
		case MatchElevId:
			out.println("Match på Elev + " + analyze.getId());
			int id = analyze.getId();
			
			boolean found = false;
			for(Elev e : elever)
			{
				if(e.getId() == id)
				{
					out.println(mapper.writeValueAsString(e));
					response.setStatus(200);
					found = true;
					break;
				}
			}
			
			if(!found)
			{
				out.println("no id");
				response.setStatus(204); //eller 204?
			}
			
			
			
			break;
		case MatchElev:
			//out.println("Match på Elev");
			out.println(mapper.writeValueAsString(elever));
			break;
		case MatchNo:
			out.println("Ingen match");
			response.setStatus(400);
			break;
			
			
		}
		
	}


}
