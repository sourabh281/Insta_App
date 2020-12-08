
package com.example.insta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText edtSignUpUsername , edtSignUpEmail , edtSignUpPassword , edtUserFullName ;
    Button btnSignUp;
    TextView txtButtonTransitionToLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.txtSignUpActionBarTitle);


        edtSignUpUsername = findViewById(R.id.edtSignUpUsername);
        edtSignUpEmail = findViewById(R.id.edtSignUpEmail);
        edtSignUpPassword = findViewById(R.id.edtSignUpPassword);
        edtUserFullName = findViewById(R.id.edtUserFullName);
        btnSignUp = findViewById(R.id.btnSignUp);
        txtButtonTransitionToLoginActivity = findViewById(R.id.txtButtonTransitionToLoginActivity);

        btnSignUp.setOnClickListener(SignUp_Activity.this);
        txtButtonTransitionToLoginActivity.setOnClickListener(SignUp_Activity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnSignUp:

                final ParseUser parseUser = new ParseUser();
                parseUser.setUsername(edtSignUpUsername.getText().toString());
                parseUser.setEmail(edtSignUpEmail.getText().toString());
                parseUser.setPassword(edtSignUpPassword.getText().toString());

                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){

                            // Toast.makeText(SignUp_Activity.this , "Success" , Toast.LENGTH_SHORT).show();

                            SuccessDialog_Activity SuccessDialog = new SuccessDialog_Activity();
                            SuccessDialog.showSuccessDialog(SignUp_Activity.this , R.string.txtSuccessDialog);

                        }else {

                            //  Toast.makeText(SignUp_Activity.this , "Error" , Toast.LENGTH_SHORT).show();
                            try {

                                ErrorDialog_Activity errorDialog = new ErrorDialog_Activity();
                                errorDialog.showErrorDialog(SignUp_Activity.this , R.string.txtErrorDialog);

                                edtSignUpUsername.getText().clear();
                                edtSignUpEmail.getText().clear();
                                edtSignUpPassword.getText().clear();
                                edtUserFullName.getText().clear();


                            }catch (Exception ex){

                                Toast.makeText(SignUp_Activity.this , "Unexceptional Error" , Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                break;

            case R.id.txtButtonTransitionToLoginActivity:

                Intent intent = new Intent(SignUp_Activity.this , Login_Activity.class);
                startActivity(intent);
                finish();

                break;
        }



    }
}