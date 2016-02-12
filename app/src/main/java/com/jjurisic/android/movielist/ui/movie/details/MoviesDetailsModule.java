/*
 *
 *  *
 *  *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *  *
 *
 */

package com.jjurisic.android.movielist.ui.movie.details;


import android.content.Context;

import com.jjurisic.android.movielist.database.Database;
import com.jjurisic.android.movielist.database.MoviesDatabase;
import com.jjurisic.android.movielist.database.MoviesDatabaseImpl;
import com.jjurisic.android.movielist.interactors.MoviesInteractor;
import com.jjurisic.android.movielist.interactors.MoviesInteractorImpl;
import com.jjurisic.android.movielist.model.DataManager;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesDetailsModule {

    private final MovieDetailsView view;
    private final Context context;

    public MoviesDetailsModule(MovieDetailsView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Provides
    public MovieDetailsView provideView() {
        return view;
    }

    @Provides
    public MoviesInteractor provideMovieInteractor() {
        return new MoviesInteractorImpl();
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    public Database provideDatabase(Context context) {
        return new Database(context);
    }

    @Provides
    public MoviesDatabase provideMoviesDatabase(Database database) {
        return new MoviesDatabaseImpl(database);
    }

    @Provides
    public DataManager provideDataManager(MoviesInteractor interactor, MoviesDatabase database) {
        return new DataManager(interactor, database);
    }

    @Provides
    public MovieDetailsPresenter providePresenter(MovieDetailsView mainView, DataManager dataManager) {
        return new MovieDetailsPresenterImpl(mainView, dataManager);
    }
}