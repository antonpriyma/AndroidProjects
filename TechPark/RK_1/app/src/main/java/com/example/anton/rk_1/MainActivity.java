package com.example.anton.rk_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public final static String BROADCAST_ACTION="action";
    public final static String EXTRA_VALUE="value";
    final FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
    NumberFragment numberFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (savedInstanceState==null) {
            RecyclerFragment recyclerFragment = new RecyclerFragment();
            openFragment(recyclerFragment);
        }
        BroadcastReceiver br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                numberFragment=new NumberFragment();
                numberFragment.setNumber(intent.getStringExtra(EXTRA_VALUE));
                openFragment(numberFragment);
            }
        };
        IntentFilter intentFilter=new IntentFilter(BROADCAST_ACTION);
        registerReceiver(br,intentFilter);

    }

    private void openFragment(final Fragment fragment)   {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        if (fragment.getClass()!=RecyclerFragment.class) {
            transaction.addToBackStack(null);
        }
        transaction.setTransition(transaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       // getSupportFragmentManager().putFragment(outState,"numberFragment",numberFragment);
    }
}
