package com.example.lenovo.baodingchejian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
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
        Glide.with(this).load(url).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(photoView);


        photoView.enable();
        photoView.setMaxScale(5.0f);
    }
}
