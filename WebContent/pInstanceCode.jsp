<%@ page import="data.*" %>
<%@ page import="controller.*" %>
<%@ page import="java.util.*" %>


<%
try
{
    if( request.getParameter("btnSubmit") != null ) {
    	
		String cust = request.getParameter("cname");
		
		AirlineController ac = new AirlineController();
		ArrayList<PassengerInstanceBean> ilist = new ArrayList<PassengerInstanceBean>();
		
		ilist = ac.getPassengersInstance(cust);   
	 
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
<% if(ilist.size() > 0) {   %>

<body>
		<strong>Flight Details for Passenger :  <i><%=cust %></i></i></Strong>
        <div align="left">
        	<br><br><br><br>
                    
            <form action="" method="post" >
            <fieldset>
            <legend><strong><i> All Flight Instances </i></strong></legend>
            <table align="">
            
            	<tr>
            		<td>  <strong>Flight No  </strong></td><td></td><td></td><td></td><td></td><td></td><td></td>
                	<td>  <strong>Travel Date </strong></td><td></td><td></td><td></td><td></td><td></td><td></td>
                	<td>  <strong>Seat No </strong></td><td></td><td></td><td></td><td></td><td></td><td></td>
                	<td>  <strong>From </strong></td><td></td><td></td><td></td><td></td><td></td><td></td>
                	<td>  <strong>To </strong></td><td></td><td></td><td></td><td></td><td></td><td></td>
                	<td>  <strong>Departure time </strong></td><td></td><td></td><td></td><td></td><td></td><td></td>
                	<td>  <strong>Arrival time </strong></td>
                	
                </tr>
                <% 
                for(int i=0; i<ilist.size(); i++) { 
                	PassengerInstanceBean p = new PassengerInstanceBean();
                	p = ilist.get(i); 
                %>
                <tr> 
                <td> <%  out.println(ilist.get(i).fno); %></td><td></td><td></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(p.date); %></td><td></td><td></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(p.seat_no); %></td><td></td><td></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(p.from); %></td><td></td><td></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(p.to); %></td><td></td><td></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(p.dtime); %></td><td></td><td></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(p.atime); %></td></tr>
                
                <%   }     %>
           	</table>
           	</fieldset>
           	
           	<br>
           	
           	
            </form>
            <br><br>
             <a href="listofFlights.jsp">  &lt;&lt;&lt;&lt;  Go Back  </a>
         </div>
</body>
</html>
<%  } else  {   %>
<body>
		<strong>Flight Details for Passenger :  <i><%=cust %></i></i></Strong>
        <div align="left">
        	<br><br><br><br>
                    
            <fieldset>
            <legend><strong><i> All Flight Instances </i></strong></legend>
           	<u><p align = "center"> No Flight Instance found for : <%=cust %> </p></u>
           	</fieldset>
           	
           	<br>
           	
           	
            </form>
            <br><br>
             <a href="listofFlights.jsp">  &lt;&lt;&lt;&lt;  Go Back  </a>
         </div>
</body>
</html>
<%
}
		}
    } catch (Exception e) {  
    	
    }

%>

 
