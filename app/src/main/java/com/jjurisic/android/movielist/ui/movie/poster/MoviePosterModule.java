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

package com.jjurisic.android.movielist.ui.movie.poster;


import com.jjurisic.android.movielist.ui.movie.poster.presenter.MoviePosterPresenter;
import com.jjurisic.android.movielist.ui.movie.poster.presenter.MoviePosterPresenterImpl;
import com.jjurisic.android.movielist.ui.movie.poster.view.MoviePosterView;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviePosterModule {

    private MoviePosterView view;

    public MoviePosterModule(MoviePosterView view) {
        this.view = view;
    }

    @Provides
    public MoviePosterView provideView() {
        return view;
    }

    @Provides
    public MoviePosterPresenter providePresenter(MoviePosterView mainView) {
        return new MoviePosterPresenterImpl(mainView);
    }
}