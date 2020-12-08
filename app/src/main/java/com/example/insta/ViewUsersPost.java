package com.example.insta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class ViewUsersPost extends AppCompatActivity {
    private LinearLayout linear_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users_post);


        Intent receivedIntentObject = getIntent();
        String receivedUserName = receivedIntentObject.getStringExtra("username");
        //Toast.makeText(ViewUsersPost.this , receivedUserName , Toast.LENGTH_SHORT).show();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(receivedUserName + "\'s Posts");
        linear_layout = findViewById(R.id.linear_layout);

        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("photo");
        parseQuery.whereEqualTo("username" , receivedUserName);
        // Above code means Where username is equal to receivedUserName
        parseQuery.orderByDescending("createdAt");

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (objects.size() > 0 && e == null) {

                    for (ParseObject post : objects){

                        final TextView txtImageDesc = new TextView(ViewUsersPost.this);
                        txtImageDesc.setText(post.get("img_des") + "");
                        ParseFile parseFile = (ParseFile) post.get("picture");
                        parseFile.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {

                                if (data != null && e == null){

                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data , 0 , data.length);
                                    ImageView imgPostPicture = new ImageView(ViewUsersPost.this);
                                    LinearLayout.LayoutParams postPicture_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,
                                            ViewGroup.LayoutParams.WRAP_CONTENT);
                                    postPicture_params.setMargins(5 , 5 , 5 , 5);
                                    imgPostPicture.setLayoutParams(postPicture_params);
                                    imgPostPicture.setScaleType(ImageView.ScaleType.FIT_CENTER);
                                    imgPostPicture.setImageBitmap(bitmap);

                                    LinearLayout.LayoutParams des_params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,
                                            ViewGroup.LayoutParams.WRAP_CONTENT);
                                    des_params.setMargins(5 , 5 , 5 , 15);
                                    txtImageDesc.setLayoutParams(des_params);
                                    txtImageDesc.setGravity(Gravity.CENTER);
                                    txtImageDesc.setBackgroundColor(getResources().getColor(R.color.primaryColor));
                                    txtImageDesc.setTextColor(Color.BLACK);
                                    txtImageDesc.setTextSize(30f);

                                    linear_layout.addView(imgPostPicture);
                                    linear_layout.addView(txtImageDesc);


                                }
                            }
                        });



                    }
                }else {

                    Toast.makeText(ViewUsersPost.this , "This user does not have any post" , Toast.LENGTH_SHORT).show();
                    finish();
                }


                progressDialog.dismiss();
                
            }
        });




    }
}