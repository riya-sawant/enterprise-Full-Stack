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
      h1{color: red; font-size:28pt;}
      h2{color: cyan; font-size:24pt;}

      /* Style the table */
      table {
    		border-collapse: collapse; /* Collapse the table borders */
    		border: 2px solid #000; /* Add a 2px black border around the table */
    		border-color: yellow;
    		width: 600px; /* Make the table full-width */
  		}

  		/* Style for the fieldset */
      fieldset {
        border: 2px solid white;
        padding: 20px;
        margin-bottom: 20px;
      }

  		/* Style for the legend */
      legend {
      	text-align: left;
        font-size: 16px;
        color: white;
      }

  		/* Style the table cells */
  		td {
  			color: white;
    		border: 1px solid #000; /* Add a 1px black border around each cell */
    		border-color: yellow; /* Set the border color to blue */
    		padding: 10px; /* Add some padding */
  		}

  		/*  Style the input fields */
      input{color: yellow; background:#665D1E; font-size:16pt;}
      	input[type=text]{
      		width: 100%;
      		padding: 5px;
      		box-sizing:border-box;
      	}
        input[type="submit"] {
        	color: lime;
        	font-weight: bold;
        	font-size: 11pt;
        	margin-top: 15px;
        }
        input[type="reset"] {
        	color :red;
        	font-weight: bold;
        	font-size: 11pt;
        }

      /* Center the table horizontally */
  		.center {
    		margin: 0 auto;
  		}

    -->
  </style>
</head>

<body>
	<div>
		<h1>Welcome to the Spring 2023 Project 4 Enterprise Database System</h1>
		<h2 class="center">Data Entry Application</h2>
	</div>
	<hr>
	<div>
		<p style=" color: white">You are connected to the Project 4 Enterprise System database as a 
		<span style=" color: red">data-entry-level </span>
		user. <br> Please enter any valid SQL query or update command in the box below.</p>
	</div>
	<hr>
	<br>

	<div>
		/* Form for first table*/
		<form action = "InsertSuppliersApp" method = "post">
			<fieldset>
				<legend>Suppliers Record Insert</legend>
				<table class="center">
					<tr>
						<td>snum</td>
						<td>sname</td>
						<td>status</td>
						<td>city</td>
					</tr>
					<tr>
	 					<td><input type="text" name="snum"></td>
	      		<td><input type="text" name="sname"></td>
	      		<td><input type="text" name="status"></td>
	      		<td><input type="text" name="city"></td>
	    		</tr>
	  		</table>
	      <input type="submit" value="Execute Supplier Record Into Database" /></span>
	      <input type="reset" value="Clear Data and Results" /></span>
	      &nbsp; &nbsp; &nbsp;
	    </fieldset>
    </form>
	</div>

	<div>
		/* Form for second table */
		<form action = "InsertPartsApp" method = "post">
			<fieldset>
				<legend>Parts Record Insert</legend>
				<table class="center">
					<tr>
						<td>pnum</td>
						<td>pname</td>
						<td>color</td>
						<td>weight</td>
						<td>city</td>
					</tr>
					<tr>
	 					<td><input type="text" name="pnum"></td>
	      		<td><input type="text" name="pname"></td>
	      		<td><input type="text" name="color"></td>
	      		<td><input type="text" name="weight"></td>
	      		<td><input type="text" name="city"></td>
	    		</tr>
	  		</table>
	      <input type="submit" value="Execute Parts Record Into Database" /></span>
	      <input type="reset" value="Clear Data and Results" /></span>
	      &nbsp; &nbsp; &nbsp;
	    </fieldset>
    </form>
	</div>

	<div>
		/* Form for third table*/
		<form action = "InsertJobsApp" method = "post">
			<fieldset>
				<legend>Jobs Record Insert</legend>
				<table class="center">
					<tr>
						<td>jnum</td>
						<td>jname</td>
						<td>numworkers</td>
						<td>city</td>
					</tr>
					<tr>
	 					<td><input type="text" name="jnum"></td>
	      		<td><input type="text" name="jname"></td>
	      		<td><input type="text" name="numworkers"></td>
	      		<td><input type="text" name="city"></td>
	    		</tr>
	  		</table>
	      <input type="submit" value="Execute Jobs Record Into Database" /></span>
	      <input type="reset" value="Clear Data and Results" /></span>
	      &nbsp; &nbsp; &nbsp;
	    </fieldset>
    </form>
	</div>

	<div>
		/* Form for fourth table*/
		<form action = "InsertShipmentsApp" method = "post">
			<fieldset>
				<legend>Shipments Record Insert</legend>
				<table class="center">
					<tr>
						<td>snum</td>
						<td>pnum</td>
						<td>jnum</td>
						<td>quantity</td>
					</tr>
					<tr>
	 					<td><input type="text" name="snum"></td>
	      		<td><input type="text" name="pnum"></td>
	      		<td><input type="text" name="jnum"></td>
	      		<td><input type="text" name="quantity"></td>
	    		</tr>
	  		</table>
	      <input type="submit" value="Execute Shipments Record Into Database" /></span>
	      <input type="reset" value="Clear Data and Results" /></span>
	      &nbsp; &nbsp; &nbsp;
	    </fieldset>
    </form>
	</div>

	<hr>
	<div>
		<p style=" color: white; font-weight: bold;">Database Results:</p>
	</div>
	<div>
		<%=message%>
	</div>
</body>
</html>