package ru.maxden.gallery.ui.albums.photos;


import java.util.ArrayList;

import ru.maxden.gallery.data.entity.PhotoModel;

public interface PhotosContract {

    interface View {
        void getAllPhotos(ArrayList<PhotoModel> arrayList);

        void showGetPhotosError(String msg);

    }

    interface Presenter  {
        void getPhotos(int albumId);
    }
}
