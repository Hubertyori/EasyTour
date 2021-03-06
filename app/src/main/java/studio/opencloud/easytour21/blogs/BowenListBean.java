package studio.opencloud.easytour21.blogs;

import java.util.List;

/**
 * Created by 英俊的mrsail on 2018/6/29.
 */

public class BowenListBean {
   private int code;
   private String message;
   private List<BowenListInfo> data;

   public static class BowenListInfo{
       int bowenId;
       String userNickname;
       String title;
       int ZanNumber;
       int collectedNumber;
       String time;
       String image;
       String userheadIcon;

       public int getBowenId() {
           return bowenId;
       }

       public void setBowenId(int bowenId) {
           this.bowenId = bowenId;
       }

       public String getUserNickname() {
           return userNickname;
       }

       public void setUserNickname(String userNickname) {
           this.userNickname = userNickname;
       }

       public String getTitle() {
           return title;
       }

       public void setTitle(String title) {
           this.title = title;
       }

       public int getZanNumber() {
           return ZanNumber;
       }

       public void setZanNumber(int zanNumber) {
           ZanNumber = zanNumber;
       }

       public int getCollectedNumber() {
           return collectedNumber;
       }

       public void setCollectedNumber(int collectedNumber) {
           this.collectedNumber = collectedNumber;
       }

       public String getTime() {
           return time;
       }

       public void setTime(String time) {
           this.time = time;
       }

       public String getImage() {
           return image;
       }

       public void setImage(String image) {
           this.image = image;
       }

       public String getUserheadIcon() {
           return userheadIcon;
       }

       public void setUserheadIcon(String userheadIcon) {
           this.userheadIcon = userheadIcon;
       }
   }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<BowenListInfo> getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<BowenListInfo> data) {
        this.data = data;
    }
}
