package com.near.module.keyboard.keyboards;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.near.module.keyboard.R;
import com.near.module.keyboard.simple_keyboard;

public class alphanumeric {

    private static final String TAG = alphanumeric.class.getSimpleName();

    private Context context;
    private simple_keyboard.TYPE TYPE;
    private View TXT;
    private int LENGTH;

    private simple_keyboard.keyboard_alphanumeric_config CONFIG = null;
    private simple_keyboard.keyboard_listener LISTENER = null;
    private boolean PREVIEW = true;
    private String TITLE = null;


    private TextView txt_result, txt_title;

    private LinearLayout layout_keyboard, layout_keyboard_symbols;
    private View.OnClickListener listener_alphanumeric;

    public alphanumeric( Context context, simple_keyboard.TYPE TYPE, View TXT, int LENGTH ) {
        this.context = context;
        this.TYPE = TYPE;
        this.TXT = TXT;
        this.LENGTH = LENGTH;
    }

    public void show(){
        if ( TYPE.equals( simple_keyboard.TYPE.DIALOG ) ){
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature( Window.FEATURE_NO_TITLE); // before
            dialog.setContentView( R.layout.keyboard_alphanumeric);
            dialog.setCancelable(true);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

            txt_result = (( TextView ) dialog.findViewById( R.id.txt_result ));
            txt_result.setVisibility( PREVIEW ? View.VISIBLE : View.GONE );
            txt_title = (( TextView ) dialog.findViewById( R.id.txt_title ));
            layout_keyboard = (( LinearLayout ) dialog.findViewById( R.id.layout_keyboard ));
            layout_keyboard_symbols = (( LinearLayout ) dialog.findViewById( R.id.layout_keyboard_symbols ));
            init_components(dialog);

            dialog.show();
            dialog.setOnDismissListener( new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss( DialogInterface dialogInterface ) {
                    if ( txt_result.getText().toString().length() > 0 ){
                        if ( LISTENER != null ){
                            LISTENER.getText( txt_result.getText().toString() );
                        }else{
                            (( TextView )TXT).setText( txt_result.getText().toString() );
                        }
                    }
                }
            } );
            dialog.getWindow().setAttributes(lp);
        }else if ( TYPE.equals( simple_keyboard.TYPE.DOWN_ACTIVITY ) ){
            final BottomSheetDialog dialog = new BottomSheetDialog(context);
            dialog.requestWindowFeature( Window.FEATURE_NO_TITLE); // before
            dialog.setContentView( R.layout.keyboard_alphanumeric );
            dialog.setCancelable(true);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER;

            txt_result = (( TextView ) dialog.findViewById( R.id.txt_result ));
            txt_result.setVisibility( PREVIEW ? View.VISIBLE : View.GONE );
            txt_title = (( TextView ) dialog.findViewById( R.id.txt_title ));
            layout_keyboard = (( LinearLayout ) dialog.findViewById( R.id.layout_keyboard ));
            layout_keyboard_symbols = (( LinearLayout ) dialog.findViewById( R.id.layout_keyboard_symbols ));
            init_components(dialog);

            dialog.show();
            dialog.setOnDismissListener( new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss( DialogInterface dialogInterface ) {
                    if ( txt_result.getText().toString().length() > 0 ){
                        if ( LISTENER != null ){
                            LISTENER.getText( txt_result.getText().toString() );
                        }else{
                            (( TextView )TXT).setText( txt_result.getText().toString() );
                        };
                    }
                }
            } );
            dialog.getWindow().setAttributes(lp);
        }
    }

    private void init_components( final Dialog dialog){

        txt_title.setVisibility( (TITLE != null) ? ((TITLE.trim().length() > 0) ? View.VISIBLE : View.GONE) : View.GONE);
        if ( txt_title.getVisibility() == View.VISIBLE ){
            txt_title.setText( TITLE );
        }

        if ( (( TextView )TXT).getText().toString().trim().length() > 0 ){
            if ( LISTENER != null ){
                txt_result.setText("");
            }else{
                if ( (( TextView )TXT).getText().toString().trim().length() > LENGTH ){
                    txt_result.setText((( TextView )TXT).getText().toString().trim().substring( 0, LENGTH ));
                }else{
                    txt_result.setText((( TextView )TXT).getText().toString().trim());
                }
            }
        }

        layout_keyboard_symbols.setVisibility( View.GONE );
        if ( CONFIG != null ){
            if ( !CONFIG.isShow_symbols() ){
                layout_keyboard_symbols.setVisibility( View.GONE );
                
                (( AppCompatButton ) dialog.findViewById( R.id.btn_simbols )).setVisibility( View.INVISIBLE );
                (( AppCompatButton ) dialog.findViewById( R.id.btn_simbols_2 )).setVisibility( View.INVISIBLE );
                (( AppCompatButton ) dialog.findViewById( R.id.btn_coma )).setVisibility( View.INVISIBLE );
                (( AppCompatButton ) dialog.findViewById( R.id.btn_punto )).setVisibility( View.INVISIBLE );
            }
            if ( !CONFIG.isShow_mayus() ) {
                (( AppCompatButton ) dialog.findViewById( R.id.btn_mayus )).setVisibility( View.INVISIBLE );
            }
            if ( CONFIG.isSet_all_mayus() ){
                set_all_mayus( dialog );
            }
        }

        listener_alphanumeric = new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                if ( txt_result.getText().toString().length() < LENGTH ){
                    txt_result.setText( String.format( "%s%s", txt_result.getText().toString(), ( ( AppCompatButton ) view ).getText().toString() ) );
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
            }
        };

        (( AppCompatButton ) dialog.findViewById( R.id.btn_q )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_w )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_e )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_r )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_t )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_y )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_u )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_i )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_o )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_p )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_a )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_s )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_d )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_f )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_g )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_h )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_j )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_k )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_l )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_enie )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_z )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_x )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_c )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_v )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_b )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_n )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_m )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_1 )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_2 )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_3 )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_4 )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_5 )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_6 )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_7 )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_8 )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_9 )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_0 )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_punto )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_coma )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_space )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_space_2 )).setOnClickListener( listener_alphanumeric );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_back )).setOnClickListener( listener_back );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_back_2 )).setOnClickListener( listener_back );


        (( AppCompatButton ) dialog.findViewById( R.id.btn_mayus )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                if ( Boolean.parseBoolean( String.valueOf((( AppCompatButton ) dialog.findViewById( R.id.btn_mayus )).getTag()) ) ){
                    set_all_minusc( dialog );
                }else{
                    set_all_mayus( dialog );
                }
            }
        } );

        generate_buttons( ( (LinearLayout) dialog.findViewById( R.id.linear_contents_button ) ), ((CONFIG != null) ? (CONFIG.isShow_symbols_default() ? CONFIG.getSymbols_default() : null ) : null) );
        generate_buttons( ( (LinearLayout) dialog.findViewById( R.id.linear_contents_button ) ), ((CONFIG != null) ? CONFIG.getSymbols() : null) );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_simbols )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                layout_keyboard.setVisibility( View.GONE );
                layout_keyboard_symbols.setVisibility( View.VISIBLE );
            }
        } );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_simbols_2 )).setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                layout_keyboard.setVisibility( View.VISIBLE );
                layout_keyboard_symbols.setVisibility( View.GONE );
            }
        } );

    }

    private void set_all_mayus( final Dialog dialog ){
        (( AppCompatButton ) dialog.findViewById( R.id.btn_mayus )).setTag( true );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_q )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_q )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_w )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_w )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_e )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_e )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_r )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_r )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_t )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_t )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_y )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_y )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_u )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_u )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_i )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_i )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_o )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_o )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_p )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_p )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_a )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_a )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_s )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_s )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_d )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_d )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_f )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_f )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_g )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_g )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_h )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_h )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_j )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_j )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_k )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_k )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_l )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_l )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_enie )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_enie )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_z )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_z )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_x )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_x )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_c )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_c )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_v )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_v )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_b )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_b )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_n )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_n )).getText().toString().toUpperCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_m )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_m )).getText().toString().toUpperCase() );
    }
    private void set_all_minusc( final Dialog dialog ){
        (( AppCompatButton ) dialog.findViewById( R.id.btn_mayus )).setTag( false );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_q )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_q )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_w )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_w )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_e )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_e )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_r )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_r )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_t )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_t )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_y )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_y )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_u )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_u )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_i )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_i )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_o )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_o )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_p )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_p )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_a )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_a )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_s )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_s )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_d )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_d )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_f )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_f )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_g )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_g )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_h )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_h )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_j )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_j )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_k )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_k )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_l )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_l )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_enie )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_enie )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_z )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_z )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_x )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_x )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_c )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_c )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_v )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_v )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_b )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_b )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_n )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_n )).getText().toString().toLowerCase() );
        (( AppCompatButton ) dialog.findViewById( R.id.btn_m )).setText( (( AppCompatButton ) dialog.findViewById( R.id.btn_m )).getText().toString().toLowerCase() );
    }

    private void generate_buttons( LinearLayout linear_base, final String[] buttons ){
        if ( buttons != null ){
            int cantidad = buttons.length;
            int columnas = ((cantidad > 10) ? (((cantidad%10) == 0) ? ((int)(cantidad/10)) : (((int)(cantidad/10)) + 1) ) : 1);
            int index = 0;

            System.out.println( "cantidad:" + cantidad + " columnas:" + columnas );

            for ( int col = 0; col < columnas; col++ ){

                LinearLayout linearLayout_fila = new LinearLayout( context );
                linearLayout_fila.setId( View.generateViewId() );
                linearLayout_fila.setLayoutParams( new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) );
                linearLayout_fila.setGravity( Gravity.CENTER );
                linearLayout_fila.setOrientation( LinearLayout.HORIZONTAL );

                for ( int limit = 0 ; limit < 10; limit++ ){
                    if ( index < cantidad ){

                        AppCompatButton button = new AppCompatButton( context ); //android.R.style.Widget_Material_Button_Borderless
                        button.setId( View.generateViewId() );
                        if ( col != (columnas-1) ){
                            button.setLayoutParams( new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f) );
                        }else{
                            button.setLayoutParams( new LinearLayout.LayoutParams( 100, 150 ) );
                        }
                        button.setBackground(ContextCompat.getDrawable(context, R.drawable.button_style_1));
                        button.setGravity(Gravity.CENTER);
                        button.setTextColor( Color.BLACK );
                        button.setTextSize( 20 );
                        button.setAllCaps( false );
                        button.setText( buttons[index] );
                        button.setOnClickListener( listener_alphanumeric );

                        linearLayout_fila.addView( button );
                        System.out.println( "index:" + index + " key:" + buttons[index] + " colum:" + col );
                    }
                    index++;
                }
                linear_base.addView( linearLayout_fila );
            }
        }
    }

    public void setConfig( simple_keyboard.keyboard_alphanumeric_config config ) { this.CONFIG = config; }
    public void setPreview( boolean preview ) { this.PREVIEW = preview; }
    public void setTitle( String title ) { this.TITLE = title; }
    public void setListener( simple_keyboard.keyboard_listener listener ) { this.LISTENER = listener; }

}
