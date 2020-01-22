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

public class SignupActivity extends AppCompatActivity {

    EditText txtname,txtplace,txtemail,txtpassword;

    Button btnRegister;

    String name,place,email,phone,username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtname=(EditText)findViewById(R.id.txtname);
        txtplace=(EditText)findViewById(R.id.txtplace);
        txtemail=(EditText)findViewById(R.id.txtemail);

        txtpassword=(EditText)findViewById(R.id.txtpassword);

        btnRegister=(Button)findViewById(R.id.btnregister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

    }
    private void Register() {

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        name = txtname.getText().toString().trim();
        place = txtplace.getText().toString().trim();
        email = txtemail.getText().toString().trim();
        password = txtpassword.getText().toString().trim();
        //Server Sending ....
        Api.getClient().register(name,place,email,password, new Callback<SignUpResponse>() {

            @Override
            public void success(SignUpResponse signUpResponse, Response response) {
                progressDialog.dismiss();

                if(signUpResponse.getMsg().equals("success"))
                {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }});



    }
}
