/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thread.MyThread;

/**
 *
 * @author mcaikovs
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/test"})
public class NewServlet extends HttpServlet {

    public static String START_ACTION = "start";
    public static String STOP_ACTION = "stop";
    public static String PARAM_NAME = "action";
    public static String REQUEST_ATTRIBUTE_NAME = "outcome";
    static MyThread toolThread;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.

    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter(PARAM_NAME);
        if (START_ACTION.equalsIgnoreCase(action)) {
            startTool();
        } else if (STOP_ACTION.equalsIgnoreCase(action)) {
            stopTool();
        }
        request.setAttribute(REQUEST_ATTRIBUTE_NAME, getToolStatus());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
//        try (PrintWriter out = response.getWriter()) {
//
//            out.println("<h1> parameters:" + request.getQueryString() + "</h1>");
//            out.println("<h1> parameters:" + request.getParameter(PARAM_NAME) + "</h1>");
//
//        }
    }

    boolean isWorking() {
        return toolThread != null && toolThread.isAlive();
    }

    String getToolStatus() {
        return isWorking() ? "Started" : "Stopped";
    }

    void startTool() {
        System.out.println(">Servlet:startTool");
        if (!isWorking()) {
            System.out.println("Servlet:startTool:starting thread");
            toolThread = new MyThread();
            toolThread.start();
        } else {
            System.out.println("Servlet:startTool:thread already working");
        }
        System.out.println("<Servlet:startTool");
    }

    void stopTool() {
        System.out.println(">Servlet:stopTool");
        if (isWorking()) {
            System.out.println("Servlet:stopTool:stopping");
            toolThread.stopTool();
        } else {
            System.out.println("Servlet:stopTool:thread is already inactive");
        }
        System.out.println("<Servlet:stopTool");
    }
}
