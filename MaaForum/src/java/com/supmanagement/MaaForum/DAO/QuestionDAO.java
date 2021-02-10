/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmanagement.MaaForum.DAO;

import com.supmanagement.MaaForum.entity.Question;
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
public class QuestionDAO {
    
    public static boolean add(Question question) throws ClassNotFoundException {
        
        String formattedTitle = question.getTitle().replace("'", "\\'");
        String formattedAuthor = question.getAuthor().replace("'", "\\'");
        
        String sql = "INSERT INTO question (title, author) VALUES (?,?)";
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(sql);
            statement.setString(1, formattedTitle);
            statement.setString(2, formattedAuthor);
            
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
    
    public static ArrayList<Question> findAll() throws ClassNotFoundException{
        ArrayList<Question> questions = new ArrayList<Question>();
        
        String sql = "SELECT * FROM question";
        
        try {
            Statement statement = Database.getConnection().createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while (result.next()) {
               Question question = new Question();
               question.setTitle(result.getString("title"));
               question.setAuthor(result.getString("author"));
               question.setId(result.getInt("id"));
               question.setCreatedAt(result.getDate("created_at"));
               question.setTime(result.getTime("created_at"));
               
               questions.add(question);
            }
            
            statement.close();
            Database.getConnection().close();
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return  questions;
    }
    
    public static Question find(int id) throws ClassNotFoundException {
        Question question = new Question();
        
        String sql = "SELECT * FROM question WHERE id = ? ";
        
        try {
            PreparedStatement statement = Database.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
               question.setTitle(result.getString("title"));
               question.setAuthor(result.getString("author"));
               question.setId(result.getInt("id"));
               question.setCreatedAt(result.getDate("created_at"));
               question.setTime(result.getTime("created_at"));
            }
            
            statement.close();
            Database.getConnection().close();
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return  question;
    }
}
