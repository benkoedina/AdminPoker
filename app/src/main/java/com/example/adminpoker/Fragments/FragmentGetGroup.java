package com.example.adminpoker.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adminpoker.Models.Group;
import com.example.adminpoker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentGetGroup extends Fragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public FragmentGetGroup(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    //FragmentGetGroup => lekerjuk az adott group_id-t amelyiket megakarja vizsgalni
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        View view = inflater.inflate(R.layout.fragment_get_group, container, false);
        final EditText et_groupId = view.findViewById(R.id.et_groupid);
        Button bt_ok = view.findViewById(R.id.bt_ok);

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_groupId.getText().toString().isEmpty()) //ellenoirzzuk hogy helyes-e
                {
                    Toast.makeText(getContext(),"Insert please", Toast.LENGTH_SHORT).show();
                }
                else {
                   final String id = et_groupId.getText().toString();
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Group group = dataSnapshot.child(id).getValue(Group.class); //adott group_idra lekerjuk a group-ot

                            if(group==null)
                            {
                                Toast.makeText(getContext(),"This is not a valid group id!", Toast.LENGTH_SHORT).show();
                                //ha nincs ilyen akkor szolunk
                            }
                            else
                            {
                                //tovabb adjuk a kovi fragmentnek a group_id-t
                                Bundle bundle = new Bundle();
                                bundle.putString("groupid",id);
                                QuestionListFragment qf = new QuestionListFragment();
                                qf.setArguments(bundle);
                                FragmentTransaction fr = getFragmentManager().beginTransaction();
                                fr.replace(R.id.container, qf);
                                fr.addToBackStack(null);
                                fr.commit();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
        return view;
    }
}
