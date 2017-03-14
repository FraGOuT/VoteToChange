package com.codeforfun.himanshu.votetochange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    EditText mUsernameInput,mPasswordInput;
    TextView mLoginFailedNotice;
    Button mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //Initialize all the variables
        mUsernameInput = (EditText) findViewById(R.id.usernameInput);
        mPasswordInput = (EditText) findViewById(R.id.passwordInput);
        mLoginFailedNotice = (TextView) findViewById(R.id.loginFailedNotice);
        mSignInButton = (Button) findViewById(R.id.signInButton);


        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    private boolean isUsernameValid(String username){
        return username.length() > 0;
    }

    private boolean isPasswordValid(String password){
        return password.length() > 0;
    }

    private void signIn(){

        //If it is login after failure we need erase the login failure message.
        mLoginFailedNotice.setVisibility(View.GONE);

        String username,password;

        username = mUsernameInput.getText().toString();
        password = mPasswordInput.getText().toString();

        Toast.makeText(this, "Jayesh ki mkc Helllllo..world", Toast.LENGTH_SHORT).show();



        //Make sure that the username and password are valid.
        if(!isUsernameValid(username) || !isPasswordValid(password)){
            Toast.makeText(this, "Input data is invalid", Toast.LENGTH_SHORT).show();
        }


        //We need to execute a network call here to authenticate the username and password.
        //The result of the network call decides login success or failure.

    }

    /**
     * Make the UI changes for login failed.
     */
    private void notifyLoginFailed(){
        mPasswordInput.setText("");
        mLoginFailedNotice.setVisibility(View.VISIBLE);
    }

}
