/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmanagement.MaaForum.controller;

import com.supmanagement.MaaForum.DAO.AnswerDAO;
import com.supmanagement.MaaForum.DAO.QuestionDAO;
import com.supmanagement.MaaForum.entity.Answer;
import com.supmanagement.MaaForum.entity.Question;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Maamoune
 */
@WebServlet(name = "AnswersServlet", urlPatterns = {"/answers"})
public class AnswersServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int questionId = Integer.parseInt(request.getParameter("question"));
        Question question = null;
        ArrayList<Answer> answers = new ArrayList<Answer>();
        
        
        //si le formulaire est soumis
        if (request.getParameter("newAnswer") != null) {
            
            try {
                String author = (String) request.getParameter("author");
                String title  = (String) request.getParameter("title");
            
                Answer answer = new Answer(author, title);
                answer.setQuestionId(questionId);
                AnswerDAO.Add(answer);
                
            } catch (ClassNotFoundException e) {
                
            }
            
        }
        
        try {
            question = QuestionDAO.find(questionId);
            answers = AnswerDAO.findByQuestion(questionId);
        } catch (ClassNotFoundException e) {
            
        }
        
        request.setAttribute("question", question);
        request.setAttribute("answers", answers);
        request.setAttribute("count", answers.size());
        
        request.getRequestDispatcher("WEB-INF/answers.jsp").forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
