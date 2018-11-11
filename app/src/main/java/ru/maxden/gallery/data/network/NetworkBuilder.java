package ru.maxden.gallery.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.maxden.gallery.config.AppConstants;

public class NetworkBuilder {
    private static RetrofitService sService = null;

    public static RetrofitService initService() {
        if (sService == null) {
            sService = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(AppConstants.BASE_URL)
                    .build()
                    .create(RetrofitService.class);
        }
        return sService;
    }

}
