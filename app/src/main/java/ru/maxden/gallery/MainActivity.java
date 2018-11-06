package ru.maxden.gallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button galleryButton = findViewById(R.id.btn_gallery);
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGalleryActivity();
            }
        });

        startGalleryActivity();
    }

    public void startGalleryActivity() {
        ArrayList<String> images = new ArrayList<String>();
        images.add("http://mirpozitiva.ru/uploads/posts/2016-09/1474011212_14.jpg");
        images.add("http://mirpozitiva.ru/uploads/posts/2016-09/1474011186_02.jpg");
        images.add("http://mirpozitiva.ru/uploads/posts/2016-09/1474011156_05.jpg");
        images.add("http://mirpozitiva.ru/uploads/posts/2016-09/1474011212_09.jpg");
        images.add("http://mirpozitiva.ru/uploads/posts/2016-09/1474011133_06.jpg");
        Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
        intent.putStringArrayListExtra(GalleryActivity.EXTRA_NAME, images);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

