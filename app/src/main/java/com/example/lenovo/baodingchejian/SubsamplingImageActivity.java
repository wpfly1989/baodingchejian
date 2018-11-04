package com.example.lenovo.baodingchejian;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.io.File;

public class SubsamplingImageActivity extends AppCompatActivity {
    private SubsamplingScaleImageView imageView;
    private File imageFile;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subsampling_image);

        imageView = (SubsamplingScaleImageView)findViewById(R.id.bigimage);
        progressBar = (ProgressBar)findViewById(R.id.subsamplingprogressbar);

        imageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
        imageView.setMaxScale(5.0f);
        imageView.setMinScale(0.5f);

        Intent intent = getIntent();
        String url = intent.getStringExtra("addforimage");

        Glide.with(this).load(url).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                imageView.setImage(ImageSource.uri(Uri.fromFile(resource)),new ImageViewState(1.0F, new PointF(0,0),0));
                progressBar.setVisibility(View.GONE);
                imageFile = resource;
            }
        });


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (imageFile.exists()) {
            imageFile.delete();
        }
    }
}
