package com.newhope.trido.voter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.newhope.trido.voter.BOCHUYENDOI.cmt_home_adapter;
import com.newhope.trido.voter.DOITUONG.cmt_home;

import java.util.ArrayList;
import java.util.Calendar;

public class Main_comment extends AppCompatActivity {

    private RecyclerView recycler;
    private cmt_home_adapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private EditText edit_cmt;
    private Button btadd_cmt;
    public static DatabaseReference references;
    public static String BUNDEAL = "bundeal";
    public static String KEAL = "keal";
    public static String KEALS = "keals";
    public static String keys = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_comment);
        references = FirebaseDatabase.getInstance().getReference("homeTopic").child("topic");
        recycler = findViewById(R.id.recycler_cmt);
        btadd_cmt = findViewById(R.id.btadd_cmt);
        edit_cmt = findViewById(R.id.edit1_cmt);


        Intent i = getIntent();
        if(i!=null){
            // tv là key của topic
            final String tv = i.getStringExtra("thisvalue");
            keys = tv;

            linearLayoutManager = new LinearLayoutManager(Main_comment.this,RecyclerView.VERTICAL,false);
            final ArrayList<cmt_home> cmt_homeArrayList = new ArrayList<>();
            references.child(tv).child("mcomment").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    cmt_homeArrayList.add(
                            new cmt_home(
                            dataSnapshot.getKey(),
                            dataSnapshot.child("mtext").getValue().toString(),
                            Main_home.auth_mhome.getUid())
                    );
                    adapter = new cmt_home_adapter(Main_comment.this,cmt_homeArrayList);
                    recycler.setLayoutManager(linearLayoutManager);
                    recycler.setAdapter(adapter);
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

            btadd_cmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    references.child(tv).child("mcomment").push().setValue(new cmt_home(Main_home.auth_mhome.getUid()
                            ,edit_cmt.getText().toString()
                            , Calendar.getInstance().getTime().toString()
                            ,null
                            ,null
                    ));
                    edit_cmt.setText("");
                }
            });


        }

    }
}
