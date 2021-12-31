package com.example.fcmcloudemessage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

public class NotificationFragment extends Fragment {

    TabLayout tab;
    ViewPager pager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_notification, container, false);

        tab=view.findViewById(R.id.tab);
        pager=view.findViewById(R.id.pager);

        PagerAdapter pagerAdapter=new PagerAdapter(getChildFragmentManager());
        tab.setupWithViewPager(pager);
        pager.setAdapter(pagerAdapter);
        return view;
    }
}