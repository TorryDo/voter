package com.newhope.trido.voter.line_12345;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.newhope.trido.voter.BOCHUYENDOI.home_adapter;
import com.newhope.trido.voter.DOITUONG.topic_home;
import com.newhope.trido.voter.Main_home;
import com.newhope.trido.voter.R;
import com.newhope.trido.voter.add_topic;

import java.util.ArrayList;

public class jfragment_home extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private home_adapter homeAdapter;
    private ArrayList<topic_home> topic_homes_arraylist;
    private Context context;
    private Button bt_upload;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference referencee;
    private UserProfileChangeRequest userProfileChangeRequest;

    public jfragment_home(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        referencee = FirebaseDatabase.getInstance().getReference("homeTopic");

        recyclerView = view.findViewById(R.id.recycler_home);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        topic_homes_arraylist = new ArrayList<>();

        bt_upload = view.findViewById(R.id.bt_upload_activity_home);
        bt_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, add_topic.class));
            }
        });

        referencee.child("topic").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                topic_homes_arraylist.add(new topic_home(dataSnapshot.child("mpicture").getValue().toString(),dataSnapshot.getKey()));
                homeAdapter = new home_adapter(topic_homes_arraylist,context);
                recyclerView.setAdapter(homeAdapter);

            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view ;

    }
}
