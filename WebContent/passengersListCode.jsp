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
		ArrayList<PassengersBean> plist = new ArrayList<PassengersBean>();
		ArrayList<FlightOnBoardBean> fob = new ArrayList<FlightOnBoardBean>();
		plist = ac.getPassengersList(flightno, date);  
		fob = ac.getFlightOnBoardList(flightno);  
		FlightOnBoardBean f1 = new FlightOnBoardBean();
		f1 = fob.get(0); 
		if(f1 != null) 
		{
		
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
<% 

String from= "", to="", dtime="", atime="";
from = f1.from;
to = f1.to;
dtime = f1.dtime;
atime = f1.atime;

if(plist.size() > 0) {   %>


<body>
		<strong>List of Passengers :  </i></Strong>
        <div align="left">
       	
        	<br><br><br><br> 
        	  <form action="" method="post" >
             
        	<fieldset>
            <legend><strong><i> Flight Details </i></strong></legend>
            <table>
            <tr><td>Flight Number : </td><td><strong><i><%=flightno %></i></Strong></td></tr>
             <tr><td>Flight Date : </td><td><strong><i><%=date %></i></Strong></td></tr>
              <tr><td>Departure From : </td><td><strong><i><%=from %> </i></Strong></td></tr>
               <tr><td>Arrival To : </td><td><strong><i><%=to %></i></Strong></td></tr>
                <tr><td>Departure time : </td><td><strong><i><%=dtime %></i></Strong></td></tr>
                <tr><td>Arrival time : </td><td><strong><i><%=atime %></i></Strong></td></tr>
              
            </table>
           	</fieldset>     
          
            <fieldset>
            <legend><strong><i>  List of Passengers </i></strong></legend>
            <table align="">
            
            	<tr>
            		<td>  <strong>Passenger's Seat No. </strong></td><td></td><td></td><td></td><td></td><td></td><td></td>
                	<td>  <strong>Passenger's Name </strong></td><td></td><td></td><td></td><td></td><td></td><td></td>
                	<td>  <strong>Passenger's Contact </strong></td>
                </tr>
                <% 
                for(int i=0; i<plist.size(); i++) { 
                	PassengersBean p = new PassengersBean();
                	p = plist.get(i); 
                %>
                <tr> 
                <td> <%  out.println(p.seat_no); %></td><td></td><td></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(p.cust_name); %></td><td></td><td></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(p.cust_phone); %></td></tr>
                
                <%   }     %>
           	</table>
           	</fieldset>
           	
           	<br>
           	
           	
            </form>
            <br><br>
             <a href="passengersList.jsp">  &lt;&lt;&lt;&lt;  Go Back  </a>
         </div>
</body>
</html>
<%  } else  {   %>
<body>
		<strong>List of Passengers  for :  </Strong>
		<div align="left">
        	<br><br><br><br>
             <form action="" method="post" >
                  
            <fieldset>
            <legend><strong><i> Flight Details </i></strong></legend>
            <table>
            <tr><td>Flight Number : </td><td><strong><i><%=flightno %></i></Strong></td></tr>
             <tr><td>Flight Date : </td><td><strong><i><%=date %></i></Strong></td></tr>
              <tr><td>Departure From : </td><td><strong><i><%=from %> </i></Strong></td></tr>
               <tr><td>Arrival To : </td><td><strong><i><%=to %></i></Strong></td></tr>
                <tr><td>Departure time : </td><td><strong><i><%=dtime %></i></Strong></td></tr>
                <tr><td>Arrival time : </td><td><strong><i><%=atime %></i></Strong></td></tr>
              
            </table>
           	</fieldset>
         </div>
         </form>
        <div align="left">
        	<br><br><br><br>
                    
            <fieldset>
            <legend><strong><i>  List of Passengers </i></strong></legend>
            <table align="">
           	<u><p align = "center"> No Passnegers records found !!</p></u>
           	</fieldset>
           	
           	<br>
           	
           	
            
            <br><br>
             <a href="passengersList.jsp">  &lt;&lt;&lt;&lt;  Go Back  </a>
         </div>
</body>
</html>

<%
		}
	} else if( f1 == null) {
		%>
		<fieldset>
            <legend><strong><i> Flight Details are invalid !!</i></strong></legend>
            <a href="passengersList.jsp">Go back and please enter correct details!!</a>
           	</fieldset> 
		
		
		
		<% 
	}
}
    } catch (Exception e) {  %>
    
    <div align="left">
        	<br><br><br><br>
                    
           
            <fieldset>
            <legend><strong><i>  Invalid !! </i></strong></legend>
            	<p align="center"> The Flight Number you provided is Invalid !!</p>
           	</fieldset>
           	
           	<br>
           	
           	
            </form>
            <br><br>
             <a href="passengersList.jsp">  &lt;&lt;&lt;&lt;  Go Home  </a>
         </div>
    
    <% 
    	
    }

%>

 
