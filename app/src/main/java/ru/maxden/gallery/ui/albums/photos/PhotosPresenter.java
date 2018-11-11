package ru.maxden.gallery.ui.albums.photos;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.maxden.gallery.data.entity.PhotoModel;
import ru.maxden.gallery.data.network.RetrofitService;

public class PhotosPresenter implements PhotosContract.Presenter{

    private RetrofitService retrofitService;
    private PhotosContract.View mView;


    public PhotosPresenter(RetrofitService service){
        this.retrofitService = service;
    }

    @Override
    public void getPhotos(int albumId) {

        retrofitService.getCallbackPhotos(albumId).enqueue(new Callback<ArrayList<PhotoModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PhotoModel>> call, Response<ArrayList<PhotoModel>> response) {
                if (response.isSuccessful() && response.body() != null){
                    if (isViewAttached()){
                        mView.getAllPhotos(response.body());

                    }
                } else {
                    if (isViewAttached()){
                        mView.showGetPhotosError(response.message());

                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PhotoModel>> call, Throwable t) {
                if (isViewAttached()){
                    mView.showGetPhotosError(t.getMessage());

                }
            }
        });
    }


    public void bind(PhotosContract.View view) {
        this.mView = view;
    }


    private boolean isViewAttached(){
        return mView != null;
    }
}
