package com.jjurisic.android.movielist.presentation;

import com.jjurisic.android.movielist.data.DataManagerInterface;
import com.jjurisic.android.movielist.model.model.MovieModel;
import com.jjurisic.android.movielist.ui.movie.details.MovieDetailsView;
import com.jjurisic.android.movielist.utils.DateTimeUtils;
import com.jjurisic.android.movielist.utils.ImageUtils;
import com.jjurisic.android.movielist.utils.StringUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by Josip Jurisic
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieDetailsPresenterImplTest {

    private static final long MOVIE_ID = 3;

    @Mock
    MovieDetailsView view;

    @Mock
    DataManagerInterface dataManager;

    @Mock
    Observable<MovieModel> movieModelObservable;

    MovieDetailsPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new MovieDetailsPresenterImpl(dataManager);
        presenter.setView(view);
    }

    @Test
    public void testRequestMovieDetailsShowProgress() {
        when(dataManager.getMovie(anyLong())).thenReturn(movieModelObservable);
        presenter.requestMovieDetails();
        verify(view).showProgress();
    }

    @Test
    public void testBindMovieObserverCallOnCompleteHideProgress() {
        presenter.bindMovieObserver().onCompleted();
        verify(view).hideProgress();
    }

    @Test
    public void testBindMovieObserverCallOnNextWithNullModelShowError() {
        presenter.bindMovieObserver().onNext(null);
        verify(view).showCannotGetMovieDetailsError();
    }

    @Test
    public void testBindMovieObserverCallOnNextWithMovieModelShowMovieDetails() {
        MovieModel data = new MovieModel();
        presenter.bindMovieObserver().onNext(data);
        verify(view).showMovieTitle(data.getName());
        verify(view).showMovieTitle(data.getName());
        verify(view).showMovieOverview(data.getDescription());
        verify(view).showMovieGenres(data.getGenres());
        verify(view).showMovieHomePage(data.getWebsiteUrl());
        verify(view).showRating(String.valueOf(data.getVoteAverage()));
        verify(view).showPopularity(StringUtils.formatPopularity(data.getPopularity()));
        verify(view).showMovieImage(ImageUtils.getImageThumbFrom(data.getPosterPath()));
        verify(view).showMovieDate(DateTimeUtils.getFormatedDateOrEmpty(data.getReleaseDate()));
    }

    @Test
    public void testBindMovieObserverCallOnErrorWithNullHideProgress() {
        presenter.bindMovieObserver().onError(null);
        verify(view).hideProgress();
    }

    @Test
    public void testBindMovieObserverCallOnErrorWithNullShowError() {
        presenter.bindMovieObserver().onError(null);
        verify(view).showCannotGetMovieDetailsError();
    }

    @Test
    public void testLoadPosterShowPosterWithNullMovieModelShowError() {
        presenter.loadPoster();
        verify(view).showCannotShowMoviePosterError();
    }

    @Test
    public void testLoadPosterShowPosterWithMovieModelShowPoster() {
        MovieModel data = new MovieModel();
        presenter.bindMovieObserver().onNext(data);
        presenter.loadPoster();
        verify(view).showPoster(data.getName(), data.getPosterPath());
    }

    @Test
    public void testLoadMovieHomepageWithNullMovieModelShowError(){
        presenter.loadMovieHomepage();
        verify(view).cannotShowMovieHomepageError();
    }

    @Test
    public void testLoadMovieHomepageWitMovieModelShowHomepage(){
        MovieModel data = new MovieModel();
        presenter.bindMovieObserver().onNext(data);
        presenter.loadMovieHomepage();
        verify(view).showMovieHomePage(data.getWebsiteUrl());
    }

    @Test
    public void setMovieIdVerifyNoViewInteractions(){
        presenter.setMovieId(MOVIE_ID);
        verifyZeroInteractions(view);
    }
}