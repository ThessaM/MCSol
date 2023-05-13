package com.example.a2501974391_uts_mcs.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.a2501974391_uts_mcs.Model.Article;
import com.example.a2501974391_uts_mcs.Model.History;
import com.example.a2501974391_uts_mcs.Model.Ticket;
import com.example.a2501974391_uts_mcs.Model.TicketType;
import com.example.a2501974391_uts_mcs.Model.User;

import java.util.Vector;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final String CREATE_ARTICLE_TABLE =
            "CREATE TABLE article (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "title TEXT NOT NULL," +
            "date TEXT NOT NULL," +
            "image INTEGER," +
            "description TEXT NOT NULL);";

    private final String CREATE_USER_TABLE =
            "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT NOT NULL," +
            "email TEXT NOT NULL," +
            "password TEXT NOT NULL);";

    private final String CREATE_TICKET_TYPE_TABLE =
            "CREATE TABLE ticket_type (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "type TEXT NOT NULL," +
            "price INTEGER);";

    private final String CREATE_TICKET_TABLE =
            "CREATE TABLE ticket (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL," +
            "venue TEXT NOT NULL," +
            "time TEXT NOT NULL," +
            "date TEXT NOT NULL," +
            "tickettypeid INTEGER);";

    private final String CREATE_HISTORY_TABLE =
            "CREATE TABLE history (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "date TEXT NOT NULL," +
            "qty INTEGER," +
            "totalprice INTEGER," +
            "userid INTEGER," +
            "ticketid INTEGER," +
            "tickettypeid INTEGER);";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "mclsol_database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ARTICLE_TABLE);
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TICKET_TYPE_TABLE);
        db.execSQL(CREATE_TICKET_TABLE);
        db.execSQL(CREATE_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS article");
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS ticket_type");
        db.execSQL("DROP TABLE IF EXISTS ticket");
        db.execSQL("DROP TABLE IF EXISTS history");
        onCreate(db);
    }

    private SQLiteDatabase db;
    private final String TABLE_ARTICLE = "article";
    private final String TABLE_USER = "user";
    private final String TABLE_TICKET_TYPE = "ticket_type";
    private final String TABLE_TICKET = "ticket";
    private final String TABLE_HISTORY = "history";
//    private DatabaseHelper dbHelper;


    public Vector<Article> getArticles(){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ARTICLE, null);
        Vector<Article> articles = new Vector<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Article article = new Article();
            article.setId(cursor.getInt(0));
            article.setTitle(cursor.getString(1));
            article.setDate(cursor.getString(2));
            article.setImage(cursor.getInt(3));
            article.setDescription(cursor.getString(4));
            articles.add(article);
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return articles;
    }

    public Article getArticlesbyId(Integer articleId){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ARTICLE + " WHERE id = ?", new String[]{articleId+""});
        Article foundArticle = new Article();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Article article = new Article();
            article.setId(cursor.getInt(0));
            article.setTitle(cursor.getString(1));
            article.setDate(cursor.getString(2));
            article.setImage(cursor.getInt(3));
            article.setDescription(cursor.getString(4));
            if(article.getId().equals(articleId)){
                foundArticle = article;
                break;
            }
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return foundArticle;
    }



    public Vector<User> getUsers(){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER, null);
        Vector<User> users = new Vector<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            users.add(user);
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return users;
    }

    public Vector<TicketType> getTicketTypes(){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TICKET_TYPE, null);
        Vector<TicketType> ticketTypes = new Vector<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            TicketType ticketType = new TicketType();
            ticketType.setId(cursor.getInt(0));
            ticketType.setType(cursor.getString(1));
            ticketType.setPrice(cursor.getInt(2));
            ticketTypes.add(ticketType);
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return ticketTypes;
    }

    public TicketType getTicketTypesbyId(Integer ticketTypeId){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TICKET_TYPE + " WHERE id = ?", new String[]{ticketTypeId+""});
        TicketType foundTicketType = new TicketType();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            TicketType ticketType = new TicketType();
            ticketType.setId(cursor.getInt(0));
            ticketType.setType(cursor.getString(1));
            ticketType.setPrice(cursor.getInt(2));
            if(ticketType.getId().equals(ticketTypeId)){
                foundTicketType = ticketType;
                break;
            }
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return foundTicketType;
    }

    public Vector<Ticket> getTickets(){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TICKET, null);
        Vector<Ticket> tickets = new Vector<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Ticket ticket = new Ticket();
            ticket.setId(cursor.getInt(0));
            ticket.setName(cursor.getString(1));
            ticket.setVenue(cursor.getString(2));
            ticket.setTime(cursor.getString(3));
            ticket.setDate(cursor.getString(4));
            tickets.add(ticket);
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return tickets;
    }

    public Ticket getTicketbyId(Integer ticketId){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TICKET + " WHERE id = ?", new String[]{ticketId+""});
        Ticket foundTicket = new Ticket();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Ticket ticket = new Ticket();
            ticket.setId(cursor.getInt(0));
            ticket.setName(cursor.getString(1));
            ticket.setVenue(cursor.getString(2));
            ticket.setTime(cursor.getString(3));
            ticket.setDate(cursor.getString(4));
            if(ticket.getId().equals(ticketId)){
                foundTicket = ticket;
                break;
            }
            cursor.moveToNext();
        }
        db.close();
        cursor.close();
        return foundTicket;
    }

    public Vector<History> getHistoriesbyUserId(Integer userId){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_HISTORY + " WHERE userid = ?", new String[]{userId+""});
        Vector<History> histories = new Vector<>();
        cursor.moveToLast();
        while (!cursor.isBeforeFirst()){
            History history = new History();
            history.setId(cursor.getInt(0));
            history.setDate(cursor.getString(1));
            history.setQty(cursor.getInt(2));
            history.setTotalPrice(cursor.getInt(3));
            history.setUserId(cursor.getInt(4));
            history.setTicketId(cursor.getInt(5));
            history.setTicketTypeId(cursor.getInt(6));
            if(history.getUserId().equals(userId)){
                histories.add(history);
            }
            cursor.moveToPrevious();
        }
        db.close();
        cursor.close();
        return histories;
    }

    public void insertArticle(String title, String date, Integer image, String description){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("date", date);
        values.put("image", image);
        values.put("description", description);
        db.insert(TABLE_ARTICLE, null, values);
        db.close();
    }

    public void insertUser(String username, String email, String password){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("email", email);
        values.put("password", password);
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void insertTicketType(String type, Integer price){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("type", type);
        values.put("price", price);
        db.insert(TABLE_TICKET_TYPE, null, values);
        db.close();
    }
    
    public void insertTicket(String name, String venue, String time, String date){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("venue", venue);
        values.put("time", time);
        values.put("date", date);
        db.insert(TABLE_TICKET, null, values);
        db.close();
    }

    public void insertHistory(String date, Integer qty, Integer totalPrice, Integer userId, Integer ticketId, Integer ticketTypeId){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("qty", qty);
        values.put("totalPrice", totalPrice);
        values.put("userId", userId);
        values.put("ticketId", ticketId);
        values.put("ticketTypeId", ticketTypeId);
        db.insert(TABLE_HISTORY, null, values);
        db.close();
    }

    public boolean userTableIsEmpty(){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER, null);
        Boolean res = false;
        if(cursor.getCount() == 0){
            res = true;
        }
        db.close();
        cursor.close();
        return res;
    }


    public boolean checkUserExistbyUsername(String username){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE username=?", new String[]{username+""});
        boolean res = true;
        if(cursor.getCount() == 0){
            res = false;
        }
        db.close();
        cursor.close();
        return res;
    }

    public boolean checkUserExistbyEmail(String email){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE email=?", new String[]{email+""});
        boolean res = true;
        if(cursor.getCount() == 0){
            res = false;
        }
        db.close();
        cursor.close();
        return res;
    }

    public User getUserbyUsername(String username){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE username=?", new String[]{username+""});
        User curUser = new User();
        if(cursor.getCount() == 1){
            cursor.moveToFirst();
            curUser.setId(cursor.getInt(0));
            curUser.setUsername(cursor.getString(1));
            curUser.setEmail(cursor.getString(2));
            curUser.setPassword(cursor.getString(3));
            return curUser;
        }
        db.close();
        cursor.close();
        return curUser;
    }
    

//    public void deleteArtile(Integer id){
//        db = this.getWritableDatabase();
//        db.delete(TABLE_ARTICLE, "id = ?", new String[]{id+""});
//        db.close();
//    }

//    public  void updateArticle(Integer id, String title, String date, Integer image, String description){
//        db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("title", title);
//        values.put("date", date);
//        values.put("image", image);
//        values.put("description", description);
//        db.update(TABLE_ARTICLE, values, "id = ?", new String[]{id+""});
//        db.close();
//    }

}
