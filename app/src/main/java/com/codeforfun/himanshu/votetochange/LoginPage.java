package com.codeforfun.himanshu.votetochange;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codeforfun.himanshu.votetochange.NetworkCalls.NetworkCall;
import com.codeforfun.himanshu.votetochange.NetworkCalls.NetworkCallInterrface;
import com.codeforfun.himanshu.votetochange.NetworkHelper.UrlData;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends AppCompatActivity {

    EditText mUsernameInput,mPasswordInput;
    TextView mLoginFailedNotice;

    private String mUsername, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //Initialize all the variables
        mUsernameInput = (EditText) findViewById(R.id.usernameInput);
        mPasswordInput = (EditText) findViewById(R.id.passwordInput);
        mLoginFailedNotice = (TextView) findViewById(R.id.loginFailedNotice);

        findViewById(R.id.signInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        findViewById(R.id.signUpButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignUp.class);
                startActivity(i);
                finish();
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

        mUsername = mUsernameInput.getText().toString();
        mPassword = mPasswordInput.getText().toString();


        //Make sure that the mUsername and mPassword are valid.
        if(!isUsernameValid(mUsername) || !isPasswordValid(mPassword)){
            Toast.makeText(this, "Input data is invalid", Toast.LENGTH_SHORT).show();
            return;
        }

        List<String> queryData = new ArrayList<>();
        queryData.add("username");
        queryData.add(mUsername);
        queryData.add("password");
        queryData.add(mPassword);

        NetworkCall.execute(this, UrlData.LOGIN_URL, queryData, "Signing In", new NetworkCallInterrface() {
            @Override
            public void onResultReturn(String result) {
                Log.i(AppConstants.TAG,"Login Result = "+result);
                if(result!=null && result.equals("1") ){//Login is Success;
                    Log.i(AppConstants.TAG,"Login success");
                    saveUserCredentials(mUsername, mPassword);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    //Toast.makeText(this, "Username/Password is incorrect", Toast.LENGTH_SHORT).show();
                    notifyLoginFailed();
                    return;
                }
            }
        });


        /*try {
            String loginResult = new BackgroundNetworkCall().execute(this,UrlData.LOGIN_URL,queryData);
            Log.i(AppConstants.TAG,"Login Result = "+loginResult);
            if(loginResult!=null && loginResult.equals("1") ){
                //Login is Success;
                Log.i(AppConstants.TAG,"Login success");

                saveUserCredentials(mUsername,mPassword);

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
            else{
                //Login Failed
                //Toast.makeText(this, "Username/Password is incorrect", Toast.LENGTH_SHORT).show();
                notifyLoginFailed();
                return;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * Make the UI changes for login failed.
     */
    private void notifyLoginFailed(){
        mPasswordInput.setText("");
        mLoginFailedNotice.setVisibility(View.VISIBLE);
    }

    /**
     * Save the mUsername and mPassword of the user in SharedPreferences
     * @param username of the logged in user
     * @param password of the logged in user
     */
    private void saveUserCredentials(String username, String password){

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("UserPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(AppConstants.SHARED_PREFS_USERNAME,username);
        editor.putString(AppConstants.SHARED_PREFS_PASSWORD,password);
        editor.apply();

    }

}
