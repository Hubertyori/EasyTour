package com.example.xh.login;

public class UserOrderData {
    private int orderID;
    private String telephone;
    private String status;
    private String place;
    private String date;
    private String note;
    private int userNickname;

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setUserNickname(int userNickname) {
        this.userNickname = userNickname;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getStatus() {
        return status;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    public int getUserNickname() {
        return userNickname;
    }
}
