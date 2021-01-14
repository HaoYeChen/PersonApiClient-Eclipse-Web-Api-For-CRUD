package dk.hao.webproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import dk.hao.AnalyzeRequest;
import dk.hao.DBTools;
import dk.hao.Elev;
import dk.hao.Person;


//@WebServlet("/ApiServlet")
public class ApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		PrintWriter out = response.getWriter();
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		AnalyzeRequest analyze = new AnalyzeRequest(request.getPathInfo());
		
		DBTools db = new DBTools();
		
		switch(analyze.getLevel())
		{
		case MatchPersonId:
			//out.println("Match på Person + " + analyze.getId());
			
			Person p = db.getPersonById(analyze.getId());
			
			out.println(mapper.writeValueAsString(p));
			
			break;
		case MatchPerson:
			//out.println("Match på Person");
			
			List<Person> persons = db.getAllPerson();
			
			out.println(mapper.writeValueAsString(persons));
			
			break;
		case MatchNo:
			//out.println("Ingen match");
			//response.setStatus(400);
			break;
			
			
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		PrintWriter out = response.getWriter(); //skrive til clienten
		//out.println("In doPost");
		
		BufferedReader reader = request.getReader(); //læser fra clienten posten fra android eller postman
		String jsonStr = reader.readLine();
		
		Person p = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		DBTools db = new DBTools();
		
		try {
			p = mapper.readValue(jsonStr, Person.class);
			db.addNewPerson(p);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			//response.setStatus(400);
			return;
		}
		
		if(p != null) 
		{
			//out.println("Name: " + p.getName());
		}
		
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		PrintWriter out = resp.getWriter();

        BufferedReader reader = req.getReader();

        String jsonStr = reader.readLine();

        Person p = null;

        ObjectMapper mapper = new ObjectMapper();

        DBTools db = new DBTools();

        try {

            p = mapper.readValue(jsonStr, Person.class);
            db.updatePerson(p);

        }
        catch(Exception e)
        {
            e.printStackTrace();
            //resp.setStatus(400);
            return;
        }
		
		
	}
	
	@Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DBTools db = new DBTools();

        AnalyzeRequest analyze = new AnalyzeRequest(request.getPathInfo());



        switch(analyze.getLevel())
        {
        case MatchPersonId:

            Person p = db.getPersonById(analyze.getId());
            //out.println("Match på Person + " + analyze.getId());

            try {
                db.deletePerson(p);
            }

            catch(Exception e)
            {
                e.printStackTrace();
                response.setStatus(404);
                return;
            }

            break;
        case MatchNo:
            //out.println("No Match");
            //response.setStatus(204);
            break;
    }


   	
	}
}
