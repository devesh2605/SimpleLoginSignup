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

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextMobile;
    private EditText editTextUserType;
    private Button buttonSignUp;
    private TextView textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextMobile = (EditText) findViewById(R.id.editTextMobile);
        editTextUserType = (EditText) findViewById(R.id.editTextUserType);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        textViewLogin = (TextView) findViewById(R.id.textViewLogin);

        buttonSignUp.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);
    }

    private void insertUser() {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        RestAPI api = adapter.create(RestAPI.class);

        api.insertUser(
                editTextName.getText().toString(),
                editTextEmail.getText().toString(),
                editTextPassword.getText().toString(),
                editTextMobile.getText().toString(),
                editTextUserType.getText().toString(),

                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {

                        BufferedReader reader = null;
                        String output = "";

                        try {
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            output = reader.readLine();
                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(SignupActivity.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(SignupActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    @Override
    public void onClick(View v) {

        if (v == buttonSignUp) {
            insertUser();
        }

        if (v == textViewLogin) {
            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        }
    }
}
