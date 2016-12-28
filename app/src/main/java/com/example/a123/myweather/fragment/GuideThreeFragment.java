package com.example.a123.myweather.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.a123.myweather.R;
import com.example.a123.myweather.acitvity.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class GuideThreeFragment extends Fragment {

    private ImageView imageView;

    public GuideThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guide_three, container, false);
        imageView = (ImageView) view.findViewById(R.id.guide_three_img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), MainActivity.class);
                startActivity(it);
            }
        });
        return view;
    }

}
