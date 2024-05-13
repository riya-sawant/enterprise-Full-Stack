/* 
Name: Jasmine Moy
Course: CNT 4714 – Spring 2023 – Project Four
Assignment title: A Three-Tier Distributed Web-Based Application
Date: April 25, 2023
*/

package project4;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Properties;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;


import com.mysql.cj.jdbc.MysqlDataSource;


public class AddShipmentsRecordServlet extends HttpServlet
{
	private Connection connection;
	private Statement statement;
	private int mysqlUpdateValue;
	private int val1;
	private int val2;
	private int[] updateReturnValues;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
			
		// get the sql command from the request object from the front-end form
		String snum = request.getParameter("snum");
		String pnum = request.getParameter("pnum");
		String jnum = request.getParameter("jnum");
		String quantity = request.getParameter("quantity");
		// variable to return the results
		String message = "";
		
		try {
			// get a connection to the database
			getDBConnection();
			
			// create a Statement object
			Statement statement = connection.createStatement();
			
			// updating command is issued by user - client user does not have that permission
			// call JDBC method executeUpdate(inboundCommand)
			String sqlStatement = "insert into shipments values (\"" + snum + "\",\"" + pnum + "\",\"" + jnum + "\"," + quantity + ")";
			val1 = statement.executeUpdate(sqlStatement);	
			int quantityNum = 0;
			quantityNum = Integer.parseInt(quantity);

			message = "<div style='background-color:#32CD32'><b>New shipments record: (" + snum + ", " + pnum + ", " + jnum + ", " + quantity + ") - sucessfully entered into database. ";
			
			if (quantityNum >= 100)
			{
				message += ("Business logic triggered.");
			}
			else
			{
				message += ("Business logic not triggered.");
			}
			
			// close statement object
			statement.close();
		}
		// catch exceptions
		// dump error message to return string
		catch (SQLException exception)
		{
			message = "<div style='background-color:#ff0000'><font color='#ffffff'><b>Error executing the SQL statement:</b><br>" + exception.getMessage() + "</font></div>";
		}
		
		// get session object
		HttpSession session = request.getSession();
		
		// set session object attribute values
		session.setAttribute("message", message);
		//session.setAttribute("sqlStatement", sqlStatement);
		
		// create RequestDispatcher object - set return context
		 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dataentryHome.jsp");
		
		// use dispatcher object to forward response to property content
		 dispatcher.forward(request, response);
		
	}
	
	private void getDBConnection() 
	{
		// use properties file like project 3
		
		Properties properties = new Properties();
		FileInputStream filein = null;
		MysqlDataSource dataSource = null;
		
		try
		{
			filein = new FileInputStream("C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\Project 4\\WEB-INF\\lib\\dataentry.properties");
			properties.load(filein);
			dataSource = new MysqlDataSource();
			dataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
			dataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
			dataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));
	
			connection = dataSource.getConnection();
			//statement = connection.createStatement();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		
		// end load connection details from properties file
		
		
	}
}