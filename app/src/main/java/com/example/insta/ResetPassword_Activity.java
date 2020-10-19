package com.example.insta;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class ResetPassword_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText edtResetPassword;
    Button btnResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.txtResetPasswordActionBarTitle);

        edtResetPassword = findViewById(R.id.edtResetPasswordEmail);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        btnResetPassword.setOnClickListener(ResetPassword_Activity.this);

    }

    @Override
    public void onClick(View view) {

        ParseUser.requestPasswordResetInBackground(edtResetPassword.getText().toString(), new RequestPasswordResetCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){

                    SuccessDialog_Activity successDialog = new SuccessDialog_Activity();
                    successDialog.showSuccessDialog(ResetPassword_Activity.this , R.string.txtResetPasswordSuccessDialogMessege);

                }else {

                    ErrorDialog_Activity errorDialogActivity = new ErrorDialog_Activity();
                    errorDialogActivity.showErrorDialog(ResetPassword_Activity.this , R.string.txtErrorDialog);
                }

            }
        });

    }
}