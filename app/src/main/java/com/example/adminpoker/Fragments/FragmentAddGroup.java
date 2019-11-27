package com.example.adminpoker.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adminpoker.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentAddGroup extends Fragment {

    Button bt_add;
    EditText et_groupid;
    public  FragmentAddGroup(){};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_group, container, false);

        bt_add = view.findViewById(R.id.bt_add_group);
        et_groupid = view.findViewById(R.id.et_groupid);

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_groupid.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(),"Please Insert a Group Id!", Toast.LENGTH_SHORT).show();
                }

                else {

                    FragmentAddQuestion addQuestion = new FragmentAddQuestion();
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.container, addQuestion);
                    fr.addToBackStack(null);
                    fr.commit();


                }


            }
        });


        return view;

    }


}
