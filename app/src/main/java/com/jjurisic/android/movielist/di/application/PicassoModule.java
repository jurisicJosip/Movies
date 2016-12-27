package com.jjurisic.android.movielist.di.application;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by josipjurisic on 06/12/2016.
 */

@Module(includes = {AppContextModule.class, NetworkModule.class})
public class PicassoModule {

    @Provides
    @ApplicationScope
    public OkHttp3Downloader provideOkHttp3Downloader(OkHttpClient client) {
        return new OkHttp3Downloader(client);
    }

    @Provides
    @ApplicationScope
    public Picasso providePicasso(OkHttp3Downloader downloader, Context context) {
        return new Picasso.Builder(context)
                .downloader(downloader)
                .build();
    }
}