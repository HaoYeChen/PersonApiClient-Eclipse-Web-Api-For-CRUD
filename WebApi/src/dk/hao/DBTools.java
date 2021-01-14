package dk.hao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DBTools {

	//JDBC driver to connect to database
	private String connectionStr = "jdbc:sqlserver://DESKTOP-AQ77FHV\\BANKINGAPP;databaseName=PersonApiDB";
	
	private Connection con;
	private Statement stmt;
	
	//Connection to MSSQL with sa and password
	private void connect() 
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			con= DriverManager.getConnection(connectionStr, "sa", "1234");
			
			stmt = con.createStatement();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	//GetPersonById		//doGet		//virker
	public Person getPersonById(int id)	
	{
		connect();
		Person p = null;
		
		String selectStr = "Select * from Person where id = " + id;
		
		try {
			ResultSet result = stmt.executeQuery(selectStr);
			
			if(result.next())
			{
				p = new Person();
				
				p.setId(result.getInt("id"));
				p.setName(result.getString("name"));
				p.setFavorit(result.getBoolean("favorit"));
				p.setHairColor(result.getInt("hairColor"));
				p.setAddress(result.getString("address"));
				p.setPhone(result.getString("phone"));
				p.setNote(result.getString("note"));
			}
			
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	//getAllPerson()	//doGet		//virker
	public List<Person> getAllPerson()
	{
		connect();
		Person p = null;
		List<Person> persons = new ArrayList<Person>();
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from Person");
			while(rs.next())
			{
				p = new Person();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setFavorit(rs.getBoolean("favorit"));
				p.setHairColor(rs.getInt("hairColor"));
				p.setAddress(rs.getString("address"));
				p.setPhone(rs.getString("phone"));
				p.setNote(rs.getString("note"));
				persons.add(p);
			}
			
			con.close();
		}
			catch (SQLException e){
				e.printStackTrace();
			}
			return persons;
		
	}
	
	//addNewPerson()	//doPost
	public void addNewPerson(Person person) 
	{
		connect();
		try {
			PreparedStatement pstmt = con.prepareStatement
					("Insert into Person(name, favorit, hairColor, address, phone, note) Values (?,?,?,?,?,?)");
			pstmt.setString(1, person.getName());
			pstmt.setBoolean(2, person.isFavorit());
			pstmt.setInt(3, person.getHairColor());
			pstmt.setString(4, person.getAddress());
			pstmt.setString(5, person.getPhone());
			pstmt.setString(6, person.getNote());
			pstmt.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//updatePerson()	//doPut
	public void updatePerson(Person p)
	{
		connect();
		try {
			PreparedStatement pstmt = con.prepareStatement("Update Person set name=?, favorit=?, hairColor=?, address=?, phone=?, note=? Where id =?");
			
			pstmt.setString(1, p.getName());
			pstmt.setBoolean(2, p.isFavorit());
			pstmt.setInt(3, p.getHairColor());
			pstmt.setString(4, p.getAddress());
			pstmt.setString(5, p.getPhone());
			pstmt.setString(6, p.getNote());
			pstmt.setInt(7, p.getId());
			pstmt.executeUpdate();
			
			
			con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	//deletePerson()	//doDelete
	public void deletePerson(Person p)
	{
		connect();
		try {
			PreparedStatement pstmt = con.prepareStatement("Delete from Person where id=?");
			pstmt.setInt(1, p.getId());
			pstmt.executeUpdate();
			
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
