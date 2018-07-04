package studio.opencloud.easytour21.Time;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import studio.opclound.easytour.R;

public class TimeCount extends CountDownTimer {
    private Button btnCode;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TimeCount(long millisInFuture, long countDownInterval, Button btn) {
        super(millisInFuture, countDownInterval);
        this.btnCode = btn;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        btnCode.setBackgroundResource(R.drawable.regist_suc);
        btnCode.setTextSize(13);
        btnCode.setText(millisUntilFinished / 1000 + "秒");
        btnCode.setClickable(false);
    }

    @Override
    public void onFinish() {
        btnCode.setBackgroundResource(R.drawable.regist_suc);
        btnCode.setTextSize(13);
        btnCode.setText("再次接收");
        btnCode.setClickable(true);
    }
}