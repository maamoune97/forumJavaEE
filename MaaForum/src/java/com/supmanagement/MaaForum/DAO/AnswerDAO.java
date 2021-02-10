/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmanagement.MaaForum.DAO;

import com.supmanagement.MaaForum.entity.Answer;
import com.supmanagement.MaaForum.service.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maamoune
 */
public class AnswerDAO {
    
    public static boolean Add(Answer answer) throws ClassNotFoundException {
        
        String formattedTitle = answer.getTitle().replace("'", "\\'");
        String formattedAuthor = answer.getAuthor().replace("'", "\\'");
        
        String sql = "INSERT INTO answer (title, author, Question_id) VALUES (?,?,?)";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(sql);
            statement.setString(1, formattedTitle);
            statement.setString(2, formattedAuthor);
            statement.setInt(3, answer.getQuestionId());
            
            int rows = statement.executeUpdate();
            
            statement.close();
            Database.getConnection().close();
            
            if (rows > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public static ArrayList<Answer> findAll() throws ClassNotFoundException {
        ArrayList<Answer> answers = new ArrayList<Answer>();
        
        String sql = "SELECT * FROM answer";
        
        try {
            Statement statement = Database.getConnection().createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while (result.next()) {
               Answer answer = new Answer();
               answer.setTitle(result.getString("title"));
               answer.setAuthor(result.getString("author"));
               answer.setId(result.getInt("id"));
               answer.setQuestionId(result.getInt("Question_id"));
               answers.add(answer);
            }
            
            statement.close();
            Database.getConnection().close();
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return  answers;
    }
    
    public static Answer find(int id) throws ClassNotFoundException {
        Answer answer = new Answer();
        
        String sql = "SELECT * FROM answer WHERE id = ? ";
        
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
               answer.setTitle(result.getString("title"));
               answer.setAuthor(result.getString("author"));
               answer.setId(result.getInt("id"));
               answer.setQuestionId(result.getInt("Question_id"));
            }
            
            statement.close();
            Database.getConnection().close();
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return  answer;
    }
    
    
    public static ArrayList<Answer> findByQuestion(int questionId) throws ClassNotFoundException {
        
        ArrayList<Answer> answers = new ArrayList<Answer>();
        
        String sql = "SELECT * FROM answer WHERE Question_id = ?";
        
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(sql);
            statement.setInt(1, questionId);
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
               Answer answer = new Answer();
               answer.setTitle(result.getString("title"));
               answer.setAuthor(result.getString("author"));
               answer.setId(result.getInt("id"));
               answer.setQuestionId(result.getInt("Question_id"));
               answer.setCreatedAt(result.getDate("created_at"));
               answer.setTime(result.getTime("created_at"));
               answers.add(answer);
            }
            
            statement.close();
            Database.getConnection().close();
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return  answers;
    }
}
