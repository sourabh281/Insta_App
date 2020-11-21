package com.example.insta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Users_Tab extends Fragment {
    private ListView listView;
    private ArrayList arrayList;
    private ArrayAdapter arrayAdapter;



    public Users_Tab() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_users__tab, container, false);

        listView = view.findViewById(R.id.listView);
        arrayList = new ArrayList();
        arrayAdapter = new ArrayAdapter(getContext() , android.R.layout.simple_list_item_1 , arrayList);
        final TextView txtLoadingContent = view.findViewById(R.id.txtLoadingContent);
        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();

        parseQuery.whereNotEqualTo("username" , ParseUser.getCurrentUser().getUsername());

        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {

                if (e == null){

                    if (users.size() > 0){

                        for (ParseUser user : users){

                            arrayList.add(user.getUsername());
                        }
                        listView.setAdapter(arrayAdapter);
                        txtLoadingContent.animate().alpha(0.0f).setDuration(200);
                        listView.setVisibility(view.VISIBLE);
                    }
                }

            }
        });

        return  view;

    }
}