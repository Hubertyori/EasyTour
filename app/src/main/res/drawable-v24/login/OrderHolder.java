package com.example.xh.login;

import android.widget.ImageView;
import android.widget.TextView;

public class OrderHolder
{
    ImageView imageView;
    TextView title;
    TextView date;


    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setDate(TextView date) {
        this.date = date;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }
    public ImageView getImageView() {
        return imageView;
    }

    public TextView getDate() {
        return date;
    }
    public TextView getTitle() {
        return title;
    }
}
