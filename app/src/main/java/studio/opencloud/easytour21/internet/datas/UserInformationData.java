package studio.opencloud.easytour21.internet.datas;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInformationData implements Parcelable {
    private String nickname;
    private String telephone;
    private String sex;
    private String headphoto;
    private String introduce;
    private String isguide;
    private int guideid;
    private float star;
    private String password;
    private String guiderealname;
    private String guideIDnumbr;
    private String guideNumber;
    private String guideservercity;
    private float guidestar;

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setHeadphoto(String headphoto) {
        this.headphoto = headphoto;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setIsguide(String isguide) {
        this.isguide = isguide;
    }

    public void setGuideid(int guideid) {
        this.guideid = guideid;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGuiderealname(String guiderealname) {
        this.guiderealname = guiderealname;
    }

    public void setGuideIDnumbr(String guideIDnumbr) {
        this.guideIDnumbr = guideIDnumbr;
    }

    public void setGuideNumber(String guideNumber) {
        this.guideNumber = guideNumber;
    }

    public void setGuideservercity(String guideservercity) {
        this.guideservercity = guideservercity;
    }

    public void setGuidestar(float guidestar) {
        this.guidestar = guidestar;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getSex() {
        return sex;
    }

    public String getHeadphoto() {
        return headphoto;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getIsguide() {
        return isguide;
    }

    public int getGuideid() {
        return guideid;
    }

    public float getStar() {
        return star;
    }

    public String getPassword() {
        return password;
    }

    public String getGuiderealname() {
        return guiderealname;
    }

    public String getGuideIDnumbr() {
        return guideIDnumbr;
    }

    public String getGuideNumber() {
        return guideNumber;
    }

    public String getGuideservercity() {
        return guideservercity;
    }

    public float getGuidestar() {
        return guidestar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nickname);
        parcel.writeString(telephone);
        parcel.writeString(sex);
        parcel.writeString(headphoto);
        parcel.writeString(introduce);
        parcel.writeString(isguide);
        parcel.writeInt(guideid);
        parcel.writeFloat(star);
        parcel.writeString(password);
        parcel.writeString(guiderealname);
        parcel.writeString(guideIDnumbr);
        parcel.writeString(guideNumber);
        parcel.writeString(guideservercity);
        parcel.writeFloat(guidestar);
    }

    public static final Parcelable.Creator<UserInformationData> CREATOR = new Parcelable.Creator<UserInformationData>() {

        @Override
        public UserInformationData createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            UserInformationData userInformationData = new UserInformationData();
            userInformationData.nickname = source.readString();
            userInformationData.telephone= source.readString();
            userInformationData.sex= source.readString();
            userInformationData.headphoto= source.readString();
            userInformationData.introduce= source.readString();
            userInformationData.isguide= source.readString();
            userInformationData.guideid= source.readInt();
            userInformationData.star= source.readFloat();
            userInformationData.password= source.readString();
            userInformationData.guiderealname= source.readString();
            userInformationData.guideIDnumbr= source.readString();
            userInformationData.guideNumber= source.readString();
            userInformationData.guideservercity= source.readString();
            userInformationData.guidestar= source.readFloat();

            return userInformationData;
        }

        @Override
        public UserInformationData[] newArray(int size) {
            // TODO Auto-generated method stub
            return new UserInformationData[size];
        }
    };
}
