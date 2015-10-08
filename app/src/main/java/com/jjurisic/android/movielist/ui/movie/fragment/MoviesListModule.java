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

package com.jjurisic.android.movielist.ui.movie.fragment;


import com.jjurisic.android.movielist.ui.movie.fragment.presenter.MoviesPresenter;
import com.jjurisic.android.movielist.ui.movie.fragment.presenter.MoviesPresenterImpl;
import com.jjurisic.android.movielist.ui.movie.fragment.view.MoviesView;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesListModule {

    private MoviesView view;

    public MoviesListModule(MoviesView view) {
        this.view = view;
    }

    @Provides
    public MoviesView provideView() {
        return view;
    }

    @Provides
    public MoviesPresenter providePresenter(MoviesView mainView) {
        return new MoviesPresenterImpl(mainView);
    }
}