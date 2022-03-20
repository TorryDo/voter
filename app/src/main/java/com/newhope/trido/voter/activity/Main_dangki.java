package com.newhope.trido.voter.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.newhope.trido.voter.R;

public class Main_dangki extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText edit_tk,edit_pass,edit_ten;
    private Button bt_dangki_dk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dangki);
        auth = FirebaseAuth.getInstance();
        MainActivity.preferences = getPreferences(Context.MODE_PRIVATE);

        init();


        bt_dangki_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String taikhoan = edit_tk.getText().toString();
                final String matkhau = edit_pass.getText().toString();
                if (TextUtils.isEmpty(taikhoan) && TextUtils.isEmpty(matkhau)){
                    Toast.makeText(Main_dangki.this,"điền gmail + pass vô anh ơi!",Toast.LENGTH_LONG).show();
            }else {

                    auth.createUserWithEmailAndPassword(taikhoan, matkhau)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(Main_dangki.this, Main_home.class));

                                        MainActivity.editor = MainActivity.preferences.edit();
                                        MainActivity.editor.putString(MainActivity.TAIKHOAN,taikhoan );
                                        MainActivity.editor.putString(MainActivity.MATKHAU, matkhau);
                                        MainActivity.editor.apply();
                                        finish();
                                    }
                                }
                            });
                }
            }
        });

    }

    private void init() {
        bt_dangki_dk = findViewById(R.id.bt_dangki_dki);
        edit_tk = findViewById(R.id.edit_taikhoan_dk);
        edit_pass = findViewById(R.id.edit_pass_dk);
        edit_ten = findViewById(R.id.edit_ten_dk);
    }
}
