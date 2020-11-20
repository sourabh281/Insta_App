package com.example.insta;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Tab_Adapter  extends FragmentPagerAdapter {

    public Tab_Adapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int TabPosition) {
        switch (TabPosition){
            case 0:
                return new SharePicture_Tab();
            case 1:
                return new Users_Tab();
            case 2:
                return new Profile_Tab();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){

            case 0:
                return "Share Picture";
            case 1:
                return "Users";
            case 2:
                return "Profile";
            default:
                return null;
        }
    }
}
