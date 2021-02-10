/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmanagement.MaaForum.entity;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Maamoune
 */
public class Answer {
   private int id;
   private String author;
   private String title;
   private Date createdAt;
   private int questionId;
   private  Time time;
   
    public Answer(String author, String title) {
        this();
        this.author = author;
        this.title = title;
    }

    public Answer() {
        //setCreatedAt(new Date());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    
   public String getCreatedAtFormated() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String createdAtFormated = simpleDateFormat.format(this.getCreatedAt());
        
        return createdAtFormated;
    }
    
    public String getTimeFormated() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String timeFormated = simpleDateFormat.format(this.getTime());
        
        return timeFormated;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
