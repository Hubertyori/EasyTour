package studio.opclound.easytour.internet.translations;

import studio.opclound.easytour.internet.datas.SignInData;

public class SignIn_Trainslation {
    private int code;
    private String message;
    private SignInData data;
    public void setCode(int code){this.code = code;}
    public void setMessage(String message){this.message = message;}
    public void setData(SignInData data){this.data = data;}

    public int getCode() {
        return code;
    }
    public String getMessage(){return message;}
    public SignInData getData(){return data;}
}
