package com.example.hindiseekho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import android.app.Activity;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.my_pager);
        FragmentStateAdapter pagerAdapter = new MyFragmentAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        TabLayoutMediator.TabConfigurationStrategy tcs =
                new TabLayoutMediator.TabConfigurationStrategy(){
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        String s = "Invalid";
                        if(position == 0)
                        {
                            s = getString(R.string.numbers);
                        }
                        else if(position == 1)
                        {
                            s = getString(R.string.family_members);
                        }
                        else if(position == 2)
                        {
                            s = getString(R.string.colors);
                        }
                        else if(position == 3)
                        {
                            s = getString(R.string.phrases);
                        }
                        tab.setText(s);
                    }
                };

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager, tcs).attach();


    }

    /**
     * Called when the activity has detected the user's press of the back
     * key. The {@link #getOnBackPressedDispatcher() OnBackPressedDispatcher} will be given a
     * chance to handle the back button before the default behavior of
     * {@link Activity#onBackPressed()} is invoked.
     *
     * @see #getOnBackPressedDispatcher()
     */
    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(0);
        }
    }
}