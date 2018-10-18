package com.example.lenovo.baodingchejian;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class ScrollingTextView extends AppCompatTextView{


    public ScrollingTextView(Context context) {
        super(context);
    }

    public ScrollingTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollingTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
