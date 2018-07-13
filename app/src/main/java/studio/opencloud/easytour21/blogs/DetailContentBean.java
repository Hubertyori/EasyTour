package studio.opencloud.easytour21.blogs;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 英俊的mrsail on 2018/7/10.
 */

public class DetailContentBean {
    private int code;
    private String message;
    private DetailContent data;

    public static class DetailContent{
        int bowenId;
        int userId;
        String userNickname;
        String title;
        String content;
        int ZanNumber;
        int collectedNumber;
        String time;
        String image;
        @SerializedName("userheadIcon")
        String userHeadIcon;

        public int getBowenId() {
            return bowenId;
        }

        public void setBowenId(int bowenId) {
            this.bowenId = bowenId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getUserHeadIcon() {
            return userHeadIcon;
        }

        public void setUserHeadIcon(String userHeadIcon) {
            this.userHeadIcon = userHeadIcon;
        }
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public DetailContent getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(DetailContent data) {
        this.data = data;
    }
}
