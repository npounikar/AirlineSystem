<%@ page import="data.*" %>
<%@ page import="controller.*" %>
<%@ page import="java.util.*" %>
<%@ include file="getFareCode.jsp" %>


<%
 	String errMessage="";
    try
     {
         if( request.getParameter("btnSubmit") != null ) {
        	 
           boolean validData = true;
           String from, to;
           
           from = request.getParameter("txtFrom");
           to = request.getParameter("txtTo");
           
           if( from == "") {
        	   errMessage = "Invalid Data!!"; 
             validData = false;
           }
           if( to == "") {
        	   errMessage = "Invalid Data!!"; 
             validData = false;
           }
           if( from == to) {
        	   errMessage = "Invalid Data!!"; 
               validData = false;
            }
           
           if( validData ) {
               
         		AirlineController lc = new AirlineController();
         		ArrayList<FlightDetailsBean> flights = new ArrayList<FlightDetailsBean>();
           		flights = lc.getFlightDetails(from, to);
           		
 %>

<html>
<head>
       
<title>Airline System</title>

<style type="text/css">
html {
	border: 1px grey solid;
	margin: 100px;
	padding: 20px;
}

fieldset {
	display: block;
	margin-left: 2px;
	margin-right: 2px;
	padding-top: 0.35em;
	padding-bottom: 0.625em;
	padding-left: 0.75em;
	padding-right: 0.75em;
	border: 2px groove(internal value);
}
</style>
</head>
<% if(flights.size() > 0) {   %>

<body>
	<h3>Flight Details </h3>
        <div align="left">
        	<br><br>
                    
            <form action="getFareCode.jsp" method="post" >
            <fieldset>
            <legend><strong><i>  Flights Running on Following Days </i></strong></legend>
            <table align="">
            
            	<tr>
            		<td><strong> Flight Number </strong></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                	<td> <strong> Weekdays     </strong>  </td>
                </tr>
                <% 
                for(int i=0; i<flights.size(); i++) { 
                FlightDetailsBean fb = new FlightDetailsBean();
                fb = flights.get(i);
                %>
                <tr> 
                <td> <%  out.println(fb.flightNo); %></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(fb.days); %></td><tr>
                <% 
                }
                %>
           	</table>
           	</fieldset>
           	
           	<br><br><br><br> 
           	
           	
           	<fieldset>
            <legend><strong><i> Get Fare</i></strong></legend>
           	 <table align=""> 
           	 	
            	
                <tr>
            		<td> Flight No.  : <input type="text" id="flight" name="flight" class="flight" maxlength="4" required> </td>
            		<td> <input type="submit" id="btnSubmitAmt" name="btnSubmitAmt" class="btnSubmitAmt" value="Get Fare" > </td>
                </tr>
           	</table>
           	</fieldset>
            </form>
            <br><br>
             <a href="index.jsp">  &lt;&lt;&lt;&lt;  Go Home  </a>
         </div>
</body>
</html>
<%  }   else {   %>
 <body>
	<h3>Flight Details </h3>
        <div align="left">
        	
                    
            <form action="" method="post">
            <fieldset>
            <legend align="center"><strong><i>  Sorry !! There are no direct flights available !! </i></strong></legend>
            <p align="center"><a href="index.jsp">Show Connecting Flights !!</a> </p>
           	</fieldset>
           	
           	<br><br><br><br>
            </form>
            <br><br>
             
         </div>
</body>
</html>
 
 <% 
 
  }
           	
           } else
    			errMessage = "Invalid Data!!";    
           		
   }
}  catch(Exception e) {

}

%>

