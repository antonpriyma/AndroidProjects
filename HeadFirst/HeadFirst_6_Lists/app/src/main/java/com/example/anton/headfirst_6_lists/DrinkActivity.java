package com.example.anton.headfirst_6_lists;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINK="drink";
    public final int LATTE=1;
    public final int CAPPUCCINO =2;
    public final int FILTER=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        int drinkNo=getIntent().getIntExtra(EXTRA_DRINK,-1);
        Drink drink=Drink.getDrinks()[drinkNo];
        ImageView photo = (ImageView)findViewById(R.id.photo);
        TextView name = (TextView)findViewById(R.id.name);
        TextView discription= (TextView)findViewById(R.id.description);
        name.setText(drink.getName());
        discription.setText(drink.getDiscription());
        photo.setImageResource(drink.getImgResId());


    }
}
