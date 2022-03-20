package com.newhope.trido.voter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.newhope.trido.voter.DOITUONG.topic_home;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class add_topic extends AppCompatActivity {

    private DatabaseReference reference;
    private Button bt_add;
    private static int FOLDER = 69;
    private ImageView imageView_add;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageReference;
    private StorageMetadata metadata;

    private FirebaseDatabase firebaseDatabase;
    private UserProfileChangeRequest userProfileChangeRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_topic);
        reference = FirebaseDatabase.getInstance().getReference("homeTopic");
        storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://voter-voter.appspot.com");

        bt_add = findViewById(R.id.bt_add);
        imageView_add = findViewById(R.id.img_add);

        imageView_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,FOLDER);
            }
        });

        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();

                imageView_add.setDrawingCacheEnabled(true);
                imageView_add.buildDrawingCache();
//                Bitmap bitmap = ((BitmapDrawable) imageView_add.getDrawable()).getBitmap();
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//                byte[] data = baos.toByteArray();

//                final UploadTask uploadTask = storageReference.child("homePicture/"+"image"+calendar.getTimeInMillis()+".png").putBytes(data);
//
//                uploadTask.addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
//                        Toast.makeText(add_topic.this,"failed",Toast.LENGTH_LONG).show();
//                    }
//                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {
//                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
//
//                        Toast.makeText(add_topic.this,"xong rồi ông giáo ạ",Toast.LENGTH_LONG).show();
//
//
//                        finish();
//                    }
//                });
                final StorageReference move = storageReference.child("homePicture/"+"picture" + calendar.getTimeInMillis());
                move.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        move.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                reference.child("topic").push().setValue(new topic_home(uri.toString(),null));

                                finish();
                            }
                        });
                    }
                });
            }
        });

    }
    Uri uri;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == FOLDER && resultCode == RESULT_OK && data!= null){
             uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView_add.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
