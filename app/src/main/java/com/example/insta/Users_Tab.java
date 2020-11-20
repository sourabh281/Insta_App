package com.example.insta;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Users_Tab extends Fragment {


    public Users_Tab() {

    }

    // TODO: Rename and change types and number of parameters
    public static Users_Tab newInstance(String param1, String param2) {
        Users_Tab fragment = new Users_Tab();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users__tab, container, false);
    }
}