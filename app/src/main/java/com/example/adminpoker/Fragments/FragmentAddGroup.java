package com.example.adminpoker.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.adminpoker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentAddGroup extends Fragment {


    Button bt_add;
    EditText et_groupid;
    public  FragmentAddGroup(){};
    static String status;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_group, container, false);


        bt_add = view.findViewById(R.id.bt_add_group);
        et_groupid = view.findViewById(R.id.et_groupid);

        RadioGroup radioGroup = view.findViewById(R.id.groupradio);
        if (radioGroup != null) {
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    status= (R.id.activ == checkedId) ? "true" : "false";
                    Toast.makeText(getContext(), status, Toast.LENGTH_SHORT).show();
                }
            });
        }


        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_groupid.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(),"Please Insert a Group Id!", Toast.LENGTH_SHORT).show();
                }

                else {

                    Boolean s;
                    Bundle bundle = new Bundle();
                    if(status.equals("true"))
                    {
                        s = true;
                    }
                    else
                    {
                        s=false;
                    }
                    bundle.putBoolean("status",s);
                    bundle.putString("group_id",et_groupid.getText().toString());
                    FragmentAddQuestion addQuestion = new FragmentAddQuestion();
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    addQuestion.setArguments(bundle);
                    fr.replace(R.id.container, addQuestion);
                    fr.addToBackStack(null);
                    fr.commit();


                }


            }
        });


        return view;

    }


}
