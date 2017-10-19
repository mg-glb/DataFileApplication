package com.example.mgigena.datafileapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    Button b1,b2;
    TextView tv;
    EditText ed;

    String data;
    private String file = "myData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declare the two buttons
        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button2);
        //Create an edit text, which will be used to enter data and a
        //text view to load data to
        ed=(EditText)findViewById(R.id.editText);
        tv=(TextView)findViewById(R.id.textView2);
        //When the first button is clicked, data is saved to file
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = ed.getText().toString();
                FileOutputStream outputStream;
                try{
                    outputStream = openFileOutput(file, Context.MODE_PRIVATE);
                    outputStream.write(data.getBytes());
                    outputStream.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        //When the second button is clicked, data is retrieved from file
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream inputStream = openFileInput(file);
                    int c;
                    data = "";
                    while ((c = inputStream.read()) != -1) {
                        data = data + Character.toString((char)c);
                    }
                    tv.setText(data);
                    Toast.makeText(getBaseContext(), "file read", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
