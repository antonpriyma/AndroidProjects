package com.example.anton.calculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainCalculatorActivity extends AppCompatActivity {
    private Button addBtn;
    private Button subBtn;
    private Button divBtn;
    private Button multBtn;
    private EditText Num1;
    private EditText Num2;
    private TextView tv;
    public LinearLayout Mainlayout;

    public LinearLayout getMainlayout() {
        return Mainlayout;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.calcmenu,menu);
        return true;
    }//Иницилизация кнопки меню

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.button_reset:
                tv.setText("");
                Num1.setText("");
                Num2.setText("");
                break;
            case R.id.button_settings:
                Intent intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }//присваивание кнопкам ссылок на действия

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calculator);


        addBtn = (Button)findViewById(R.id.button_plus);
        subBtn = (Button)findViewById(R.id.button_Sub);
        divBtn = (Button)findViewById(R.id.button_Div);
        multBtn = (Button)findViewById(R.id.button_Mult);
        Num1 = (EditText)findViewById(R.id.Num1);
        Num2=(EditText)findViewById(R.id.Num2);
        tv = (TextView)findViewById(R.id.tv_result) ;
        Mainlayout = (LinearLayout)findViewById(R.id.mainlayout);
        Mainlayout.setBackgroundColor(getColor(R.color.colorback));


        Animation anim = AnimationUtils.loadAnimation(this,R.anim.myalpha);

        View.OnClickListener OnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(Num1.getText().toString())
                        || TextUtils.isEmpty(Num2.getText().toString())) {
                    return;
                }

                float result = 0;
                float num1 = Float.parseFloat(Num1.getText().toString());
                float num2 = Float.parseFloat(Num2.getText().toString());
                String oper = "";

                switch (view.getId()){
                    case R.id.button_plus:
                        oper = "+";
                        result = num1+num2;
                        break;
                    case R.id.button_Sub:
                        oper = "-";
                        result = num1-num2;
                        break;
                    case R.id.button_Mult:
                        oper = "*";
                        result = num1*num2;
                        break;
                    case R.id.button_Div:
                        oper = "/";
                        result = num1/num2;
                        break;
                }

                tv.setText(num1 + " " + oper + " " + num2 + "=" + result);
            }
        };
        tv.startAnimation(anim);
        addBtn.setOnClickListener(OnClickListener);
        subBtn.setOnClickListener(OnClickListener);
        multBtn.setOnClickListener(OnClickListener);
        divBtn.setOnClickListener(OnClickListener);
    }
}
