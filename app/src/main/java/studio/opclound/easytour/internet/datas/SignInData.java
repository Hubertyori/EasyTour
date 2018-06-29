package studio.opclound.easytour.internet.datas;

public class SignInData {
    private String nickname;
    private String telephone;
    private String sex;
    private String headphoto;
    private String introduce;
    private String isguide;
    private int guideid;
    private int collectguideid;
    private int collecteassyid;
    private int star;
    private String password;
    public int getCollecteassyid() {
        return collecteassyid;
    }

    public int getCollectguideid() {
        return collectguideid;
    }

    public int getGuideid() {
        return guideid;
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

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public int getStar() {
        return star;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setCollecteassyid(int collecteassyid) {
        this.collecteassyid = collecteassyid;
    }

    public void setCollectguideid(int collectguideid) {
        this.collectguideid = collectguideid;
    }

    public void setGuideid(int guideid) {
        this.guideid = guideid;
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

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
