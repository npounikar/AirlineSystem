<%@ page import="data.*" %>
<%@ page import="controller.*" %>
<%@ page import="java.util.*" %>


<%
	String errMessage="";
    try
     {
         if( request.getParameter("btnSubmit") != null ) {
           boolean validData = true;
           String from, to;
           int max;
           
           from = request.getParameter("txtFrom");
           to = request.getParameter("txtTo");
           max = Integer.parseInt(request.getParameter("connecting"));
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
         		ArrayList<ConnectingBean> conct = null;
         		ArrayList<Connecting1Bean> conct1 = null;
         		ArrayList<Connecting2Bean> conct2 = null;
           		if(max==2) {
           			conct = new ArrayList<ConnectingBean>(); 
             		conct1 = new ArrayList<Connecting1Bean>(); 
             		conct2 = new ArrayList<Connecting2Bean>(); 
             		conct = lc.getDirectFlight(from, to);
             		conct1 = lc.getOneConnectingFlight(from, to);
             		conct2 = lc.getTwoConnectingFlight(from, to);
           		}
           		if(max==1) {
           			conct = new ArrayList<ConnectingBean>(); 
             		conct1 = new ArrayList<Connecting1Bean>(); 
             		conct = lc.getDirectFlight(from, to);
             		conct1 = lc.getOneConnectingFlight(from, to);
           		}
           		if(max==0) {
           			conct = new ArrayList<ConnectingBean>(); 
           			conct = lc.getDirectFlight(from, to); 
           		}
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



<% if(conct.size() > 0 && max == 0) { %> 
<body>
	<h3>Flight Details </h3>
        <div align="left">
        	
                    
            <form action="" method="post">
            <br><hr><br>
            <i>Departure From : &nbsp;&nbsp;</i><strong><%=from %></strong><br>
            <i>Arrival To &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: &nbsp;&nbsp;</i><strong><%=to %></strong><br><br><hr>
            <br>
            <fieldset>
            <legend><strong><i>Direct Flights</i></strong></legend>
            <table align="" style ="background:#FFFFCC; width: 100%">
            
            	 <tr> 
                <td><strong> <u>Flight No.</u></strong></td><td></td><td></td><td></td><td></td>
                <td><strong> <u>Departure From</u></strong></td><td></td><td></td><td></td><td></td>
                <td><strong> <u>Arrival To </u></strong></td><td></td><td></td><td></td><td></td>
                <td><strong> <u>Departure Time </u></strong></td><td></td><td></td><td></td><td></td>
                <td><strong> <u>Arrival Time </u></strong></td><td></td><td></td><td></td><td></td>
                <td><strong> <u>Weekdays </u></strong></td></tr>
                <% 
                for(int i=0; i<conct.size(); i++) { 
                ConnectingBean cbean = new ConnectingBean();
                cbean = conct.get(i);
                %>
                <tr> 
                <td> <%  out.println(cbean.flightNo1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean.from1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean.to1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean.dtime1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean.atime1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean.day); %></td></tr> 
                <tr></tr><tr></tr>
                <%  }  %>
           	</table>
           	</fieldset>
           	
           	<br><br><br><br>
           	
            </form>
            <br><br>
             <a href="connectingFlights.jsp">  &lt;&lt;&lt;&lt;  Go Back  </a>
         </div>
</body>
</html>

<% } else if((conct.size() > 0 || conct1.size() > 0) && max == 1) { %> 
<body>
	<h3>Flight Details </h3>
        <div align="left">
        	
                    
            <form action="" method="post">
            <br><hr><br>
            <%  if(conct.size() > 0) { %>
            <i>Departure From : &nbsp;&nbsp;</i><strong><%=from %></strong><br>
            <i>Arrival To &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: &nbsp;&nbsp;</i><strong><%=to %></strong><br><br><hr>
            <br>
            <fieldset>
            <legend><strong><i>Direct Flights</i></strong></legend>
            <table align="" style ="background:#FFFFCC; width: 100%">
            
            	 <tr> 
                <td> <strong><u>Flight No.</u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Departure From</u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Arrival To </u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Departure Time </u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Arrival Time </u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Weekdays </u></strong></td></tr>
                <% 
                for(int i=0; i<conct.size(); i++) { 
                ConnectingBean cbean = new ConnectingBean();
                cbean = conct.get(i);
                %>
                <tr> 
                <td> <%  out.println(cbean.flightNo1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean.from1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean.to1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean.dtime1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean.atime1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean.day); %></td></tr> 
                <tr></tr><tr></tr>
                <%  }  %>
           	</table>
           	</fieldset>
           	<%  }   %>
           	<br><br><br><br>
           	<%  if(conct1.size() > 0) { %>
           	<fieldset>
            <legend><strong><i>Single Connecting Flights</i></strong></legend>
            <table align=""style =" width: 100%">
            
            	
             </table>
                <% 
                for(int i=0; i<conct1.size(); i++) { 
                Connecting1Bean cbean1 = new Connecting1Bean();
                cbean1 = conct1.get(i);
                %>
                <table style ="background:#FFFFCC; width: 100%">
                 <tr> 
                <td>Departure : <strong><i><%=from %></i></strong></td><td></td><td></td><td></td><td></td>
                <td>Arrival : <strong><i><%=to %></i></strong></td><td></td><td></td><td></td><td></td>
                <td>Day : <strong><i><%=cbean1.day %></i></strong></td><td></td><td></td><td></td><td></td>
                <td> </td><td></td><td></td><td></td><td></td>
                <td> </td></tr>
                 <tr> 
                <td> <strong><u>Flight No.</u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Departure From</u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Arrival To </u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Departure Time </u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Arrival Time </u></strong></td></tr>
                
                <tr>
                <tr> 
                <td> <%  out.println(cbean1.flightNo1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean1.from1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean1.to1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean1.dtime1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean1.atime1); %></td></tr> 
                <tr>
                <td> <%  out.println(cbean1.flightNo2); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean1.from2); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean1.to2); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean1.dtime2); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean1.atime2); %></td></tr> 
                </table><br><br>
                <%  }  %>
           	
           	</fieldset>
           	<%    }    %>
            </form>
            <br><br>
             <a href="connectingFlights.jsp">  &lt;&lt;&lt;&lt;  Go Back  </a>
         </div>
</body>
</html>

<% } else if((conct.size() > 0 || conct1.size() > 0 || conct2.size() > 0) && max == 2) { %> 
<body>
	<h3>Flight Details </h3>
        <div align="left">
        	
                    
            <form action="" method="post">
            <br><hr><br>
            <%  if(conct.size() > 0) { %>
            <i>Departure From : &nbsp;&nbsp;</i><strong><%=from %></strong><br>
            <i>Arrival To &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: &nbsp;&nbsp;</i><strong><%=to %></strong><br><br><hr>
            <br>
            <fieldset>
            <legend><strong><i>Direct Flights</i></strong></legend>
            <table align="" style ="background:#FFFFCC; width: 100%">
            
            	 <tr> 
                <td> <strong><u>Flight No.</u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Departure From</u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Arrival To </u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Departure Time </u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Arrival Time </u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Weekdays </u></strong></td></tr>
                <% 
                for(int i=0; i<conct.size(); i++) { 
                ConnectingBean cbean = new ConnectingBean();
                cbean = conct.get(i);
                %>
                <tr> 
                <td> <%  out.println(cbean.flightNo1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean.from1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean.to1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean.dtime1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean.atime1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean.day); %></td></tr> 
                <tr></tr><tr></tr>
                <%  }  %>
           	</table>
           	</fieldset>
           	<%  }   %>
           	<br><br><br><br>
           	<%  if(conct1.size() > 0) { %>
           	<fieldset>
            <legend><strong><i>Single Connecting Flights</i></strong></legend>
            <table align=""style =" width: 100%">
            
            	
             </table>
                <% 
                for(int i=0; i<conct1.size(); i++) { 
                Connecting1Bean cbean1 = new Connecting1Bean();
                cbean1 = conct1.get(i);
                %>
                <table style ="background:#FFFFCC; width: 100%">
                 <tr> 
                <td>Departure : <strong><i><%=from %></i></strong></td><td></td><td></td><td></td><td></td>
                <td>Arrival : <strong><i><%=to %></i></strong></td><td></td><td></td><td></td><td></td>
                <td>Day : <strong><i><%=cbean1.day %></i></strong></td><td></td><td></td><td></td><td></td>
                <td> </td><td></td><td></td><td></td><td></td>
                <td> </td></tr>
                 <tr> 
                <td> <strong><u>Flight No.</u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Departure From</u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Arrival To </u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Departure Time </u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Arrival Time </u></strong></td></tr>
                
                <tr>
                <tr> 
                <td> <%  out.println(cbean1.flightNo1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean1.from1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean1.to1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean1.dtime1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean1.atime1); %></td></tr> 
                <tr>
                <td> <%  out.println(cbean1.flightNo2); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean1.from2); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean1.to2); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean1.dtime2); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean1.atime2); %></td></tr> 
                </table><br><br>
                <%  }  %>
           	
           	</fieldset>
           	<%    }    %>
           	<br><br><br><br>
           	<%  if(conct2.size() > 0) { %>
           	<fieldset>
            <legend><strong><i>Double Connecting Flights</i></strong></legend>
            <table align=""style =" width: 100%">
            
            	
             </table>
                <% 
                for(int i=0; i<conct2.size(); i++) { 
                Connecting2Bean cbean2 = new Connecting2Bean();
                cbean2 = conct2.get(i);
                %>
                <table style ="background:#FFFFCC; width: 100%">
                 <tr> 
                <td>Departure : <strong><i><%=from %></i></strong></td><td></td><td></td><td></td><td></td>
                <td>Arrival : <strong><i><%=to %></i></strong></td><td></td><td></td><td></td><td></td>
                <td>Day : <strong><i><%=cbean2.day %></i></strong></td><td></td><td></td><td></td><td></td>
                <td> </td><td></td><td></td><td></td><td></td>
                <td> </td></tr>
                 <tr> 
                <td> <strong><u>Flight No.</u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Departure From</u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Arrival To </u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Departure Time </u></strong></td><td></td><td></td><td></td><td></td>
                <td> <strong><u>Arrival Time </u></strong></td></tr>
                
                <tr>
                <tr> 
                <td> <%  out.println(cbean2.flightNo1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean2.from1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean2.to1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean2.dtime1); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean2.atime1); %></td></tr> 
                <tr>
                <td> <%  out.println(cbean2.flightNo2); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean2.from2); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean2.to2); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean2.dtime2); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean2.atime2); %></td></tr> 
                <tr>
                <td> <%  out.println(cbean2.flightNo3); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean2.from3); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean2.to3); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean2.dtime3); %></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(cbean2.atime3); %></td></tr> 
                </table><br><br>
                <%  }  %>
           	
           	</fieldset>
           	<%    }    %>
            </form>
            <br><br>
             <a href="connectingFlights.jsp">  &lt;&lt;&lt;&lt;  Go Back  </a>
         </div>
</body>
</html>

<%   } else  {  %>

 <fieldset>
            <legend><strong><i>No Direct or Connecting Flights</i></strong></legend>
            	<p align="center"> Please check Your Input !! </p>
           	</fieldset>
           	
           	<br><br><br><br>
            <br><br>
             <a href="connectingFlights.jsp">  &lt;&lt;&lt;&lt;  Go Back  </a>
        
</body>
</html>
	
	
	
<% 
	}
} else
    	errMessage = "Invalid Data!!";    
   	}
}  catch(Exception e) {
		%>
		<fieldset>
            <legend><strong><i>No Direct or Connecting Flights</i></strong></legend>
            	<p align="center"> Please check Your Input !! </p>
           	</fieldset>
           	
           	<br><br><br><br>
            <br><br>
             <a href="connectingFlights.jsp">  &lt;&lt;&lt;&lt;  Go Back  </a>
        
</body>
</html>
		
		<%
}

%>

