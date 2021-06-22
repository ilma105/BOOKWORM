package com.example.system_project.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.system_project.Fragments.ChatsFragment;
import com.example.system_project.Fragments.StatuskFragment;

public class FragmentAdapter1 extends FragmentPagerAdapter {
    public FragmentAdapter1(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {


        return new StatuskFragment();

    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0){
            title = "Chats";
        }
        if (position == 1){
            title = "Status";
        }
        return title;
    }
}

