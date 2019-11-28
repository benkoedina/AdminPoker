package com.example.adminpoker.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adminpoker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class LoginFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_login, container, false);
        final EditText et_name = view.findViewById(R.id.et_name);
        final EditText et_password = view.findViewById(R.id.et_password);
        Button bt_login = view.findViewById(R.id.bt_login);

        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_name.getText().toString().isEmpty() || (et_password.toString().isEmpty())) {

                    Toast.makeText(getContext(), "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
                } else {

                    final String password=  et_password.getText().toString();
                    final String name = et_name.getText().toString();

                    if(password.equals("12345") && name.equals("benkoedina"))
                    {
                         MainFragment mainFragment = new MainFragment();
                        FragmentTransaction fr = getFragmentManager().beginTransaction();
                        fr.replace(R.id.container, mainFragment);
                        fr.addToBackStack(null);
                        fr.commit();
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Passwords or User Name is INCORRECT!", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });

        return view;

    }
}