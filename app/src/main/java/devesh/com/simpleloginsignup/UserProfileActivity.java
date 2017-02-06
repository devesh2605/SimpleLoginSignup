package devesh.com.simpleloginsignup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity {

    private TextView textViewEmail;
    private Bundle bundle;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        textViewEmail = (TextView) findViewById(R.id.textViewEmail);

        bundle = getIntent().getExtras();
        userEmail = bundle.getString("email");
        textViewEmail.setText(userEmail.toString());

    }
}
