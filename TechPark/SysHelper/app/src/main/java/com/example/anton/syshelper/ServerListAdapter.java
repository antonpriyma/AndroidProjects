package com.example.anton.syshelper;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ServerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ServerInfo> data;
    private final String NAME="Name: ";
    private final String HOST="Host: ";

    public ServerListAdapter(ArrayList<ServerInfo> data){
        this.data=data;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.server_item,viewGroup,false);
        return new ServerListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ServerListViewHolder)viewHolder).title.setText(data.get(i).getTitle());
        ((ServerListViewHolder)viewHolder).name.setText(NAME+data.get(i).getName());
        ((ServerListViewHolder)viewHolder).host.setText(HOST+data.get(i).getHost()+":"+data.get(i).getPort());
        ((ServerListViewHolder)viewHolder).passwordString=data.get(i).getPassword();
        ((ServerListViewHolder)viewHolder).portString=data.get(i).getPort();
        ((ServerListViewHolder)viewHolder).hostString=data.get(i).getHost();
        ((ServerListViewHolder)viewHolder).nameString=data.get(i).getName();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ServerListViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView title;
        TextView name;
        TextView host;
        String passwordString;
        String hostString;
        String portString;
        String nameString;

        View.OnClickListener onClickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.server_card_view:
                        Intent intent=new Intent(view.getContext(),ServerInfoActivity.class);
                        intent.putExtra("HOST",hostString);
                        intent.putExtra("PORT",portString);
                        intent.putExtra("NAME",nameString);
                        intent.putExtra("PASSWORD",passwordString);
                        view.getContext().startActivity(intent);
                        break;
                }
            }
        };

        public ServerListViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.server_title);
            cardView=itemView.findViewById(R.id.server_card_view);
            name=itemView.findViewById(R.id.server_name);
            host=itemView.findViewById(R.id.server_host);
            cardView.setOnClickListener(onClickListener);



        }



    }
}
