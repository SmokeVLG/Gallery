package ru.maxden.gallery.ui.albums.photos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;


import java.util.ArrayList;

import ru.maxden.gallery.App;
import ru.maxden.gallery.R;
import ru.maxden.gallery.data.entity.PhotoModel;
import ru.maxden.gallery.data.network.RetrofitService;
import ru.maxden.gallery.ui.albums.photos.photoDetails.PhotoDetailActivity;

public class PhotosActivity extends Activity implements OnItemClickListener, PhotosContract.View{

    private GridView mGridView;
    private PhotosPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        initUI();
        init();
    }

    private void initUI() {
        mGridView = findViewById(R.id.grid_view_photos);
        mGridView.setOnItemClickListener(this);

    }

    private void init() {
        RetrofitService service = App.get(this).getService();
        mPresenter = new PhotosPresenter(service);
        mPresenter.bind(this);
        mPresenter.getPhotos(getIntent().getIntExtra("albumId", 0));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), PhotoDetailActivity.class);
        intent.putExtra("photo", ((PhotoModel)parent.getItemAtPosition(position)).getUrl());
        startActivity(intent);
    }

    @Override
    public void getAllPhotos(ArrayList<PhotoModel> arrayList) {
        mGridView.setAdapter(new PhotosAdapter(this, arrayList));
    }

    @Override
    public void showGetPhotosError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
