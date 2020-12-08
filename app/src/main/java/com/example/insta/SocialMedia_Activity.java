package com.example.insta;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;

public class SocialMedia_Activity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Tab_Adapter tab_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media_);
        viewPager = findViewById(R.id.view_pager);

        tab_adapter = new Tab_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(tab_adapter);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager, false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menus , menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.image_post){


            if (android.os.Build.VERSION.SDK_INT>=23
                    && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3000);

            }else {

              ChooseImage();
            }


        }else if(item.getItemId() == R.id.logout_user){

            ParseUser.getCurrentUser().logOut();
            finish();
            Intent intent = new Intent(SocialMedia_Activity.this , Login_Activity.class);
            startActivity(intent);
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 3000){

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

               ChooseImage();
            }
        }
    }

    private void ChooseImage() {

        Intent intent = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent , 4000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 4000 && resultCode == RESULT_OK && data != null){


                try {

                    Uri chooseImg = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver() , chooseImg);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG , 100 , byteArrayOutputStream);
                    byte[] bytes = byteArrayOutputStream.toByteArray();

                    ParseFile parseFile = new ParseFile("img.png" ,bytes);
                    ParseObject parseObject = new ParseObject("photo");
                    parseObject.put("picture" , parseFile);
                    parseObject.put("username" , ParseUser.getCurrentUser().getUsername());
                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                    parseObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null){

                                Toast.makeText(SocialMedia_Activity.this , "Picture Uploaded" , Toast.LENGTH_SHORT).show();
                            }else {

                                Toast.makeText(SocialMedia_Activity.this , "Error In Uploading Picture" , Toast.LENGTH_SHORT).show();

                            }
                            progressDialog.dismiss();
                        }
                    });

                }catch (Exception e){

                    e.printStackTrace();
                }

        }
    }
}
