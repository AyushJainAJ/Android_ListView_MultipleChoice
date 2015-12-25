package com.example.aj.multiplechoicelistview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity {

    private ListView greater, lesser;
    private EditText number;

    private ArrayList<String> g,l;
    private ArrayAdapter<String> gAdapter,lAdapter;

    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_main);

        number = (EditText) findViewById(R.id.editText);
        greater = (ListView) findViewById(R.id.greater500);
        lesser = (ListView) findViewById(R.id.less500);

        g = new ArrayList<String>();
        l = new ArrayList<String>();

        gAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,g);
        lAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,l);
    }

    public void addToList(View v) {
        String num = number.getText().toString();
        if(num.compareTo("500") > 0) {
            g.add(num);
            greater.setAdapter(gAdapter);
        }
        else {
            l.add(num);
            lesser.setAdapter(lAdapter);
        }
    }

    public void getListOfNumbers(View v) {
        int leng = greater.getCount(),lenl=lesser.getCount(),i;
        String arr[]=new String[leng+lenl];
        int x=0;

        SparseBooleanArray checked = greater.getCheckedItemPositions();

        for (i = 0; i < leng; i++)
            if (checked.get(i))
                arr[x++] = (String)greater.getItemAtPosition(i);

        checked = lesser.getCheckedItemPositions();
        for(i=0; i<lenl; i++)
            if(checked.get(i))
                arr[x++] = (String)lesser.getItemAtPosition(i);

        Arrays.sort(arr,0,x);

        Intent in = new Intent(this,ViewNumbers.class);
        in.putExtra("sorted",arr);
        startActivity(in);
    }
}
