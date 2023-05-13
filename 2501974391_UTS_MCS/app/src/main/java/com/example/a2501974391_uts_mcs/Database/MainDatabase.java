package com.example.a2501974391_uts_mcs.Database;

import android.content.Context;
import android.database.Cursor;

import com.example.a2501974391_uts_mcs.Model.Article;
import com.example.a2501974391_uts_mcs.Model.History;
import com.example.a2501974391_uts_mcs.Model.Ticket;
import com.example.a2501974391_uts_mcs.Model.TicketType;
import com.example.a2501974391_uts_mcs.Model.User;

import java.util.Vector;

public class MainDatabase {

    private static MainDatabase database;
    private DatabaseHelper databaseHelper;

    public MainDatabase(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static MainDatabase getDb(Context context){
        if(database == null){
            synchronized (MainDatabase.class){
                if(database == null){
                    database = new MainDatabase(context);
                }
            }
        }
        return database;
    }

    //Articles

    public Vector<Article> getArticles(){
        return databaseHelper.getArticles();
    }

    public Article getArticlesbyId(Integer articleId){
        return databaseHelper.getArticlesbyId(articleId);
    }

    public void insertArticle(String title, String date, Integer image, String description){
        databaseHelper.insertArticle(title, date, image, description);
    }

    //Users

    public Vector<User> getUsers(){
        return databaseHelper.getUsers();
    }

    public void insertUser(String username, String email, String password){
        databaseHelper.insertUser(username, email, password);
    }

    public boolean userTableIsEmpty(){
        return databaseHelper.userTableIsEmpty();
    }

    public boolean checkUserExistbyUsername(String username){
        return databaseHelper.checkUserExistbyUsername(username);
    }

    public boolean checkUserExistbyEmail(String email){
        return databaseHelper.checkUserExistbyEmail(email);
    }

    public User getUserbyUsername(String username){
        return databaseHelper.getUserbyUsername(username);
    }

    //Ticket Types

    public Vector<TicketType> getTicketTypes(){
        return databaseHelper.getTicketTypes();
    }

    public void insertTicketType(String type, Integer price){
        databaseHelper.insertTicketType(type,price);
    }

    public TicketType getTicketTypebyId(Integer ticketTypeId){
        return databaseHelper.getTicketTypesbyId(ticketTypeId);
    }

    //Ticket

    public Vector<Ticket> getTickets(){
        return databaseHelper.getTickets();
    }

    public void insertTicket(String name, String venue, String time, String date){
        databaseHelper.insertTicket(name, venue, time, date);
    }

    public Ticket getTicketbyId(Integer ticketId){
        return databaseHelper.getTicketbyId(ticketId);
    }

    //History

    public Vector<History> getHistoriesUserById(Integer userId){
        return databaseHelper.getHistoriesbyUserId(userId);
    }

    public void insertHistory(String date, Integer qty, Integer totalPrice, Integer userId, Integer ticketId, Integer ticketTypeId){
        databaseHelper.insertHistory(date, qty, totalPrice, userId, ticketId, ticketTypeId);
    }


}
