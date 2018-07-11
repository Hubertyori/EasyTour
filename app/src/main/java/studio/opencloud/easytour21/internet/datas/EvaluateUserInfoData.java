package studio.opencloud.easytour21.internet.datas;

public class EvaluateUserInfoData {
    //评价用户界面所需信息
    private String nickname;
    private String telephone;
    private String sex;
    private String headphoto;
    private String introduce;
    private float star;

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

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }
}
