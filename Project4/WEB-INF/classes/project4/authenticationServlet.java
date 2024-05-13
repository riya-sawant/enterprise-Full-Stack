/* 
Name: Jasmine Moy
Course: CNT 4714 – Spring 2023 – Project Four
Assignment title: A Three-Tier Distributed Web-Based Application
Date: April 25, 2023
*/

package project4;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class authenticationServlet extends HttpServlet
{
	// method variables as needed
	boolean userCredentialsOK;
	// process "get" request from client
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// inbound username and password from front-end page
				String inBoundUserName = request.getParameter("username");
				String inBoundPassword = request.getParameter("passwd");
				
				// method variables as needed
				//boolean userCredentialsOK = false;
				
				// file that holds user credential information
				File credentialsFile = new File("C:/Program Files/Apache Software Foundation/Tomcat 10.1/webapps/Project 4/WEB-INF/lib/credentials.txt");
		
				
				try (BufferedReader br = new BufferedReader(new FileReader(credentialsFile)))
				{
					String line;
					
					// while loop until credentials match 
					while((line = br.readLine()) != null)
					{
						// split the line into username and password
						String[] parts = line.split(", ");
						String username = parts[0];
						String password = parts[1];
						
						// if user credentials match then userCredentialsOK - break while loop
						if(username.equals(inBoundUserName) && password.equals(inBoundPassword))
						{
							userCredentialsOK = true;
							break;
						}
						else 
						{
							userCredentialsOK= false;
							break;
						}
					}
				}
				catch(FileNotFoundException fileNotFoundException)
				{
					JOptionPane.showMessageDialog(null, "Error: File not found", "Nile Dot Com - ERROR", JOptionPane.ERROR_MESSAGE);
				}
				catch(IOException ioException)
				{
					JOptionPane.showMessageDialog(null, "Error: Problem reading from file", "Nile Dot Com - ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				// if inbound user credentials matched those in credentials file, then apply appropriate redirection
				if(userCredentialsOK = true)
				{
					// redirect to correct front-end page based on username
					// if username is root redirect to root page
					if(inBoundUserName.equals("root"))
					{
						response.sendRedirect("/Project 4/rootHome.jsp");
					}
					// if username is client redirect to client page
					else if(inBoundUserName.equals("client"))
					{
						response.sendRedirect("/Project 4/clientHome.jsp");
					}
					// if username is data entry
					else if(inBoundUserName.equals("dataentryuser"))
					{
						response.sendRedirect("/Project 4/dataentryHome.jsp");
					}
				}
				else
				{
					// user credentials match failed - access denied - redirect to error page
					response.sendRedirect("/Project 4/errorpage.html");
					
				}
		
		
		
	}
}