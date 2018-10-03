package com.example.anton.headfirst_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class FindBeerActivity extends AppCompatActivity {
    private BeerExpert BeerExpert= new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);


    }


    public void onClickFindBeer(View view){
        TextView brands = (TextView)findViewById(R.id.TextView);
        Spinner color = (Spinner)findViewById(R.id.Spinner);
        String colorString = String.valueOf(color.getSelectedItem());
        List<String> beerBrands = BeerExpert.getBrands(colorString);
        StringBuilder resultBrands= new StringBuilder();
        for (String s: beerBrands){
            resultBrands.append(s).append("\n");
        }

        brands.setText(resultBrands);


    }



}
