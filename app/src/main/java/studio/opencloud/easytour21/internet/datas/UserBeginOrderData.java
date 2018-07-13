package studio.opencloud.easytour21.internet.datas;

import android.os.Parcel;
import android.os.Parcelable;

public class UserBeginOrderData implements Parcelable {
    //用户可获得的订单信息
    private int orderID;
    private String status;
    private String place;
    private String date;
    private int numberOfPeople;
    private String note;
    private String userNickname;
    private String guideHeadIcon;

    public String getGuideHeadIcon() {
        return guideHeadIcon;
    }

    public void setGuideHeadIcon(String guideHeadIcon) {
        this.guideHeadIcon = guideHeadIcon;
    }

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
    public static final Creator<UserBeginOrderData> CREATOR = new Creator<UserBeginOrderData>() {

        @Override
        public UserBeginOrderData createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            UserBeginOrderData userOrderData = new UserBeginOrderData();
            userOrderData.orderID = source.readInt();
            userOrderData.status= source.readString();
            userOrderData.place = source.readString();
            userOrderData.date = source.readString();
            userOrderData.numberOfPeople= source.readInt();
            userOrderData.note= source.readString();
            userOrderData.userNickname= source.readString();
            return userOrderData;
        }

        @Override
        public UserBeginOrderData[] newArray(int size) {
            // TODO Auto-generated method stub
            return new UserBeginOrderData[size];
        }
    };
}
