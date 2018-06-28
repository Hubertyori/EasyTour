package studio.opclound.easytour.photo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import studio.opclound.easytour.R;

/**
 * Created by wangpeiyu on 2018/3/30.
 */

public class BitmapUtil {

    public static Bitmap getBitmapFormPath(Context context, String temppath) {
        try {
            FileInputStream fis = new FileInputStream(temppath);
            Bitmap bitmap = BitmapFactory.decodeStream(fis);
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.et_logo);
    }
}
