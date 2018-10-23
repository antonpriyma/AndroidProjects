package com.example.anton.rk_1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NumberFragment extends Fragment {

    private int number;

    public NumberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_number, container, false);
    }

    public void setNumber(int number){
        this.number=number;
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view!=null){
            TextView title=(TextView)view.findViewById(R.id.text_number);
            title.setText(((Integer)number).toString());
        }
    }

}
