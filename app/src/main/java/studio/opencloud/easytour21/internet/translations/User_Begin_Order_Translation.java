package studio.opencloud.easytour21.internet.translations;

import java.util.List;

import studio.opencloud.easytour21.internet.datas.UserBeginOrderData;

public class User_Begin_Order_Translation {
    //用户BeginOrder转译
    private int code;
    private String message;
    private List<UserBeginOrderData> data;

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

    public List<UserBeginOrderData> getData() {
        return data;
    }

    public void setData(List<UserBeginOrderData> data) {
        this.data = data;
    }
}
