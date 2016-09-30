<%@ page import="data.*" %>
<%@ page import="controller.*" %>
<%@ page import="java.util.*" %>
<%@ include file="getFareCode.jsp" %>


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

<body>
	<h3>Flight Details </h3>
        <div align="left">
        	<br><br>
                    
            <form action="getFareCode.jsp" method="post" >
           	
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