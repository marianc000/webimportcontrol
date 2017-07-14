<%-- 
    Document   : index
    Created on : 13 juil. 2017, 13:47:11
    Author     : mcaikovs
--%>

<%@page import="static servlet.NewServlet.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            html,body {
                height: 100%;
                margin: 0;font-family: Arial, Helvetica, sans-serif;
            }
            button {
                font-size: 700%;
                padding: 0.3em;
                margin: 0.3em;
                border: none;
                cursor:pointer;
            }
            button:active{
                opacity:0.3;
            }
            .container {
                width: 100%;
                height: 100%;
                display:table;
            }
            .cell {
                display: table-cell;
                text-align: center;
                vertical-align: middle;
            }
            button.start {
                background-color: mediumspringgreen;
            }

            button.stop {
                background-color: orangered;
            }
            .outcome{
                font-size: 5em;
            }
        </style>
    </head>
    <body>
        <div class="container"> 
            <div class="cell"> 
                <form action="test" method="get">
                    <button class="start" type="submit"  name="action" value="<%=START_ACTION%>">Start</button>
                    <button class="stop" type="submit" name="action" value="<%=STOP_ACTION%>">Stop</button>
                </form>
                <div class="outcome"><%= (request.getAttribute(REQUEST_ATTRIBUTE_NAME) == null) ? "" : request.getAttribute(REQUEST_ATTRIBUTE_NAME)%></div>
            </div>
        </div>


    </body>
</html>
