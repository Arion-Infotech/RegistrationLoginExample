package com.arioninfotech.registrationloginexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {


    EditText txtUsername,txtPassword;

    Button btnLogin,btnSignUP;

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername=findViewById(R.id.txtusername);
        txtPassword=findViewById(R.id.txtpassword);

        btnLogin=findViewById(R.id.btnlogin);
        btnSignUP=findViewById(R.id.btnregister);

        session=new SessionManager(getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SignIn();

            }
        });

        btnSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    private void SignIn() {

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("please wait");
        progressDialog.show();

        String email = txtUsername.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();


        Api.getClient().signin(email, password, new Callback<SignUpResponse>() {
            @Override
            public void success(SignUpResponse loginResponse, Response response) {
                progressDialog.dismiss();
                if(loginResponse!=null){
                    String username = loginResponse.getEmail();

                    if (loginResponse.getMsg().equals("success")) {


                        session.createSignInSession(username, txtPassword.getText().toString().trim());
                        Intent i = new Intent(getApplicationContext(), HomeActivity.class);

                        startActivity(i);
                        finish();

                    } }else {
                    Toast.makeText(getApplicationContext(), "Invalid User !!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {

                Toast.makeText(getApplicationContext(), "failed" + error, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }});

    }
}
