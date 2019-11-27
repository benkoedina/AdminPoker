package com.example.adminpoker.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.adminpoker.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentAddQuestion extends Fragment {

    EditText et_question;
    EditText et_status;

    public FragmentAddQuestion(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_question, container, false);

        et_question = view.findViewById(R.id.et_question);
        et_status = view.findViewById(R.id.et_status);


        return view;
    }
}
