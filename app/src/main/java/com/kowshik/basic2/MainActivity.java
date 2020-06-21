package com.kowshik.basic2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bfrag1=findViewById(R.id.b_frag1);
        Button bfrag2=findViewById(R.id.b_frag2);

        final Fragment fragment1=new Fragment_1();
        final Fragment fragment2=new Fragment_2();



        bfrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flfragment,fragment1).commit();
            }
        });
        bfrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.flfragment,fragment2).commit();
            }
        });

    }
}
