package studio.opencloud.easytour21.internet.datas;

import android.os.Parcel;
import android.os.Parcelable;

public class GuideGetUserInfoByIDData implements Parcelable {
    private String nickname;
    private String telephone;
    private String sex;
    private String headphoto;
    private String introduce;
    private String isguide;
    private int guideid;
    private float star;
    private String password;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadphoto() {
        return headphoto;
    }

    public void setHeadphoto(String headphoto) {
        this.headphoto = headphoto;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getIsguide() {
        return isguide;
    }

    public void setIsguide(String isguide) {
        this.isguide = isguide;
    }

    public int getGuideid() {
        return guideid;
    }

    public void setGuideid(int guideid) {
        this.guideid = guideid;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    }

    public static final Creator<GuideGetUserInfoByIDData> CREATOR = new Creator<GuideGetUserInfoByIDData>() {;

        @Override
        public GuideGetUserInfoByIDData createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            GuideGetUserInfoByIDData userInformationData = new GuideGetUserInfoByIDData();
            userInformationData.nickname = source.readString();
            userInformationData.telephone = source.readString();
            userInformationData.sex = source.readString();
            userInformationData.headphoto = source.readString();
            userInformationData.introduce = source.readString();
            userInformationData.isguide = source.readString();
            userInformationData.guideid = source.readInt();
            userInformationData.star = source.readFloat();
            userInformationData.password = source.readString();
            return userInformationData;
        }

        @Override
        public GuideGetUserInfoByIDData[] newArray(int size) {
            // TODO Auto-generated method stub
            return new GuideGetUserInfoByIDData[size];
        }
    };
}
