package com.example.hindiseekho;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyFragmentAdapter extends FragmentStateAdapter {

    MyFragmentAdapter(FragmentActivity fa){
        super(fa);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0) {
            return new NumbersActivity();
        }else if(position == 1) {
            return new FamilyMembersActivity();
        }else if(position == 2) {
            return new ColorsActivity();
        }else if(position == 3) {
            return new PhrasesActivity();
        }
        return null;
    }
}
