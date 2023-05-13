package com.example.a2501974391_uts_mcs.Model;

public class History {
    Integer id;
    String date;
    Integer qty;
    Integer totalPrice;
    Integer userId;
    Integer ticketId;
    Integer ticketTypeId;

    public History() {
    }

    public History(Integer id, String date, Integer qty, Integer totalPrice, Integer userId, Integer ticketId, Integer ticketTypeId) {
        this.id = id;
        this.date = date;
        this.qty = qty;
        this.totalPrice = totalPrice;
        this.userId = userId;
        this.ticketId = ticketId;
        this.ticketTypeId = ticketTypeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Integer ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }
}
