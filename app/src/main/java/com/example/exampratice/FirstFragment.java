package com.example.exampratice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_first_fragment, container, false);

        TextView tv = view.findViewById(R.id.tv_fragment);
        tv.setText("This is First Fragment (added via XML)");

        return view;
    }
}