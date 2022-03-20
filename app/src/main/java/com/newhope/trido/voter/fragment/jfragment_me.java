package com.newhope.trido.voter.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.newhope.trido.voter.model.userProfile;
import com.newhope.trido.voter.activity.Main_home;
import com.newhope.trido.voter.R;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class jfragment_me extends Fragment {
    private ImageView background;
    private EditText edit_nickname,edit_mail,edit_sdt,edit_realname,edit_place,edit_sex;
    private TextView txt_nickname,txt_mail,txt_sdt,txt_realname,txt_place,txt_sex;
    private Button bt_update;
    private CircleImageView avatar;
    private DatabaseReference reference;
    private static int FOLD = 48;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_me,container,false);
        reference = FirebaseDatabase.getInstance().getReference("Me");

        anhxa(v);

        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                reference.child(Main_home.auth_mhome.getUid())
                        .setValue(new userProfile(edit_nickname.getText().toString(),null)).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(),"failed",Toast.LENGTH_LONG).show();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(),"succesful",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,FOLD);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==FOLD && resultCode == RESULT_OK && data!=null){{
            Toast.makeText(getContext(),"successful",Toast.LENGTH_LONG).show();
        }}

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void anhxa(View v) {
        background = v.findViewById(R.id.img_background_me);
        edit_nickname = v.findViewById(R.id.edit_nickname_me);
        edit_mail = v.findViewById(R.id.edit_mail_me);
        edit_sdt = v.findViewById(R.id.edit_sdt_me);
        edit_realname = v.findViewById(R.id.edit_realname_me);
        edit_place = v.findViewById(R.id.edit_place_me);
        edit_sex = v.findViewById(R.id.edit_sex_me);

        txt_nickname = v.findViewById(R.id.txt_nickname_me);
        txt_mail = v.findViewById(R.id.txt_mail_me);
        txt_sdt = v.findViewById(R.id.txt_sdt_me);
        txt_realname = v.findViewById(R.id.txt_realname_me);
        txt_place = v.findViewById(R.id.txt_place_me);
        txt_sex = v.findViewById(R.id.txt_sex_me);

        bt_update = v.findViewById(R.id.bt_update);
        avatar = v.findViewById(R.id.avatar_me);

    }
}
