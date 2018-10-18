package com.example.lenovo.baodingchejian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.bm.library.PhotoView;
import com.squareup.picasso.Picasso;

public class MaxImageView extends AppCompatActivity {
    private PhotoView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max_image_view);

        Intent intent = getIntent();
        String url = intent.getStringExtra("add");

        photoView = (PhotoView)findViewById(R.id.maxImageView);
        Picasso.with(this).load(url).placeholder(R.mipmap.tupianjiazaizhong).into(photoView);

        photoView.enable();
        photoView.setMaxScale(5.0f);
    }
}
