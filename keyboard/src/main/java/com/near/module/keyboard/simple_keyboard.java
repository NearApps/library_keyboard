package com.near.module.keyboard;

import android.content.Context;
import android.os.Build;
import android.text.InputFilter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.near.module.keyboard.keyboards.alphanumeric;
import com.near.module.keyboard.keyboards.numeric;

public class simple_keyboard {

    private static final String TAG = simple_keyboard.class.getSimpleName();

    public enum TYPE{ DIALOG, DOWN_ACTIVITY }
    public enum KEYBOARD{ NUMERIC, ALPHANUMERIC }

    public static final int NULL = -1;

    public static Builder newBuilder( Context activity) {
        return new Builder(activity);
    }




    public static final class Builder {

        private Context activity;
        private simple_keyboard.TYPE TYPE = simple_keyboard.TYPE.DIALOG;

        private simple_keyboard.KEYBOARD KEYBOARD = simple_keyboard.KEYBOARD.NUMERIC;
        private keyboard_numeric_config CONFIG_NUMERIC;
        private keyboard_alphanumeric_config CONFIG_ALPHANUMERIC;

        private keyboard_listener LISTENER = null;
        private boolean DEBUG = false;
        private boolean PREVIEW = true;
        private String TITLE = null;

        private int LENGTH = 100;

        private TextView content1;
        private EditText content2;

        private Builder(Context activity) {
            this.activity = activity;
        }

        public Builder setContent(@NonNull TextView textView_content) {
            this.content1 = textView_content;
            return this;
        }

        public Builder setContent(@NonNull EditText EditText_content) {
            this.content2 = EditText_content;
            return this;
        }

        public Builder setType(@NonNull simple_keyboard.TYPE type ) {
            this.TYPE = type;
            return this;
        }

        public Builder setKeyboard( @NonNull simple_keyboard.KEYBOARD keyboard, @NonNull keyboard_numeric_config config) {
            this.KEYBOARD = keyboard;
            this.CONFIG_NUMERIC = config;
            return this;
        }

        public Builder setKeyboard( @NonNull simple_keyboard.KEYBOARD keyboard, @NonNull keyboard_alphanumeric_config config) {
            this.KEYBOARD = keyboard;
            this.CONFIG_ALPHANUMERIC = config;
            return this;
        }

        public Builder setKeyboard(@NonNull simple_keyboard.KEYBOARD keyboard) {
            this.KEYBOARD = keyboard;
            return this;
        }

        public Builder setLength(@NonNull int text_size) {
            if ( text_size > 0 ) this.LENGTH = text_size;
            return this;
        }

        public Builder setDebug(@NonNull boolean is_debug ) {
            this.DEBUG = is_debug;
            return this;
        }

        public Builder setTitle(@NonNull String title ) {
            this.TITLE = title;
            return this;
        }

        public Builder setListener(@NonNull keyboard_listener keyboard_listener ) {
            this.LISTENER = keyboard_listener;
            return this;
        }

        public simple_keyboard show() {
            simple_keyboard simplekeyboard = new simple_keyboard();

            if ( KEYBOARD.equals( simple_keyboard.KEYBOARD.NUMERIC ) ){
                numeric view_num = null;
                if ( content1 != null ){
                    view_num = new numeric(activity, TYPE, content1, LENGTH);
                }else if ( content2 != null ){
                    if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        content2.setShowSoftInputOnFocus(false);
                    }
                    content2.setFilters(new InputFilter[] {new InputFilter.LengthFilter(LENGTH)});
                    view_num = new numeric(activity, TYPE, content2, LENGTH);
                }

                view_num.setConfig( CONFIG_NUMERIC );
                view_num.setPreview( PREVIEW );
                view_num.setTitle( TITLE );
                view_num.setListener( LISTENER );
                view_num.show();
            }else if (  KEYBOARD.equals( simple_keyboard.KEYBOARD.ALPHANUMERIC ) ){
                alphanumeric view_alphanum = null;
                if ( content1 != null ){
                    view_alphanum = new alphanumeric(activity, TYPE, content1, LENGTH);
                }else if ( content2 != null ){
                    if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        content2.setShowSoftInputOnFocus(false);
                    }
                    content2.setFilters(new InputFilter[] {new InputFilter.LengthFilter(LENGTH)});
                    view_alphanum = new alphanumeric(activity, TYPE, content2, LENGTH);
                }

                view_alphanum.setConfig( CONFIG_ALPHANUMERIC );
                view_alphanum.setPreview( PREVIEW );
                view_alphanum.setTitle( TITLE );
                view_alphanum.setListener( LISTENER );
                view_alphanum.show();
            }

            return simplekeyboard;
        }
    }


    public static class keyboard_numeric_config{

        private double limit = NULL;
        private boolean is_decimal = false;
        private int limit_decimal = NULL;

        public keyboard_numeric_config( double limit, @NonNull boolean is_decimal ) {
            if ( limit != NULL ) this.limit = limit;
            this.is_decimal = is_decimal;
        }

        public keyboard_numeric_config( @NonNull boolean is_decimal ) {
            this.is_decimal = is_decimal;
        }

        public keyboard_numeric_config( @NonNull boolean is_decimal, @NonNull int limit_decimal ) {
            this.limit = NULL;
            this.is_decimal = is_decimal;
            if ( limit_decimal != NULL ) this.limit_decimal = limit_decimal;
        }

        public keyboard_numeric_config( @NonNull double limit, @NonNull boolean is_decimal, @NonNull int limit_decimal ) {
            this.limit = limit;
            this.is_decimal = is_decimal;
            if ( limit_decimal != NULL ) this.limit_decimal = limit_decimal;
        }


        public double getLimit() { return limit; }
        public boolean isIs_decimal() { return is_decimal; }
        public int getLimit_decimal() { return limit_decimal; }
    }

    public static class keyboard_alphanumeric_config{

        private boolean show_symbols = false;
        private boolean show_mayus = false;
        private boolean set_all_mayus = false;
        private boolean show_symbols_default = false;
        private final String[] symbols_default = {"@", "#", "%", "&", "*", "/", "\\", "-", "+", "(", ")", "¿", "?", "!", "¡", "\"", "'", ":", ";", ",", ".", "$", "{", "}", "[", "]", "_", "<", ">", "|", "é", "É", "í", "Í", "ï", "Ï", "ö", "Ö", "ø", "Ø", "ü", "Ü", "ú", "Ú", "ƒ", "ç", "£", "½", "¼", "¬"/*, "", ""*/};
        private String[] symbols = null;

        public keyboard_alphanumeric_config( boolean set_all_mayus, boolean show_mayus, boolean show_symbols_default, String[] symbols ) {
            this.set_all_mayus = set_all_mayus;
            this.show_mayus = show_mayus;
            this.show_symbols_default = show_symbols_default;
            this.show_symbols = ( show_symbols_default || ( symbols != null ) );
            this.symbols = symbols;
        }

        public keyboard_alphanumeric_config( boolean set_all_mayus, boolean show_mayus, boolean show_symbols_default) {
            this.set_all_mayus = set_all_mayus;
            this.show_mayus = show_mayus;
            this.show_symbols_default = show_symbols_default;
            this.show_symbols = show_symbols_default;
            this.symbols = null;
        }

        public boolean isShow_symbols() { return show_symbols; }
        public boolean isShow_mayus() { return show_mayus; }
        public boolean isSet_all_mayus() { return set_all_mayus; }
        public boolean isShow_symbols_default() { return show_symbols_default; }
        public String[] getSymbols_default() { return symbols_default; }
        public String[] getSymbols() { return symbols; }
    }

    public interface keyboard_listener{
        public void getText( String text );
    }

}
