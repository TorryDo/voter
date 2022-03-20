package com.newhope.trido.voter.activity;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.newhope.trido.voter.R;
import com.newhope.trido.voter.adapter.small_cmt_home_adapter;
import com.newhope.trido.voter.model.small_cmt;

import java.util.ArrayList;
import java.util.Calendar;

public class Main_small_comment extends AppCompatActivity {

    public static DatabaseReference reference;
    private TextView txt_stext,txt_slike,txt_stime,txt_scmt;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private EditText edit_scmt;
    private Button bt_add_scmt;
    private small_cmt_home_adapter adapter;
    private ArrayList<small_cmt> smallCmtArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_small_comment);
        txt_stext = findViewById(R.id.txt_item_scmt);
        txt_slike = findViewById(R.id.txtlike_item_scmt);
        txt_stime = findViewById(R.id.txtTime_item_scmt);
        txt_scmt = findViewById(R.id.txtcmt_item_scmt);
        recyclerView = findViewById(R.id.recycler_scmt);
        edit_scmt = findViewById(R.id.edit_scmt);
        bt_add_scmt = findViewById(R.id.btadd_scmt);

        recyclerView.setHasFixedSize(true);

        Intent intent = getIntent();
        if(intent != null){
            String key = intent.getStringExtra(Main_comment.KEAL);

            reference = Main_comment.references.child(Main_comment.keys).child("mcomment").child(key);

            linearLayoutManager = new LinearLayoutManager(Main_small_comment.this,RecyclerView.VERTICAL,false);

            smallCmtArrayList = new ArrayList<>();
            reference.child("mrep").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    if(dataSnapshot.exists()){
                        smallCmtArrayList.add(new small_cmt(
                                dataSnapshot.child("mstraloi").getValue().toString())
                        );
                        adapter = new small_cmt_home_adapter(Main_small_comment.this,smallCmtArrayList);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                    }
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

            // setText cho item đã click
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    txt_stext.setText(dataSnapshot.child("mtext").getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            bt_add_scmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edit_scmt.getText().toString().length()<2){
                        Toast.makeText(Main_small_comment.this,"bình luận quá ngắn",Toast.LENGTH_LONG).show();
                    }else {
                        reference.child("mrep").push().setValue(new small_cmt(
                                Main_home.auth_mhome.getUid(),
                                Calendar.getInstance().getTime().toString(),
                                null,
                                edit_scmt.getText().toString()));

                    }
                    edit_scmt.setText("");
                }
            });

        }

    }
}
