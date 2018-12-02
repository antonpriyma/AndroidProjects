package com.example.anton.bikefit.dto;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.anton.bikefit.R;

import java.util.List;

public class RemindListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((RemindViewHolder)viewHolder).title.setText(data.get(i).getTitle());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    static class RemindViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView title;
        Button deleteButton;

        public RemindViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            cardView=(CardView)itemView.findViewById(R.id.card_view);
            deleteButton=itemView.findViewById(R.id.delete_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });


        }
    }

    public List<RemindDTO> getData() {
        return data;
    }
}
