package com.example.insta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class SuccessDialog_Activity extends AppCompatActivity {

   public void showSuccessDialog(Activity activity , int msg){

       final Dialog dialog = new Dialog(activity);
       dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
       dialog.setCancelable(false);
       dialog.setContentView(R.layout.activity_success_dialog_);

       TextView txtSuccessdialog = dialog.findViewById(R.id.txtSuccessDialog);
       txtSuccessdialog.setText(msg);

       Button btnSuccessDialog = dialog.findViewById(R.id.btnSuccessDialog);
       btnSuccessDialog.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(SuccessDialog_Activity.this ,Login_Activity.class);
               startActivity(intent);
           }
       });
       dialog.show();


   }
}