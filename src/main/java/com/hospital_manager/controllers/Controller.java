package com.hospital_manager.controllers;



import com.hospital_manager.controllers.commands.Command;
import com.hospital_manager.controllers.commands.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1788742651L;

    private final CommandProvider provider = new CommandProvider();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String name;
        Command command;
        try {
            name = request.getParameter("command");
            command = provider.takeCommand(name);
            command.execute(request, response);
        }catch (RuntimeException e){
            request.getRequestDispatcher("error404.html").forward(request,response);
        }
    }
}
