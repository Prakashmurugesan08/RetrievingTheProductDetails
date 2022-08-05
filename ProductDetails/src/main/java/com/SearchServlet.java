package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 System.out.println("driver loaded successfully");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javatraining","root","Pramya1999@*");
		     System.out.println("Successfully connected");
		     Statement stmt = con.createStatement();
		     PrintWriter pw=response.getWriter();
		     String search = request.getParameter("search");
		     
		     if(search.isEmpty()) {
		    	 pw.print("You not given any id to search the product!<br>Try again!  ");
		    	 pw.print(" Avaliable Id are (1-5)");
		     }
		     else {
		    	 String sql ="select * from product where id ="+search;
		    	 ResultSet res = stmt.executeQuery(sql);
		    	 if(res.next()) {
		    		 pw.println("Id is "+res.getInt(1)+ " product Name is "+res.getString(2)+" Price is "+res.getFloat(3));
		    	 }
		    	 else {
		    		 pw.print("There is no product with that product id: "+search+ "<br>Try again!");
		    	 }
		    	 
		     }
		     
		     
		     
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
