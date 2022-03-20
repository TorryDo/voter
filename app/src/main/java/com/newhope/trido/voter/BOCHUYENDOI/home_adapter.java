package com.newhope.trido.voter.BOCHUYENDOI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.newhope.trido.voter.DOITUONG.islike;
import com.newhope.trido.voter.DOITUONG.topic_home;
import com.newhope.trido.voter.Main_comment;
import com.newhope.trido.voter.Main_home;
import com.newhope.trido.voter.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class home_adapter extends RecyclerView.Adapter<home_adapter.ViewHolder> {

    private List<topic_home> homeList;
    private Context context;
    private String KEYWORDS = "keyws";

    public home_adapter(List<topic_home> homeList, Context context) {
        this.homeList = homeList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.circleImageView.setImageResource(R.drawable.ic_launcher_background);
        String links = homeList.get(position).getMpicture();
        new loadImageFromURL(holder).execute(links);
        holder.txtTen.setText("teen");
        holder.txtKeys.setText(homeList.get(position).getKeys());

        // code cập nhật số like
        Main_home.reference.child(holder.txtKeys.getText().toString()).child("mlike").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final int digit = (int) dataSnapshot.getChildrenCount();
                final ArrayList<String> stringArrayList = new ArrayList<>();
                if(dataSnapshot.exists()){
                Main_home.reference.child(holder.txtKeys.getText().toString()).child("mlike").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        stringArrayList.add(dataSnapshot.getValue().toString());
                        if(stringArrayList.size()==digit){
                            for(String adu : stringArrayList){
                                if(adu.equals(Main_home.auth_mhome.getUid())){
                                    holder.txtlike.setText("liked : "+digit+"");
                                }else {
                                    holder.txtlike.setText("like : "+digit+"");
                                }
                            }
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

                }else {
                    holder.txtlike.setText("like : 0");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // update comment
        Main_home.reference.child(holder.txtKeys.getText().toString()).child("mcomment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                holder.txtcomment.setText("comment : "+dataSnapshot.getChildrenCount()+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private class loadImageFromURL extends AsyncTask<String,Void,Bitmap>{
        Bitmap bitmapp=null;
        ViewHolder holder;
        public loadImageFromURL(ViewHolder holder) {
            this.holder = holder;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmapp = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmapp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmapt) {
            super.onPostExecute(bitmapt);
            holder.imageView.setImageBitmap(bitmapt);
        }
    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView circleImageView;
        private ImageView imageView;
        private TextView txtlike,txtdislike,txtcomment,txtsave;
        private TextView txtTen,txtKeys;
        private DatabaseReference references;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.circle_item_home);
            imageView = itemView.findViewById(R.id.image_item_home);
            txtTen = itemView.findViewById(R.id.txtTen_item_home);
            txtlike = itemView.findViewById(R.id.txtlike_item_home);
            txtcomment = itemView.findViewById(R.id.txtcomment_item_home);
            txtsave = itemView.findViewById(R.id.txtsave_item_home);
            txtKeys = itemView.findViewById(R.id.txtKeys_item_home);

            txtsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"function has not been updated yet",Toast.LENGTH_SHORT).show();
                }
            });
            circleImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"function has not been updated yet",Toast.LENGTH_SHORT).show();
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"function has not been updated yet",Toast.LENGTH_SHORT).show();
                }
            });

            // code khi like
            txtlike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final ArrayList<islike> strings = new ArrayList<>();
                    Main_home.reference.child(txtKeys.getText().toString()).child("mlike").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final int mcounts = (int) dataSnapshot.getChildrenCount();

                            if(dataSnapshot.hasChildren()) {
                                // like khi đã có ít nhất 1 người like
                                Main_home.reference.child(txtKeys.getText().toString())
                                        .child("mlike")
                                        .addChildEventListener(new ChildEventListener() {

                                            @Override
                                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                                if (dataSnapshot.exists()) {
                                                    String me = Main_home.auth_mhome.getUid();
                                                    String mykey = dataSnapshot.getKey();
                                                    String myvalue = dataSnapshot.getValue().toString();
                                                    strings.add(new islike(mykey, myvalue));

                                                    checkitpls(strings, Main_home.reference.child(txtKeys.getText().toString())
                                                            .child("mlike"), Main_home.auth_mhome, me, mcounts);
                                                }

                                            }
                                            private void checkitpls(List<islike> islikes,
                                                                    DatabaseReference reference,
                                                                    FirebaseAuth auth,
                                                                    String me,
                                                                    int so ) {
                                                if (so == islikes.size()) {
                                                    boolean b = false;
                                                    String khoa = null;

                                                    for (islike iss : islikes) {
                                                        if (iss.getValue().equals(me)) {
                                                            b = true;
                                                            khoa = iss.getKey();
                                                            break;
                                                        }
                                                    }
                                                    if (b == false) {
                                                        reference.push().setValue(me);
                                                        txtlike.setText("liked : " +so+1+"");
                                                    } else {
                                                        reference.child(khoa).removeValue();
                                                        txtlike.setText("like : " + (so-1)+"");
                                                    }
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
                            }else if(!dataSnapshot.hasChildren()) {
                                if(mcounts==0){
                                    Main_home.reference.child(txtKeys.getText().toString())
                                            .child("mlike").push().setValue(Main_home.auth_mhome.getUid());
                                    txtlike.setText("like : 1");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });

                }
            });
            // code khi comment
            txtcomment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context,Main_comment.class);
                    i.putExtra("thisvalue",txtKeys.getText().toString());
                    context.startActivity(i);

                }
            });


        }

    }

}
