package com.example.insta;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;

public class SharePicture_Tab extends Fragment implements View.OnClickListener {
    private ImageView imgShareImage;
    private EditText edtShareImage;
    private Button btnShareImage;
    Bitmap receivedImageBitmap;



    public SharePicture_Tab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_share_picture__tab, container, false);

        imgShareImage = view.findViewById(R.id.imgShareImage);
        edtShareImage = view.findViewById(R.id.edtShareImage);
        btnShareImage = view.findViewById(R.id.btnShareImage);

        imgShareImage.setOnClickListener(SharePicture_Tab.this);
        btnShareImage.setOnClickListener(SharePicture_Tab.this);

        return  view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.imgShareImage:

                if (android.os.Build.VERSION.SDK_INT>=23
                        && ActivityCompat.checkSelfPermission(getContext() ,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);

                }else {

                    getChossenImage();
                }
                break;
            case R.id.btnShareImage:
                if (receivedImageBitmap != null){
                    if (edtShareImage.getText().toString().equals("")){

                        Toast.makeText(getContext() , "Please Give Description" , Toast.LENGTH_SHORT).show();

                    }else {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        receivedImageBitmap.compress(Bitmap.CompressFormat.PNG , 100 , byteArrayOutputStream);
                        byte[] bytes = byteArrayOutputStream.toByteArray();
                        ParseFile parseFile = new ParseFile("img.png" ,bytes);
                        ParseObject parseObject = new ParseObject("photo");
                        parseObject.put("picture" , parseFile);
                        parseObject.put("img_des" , edtShareImage.getText().toString());
                        parseObject.put("username" , ParseUser.getCurrentUser().getUsername());
                        final ProgressDialog progressDialog = new ProgressDialog(getContext());
                        progressDialog.setMessage("Loading...");
                        progressDialog.show();
                        parseObject.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null){

                                    Toast.makeText(getContext() , "Success In Saving Profile" , Toast.LENGTH_SHORT).show();
                                }else {

                                    Toast.makeText(getContext() , "Error In Saving Profile" , Toast.LENGTH_SHORT).show();

                                }
                                progressDialog.dismiss();
                            }
                        });


                    }

                }else {
                    Toast.makeText(getContext() , "Please Upload Image" , Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }

    private void getChossenImage() {

        Intent intent = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent , 2000);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1000){

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                getChossenImage();
            }
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2000){

            if (resultCode == Activity.RESULT_OK){

                try {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getActivity().getContentResolver().query(selectedImage ,filePathColumn
                            , null , null , null );
                    cursor.moveToFirst();
                    int ColumnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(ColumnIndex);
                    cursor.close();
                    receivedImageBitmap = BitmapFactory.decodeFile(picturePath);
                    imgShareImage.setImageBitmap(receivedImageBitmap);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}