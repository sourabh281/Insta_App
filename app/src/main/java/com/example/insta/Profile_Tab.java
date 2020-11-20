package com.example.insta;

import android.os.Bundle;


import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class Profile_Tab extends Fragment {

    EditText edtProfileUserName , edtProfileUserBio , edtProfileUserProfession , edtProfileUserHobbie ,
             edtProfileUserFavSong ;
    Button btnSaveProfile;




    public Profile_Tab() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     
         View view = inflater.inflate(R.layout.fragment_profile__tab, container, false);

         edtProfileUserName = view.findViewById(R.id.edtProfileUserName);
         edtProfileUserBio = view.findViewById(R.id.edtProfileUserBio);
         edtProfileUserProfession = view.findViewById(R.id.edtProfileUserProfession);
         edtProfileUserHobbie = view.findViewById(R.id.edtProfileUserHobbies);
         edtProfileUserFavSong = view.findViewById(R.id.edtProfileUserFavSong);
         btnSaveProfile = view.findViewById(R.id.btnSaveProfile);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser.get("userProfileName") == null){

            edtProfileUserName.setText("");
        }else {
            edtProfileUserName.setText( parseUser.get("userProfileName").toString());
        }

        if (parseUser.get("bio") == null){

            edtProfileUserBio.setText("");
        }else {
            edtProfileUserBio.setText(parseUser.get("bio").toString());
        }

        if (parseUser.get("profession") == null){

            edtProfileUserProfession.setText("");
        }else {
            edtProfileUserProfession.setText(parseUser.get("bio").toString());
        }

        if (parseUser.get("hobbies") == null){

            edtProfileUserHobbie.setText("");
        }else {
            edtProfileUserHobbie.setText(parseUser.get("hobbies").toString());
        }

        if (parseUser.get("favSong") == null){

            edtProfileUserFavSong.setText("");
        }else {
            edtProfileUserFavSong.setText(parseUser.get("favSong").toString());
        }



         btnSaveProfile.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 parseUser.put("userProfileName" , edtProfileUserName.getText().toString());
                 parseUser.put("bio" , edtProfileUserBio.getText().toString());
                 parseUser.put("profession" , edtProfileUserProfession.getText().toString());
                 parseUser.put("hobbies" , edtProfileUserHobbie.getText().toString());
                 parseUser.put("favSong" , edtProfileUserFavSong.getText().toString());

                 parseUser.saveInBackground(new SaveCallback() {
                     @Override
                     public void done(ParseException e) {
                         if (e == null ){

                             Toast.makeText(getContext() , "Success In Saving Profile" , Toast.LENGTH_SHORT).show();

                     }else {

                             Toast.makeText(getContext() , "Error In Saving Profile" , Toast.LENGTH_SHORT).show();
                         }
                     }
                 });

             }
         });

         return view;

    }
}