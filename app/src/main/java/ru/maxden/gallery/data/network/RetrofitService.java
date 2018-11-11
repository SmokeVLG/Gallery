package ru.maxden.gallery.data.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.maxden.gallery.config.AppConstants;
import ru.maxden.gallery.data.entity.AlbumModel;
import ru.maxden.gallery.data.entity.PhotoModel;

public interface RetrofitService {

    @GET(AppConstants.ALBUMS_ENDPOINT)
    Call<ArrayList<AlbumModel>> getCallbackAlbums();

    @GET(AppConstants.PHOTOS_ENDPOINT)
    Call<ArrayList<PhotoModel>> getCallbackPhotos(@Query("albumId") int albumId);
}
