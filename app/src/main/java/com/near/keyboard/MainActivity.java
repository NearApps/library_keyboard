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


        final TextView txt_demo_0 = ( ( TextView ) findViewById( R.id.txt_demo_0 ) );
        txt_demo_0.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                simple_keyboard.newBuilder( MainActivity.this )
                        .setContent( txt_demo_0 )
                        .setType( simple_keyboard.TYPE.DIALOG )
                        .setKeyboard( simple_keyboard.KEYBOARD.NUMERIC, new simple_keyboard.keyboard_numeric_config( 2232.1050, true, 2 ) )
                        .setLength( 8 )
                        .setTitle( "Escriba un número" )
                        .setDebug( true )
                        .show();
            }
        } );

        final EditText txt_demo_1 = ( ( EditText ) findViewById( R.id.txt_demo_1 ) );
        txt_demo_1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                simple_keyboard.newBuilder( MainActivity.this )
                        .setContent( txt_demo_1 )
                        .setType( simple_keyboard.TYPE.DIALOG )
                        .setKeyboard( simple_keyboard.KEYBOARD.NUMERIC, new simple_keyboard.keyboard_numeric_config( 12345678, false ) )
                        .setLength( 8 )
                        .setDebug( true )
                        .show();
            }
        } );

        final TextView txt_demo_2 = ( ( TextView ) findViewById( R.id.txt_demo_2 ) );
        txt_demo_2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                simple_keyboard.newBuilder( MainActivity.this )
                        .setContent( txt_demo_2 )
                        .setType( simple_keyboard.TYPE.DIALOG )
                        .setKeyboard( simple_keyboard.KEYBOARD.NUMERIC, new simple_keyboard.keyboard_numeric_config( 2232.1050, true ) )
                        .setLength( 8 )
                        .setDebug( true )
                        .show();
            }
        } );

        final TextView txt_demo_3 = ( ( TextView ) findViewById( R.id.txt_demo_3 ) );
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





        /**     ALPHANUMERIC    */

        final TextView txt_demo_4 = (( TextView ) findViewById( R.id.txt_demo_4 ));
        txt_demo_4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                simple_keyboard.newBuilder( MainActivity.this )
                        .setContent( txt_demo_4 )
                        .setType( simple_keyboard.TYPE.DIALOG )
                        .setKeyboard( simple_keyboard.KEYBOARD.ALPHANUMERIC, new simple_keyboard.keyboard_alphanumeric_config( false, false,false ) )
                        .setLength( 8 )
                        .setTitle( "Escriba aquí" )
                        .setDebug( true )
                        .show();
            }
        } );

        final TextView txt_demo_5 = (( TextView ) findViewById( R.id.txt_demo_5 ));
        txt_demo_5.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                simple_keyboard.newBuilder( MainActivity.this )
                        .setContent( txt_demo_5 )
                        .setType( simple_keyboard.TYPE.DOWN_ACTIVITY )
                        .setKeyboard( simple_keyboard.KEYBOARD.ALPHANUMERIC, new simple_keyboard.keyboard_alphanumeric_config( true, true,false ) )
                        .setLength( 100 )
                        .setDebug( true )
                        .show();
            }
        } );

        final TextView txt_demo_6 = (( TextView ) findViewById( R.id.txt_demo_6 ));
        txt_demo_6.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                simple_keyboard.newBuilder( MainActivity.this )
                        .setContent( txt_demo_6 )
                        .setType( simple_keyboard.TYPE.DOWN_ACTIVITY )
                        .setKeyboard( simple_keyboard.KEYBOARD.ALPHANUMERIC, new simple_keyboard.keyboard_alphanumeric_config( true, true,true ) )
                        .setLength( 50 )
                        .setDebug( true )
                        .show();
            }
        } );

        final TextView txt_demo_7 = (( TextView ) findViewById( R.id.txt_demo_7 ));
        txt_demo_7.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                simple_keyboard.newBuilder( MainActivity.this )
                        .setContent( txt_demo_7 )
                        .setType( simple_keyboard.TYPE.DOWN_ACTIVITY )
                        .setKeyboard( simple_keyboard.KEYBOARD.ALPHANUMERIC, new simple_keyboard.keyboard_alphanumeric_config( true, true,true, new String[]{"*", "¨", "°", "|"} ) )
                        .setLength( 50 )
                        .setDebug( true )
                        .show();
            }
        } );

    }
}
