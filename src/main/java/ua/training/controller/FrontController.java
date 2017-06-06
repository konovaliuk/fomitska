/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.training.controller;

import ua.training.command.ICommand;
import ua.training.command.Message;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FrontController extends HttpServlet {
    ControllerHelper controllerHelper = ControllerHelper.getInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //    response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        session.setAttribute("locale", "uk_UA");
        String page = null;
            ICommand command = controllerHelper.getCommand(request);
            page = command.execute(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//            request.setAttribute("messageError", Message.getInstance().getProperty(Message.SERVLET_EXECPTION));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            request.setAttribute("messageError", Message.getInstance().getProperty(Message.IO_EXCEPTION));
//
//        }

        //test for repository
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
