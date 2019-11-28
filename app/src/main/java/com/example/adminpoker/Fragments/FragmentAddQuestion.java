package com.example.adminpoker.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.adminpoker.Models.Group;
import com.example.adminpoker.Models.Question;
import com.example.adminpoker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentAddQuestion extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private String status1,status2,status3,status4;

    public FragmentAddQuestion(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_question, container, false);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        Button bt_add = view.findViewById(R.id.bt_add_question);
        final EditText et_q1 = view.findViewById(R.id.et_question1);
        final EditText et_q2 = view.findViewById(R.id.et_question2);
        final EditText et_q3 = view.findViewById(R.id.et_question3);
        final EditText et_q4 = view.findViewById(R.id.et_question4);



        RadioGroup radioGroup1 = view.findViewById(R.id.groupradio1);
        if (radioGroup1 != null) {
            radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    status1 = (R.id.activ1 == checkedId) ? "active" : "inactive";
                    Toast.makeText(getContext(), status1, Toast.LENGTH_SHORT).show();
                }
            });
        }
        RadioGroup radioGroup2 = view.findViewById(R.id.groupradio2);
        if (radioGroup2 != null) {
            radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    status2= (R.id.activ2 == checkedId) ? "active" : "inactive";
                    Toast.makeText(getContext(), status2, Toast.LENGTH_SHORT).show();
                }
            });
        }
        RadioGroup radioGroup3 = view.findViewById(R.id.groupradio3);
        if (radioGroup3 != null) {
            radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    status3 = (R.id.activ3 == checkedId) ? "active" : "inactive";
                    Toast.makeText(getContext(), status3, Toast.LENGTH_SHORT).show();
                }
            });
        }
        RadioGroup radioGroup4 = view.findViewById(R.id.groupradio4);
        if (radioGroup4 != null) {
            radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    status4= (R.id.activ4 == checkedId) ? "active" : "inactive";
                    Toast.makeText(getContext(), status4, Toast.LENGTH_SHORT).show();
                }
            });
        }
        Bundle bundle = this.getArguments();
        final String group_id = bundle.getString("group_id");
        final Boolean group_status = bundle.getBoolean("status");
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q1 = et_q1.getText().toString();
                String q2 = et_q2.getText().toString();
                String q3 = et_q3.getText().toString();
                String q4 = et_q4.getText().toString();

                ArrayList<Question> questions = new ArrayList<>();

                Question question1 = new Question(0,q1,status1);
                Question question2 = new Question(1,q2,status2);
                Question question3 = new Question(2,q3,status3);
                Question question4 = new Question(3,q4,status4);

                questions.add(question1);
                questions.add(question2);
                questions.add(question3);
                questions.add(question4);

                Group group = new Group(group_id,group_status,questions);
                databaseReference.child(group.getId()).setValue(group);

                Toast.makeText(getContext(),"Added Successfully",Toast.LENGTH_LONG).show();


                Log.d("GROUP", group.toString());
            }
        });





        return view;
    }
}
