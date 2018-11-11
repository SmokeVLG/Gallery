package ru.maxden.gallery.ui.albums;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Random;

import ru.maxden.gallery.App;
import ru.maxden.gallery.R;
import ru.maxden.gallery.data.entity.AlbumModel;
import ru.maxden.gallery.ui.albums.photos.PhotosActivity;

public class AlbumsFragment extends Fragment implements OnItemClickListener, AlbumsContract.View{
    private ListView mListView;
    private AlbumsPresenter mPresenter;
    private ArrayList<AlbumModel> mListRandom;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListRandom = new ArrayList<>();
        mPresenter = new AlbumsPresenter(App.get(getContext()).getService());
        mPresenter.bind(this);
        mPresenter.getAlbums();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_albums, container, false);
        mListView = view.findViewById(R.id.list_view_albums);
        mListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), PhotosActivity.class);
        intent.putExtra("albumId", ((AlbumModel)parent.getItemAtPosition(position)).getUserId());
        Log.d("albumId", "onItemClick: " + ((AlbumModel)parent.getItemAtPosition(position)).getUserId());
        startActivity(intent);
    }

    @Override
    public void getAllAlbums(ArrayList<AlbumModel> arrayList) {
        Random random = new Random();
        for (int i = 0; i < 14; i++) {
            mListRandom.add(i, arrayList.get(random.nextInt(arrayList.size())));
        }
        AlbumAdapter adapter = new AlbumAdapter(getContext(), mListRandom);
        mListView.setAdapter(adapter);
    }

    @Override
    public void showGetAlbumsError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }
}
