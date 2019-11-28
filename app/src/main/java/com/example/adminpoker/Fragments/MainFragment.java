package com.example.adminpoker.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.adminpoker.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainFragment extends Fragment {

    Button bt_view;
    Button bt_add;

    public  MainFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_main, container, false);

        bt_view = view.findViewById(R.id.bt_check_answers);
        bt_add = view.findViewById(R.id.bt_add_group);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentAddGroup addGroupd = new FragmentAddGroup();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.container, addGroupd);
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        bt_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             /*   FragmentAddGroup addGroupd = new FragmentAddGroup();
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.container, addGroupd);
                fr.addToBackStack(null);
                fr.commit();
                */
            }
        });
        return view;

    }
}
