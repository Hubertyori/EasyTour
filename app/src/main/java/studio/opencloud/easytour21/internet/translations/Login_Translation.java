package studio.opencloud.easytour21.internet.translations;

import studio.opencloud.easytour21.internet.datas.UserInformationData;

public class Login_Translation {
    private int code;
    private String message;
    private UserInformationData data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(UserInformationData data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public UserInformationData getData() {
        return data;
    }
}
