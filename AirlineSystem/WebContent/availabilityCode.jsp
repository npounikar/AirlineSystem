<%@ page import="data.*" %>
<%@ page import="controller.*" %>
<%@ page import="java.util.*" %>


<%
try
{
    if( request.getParameter("btnSubmit") != null ) {
    	
		int flightno = Integer.parseInt(request.getParameter("fno"));
		String date = request.getParameter("date");
		
		AirlineController ac = new AirlineController();
		ArrayList<AvailabilityBean> avail = new ArrayList<AvailabilityBean>();
		
		avail = ac.getAvailability(flightno, date);  
	
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
<% if(avail.size() > 0) {   %>

<body>
		<strong>Availability  for Flight :  <i><%=flightno %></i> on <i><%=date %></i></Strong>
        <div align="left">
        	<br><br><br><br>
                    
            <form action="" method="post" >
            <fieldset>
            <legend><strong><i>  Seat Availability </i></strong></legend>
            <table align="">
            
            	<tr>
            		<td>  <strong>Flight Number </strong></td><td></td><td></td><td></td><td></td><td></td><td></td>
                	<td>  <strong>Date </strong></td><td></td><td></td><td></td><td></td><td></td><td></td>
                	<td>  <strong>Available Seats </strong></td>
                </tr>
                <% 
                for(int i=0; i<avail.size(); i++) { 
                	AvailabilityBean a = new AvailabilityBean();
                	a = avail.get(i); 
                %>
                <tr> 
                <td> <%  out.println(a.flightno); %></td><td></td><td></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(a.date); %></td><td></td><td></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(a.aseats); %></td></tr>
                
                <%   }     %>
           	</table>
           	</fieldset>
           	
           	<br>
           	
           	
            </form>
            <br><br>
             <a href="displayAvailability.jsp">  &lt;&lt;&lt;&lt;  Go Back  </a>
         </div>
</body>
</html>
<%
} else {
	%>
			<fieldset>
            <legend><strong><i>Invalid !!</i></strong></legend>
            	<p align="center"> Please check Your Input !! </p>
           	</fieldset>
           	
           	<br><br><br><br>
            <br><br>
             <a href="connectingFlights.jsp">  &lt;&lt;&lt;&lt;  Go Back  </a>
        
</body>
</html>
	
	
	<% 
}
		}
    } catch (Exception e) {  %>
    
    			<fieldset>
            <legend><strong><i>Invalid !!</i></strong></legend>
            	<p align="center"> Please check Your Input !! </p>
           	</fieldset>
           	
           	<br><br><br><br>
            <br><br>
             <a href="displayAvailability.jsp">  &lt;&lt;&lt;&lt;  Go Back  </a>
        
</body>
</html>
<%
    	
    }

%>

 
