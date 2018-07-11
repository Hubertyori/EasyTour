package com.example.xh.login;

/**
 * Created by Administrator on 2018/3/24.
 */

public class Order {
    private int mImage;
    private String mPlace;
    private String mPlaceDescrible;
    private String mDate;
    private String mDays;
    private String mUsername;
    private String mGuidename;
    private String mBegin_day;
    private String mEnd_day;
    private String mTimeDescrible;
    private String m_Remark;
    private int numOFPeople;
    private int mOrderID;
    private  String m_tel;

    public String getM_tel() {
        return m_tel;
    }

    public void setM_tel(String m_tel) {
        this.m_tel = m_tel;
    }



    public String getM_Remark() {
        return m_Remark;
    }

    public void setM_Remark(String m_Remark) {
        this.m_Remark = m_Remark;
    }




    public Order(int picture, String place, String describle, String date, String days){
        this.mImage = picture;
        this.mPlace = place;
        this.mPlaceDescrible = describle;
        this.mDate = date;
        this.mDays = days;
    }

    public Order(int mOrderID, String mUsername, String mPlace, String mDate, int numOFPeople,String m_Remark,String m_tel){
        this.mOrderID =mOrderID;
        this.mUsername = mUsername;
        this.mPlace = mPlace;
        this.mDate = mDate;
        this.numOFPeople =numOFPeople;
        this.m_Remark=m_Remark;
        this.m_tel=m_tel;

    }


    public Order(int mImage, String mPlace, String mPlaceDescrible, String mDate, String mDays, int mOrderID) {
        this.mImage = mImage;
        this.mPlace = mPlace;
        this.mPlaceDescrible = mPlaceDescrible;
        this.mDate = mDate;
        this.mDays = mDays;
        this.mOrderID = mOrderID;
    }

    public Order(int mImage, String mPlace, String mPlaceDescrible, String mDate, String mDays,
                 String mUsername, String mGuidename, String mBegin_day, String mEnd_day,
                 String mTimeDescrible, int numOFPeople, int mOrderID) {
        this.mImage = mImage;
        this.mPlace = mPlace;
        this.mPlaceDescrible = mPlaceDescrible;
        this.mDate = mDate;
        this.mDays = mDays;
        this.mUsername = mUsername;
        this.mGuidename = mGuidename;
        this.mBegin_day = mBegin_day;
        this.mEnd_day = mEnd_day;
        this.mTimeDescrible = mTimeDescrible;
        this.numOFPeople = numOFPeople;
        this.mOrderID = mOrderID;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public void setmDays(String mDays) {
        this.mDays = mDays;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public void setmPlace(String mPlace) {
        this.mPlace = mPlace;
    }

    public void setmPlaceDescrible(String mPlaceDescrible) {
        this.mPlaceDescrible = mPlaceDescrible;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public void setmGuidename(String mGuidename) {
        this.mGuidename = mGuidename;
    }

    public void setmBegin_day(String mBegin_day) {
        this.mBegin_day = mBegin_day;
    }

    public void setmEnd_day(String mEnd_day) {
        this.mEnd_day = mEnd_day;
    }

    public void setmOrderID(int mOrderID) {
        this.mOrderID = mOrderID;
    }

    public void setmTimeDescrible(String mTimeDescrible) {
        this.mTimeDescrible = mTimeDescrible;
    }

    public void setNumOFPeople(int numOFPeople) {
        this.numOFPeople = numOFPeople;
    }

    public int getmImage() {
        return mImage;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmBegin_day() {
        return mBegin_day;
    }

    public String getmDays() {
        return mDays;
    }

    public String getmPlace() {
        return mPlace;
    }

    public String getmEnd_day() {
        return mEnd_day;
    }

    public String getmPlaceDescrible() {
        return mPlaceDescrible;
    }

    public String getmGuidename() {
        return mGuidename;
    }

    public String getmUsername() {
        return mUsername;
    }

    public int getmOrderID() {
        return mOrderID;
    }

    public int getNumOFPeople() {
        return numOFPeople;
    }

    public String getmTimeDescrible() {
        return mTimeDescrible;
    }
}
