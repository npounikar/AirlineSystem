<%@ page import="data.*" %>
<%@ page import="controller.*" %>
<%@ page import="java.util.*" %>


<%
try
{
    if( request.getParameter("btnSubmitAmt") != null ) {
    	
		int flightno = Integer.parseInt(request.getParameter("flight"));
		
		AirlineController ac = new AirlineController();
		ArrayList<FareBean> fares = new ArrayList<FareBean>();
		
		fares = ac.getFareDetails(flightno); 
	
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
<% if(fares.size() > 0) {   %>

<body>
		<strong><i>Fare Details  for Flight :  <%=flightno %></i></Strong>
        <div align="left">
        	<br><br><br><br>
                    
            <form action="" method="post" >
            <fieldset>
            <legend><strong><i>  Fare details </i></strong></legend>
            <table align="">
            
            	<tr>
            		<td>  <strong> Flight Number </strong></td><td></td><td></td><td></td><td></td><td></td><td></td>
                	<td>  <strong>Class </strong></td><td></td><td></td><td></td><td></td><td></td><td></td>
                	<td>  <strong>Fare </strong></td><td></td><td></td><td></td><td></td><td></td><td></td>
                	<td>  <strong>Restrictions </strong></td>
                </tr>
                <% 
                for(int i=0; i<fares.size(); i++) { 
                	FareBean fb = new FareBean();
                	fb = fares.get(i); 
                %>
                <tr> 
                <td> <%  out.println(fb.flightNo); %></td><td></td><td></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(fb.fare_code); %></td><td></td><td></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(fb.amount); %></td><td></td><td></td><td></td><td></td><td></td><td></td>
                <td> <%  out.println(fb.restrictions); %></td></tr>
                
                <%   }     %>
           	</table>
           	</fieldset>
           	
           	<br>
           	
           	
            </form>
            <br><br>
             <a href="index.jsp">  &lt;&lt;&lt;&lt;  Go Home  </a>
         </div>
</body>
</html>
<% } else {   %>

<body>
 <form action="" method="post" >
		<strong><i>Fare Details  for Flight :  <%=flightno %></i></Strong>
        <div align="left">
        	<br><br><br><br>
                    
           
            <fieldset>
            <legend><strong><i>  Invalid !! </i></strong></legend>
            	<p align="center"> The Flight Number you provided is Invalid !!</p>
           	</fieldset>
           	
           	<br>
           	
           	
            </form>
            <br><br>
             <a href="index.jsp">  &lt;&lt;&lt;&lt;  Go Home  </a>
         </div>
</body>
</html>
<%
}
		}
    } catch (Exception e) {  
    	%>
    	 <div align="left">
        	<br><br><br><br>
                    
           
            <fieldset>
            <legend><strong><i>  Invalid !! </i></strong></legend>
            	<p align="center"> The Flight Number you provided is Invalid !!</p>
           	</fieldset>
           	
           	<br>
           	
           	
            </form>
            <br><br>
             <a href="getFare.jsp">  &lt;&lt;&lt;&lt;  Go Home  </a>
         </div>
    	
    	<% 
    }

%>

 
