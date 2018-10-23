package com.example.anton.headfirst_6_lists;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DrinkCategoryActivity extends ListActivity{

    @Override
    public void onListItemClick(ListView listView,
                                View itemView,
                                int position,
                                long id){
        Intent intent = new Intent(DrinkCategoryActivity.this,DrinkActivity.class);{
            intent.putExtra(DrinkActivity.EXTRA_DRINK,(int)id);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<Drink> listAdaper=new ArrayAdapter<Drink>(this,
                android.R.layout.simple_list_item_1,
                Drink.getDrinks());
        ListView listView=getListView();
        listView.setAdapter(listAdaper);
    }
}
