package com.near.keyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.near.module.keyboard.simple_keyboard;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        final EditText txt_demo_1 = (( EditText ) findViewById( R.id.txt_demo_1 ));
        txt_demo_1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                simple_keyboard.newBuilder( MainActivity.this )
                        .setContent( txt_demo_1 )
                        .setType( simple_keyboard.TYPE.DIALOG )
                        .setKeyboard( simple_keyboard.KEYBOARD.NUMERIC, new simple_keyboard.keyboard_numeric_config(12345678, false) )
                        .setLength( 8 )
                        .setDebug( true )
                        .show();
            }
        } );

        final TextView txt_demo_2 = (( TextView ) findViewById( R.id.txt_demo_2 ));
        txt_demo_2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                simple_keyboard.newBuilder( MainActivity.this )
                        .setContent( txt_demo_2 )
                        .setType( simple_keyboard.TYPE.DIALOG )
                        .setKeyboard( simple_keyboard.KEYBOARD.NUMERIC, new simple_keyboard.keyboard_numeric_config(2232.1050, true) )
                        .setLength( 8 )
                        .setDebug( true )
                        //VISIBLE PREVIEW
                        .show();
            }
        } );

        final TextView txt_demo_3 = (( TextView ) findViewById( R.id.txt_demo_3 ));
        txt_demo_3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                simple_keyboard.newBuilder( MainActivity.this )
                        .setContent( txt_demo_3 )
                        .setType( simple_keyboard.TYPE.DOWN_ACTIVITY )
                        .setKeyboard( simple_keyboard.KEYBOARD.NUMERIC, new simple_keyboard.keyboard_numeric_config( false ) )
                        .setLength( 5 )
                        .setDebug( true )
                        .show();
            }
        } );


    }
}
