package studio.opencloud.easytour21.internet.datas;

import android.os.Parcel;
import android.os.Parcelable;

public class GuideOrderData implements Parcelable{
    //导游可获取的订单信息
    private int orderID;
    private String status;
    private String place;
    private String date;
    private int numberOfPeople;
    private String note;
    private String userNickname;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(orderID);
        parcel.writeString(status);
        parcel.writeString(place);
        parcel.writeString(date);
        parcel.writeInt(numberOfPeople);
        parcel.writeString(note);
        parcel.writeString(userNickname);

    }
    public static final Parcelable.Creator<GuideOrderData> CREATOR = new Parcelable.Creator<GuideOrderData>() {

        @Override
        public GuideOrderData createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            GuideOrderData guideOrderData = new GuideOrderData();
            guideOrderData.orderID = source.readInt();
            guideOrderData.status= source.readString();
            guideOrderData.place = source.readString();
            guideOrderData.date = source.readString();
            guideOrderData.numberOfPeople= source.readInt();
            guideOrderData.note= source.readString();
            guideOrderData.userNickname= source.readString();
            return guideOrderData;
        }

        @Override
        public GuideOrderData[] newArray(int size) {
            // TODO Auto-generated method stub
            return new GuideOrderData[size];
        }
    };
}
