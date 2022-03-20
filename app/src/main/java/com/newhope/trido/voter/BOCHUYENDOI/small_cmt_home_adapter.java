package com.newhope.trido.voter.BOCHUYENDOI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.newhope.trido.voter.DOITUONG.small_cmt;
import com.newhope.trido.voter.R;

import java.util.List;

public class small_cmt_home_adapter extends RecyclerView.Adapter<small_cmt_home_adapter.ViewHolder> {

    private Context context;
    private List<small_cmt> small_cmt_homeList;

    public small_cmt_home_adapter(Context context, List<small_cmt> small_cmt_homeList) {
        this.context = context;
        this.small_cmt_homeList = small_cmt_homeList;
    }

    @NonNull
    @Override
    public small_cmt_home_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_small_cmt_home,parent,false);
        return new small_cmt_home_adapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull small_cmt_home_adapter.ViewHolder holder, int position) {
        holder.txt_item_scmt.setText(small_cmt_homeList.get(position).getMstraloi());
    }

    @Override
    public int getItemCount() {
        return small_cmt_homeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txt_item_scmt,txtkey_item_scmt;
        private TextView txtcmt_item_scmt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_item_scmt = itemView.findViewById(R.id.txt1_item_scmt);
            txtkey_item_scmt = itemView.findViewById(R.id.txtkey_item_scmt);
            txtcmt_item_scmt = itemView.findViewById(R.id.txtcmt_item_scmt);


        }
    }

}
