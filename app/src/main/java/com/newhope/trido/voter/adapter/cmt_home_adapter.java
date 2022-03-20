package com.newhope.trido.voter.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.newhope.trido.voter.model.cmt_home;
import com.newhope.trido.voter.activity.Main_comment;
import com.newhope.trido.voter.activity.Main_small_comment;
import com.newhope.trido.voter.R;

import java.util.List;

public class cmt_home_adapter extends RecyclerView.Adapter<cmt_home_adapter.ViewHolder> {

    private Context context;
    private List<cmt_home> cmt_homeList;

    public cmt_home_adapter(Context context, List<cmt_home> cmt_homeList) {
        this.context = context;
        this.cmt_homeList = cmt_homeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cmt_home,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_item_cmt.setText(cmt_homeList.get(position).getMtext());
        holder.txtkey_item_cmt.setText(cmt_homeList.get(position).getMkey());
    }

    @Override
    public int getItemCount() {
        return cmt_homeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_item_cmt,txtkey_item_cmt;
        private TextView txtcmt_item_cmt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_item_cmt = itemView.findViewById(R.id.txt1_item_cmt);
            txtkey_item_cmt = itemView.findViewById(R.id.txtkey_item_cmt);
            txtcmt_item_cmt = itemView.findViewById(R.id.txtcmt_item_cmt);

            txt_item_cmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,txtkey_item_cmt.getText().toString(),Toast.LENGTH_LONG).show();
                }
            });
            txtcmt_item_cmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, Main_small_comment.class);
                    i.putExtra(Main_comment.KEAL,txtkey_item_cmt.getText().toString());
                    context.startActivity(i);

                }
            });
        }
    }

}
