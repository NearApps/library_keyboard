package com.near.module.keyboard.keyboards;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.near.module.keyboard.R;
import com.near.module.keyboard.simple_keyboard;

public class numeric {

    private static final String TAG = numeric.class.getSimpleName();

    private Context context;
    private simple_keyboard.TYPE TYPE;

    private View TXT;
    private int LENGTH;

    private TextView txt_result;
    private LinearLayout layout_limit;



    private simple_keyboard.keyboard_numeric_config CONFIG = null;
    private boolean PREVIEW = true;


    public numeric( Context context, simple_keyboard.TYPE type, View TXT, int LENGTH ){
        this.context = context;
        this.TYPE = type;
        this.TXT = TXT;
        this.LENGTH = LENGTH;
    }

    public void show(){

        if ( TYPE.equals( simple_keyboard.TYPE.DIALOG ) ){
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature( Window.FEATURE_NO_TITLE); // before
            dialog.setContentView( R.layout.keyboard_numeric );
            dialog.setCancelable(true);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

            txt_result = (( TextView ) dialog.findViewById( R.id.txt_result ));
            txt_result.setVisibility( PREVIEW ? View.VISIBLE : View.GONE );
            layout_limit = (( LinearLayout ) dialog.findViewById( R.id.layout_limit ));
            layout_limit.setVisibility( View.GONE );
            init_components(dialog);

            dialog.show();
            dialog.setOnDismissListener( new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss( DialogInterface dialogInterface ) {
                    if ( txt_result.getText().toString().length() > 0 ){
                        (( TextView )TXT).setText( txt_result.getText().toString() );
                    }
                }
            } );
            dialog.getWindow().setAttributes(lp);
        }else if ( TYPE.equals( simple_keyboard.TYPE.DOWN_ACTIVITY ) ){
            final BottomSheetDialog dialog = new BottomSheetDialog(context);
            dialog.requestWindowFeature( Window.FEATURE_NO_TITLE); // before
            dialog.setContentView( R.layout.keyboard_numeric );
            dialog.setCancelable(true);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER;

            txt_result = (( TextView ) dialog.findViewById( R.id.txt_result ));
            txt_result.setVisibility( PREVIEW ? View.VISIBLE : View.GONE );
            layout_limit = (( LinearLayout ) dialog.findViewById( R.id.layout_limit ));
            layout_limit.setVisibility( View.GONE );
            init_components(dialog);

            dialog.show();
            dialog.setOnDismissListener( new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss( DialogInterface dialogInterface ) {
                    if ( txt_result.getText().toString().length() > 0 ){
                        (( TextView )TXT).setText( txt_result.getText().toString() );
                    }
                }
            } );
            dialog.getWindow().setAttributes(lp);
        }
    }

    private void init_components( final Dialog dialog){



        if ( (( TextView )TXT).getText().toString().trim().length() > 0 ){

            String number = "";
            if ( (( TextView )TXT).getText().toString().trim().length() > LENGTH ){
                number = (( TextView )TXT).getText().toString().trim().substring( 0, LENGTH );
            }else{
                number = (( TextView )TXT).getText().toString().trim();
            }

            try {
                Integer.parseInt( number );
                txt_result.setText( number );
            } catch (NumberFormatException e) {
                e.printStackTrace();
                try {
                    Double.parseDouble( number );
                    txt_result.setText( number );
                } catch (NumberFormatException e2) {
                    e2.printStackTrace();
                    txt_result.setText( "" );
                }
            }
        }


        View.OnClickListener listener_numeric = new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                if ( txt_result.getText().toString().length() < ( txt_result.getText().toString().contains(".") ? (LENGTH + 1) : LENGTH ) ){
                    try {
                        double d =  Double.parseDouble( String.format( "%s%s", txt_result.getText().toString(), ( ( AppCompatButton ) view ).getText().toString() ) );
                        if ( CONFIG != null && CONFIG.getLimit() != simple_keyboard.NULL ){
                            if ( d <= CONFIG.getLimit() ){
                                txt_result.setText( String.format( "%s%s", txt_result.getText().toString(), ( ( AppCompatButton ) view ).getText().toString() ) );
                                layout_limit.setVisibility( View.GONE );
                            }else {
                                layout_limit.setVisibility( View.VISIBLE );
                                String limit = String.format("%f", CONFIG.getLimit());
                                (( TextView ) dialog.findViewById( R.id.txt_limit )).setText( ( ( !limit.contains( "." ) ) ? limit : limit.replaceAll("0*$", "").replaceAll("\\.$", "") ) );
                            }
                        }else{
                            txt_result.setText( String.format( "%s%s", txt_result.getText().toString(), ( ( AppCompatButton ) view ).getText().toString() ) );
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        View.OnClickListener listener_punto = new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                if ( !txt_result.getText().toString().contains( ( ( AppCompatButton ) view ).getText().toString() ) ){
                    if ( txt_result.getText().toString().length() == 0 ){
                        txt_result.setText( String.format( "0%s", ( ( AppCompatButton ) view ).getText().toString() ) );
                    }else if ( txt_result.getText().toString().length() == (LENGTH - 1) ){
                        /** Nada */
                    }else {
                        txt_result.setText( String.format( "%s%s", txt_result.getText().toString(), ( ( AppCompatButton ) view ).getText().toString() ) );
                    }
                }
            }
        };

        View.OnClickListener listener_back = new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                String txt = txt_result.getText().toString();
                if ( txt.length() > 1 ){
                    txt_result.setText( txt_result.getText().toString().substring( 0, (txt_result.getText().toString().length() -1 )) );
                }else {
                    txt_result.setText( "" );
                }
                layout_limit.setVisibility( View.GONE );
            }
        };


        (( AppCompatButton ) dialog.findViewById( R.id.btn_1 )).setOnClickListener( listener_numeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_2 )).setOnClickListener( listener_numeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_3 )).setOnClickListener( listener_numeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_4 )).setOnClickListener( listener_numeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_5 )).setOnClickListener( listener_numeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_6 )).setOnClickListener( listener_numeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_7 )).setOnClickListener( listener_numeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_8 )).setOnClickListener( listener_numeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_9 )).setOnClickListener( listener_numeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_0 )).setOnClickListener( listener_numeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_back )).setOnClickListener( listener_back );

        if ( CONFIG != null ){
            if ( !CONFIG.isIs_decimal() ){
                (( AppCompatButton ) dialog.findViewById( R.id.btn_punto )).setVisibility( View.INVISIBLE );
                txt_result.setText( txt_result.getText().toString().replace( ".", "" ).replace( ",", "" ) );
            }else{
                txt_result.setText( txt_result.getText().toString().replace( ",", "." ) );
            }
        }
        (( AppCompatButton ) dialog.findViewById( R.id.btn_punto )).setOnClickListener( listener_punto );

    }


    public void setConfig( simple_keyboard.keyboard_numeric_config config ) { this.CONFIG = config; }
    public void setPreview( boolean preview ) { this.PREVIEW = preview; }
}
