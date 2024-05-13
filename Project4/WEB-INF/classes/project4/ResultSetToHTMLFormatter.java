package project4;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetToHTMLFormatter{
	
	public static synchronized String getHtmlRows(ResultSet results) throws SQLException {
		
		// define a StringBuffer object that will hold a giant string that contains the result table in HTML format
		StringBuffer html = new StringBuffer();
		ResultSetMetaData metaData = results.getMetaData();
		
		// get the number of columns in the result set
		int columnCount = metaData.getColumnCount();
		
		// set the table header row
		// append to the StringBuffer instance a table header row element
		html.append("<center><table bgcolor='#ffffff'>");
		html.append("<tr style=\"background-color:#ff0000;\">");
		
		// loop through all the columns in the ResultSet extracting the column name for placement in the table 
		// header row - append each column name to the StringBuffer instance
		for (int i=1; i <= columnCount; i++)
		{
			String columnName = metaData.getColumnName(i);
			html.append("<th>").append(columnName).append("</th>");
		}
		
		// close the table header row
		html.append("</tr>");
		
		// set the remainder of the table - row-by-row
		// loop through the ResultSet row by row and append a new <tr> element (table row) each time
		// within each row, loop through the columns in each row and add the <td> elements (table data) for each column in the row
		while (results.next())
		{
			html.append("<tr>");
			
			for (int i=1; i <= columnCount; i++)
			{
				Object value = results.getObject(i);
				html.append("<td style='text-align:center;'>").append(value).append("</td>");
			}
			html.append("</tr>");
		}
		
		html.append("</table></center>");
		
		// convert the StringBuffer into a string and return it
		String message = html.toString();
		return message;	
		
	}
}