<!DOCTYPE html>

<%
	String message = (String) session.getAttribute("message");

	if (message == null) message = " ";
%>

<html lang="en">
<head>
  <title>CNT 4714 Remote Database Management System</title>
  <meta charset="utf-8" />
  <style type="text/css">
    <!--
      body{background-color: black; text-align: center;font-family: Arial;}
      h1{color: yellow; font-size:28pt;}
      h2{color: lime; font-size:24pt;}
      textarea{resize: none; background: blue; color: white; font-family: Verdana; font-size: 15pt; width: 600px; height: 200px;}
      th,td{padding: 5px; border: 1px solid black;}
      input{color: yellow; background:#665D1E; font-weight:bold; font-size:16pt;}
        input[type="submit"] {color: lime;}
        input[type="reset"] {color :red;}
    -->
  </style>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script type="text/javascript">
	function eraseData()
	{
		$("#data").remove();
		//document.getElementsByID("data").innerHTML = "";
	}
</script>
</head>
<body>
	<div>
		<h1>Welcome to the Spring 2023 Project 4 Enterprise Database System</h1>
		<h2 class="center">A Servlet/JSP-based Multi-tiered Enterprise Application Using A Tomcat Container</h2>
	</div>
	<hr>
	<div>
		<p style=" color: white">You are connected to the Project 4 Enterprise System database as a 
		<span style=" color: red">root-level </span>
		user. <br> Please enter any valid SQL query or update command in the box below.</p>
	<form action = "RootApp" method = "post">
		Feedback: <br/>
		<textarea row="20" cols="100" name="sqlStatement"></textarea>
		<br/>
      	<input type="submit" value="Execute Command" /></span>
      	<input type="reset" value="Reset Form" /></span>
      	<input type="button" value="Clear Results" onclick="javascript:eraseData();" />
    </form>
	</div>	
	<div>
		<p style=" color: white">All execution results will appear below this line.</p>
		<hr>
		<p style=" color: white; font-weight: bold;">Database Results:</p>
	</div>
	<div>
		<center><table id="data">
			<%=message%>
		</table></center>
	</div>
</body>
</html>