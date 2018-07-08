package studio.opencloud.easytour21.internet.translations;

import java.util.List;

import studio.opencloud.easytour21.internet.datas.UserOrderData;

public class User_Order_Translation {
    private int code;
    private String message;
    private List<UserOrderData> data;

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

    public List<UserOrderData> getData() {
        return data;
    }

    public void setData(List<UserOrderData> data) {
        this.data = data;
    }
}
