package com.example.insta;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class Users_Tab extends Fragment  implements AdapterView.OnItemClickListener , AdapterView.OnItemLongClickListener {
    private ListView listView;
    private ArrayList<String> arrayList;
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

        listView.setOnItemClickListener(Users_Tab.this);
        listView.setOnItemLongClickListener(Users_Tab.this);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getContext() , ViewUsersPost.class);
        intent.putExtra("username" , arrayList.get(position));
        startActivity(intent);


    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
        parseQuery.whereEqualTo("username" , arrayList.get(position));
        parseQuery.getFirstInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser users, ParseException e) {
                if (users != null && e == null){

                    //Toast.makeText(getContext(), "Details", Toast.LENGTH_SHORT).show();

                    final PrettyDialog prettyDialog = new PrettyDialog(getContext());

                    prettyDialog.setTitle(users.getUsername() + "\'s Info")
                            .setMessage(users.get("bio")+"\n"
                            + users.get("profession") + "\n"
                            + users.get("hobbie") + "\n"
                            + users.get("favsong"))
                            .setIcon(R.drawable.ic_baseline_person_24)
                            .addButton(
                                    "ok",
                                    R.color.primaryTextColor,
                                    R.color.secondaryColor,
                                    new PrettyDialogCallback() {
                                        @Override
                                        public void onClick() {

                                            prettyDialog.dismiss();
                                        }
                                    }
                            ).show();

                }

            }
        });
        return true;
    }
}
