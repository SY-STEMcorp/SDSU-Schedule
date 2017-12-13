package com.example.root.sdsu_gmap;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Registration(View v)
    {
        Intent RegActivity = new Intent(this, RegistrationActivity.class);
        startActivity(RegActivity);
    }

    public void Login(View v)
    {
        final EditText login = (EditText) findViewById(R.id.LoginField);
        final EditText pass = (EditText) findViewById(R.id.PasswordField);

        ArrayList<String> parameters = new ArrayList<>();
        parameters.add("Email=" + login.getText());
        parameters.add("Password=" + pass.getText());

        NetworkCommunicator NC = new NetworkCommunicator(Constants.HOST + "login.php", parameters);
        try {
            HashMap<String, Object> Response = NC.execute().get();

            String ErrorCode = Response.get("ErrorCode").toString();
            String Status = Response.get("Status").toString();

            if(ErrorCode.equals("0") && Status.equals("0"))
            {
                Intent LoggedInActivity = new Intent(getBaseContext(), LoggedInActivity.class);
                startActivity(LoggedInActivity);
            }
            else if(ErrorCode.equals("1"))
            {
                //
            }
            else if(ErrorCode.equals("2"))
            {
                //return 2;
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    public class UserLoginTask extends AsyncTask<Void, Void, Integer> {

        private final String mEmailORSID;
        private final String mPassword;

        private String ID = "";
        private String Token = "";

        UserLoginTask(String email, String password) {
            mEmailORSID = email;
            mPassword = password;
        }

        @Override
        protected Integer doInBackground(Void... params) {

            try {
                URL LoginURL = new URL(Constants.HOST + "login.php?emailorsid=" + mEmailORSID + "&password=" + mPassword);
                //TODO: Convert this to https (HttpsURLConnection)
                HttpURLConnection conn = (HttpURLConnection) LoginURL.openConnection();

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String[] Response = in.readLine().split(" ");

                if(Response.length == 3 && Response[0].equals("0") && Response[1].length() > 0 && Response[2].length() > 0)
                {
                    ID = Response[1];
                    Token = Response[2];
                    return 0;
                }
                else if(Response.length == 1 && Response[0].equals("1"))
                {
                    return 1;
                }
                else if(Response.length == 1 && Response[0].equals("2"))
                {
                    return 2;
                }

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
                Intent LoggedInActvitiy = new Intent(getBaseContext(), LoggedInActivity.class);
                startActivity(LoggedInActvitiy);
            }
            else if(success == 1) {
//                mEmailView.setError(getString(R.string.error_incorrect_email_or_password));
//                mEmailView.requestFocus();
            }
            else if(success == 2) {
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
