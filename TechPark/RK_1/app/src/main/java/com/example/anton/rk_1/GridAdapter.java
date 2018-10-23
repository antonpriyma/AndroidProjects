package com.example.anton.rk_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = "Adapter";
    static final int ITEM_CARD = R.layout.recycler_item;
    static final int INVALID_TYPE = -1;

    private List<Item> items;

    public GridAdapter(List<Item> itemList) {
        items = itemList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        switch (viewType) {


            case ITEM_CARD:
                View cardView = LayoutInflater.from(context).inflate(ITEM_CARD, parent, false);
                return new CardViewHolder(cardView);

            default:
                throw new IllegalArgumentException("invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Item item = items.get(position);
        Context context = holder.itemView.getContext();

        switch (getItemViewType(position)) {


            case ITEM_CARD:
                CardViewHolder cardHolder = ((CardViewHolder) holder);
                String value = item.getTitle();
                if (Integer.parseInt(item.getTitle())%2==0){
                    cardHolder.textView.setTextColor(context.getResources().getColor(R.color.blackText));
                }else {
                    cardHolder.textView.setTextColor(context.getResources().getColor(R.color.blueText));
                }

                Log.v(TAG, "onBindViewHolder CARD: " + value);

                cardHolder.textView.setText(value);



                break;

            default:
                throw new IllegalArgumentException("invalid view type");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return ITEM_CARD;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {

        TextView textView;


        CardViewHolder(final View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.cart_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(itemView.getContext(),NumberActivity.class);
                    intent.putExtra(NumberActivity.EXTRA_NUMBER,Integer.parseInt(textView.getText().toString()));
                    view.getContext().startActivity(intent);
                }
            });

        }
    }

    class AddRemoveClickListener implements View.OnClickListener {
        CardViewHolder holder;

        AddRemoveClickListener(CardViewHolder holder) {
            this.holder = holder;
        }

        @Override
        public void onClick(View v) {
            /*
            int position = holder.getLayoutPosition();
            if (position != RecyclerView.NO_POSITION) {
                Cheese item = (Cheese) items.get(position);

                if (Cheeses.getShoppingCart().contains(item)) {
                    Cheeses.getShoppingCart().remove(item);
                } else {
                    Cheeses.getShoppingCart().add(item);
                }
                notifyItemChanged(position);
            }
            */
        }
    }


}
