package ru.maxden.gallery.ui.albums;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.maxden.gallery.data.entity.AlbumModel;
import ru.maxden.gallery.data.network.RetrofitService;

public class AlbumsPresenter implements AlbumsContract.Presenter {
    private RetrofitService mService;
    private AlbumsContract.View mView;

    public AlbumsPresenter(RetrofitService service) {
        this.mService = service;
    }

    @Override
    public void getAlbums() {


        mService.getCallbackAlbums().enqueue(new Callback<ArrayList<AlbumModel>>() {
            @Override
            public void onResponse(Call<ArrayList<AlbumModel>> call, Response<ArrayList<AlbumModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (isViewAttached()) {
                        mView.getAllAlbums(response.body());
                    }
                } else {
                    if (isViewAttached()) {
                        mView.showGetAlbumsError(response.message());

                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AlbumModel>> call, Throwable t) {
                if (isViewAttached()) {
                    mView.showGetAlbumsError(t.getMessage());
                }
            }
        });
    }


    public void bind(AlbumsContract.View view) {
        this.mView = view;
    }


    public void unbind() {
        this.mView = null;
    }

    private boolean isViewAttached() {
        return mView != null;
    }
}
