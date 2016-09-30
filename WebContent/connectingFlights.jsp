<%@ page import="data.*" %>
<%@ page import="controller.*" %>
<%@ include file="connectingCode.jsp" %> 
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
<script>
function validate()
{
   var src = document.getElementById("from").value;
   var dest = document.getElementById("to").value;
   valid = true;
   if(src==dest ){
   	alert("From and To can't be same");
       valid = false;
   }
   if(src == "" || dest == "" ){
	   	alert("Both the fields are mandatory !!");
	    valid = false;
	   }
   
   return valid;
};

</script>
</head>
    <body>
	<h1>Airline System </h1>
        <div align="left">
        	
                    
            <form action="connectingCode.jsp" method="post" onsubmit="return validate();">
            <fieldset>
            <legend><strong><i>  Display Connecting Flights</i></strong></legend>
            <table align="">
            
            	<tr>
            		<td>  From  </td>
                	<td><input type="text" name="txtFrom" value="" id="from" required/></td>
                </tr>
               
                
                <tr>
            		<td>  To  </td>
            		<td><input type="text" name="txtTo" value="" id="to" required/></td>
            	</tr>
              
                <tr>
            		<td>  Max Connectings  </td>
                	<td>
                	<select name="connecting">
                	   	 <option value="0">Direct</option>
  			   			 <option value="1">1</option>
 			 			 <option value="2">2</option>
 						
  						 
                    </select>
                	</td>
                </tr>
                <tr>
                	<td></td>	
                	<td><input type="submit" value="Display Connecting Flights" name="btnSubmit" class="btnSubmit" id="btnsubmit"/><br/>
                		
                	</td>
                </tr>
           	</table>
           	</fieldset>
           	
           	<br><br><br><br>
           	
           	
           	<fieldset>
            <legend><strong><i>  More Options</i></strong></legend>
           	 <table align="">
				 <tr>
            		<td> <a href="displayAvailability.jsp">Display the number of available seats on a particular flight instance</a> </td>
                	
                </tr>
               <tr>
            		<td> <a href="getFare.jsp"> If you know the Flight Number !! Get The fare. </a> </td>
                	
                </tr>
                <tr>
            		<td> <a href="passengersList.jsp">Display list of passengers on a given flight instance</a>  </td>
                	
                </tr>
                <tr>
            		<td> <a href="listofFlights.jsp">Display a list of flight instances for a particular passenger name</a>  </td>
                	
                </tr>
           	</table>
           	</fieldset>
            </form>
            
            
         </div>

</body>
</html>

          
