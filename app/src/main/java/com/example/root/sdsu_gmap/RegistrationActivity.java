package com.example.root.sdsu_gmap;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void Register(View v)
    {
        final EditText email = (EditText) findViewById(R.id.emailField);
        final EditText pwd = (EditText) findViewById(R.id.passwordField);

        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("Email=" + email.getText());
        parameters.add("Password=" + pwd.getText());

        NetworkCommunicator NC = new NetworkCommunicator(Constants.HOST + "registration.php", parameters);
        try {
            HashMap<String, Object> Response = NC.execute().get();
            String ErrorCode = Response.get("ErrorCode").toString();
            String Status = Response.get("Status").toString();

            if(ErrorCode.equals("0") && Status.equals("0"))
                finish();
            else if(ErrorCode.equals("1"))
                return;
            else if(ErrorCode.equals("2"))
                return;
            else if(ErrorCode.equals("3"))
                return;

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    public class UserRegistrationTask extends AsyncTask<Void, Void, Integer> {

        private final String email;
        private final String pwd;

        UserRegistrationTask(String email, String pwd) {
            this.email = email;
            this.pwd = pwd;
        }

        @Override
        protected Integer doInBackground(Void... params) {

            try {
                URL LoginURL = new URL(Constants.HOST + "registration.php?Email=" + email + "&Password=" + pwd);
                //TODO: Convert this to https (HttpsURLConnection)
                HttpURLConnection conn = (HttpURLConnection) LoginURL.openConnection();

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String Response = in.readLine();

                if(Response.equals("0"))
                    return 0;
                else if(Response.equals("1"))
                    return 1;
                else if(Response.equals("2"))
                    return 2;
                else if(Response.equals("3"))
                    return 3;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return -1;
        }

        @Override
        protected void onPostExecute(final Integer success) {
            //mAuthTask = null;
            //showProgress(false);

            if(success == -1) {
                //mEmailView.setError(getString(R.string.error_connection_problem));
                //mEmailView.requestFocus();
            }
            else if (success == 0)
            {
                finish();
            }
            else if(success == 1) {
//                mEmailView.setError(getString(R.string.error_incorrect_email_or_password));
//                mEmailView.requestFocus();
            }
            else if(success == 2) {
//                mEmailView.setError(getString(R.string.error_unknown));
//                mEmailView.requestFocus();
            }
            else if(success == 3) {
//                mEmailView.setError(getString(R.string.error_unknown));
//                mEmailView.requestFocus();
            }

        }

        @Override
        protected void onCancelled() {
//            mAuthTask = null;
//            showProgress(false);
        }
    }
}
