package studio.opencloud.easytour21.internet.translations;

import java.util.List;

import studio.opencloud.easytour21.internet.datas.GuideInfomation;

public class Guide_Information_Translation {
    private int code;
    private String message;
    private List<GuideInfomation> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<GuideInfomation> getData() {
        return data;
    }

    public void setData(List<GuideInfomation> data) {
        this.data = data;
    }
}
