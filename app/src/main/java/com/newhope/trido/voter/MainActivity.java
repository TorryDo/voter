package com.newhope.trido.voter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText edit_acc,edit_pass;
    private Button btdn,btdk,btfb;
    private FirebaseAuth auth;
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    public static String TAIKHOAN = "taikhoan";
    public static String MATKHAU = "matkahu";
//    private FirebaseUser user;


    /**
     *
     * Họ tên : Nguyễn Đỗ Trí
     * Mã Sinh Viên : N20DCCN155
     * Mã Lớp : D20CQCN02-N
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();
        auth = FirebaseAuth.getInstance();
        preferences = getPreferences(Context.MODE_PRIVATE);

        edit_acc.setText("abcdef@gmail.com"); //
        edit_pass.setText("123456"); //123456

//        if(preferences.getString(TAIKHOAN,"").equals("xcmdsjfy")){
//
//        }else {
//            auth.signInWithEmailAndPassword(
//                    preferences.getString(TAIKHOAN,""),
//                    preferences.getString(MATKHAU,"")
//            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    Intent i = new Intent(MainActivity.this,Main_home.class);
//                    startActivity(i);
//                    finish();
//                }
//            });
//        }

        btdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangnhap(edit_acc.getText().toString(),edit_pass.getText().toString());
             editor = preferences.edit();
             editor.putString(TAIKHOAN,edit_acc.getText().toString());
             editor.putString(MATKHAU,edit_pass.getText().toString());
             editor.apply();
            }
        });
        btdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main_dangki.class));
            }
        });

    }
    private void dangnhap(String tk,String mk){
        auth.signInWithEmailAndPassword(tk,mk).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(MainActivity.this,Main_home.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    private void anhxa() {
        edit_acc = findViewById(R.id.edit_taikhoan);
        edit_pass = findViewById(R.id.edit_pass);
        btdk = findViewById(R.id.btdangki);
        btdn = findViewById(R.id.btdangnhap);
        btfb = findViewById(R.id.bt_fb);
    }
}
