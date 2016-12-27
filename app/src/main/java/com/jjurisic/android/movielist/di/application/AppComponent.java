package com.jjurisic.android.movielist.di.application;


import com.jjurisic.android.movielist.App;
import com.jjurisic.android.movielist.data.DataManagerInterface;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by Josip Jurisic
 */

@ApplicationScope
@Component(modules = {
        AppModule.class,
        DataModule.class,
        PicassoModule.class,
})
public interface AppComponent {

    DataManagerInterface getDataManager();

    Picasso getPicasso();

    void inject(App app);

}