package in.qbent.com.qbentrecclick;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    Button backButton;
    EditText username,password;
    ConstraintLayout userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.myLogin);
        backButton = (Button) findViewById(R.id.backHome);

        username = findViewById(R.id.uName);
        password = findViewById(R.id.uPass);
        userLogin = findViewById(R.id.LoginForm);

        //Check if user in already logged in//
        if(SaveSharedPreferences.getLoggedStatus(getApplicationContext()))
        {
            Intent intent4 = new Intent(getApplicationContext(),RecycleClickActivity.class);
            startActivity(intent4);
        }
        else
        {
            userLogin.setVisibility(View.VISIBLE);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usLogin(username.getText().toString(), password.getText().toString());
            }
        });

    }

    //login API call//
    public void usLogin(String usname, String pass)
    {

    }

//    public void goMain(View view)
//    {
//        Intent intent4 = new Intent(getApplicationContext(),RecycleClickActivity.class);
//        startActivity(intent4);
//    }

    public void backToHome(View view)
    {
        Intent intent5 = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent5);
    }
}
