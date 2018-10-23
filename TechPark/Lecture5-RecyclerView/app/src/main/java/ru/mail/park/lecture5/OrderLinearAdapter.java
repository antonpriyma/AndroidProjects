package ru.mail.park.lecture5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;

public class OrderLinearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static final String TAG = "Adapter";
    private List<OrderItem> itemsList;
    private int totalPrice;


    static final int ITEM_TOTAL = R.layout.order_total;
    static final int ITEM_CHEESE_ORDER_CARD = R.layout.order_item;
    static final int INVALID_TYPE = -1;
    static final int TOTAL_PRICE_POZITION = 0;



    public void Remove(String title){
        int i=-1;
        for (OrderItem c: itemsList) {
            i++;
            if (c.getType()== OrderItem.OrderType.CHEESE)
            if (((OrderCheese)c).getTitle().equals(title)){
                totalPrice-=c.price;
                itemsList.remove(i);

                break;
            }
        }

    }

    public List<OrderItem> getItemsList() {
        return itemsList;
    }

    OrderLinearAdapter(List<OrderItem> items, int totalPrice){
        itemsList=items;
        this.totalPrice=totalPrice;
    }

    public void setItems(Collection<OrderItem> items){
        itemsList.addAll(items);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();


        switch (viewType) {
            case ITEM_TOTAL:
                View totalView = LayoutInflater.from(context).inflate(ITEM_TOTAL, parent, false);
                return new OrderLinearAdapter.OrderTotalViewHolder(totalView);

            case ITEM_CHEESE_ORDER_CARD:
                View cardView = LayoutInflater.from(context).inflate(ITEM_CHEESE_ORDER_CARD, parent, false);
                return new OrderLinearAdapter.OrderCardViewHolder(cardView);

        }

        return null;

    }

    @Override
    public int getItemViewType(int position) {
        switch(itemsList.get(position).getType()) {
            case CHEESE:
                return ITEM_CHEESE_ORDER_CARD;
            case TOTAL:
                return ITEM_TOTAL;
        }
        return INVALID_TYPE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        OrderItem item = itemsList.get(position);
        Context context = holder.itemView.getContext();


        //MainActivity.getAdapter().notifyDataSetChanged();

        switch (getItemViewType(position)) {
            case ITEM_TOTAL:
                //String header = item.getTitle();
                //Log.v(TAG, "onBindViewHolder HEADER: " + header);


                ((OrderTotalViewHolder) holder).totalPrice.setText(Integer.toString(totalPrice));
                break;

            case ITEM_CHEESE_ORDER_CARD:

                OrderCardViewHolder cardHolder = ((OrderCardViewHolder) holder);
                String cheese = ((OrderCheese)item).getTitle();
                String price = item.getPrice()+"р";


                cardHolder.textView.setText(cheese);
                cardHolder.price.setText(price);
                cardHolder.cheesePicture.setImageResource(((OrderCheese) item).getImageResId());

                break;

            default:
                throw new IllegalArgumentException("invalid view type");
        }


    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class OrderCardViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        TextView price;
        TextView totalPrice;
        ImageView cheesePicture;
        ImageView addRemoveButton;

        OrderCardViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.order_name);
            price = itemView.findViewById(R.id.order_price);

            addRemoveButton = itemView.findViewById(R.id.order_remove);
            cheesePicture=itemView.findViewById(R.id.cheese_picture);

            addRemoveButton.setOnClickListener(new OrderLinearAdapter.AddRemoveClickListener(this));
        }
    }

    class OrderTotalViewHolder extends RecyclerView.ViewHolder {

        TextView totalPrice;

        OrderTotalViewHolder(View itemView) {
            super(itemView);

            totalPrice = itemView.findViewById(R.id.total_price_text1);
            //addRemoveButton = itemView.findViewById(R.id.button);


            // addRemoveButton.setOnClickListener(new CheeseGridAdapter.AddRemoveClickListener(this));
        }
    }


    class AddRemoveClickListener implements View.OnClickListener {
        OrderLinearAdapter.OrderCardViewHolder holder;

        AddRemoveClickListener(OrderLinearAdapter.OrderCardViewHolder holder) {
            this.holder = holder;
        }

        @Override
        public void onClick(View v) {
            int position = holder.getLayoutPosition();
            OrderCheese item = (OrderCheese) itemsList.get(position);
            Cheeses.Remove(item.getTitle());
            Remove(item.getTitle());
            notifyItemChanged(position);

        }
    }







}
