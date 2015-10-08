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

package com.jjurisic.android.movielist.ui.webview;


import com.jjurisic.android.movielist.ui.webview.presenter.WebViewPresenter;
import com.jjurisic.android.movielist.ui.webview.presenter.WebViewPresenterImpl;
import com.jjurisic.android.movielist.ui.webview.view.WebActivityView;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieWebModule {

    private WebActivityView view;

    public MovieWebModule(WebActivityView view) {
        this.view = view;
    }

    @Provides
    public WebActivityView provideView() {
        return view;
    }

    @Provides
    public WebViewPresenter providePresenter(WebActivityView mainView) {
        return new WebViewPresenterImpl(mainView);
    }
}