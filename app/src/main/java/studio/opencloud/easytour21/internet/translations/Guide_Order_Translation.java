package studio.opencloud.easytour21.internet.translations;

import java.util.List;

import studio.opencloud.easytour21.internet.datas.GuideOrderData;

public class Guide_Order_Translation {
    private int code;
    private String message;
    private List<GuideOrderData> data;

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

    public List<GuideOrderData> getData() {
        return data;
    }

    public void setData(List<GuideOrderData> data) {
        this.data = data;
    }
}
