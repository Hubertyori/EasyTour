package studio.opencloud.easytour21.internet.datas;

public class UserOrderData {
    private int orderID;
    private String status;
    private String place;
    private String date;
    private int numberOfPeople;
    private String note;
    private String userNickname;

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public int getOrderID() {
        return orderID;
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

    public String getUserNickname() {
        return userNickname;
    }
}
