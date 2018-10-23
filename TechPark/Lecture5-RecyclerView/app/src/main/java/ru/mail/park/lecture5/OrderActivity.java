package ru.mail.park.lecture5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private OrderLinearAdapter adapter;
    protected int totalPrice=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle(R.string.order_title);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewOrder);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter=new OrderLinearAdapter(buildItemList(Cheeses.getShoppingCart()),totalPrice);


        recyclerView.setAdapter(adapter);



    }

    private List<OrderItem> buildItemList(LinkedHashSet<Cheese> goods){
        List<OrderItem> orderItems = new ArrayList<>();


        for(Cheese c: goods){
            orderItems.add(new OrderCheese(c.getPrice(),c.getTitle(),c.getImageResId()));
            totalPrice+=c.getPrice();
        }
        orderItems.add(new OrderTotal(totalPrice));


        return orderItems;
    }
}
