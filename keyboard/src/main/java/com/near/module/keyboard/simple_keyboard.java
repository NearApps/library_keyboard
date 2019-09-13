package com.near.module.keyboard;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.InputFilter;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.near.module.keyboard.keyboards.numeric;

public class simple_keyboard {

    private static final String TAG = simple_keyboard.class.getSimpleName();

    public enum TYPE{ DIALOG, DOWN_ACTIVITY }
    public enum KEYBOARD{ NUMERIC, TEXT }

    public static final int NULL = -1;

    public static Builder newBuilder( Context activity) {
        return new Builder(activity);
    }




    public static final class Builder {

        private Context activity;
        private simple_keyboard.TYPE TYPE = simple_keyboard.TYPE.DIALOG;

        private simple_keyboard.KEYBOARD KEYBOARD = simple_keyboard.KEYBOARD.NUMERIC;
        private keyboard_numeric_config CONFIG;

        private boolean DEBUG = false;
        private boolean PREVIEW = true;

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
            this.CONFIG = config;
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

        public Builder setPreview(@NonNull boolean preview ) {
            this.PREVIEW = preview;
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

                view_num.setConfig( CONFIG );
                view_num.setPreview( PREVIEW );
                view_num.show();
            }

            return simplekeyboard;
        }
    }


    public static class keyboard_numeric_config{

        private double limit = NULL;
        private boolean is_decimal = false;

        public keyboard_numeric_config( double limit, @NonNull boolean is_decimal ) {
            if ( limit != NULL ) this.limit = limit;
            this.is_decimal = is_decimal;
        }

        public keyboard_numeric_config(@NonNull boolean is_decimal ) {
            this.limit = NULL;
            this.is_decimal = is_decimal;
        }

        public double getLimit() { return limit; }
        public boolean isIs_decimal() { return is_decimal; }
    }

}
