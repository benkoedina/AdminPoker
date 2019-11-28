package com.example.adminpoker.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adminpoker.Adapter.QuestionListAdapter;
import com.example.adminpoker.Interfaces.onClickInterface;
import com.example.adminpoker.Models.Group;
import com.example.adminpoker.Models.Question;
import com.example.adminpoker.Models.User;
import com.example.adminpoker.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionListFragment extends Fragment {

    QuestionListAdapter adapter;
    RecyclerView recyclerView;
    public onClickInterface onclickInterface;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public QuestionListFragment(){};

    public void Oncreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_questionlist, container, false);

        Bundle bundle = this.getArguments();
        final  String groupid = bundle.getString("groupid");

        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Group g = dataSnapshot.child(groupid).getValue(Group.class);
                final ArrayList<Question> q1 = g.getQuestions();

                onclickInterface = new onClickInterface() {
                    @Override
                    public void setClick(int pos) {

                        AnswerListFragment Afragment = new AnswerListFragment();

                        final Bundle bundle = new Bundle();
                        if (q1.get(pos).getUsers()!=null) {
                            ArrayList<User> users = q1.get(pos).getUsers();
                            ArrayList<String> userAnswers = new ArrayList<>();

                            for (User u : users) {
                                String value = u.getName() + ": " + u.getAnswer();
                                userAnswers.add(value);
                            }
                            bundle.putStringArrayList("answers", userAnswers);

                        }
                        else
                        {
                            ArrayList<String> userAnswers = new ArrayList<>();
                            bundle.putStringArrayList("answers", userAnswers);
                        }
                        Afragment.setArguments(bundle);

                        Fragmentchange(Afragment);

                    }
                };

                recyclerView = (RecyclerView)view.findViewById(R.id.questionrecyclerView);
                recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
                adapter = new QuestionListAdapter(q1,getContext(), onclickInterface);

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        return  view;
    }

    public void Fragmentchange (Fragment fragment)
    {

        FragmentTransaction fr = getFragmentManager().beginTransaction();
        fr.replace(R.id.container, fragment);
        fr.addToBackStack(null);
        fr.commit();
    }
}