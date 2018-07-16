package com.example.anton.remindme.dto;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anton.remindme.R;

import java.util.List;

public class RemindListAdapter extends RecyclerView.Adapter<RemindListAdapter.RemindViewHolder> {
    private List<RemindDTO> data;


    public RemindListAdapter(List<RemindDTO> data){
        this.data=data;
    }

    @NonNull
    @Override
    public RemindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.remind_item,parent,false);
        return new RemindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RemindViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitile());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class RemindViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView title;

        public RemindViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            cardView=(CardView)itemView.findViewById(R.id.card_view);
        }
    }
}
