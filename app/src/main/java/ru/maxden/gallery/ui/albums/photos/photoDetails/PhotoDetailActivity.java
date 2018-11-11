package ru.maxden.gallery.ui.albums.photos.photoDetails;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ru.maxden.gallery.R;

public class PhotoDetailActivity extends Activity {
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        mImage = findViewById(R.id.image_full);
        Glide.with(this)
                .load(getIntent().getStringExtra("photo"))
                .into(mImage);
    }
}
