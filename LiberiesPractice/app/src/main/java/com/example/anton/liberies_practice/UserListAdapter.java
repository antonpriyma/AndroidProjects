package com.example.anton.liberies_practice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends BaseAdapter {
    private List<UserInfo> list;
    private LayoutInflater layoutInflater;
    private Context context;

    public UserListAdapter(Context aContext,  List<UserInfo> listData) {
        this.context = aContext;
        this.list = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=layoutInflater.inflate(R.layout.list_item_layout,null);
        ViewHolder holder=new ViewHolder();
        holder.ageTextView=view.findViewById(R.id.age);
        holder.genderTextView=view.findViewById(R.id.gender);
        holder.nameTextView=view.findViewById(R.id.name);
        holder.relationTextView=view.findViewById(R.id.relation_status);
        holder.weightTextView=view.findViewById(R.id.weigth);


        holder.ageTextView.setText("Age: "+list.get(i).getAge());
        holder.nameTextView.setText("Name: "+list.get(i).getFullName());
        holder.weightTextView.setText("Weight: "+list.get(i).getWeigh());
        holder.relationTextView.setText("Married: "+list.get(i).getRelation());
        holder.genderTextView.setText("Gender: "+list.get(i).getGender());


        return view;//Возвращает созданный объект списка
    }


    static class ViewHolder {
        TextView ageTextView;
        TextView nameTextView;
        TextView weightTextView;
        TextView relationTextView;
        TextView genderTextView;
    }
}
