package studio.opencloud.easytour21.internet.translations;

import java.util.List;

import studio.opencloud.easytour21.internet.datas.EvaluateUserInfoData;

public class EvaluateUser_Translation_Interface {
    //评价用户界面
    private int code;
    private String message;
    private List<EvaluateUserInfoData> data;

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

    public List<EvaluateUserInfoData> getData() {
        return data;
    }

    public void setData(List<EvaluateUserInfoData> data) {
        this.data = data;
    }
}
