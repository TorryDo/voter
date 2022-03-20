package com.newhope.trido.voter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.newhope.trido.voter.line_12345.jfragment_home;
import com.newhope.trido.voter.line_12345.jfragment_me;
import com.newhope.trido.voter.line_12345.jfragment_mess;
import com.newhope.trido.voter.line_12345.jfragment_notify;
import com.newhope.trido.voter.line_12345.jfragment_top;

public class Main_home extends AppCompatActivity {

    private ViewPager pager;
    private TabLayout tabLayout;
    private FirebaseUser use;

    public static DatabaseReference reference;
    public static FirebaseAuth auth_mhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        reference = FirebaseDatabase.getInstance().getReference("homeTopic").child("topic");
        auth_mhome = FirebaseAuth.getInstance();

        pager = (ViewPager) findViewById(R.id.viewPager_home);


        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_home);
        tabLayout.setupWithViewPager(pager);

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));

        tabLayout.getTabAt(0).setIcon(R.drawable.home_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.star);
        tabLayout.getTabAt(2).setIcon(R.drawable.chat_icon);
        tabLayout.getTabAt(3).setIcon(R.drawable.notif);
        tabLayout.getTabAt(4).setIcon(R.drawable.me_icon);

    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {

        MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }
        @Override
        public Fragment getItem(int position) {
            Fragment frag=null;
            switch (position){
                case 0:
                    frag = new jfragment_home(Main_home.this);
                    break;
                case 1:
                    frag = new jfragment_top();
                    break;
                case 2:
                    frag = new jfragment_mess();
                    break;
                case 3:
                    frag = new jfragment_notify();
                    break;
                case 4:
                    frag = new jfragment_me();
                    break;
            }
            return frag;
        }

        @Override
        public int getCount() {
            return 5;
        }


    }
}

