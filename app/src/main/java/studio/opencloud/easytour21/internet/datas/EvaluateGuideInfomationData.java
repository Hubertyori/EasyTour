package studio.opencloud.easytour21.internet.datas;

public class EvaluateGuideInfomationData {
    //评价导游界面所需信息
    private String realname;
    private String guideNumber;
    private String servercity;
    private float star;
    private String headphoto;
    private String guideIntro;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getGuideNumber() {
        return guideNumber;
    }

    public void setGuideNumber(String guideNumber) {
        this.guideNumber = guideNumber;
    }

    public String getServercity() {
        return servercity;
    }

    public void setServercity(String servercity) {
        this.servercity = servercity;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public String getHeadphoto() {
        return headphoto;
    }

    public void setHeadphoto(String headphoto) {
        this.headphoto = headphoto;
    }

    public String getGuideIntro() {
        return guideIntro;
    }

    public void setGuideIntro(String guideIntro) {
        this.guideIntro = guideIntro;
    }
}
