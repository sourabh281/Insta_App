package com.example.insta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {
    EditText edtLoginUserName , edtLoginPassword ;
    Button btnLogin;
    TextView txtLoginTransferToSignUpActivity , txtLoginResetPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);


        edtLoginUserName = findViewById(R.id.edtLoginUsername);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtLoginTransferToSignUpActivity = findViewById(R.id.txtLoginTransfeerToSignUpActivity);
        txtLoginResetPasswordText = findViewById(R.id.txtLoginResetPasswordText);


        btnLogin.setOnClickListener(Login_Activity.this);
        txtLoginTransferToSignUpActivity.setOnClickListener(Login_Activity.this);
        txtLoginResetPasswordText.setOnClickListener(Login_Activity.this);
        if (ParseUser.getCurrentUser() != null){

            Intent intent = new Intent(Login_Activity.this ,SocialMedia_Activity.class);
            startActivity(intent);
            finish();

        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnLogin:

                ParseUser.logInInBackground(edtLoginUserName.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null){

                            SuccessDialog_Activity successDialog = new SuccessDialog_Activity();
                            successDialog.showSuccessDialog(Login_Activity.this , R.string.txtLoggedInSuccessfullyText);

                            Intent intent = new Intent(Login_Activity.this , SocialMedia_Activity.class);
                            startActivity(intent);
                            finish();


                        }else {


                            ErrorDialog_Activity errorDialog = new ErrorDialog_Activity();
                            errorDialog.showErrorDialog(Login_Activity.this , R.string.txtErrorDialog);
                        }
                    }
                });

                break;
            case R.id.txtLoginTransfeerToSignUpActivity:

               Intent intent = new Intent(Login_Activity.this , SignUp_Activity.class);
               startActivity(intent);
               finish();

                break;
            case R.id.txtLoginResetPasswordText:

                Intent intent1 = new Intent(Login_Activity.this , ResetPassword_Activity.class);
                startActivity(intent1);

                break;


        }
    }
}