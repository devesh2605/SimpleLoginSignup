package devesh.com.simpleloginsignup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import devesh.com.simpleloginsignup.Interfaces.RestAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static devesh.com.simpleloginsignup.StaticStrings.ROOT_URL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        textViewSignup = (TextView) findViewById(R.id.textViewSignup);

        buttonLogin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }

    private void loginUser() {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        RestAPI api = adapter.create(RestAPI.class);

        api.loginUser(
                editTextEmail.getText().toString(),
                editTextPassword.getText().toString(),

                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {

                        BufferedReader reader = null;
                        String output = "";

                        try {
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            output = reader.readLine();
                            Intent intent = new Intent(LoginActivity.this, UserProfileActivity.class);
                            intent.putExtra("email", editTextEmail.getText().toString());
                            startActivity(intent);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    @Override
    public void onClick(View v) {
        if (v == buttonLogin) {
            loginUser();
        }

        if (v == textViewSignup) {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        }
    }
}