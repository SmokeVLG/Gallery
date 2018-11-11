package ru.maxden.gallery.ui.albums;

import java.util.ArrayList;

import ru.maxden.gallery.data.entity.AlbumModel;

public interface AlbumsContract {

    interface View {
        void getAllAlbums(ArrayList<AlbumModel> arrayList);

        void showGetAlbumsError(String msg);
    }

    interface Presenter {
        void getAlbums();
    }
}
