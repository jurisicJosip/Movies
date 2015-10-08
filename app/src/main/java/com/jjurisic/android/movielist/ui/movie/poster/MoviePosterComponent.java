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

import com.jjurisic.android.movielist.ActivityScope;
import com.jjurisic.android.movielist.AppComponent;
import com.jjurisic.android.movielist.ui.movie.poster.activity.MoviePosterActivity;
import com.jjurisic.android.movielist.ui.movie.poster.presenter.MoviePosterPresenter;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MoviePosterModule.class)
public interface MoviePosterComponent {

    void inject(MoviePosterActivity activity);

    MoviePosterPresenter getMoviePosterPresenter();

}