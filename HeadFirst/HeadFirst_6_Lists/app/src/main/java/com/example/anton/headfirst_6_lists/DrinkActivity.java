package com.example.anton.headfirst_6_lists;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        try{
            SQLiteOpenHelper starbuzzDatabaseHelper= new StarbuzzDatabaseHelper(this);
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor= db.query("DRINK",
                    new String[]{"NAME","DESCRIPTION","IMAGE_RESOURCE_ID"},
                    "_id=?",
                    new String[]{Integer.toString(drinkNo)},
                    null,null,null);

            if (cursor.moveToFirst()) {
                Drink drink = Drink.getDrinks()[drinkNo];
                ImageView photo = (ImageView) findViewById(R.id.photo);
                TextView name = (TextView) findViewById(R.id.name);
                TextView discription = (TextView) findViewById(R.id.description);
                name.setText(cursor.getString(0));
                discription.setText(cursor.getString(1));
                photo.setImageResource(cursor.getInt(2));
            }
            cursor.close();
            db.close();
            }catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        }



    }

