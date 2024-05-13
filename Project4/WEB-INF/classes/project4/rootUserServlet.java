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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;


import com.mysql.cj.jdbc.MysqlDataSource;


public class rootUserServlet extends HttpServlet
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
		String sqlStatement = request.getParameter("sqlStatement");
		// variable to return the results
		String message = "";
		
		// true if starts with SELECT statement
		sqlStatement = sqlStatement.trim();
		//boolean isSelect = sqlStatement.trim().startsWith("Select");
		String sqlType = sqlStatement.substring(0, 6);
		
		try {
			// get a connection to the database
			getDBConnection();
			
			// create a Statement object
			Statement statement = connection.createStatement();
			
			// determine if inbound command is a query or something else and handle inbound user command
			if (sqlType.equalsIgnoreCase("select"))
			{
				// call JDBC method executeQuery(inboundCommand)
				ResultSet resultSet = statement.executeQuery(sqlStatement);
				
				// process the ResultSet return object into an HTML table
				// assign the HTML result table to the outbound message string
				message = ResultSetToHTMLFormatter.getHtmlRows(resultSet);
				
			}
			// insert and update command
			else {
				// updating command is issued by user - client user does not have that permission
				// call JDBC method executeUpdate(inboundCommand)
				String sBLCommand = "update suppliers set status = status + 5 where snum in(select distinct snum from shipments where quantity >= 100)";
				val1 = statement.executeUpdate(sqlStatement);
				val2 = statement.executeUpdate(sBLCommand);
				int quantityNum = 0;
				
				// get quantity number from an insert statement format
				Pattern pattern = Pattern.compile("\\(([^,]+),([^,]+),([^,]+),(\\d+)\\)");
				Matcher matcher = pattern.matcher(sqlStatement);
				if (matcher.find()) {
				    String quantity = matcher.group(4);
				    quantityNum = Integer.parseInt(quantity);
				}
				
				// determine business logic
				if (sqlStatement.contains("shipments") && quantityNum >= 100)
				{
					message = "<div style='background-color:#32CD32'><b>The statement executed sucessfully.<br>" + val1 + " row(s) affected.<br><br>";
					message += "Business Logic Detected! - Updating Supplier Status<br><br>Business Logic updated " + val2 
							+ " supplier status marks</div>";
				}
				// update
				else
				{
					message = "<div style='background-color:#32CD32'><b>The statement executed sucessfully. A total of " + val1 + " row(s) updated.<br><br>";
					message += "Business Logic Not Triggered</div>";
				}
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
		session.setAttribute("sqlStatement", sqlStatement);
		
		// create RequestDispatcher object - set return context
		 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/rootHome.jsp");
		
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
			filein = new FileInputStream("C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\Project 4\\WEB-INF\\lib\\root.properties");
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