package studio.opencloud.easytour21.blogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import studio.opclound.easytour.R;
import studio.opencloud.easytour21.users.Login;
import studio.opencloud.easytour21.users.Verification;

/**
 * Created by 英俊的mrsail on 2018/7/4.
 */

public class ContentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity);
        Bundle bundle = this.getIntent().getExtras();

        String title = bundle.getString("Title");
        String time = bundle.getString("Time");
        String author = bundle.getString("Author");
        String content = bundle.getString("Content");
        ((TextView)findViewById(R.id.Title)).setText(title);
        ((TextView)findViewById(R.id.Time)).setText(time);
        ((TextView)findViewById(R.id.Author)).setText(author);
        ((TextView)findViewById(R.id.Content)).setText(content);



    }

    public void onClick(View view) {
        Intent intent = getIntent();
        intent = new Intent(ContentActivity.this, CommentActivity.class);


        intent.setClass(ContentActivity.this, CommentActivity.class);

//                        userDataDiliver();
        ContentActivity.this.startActivity(intent);
        finish();
    }
}
