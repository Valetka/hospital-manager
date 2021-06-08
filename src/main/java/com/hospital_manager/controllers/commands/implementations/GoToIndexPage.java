package com.hospital_manager.controllers.commands.implementations;


import com.hospital_manager.controllers.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.hospital_manager.controllers.commands.CommandParameter.*;


public class GoToIndexPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(GO_TO_INDEX_PAGE).forward(request, response);
    }
}
