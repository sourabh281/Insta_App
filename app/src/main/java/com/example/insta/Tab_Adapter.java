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
                Profile_Tab profile_tab = new Profile_Tab();
                return profile_tab;
            case 1:
                Users_Tab users_tab = new Users_Tab();
                return users_tab;
            case 2:
                SharePicture_Tab sharePicture_tab = new SharePicture_Tab();
                return sharePicture_tab;
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
                return "Profile";
            case 1:
                return "Users";
            case 2:
                return "Share Picture";
            default:
                return null;
        }
    }
}
