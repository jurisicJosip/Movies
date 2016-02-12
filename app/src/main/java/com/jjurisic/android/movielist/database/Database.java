package com.jjurisic.android.movielist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.jjurisic.android.model.MovieModel;
import com.jjurisic.android.sort.MovieSortType;

import java.util.ArrayList;
import java.util.List;

import hugo.weaving.DebugLog;

/**
 * Created by Josip Jurisic
 */
public class Database extends SQLiteOpenHelper {

    public static final String TABLE_MOVIES = "movies";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_MOVIE_ID = "movie_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_POPULARITY = "popularity";
    public static final String COLUMN_GENRES = "genres";
    public static final String COLUMN_RELEASE_DATE = "release_date";
    public static final String COLUMN_POSTER_PATH = "poster_path";
    public static final String COLUMN_WEBSITE = "website";
    public static final String COLUMN_VOTE_AVERAGE = "vote_average";
    public static final String COLUMN_MOVIE_TYPE = "movie_type";

    private static final String DATABASE_NAME = "cities.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_MOVIES + "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_MOVIE_ID + " integer not null, "
            + COLUMN_NAME + " text, "
            + COLUMN_DESCRIPTION + " text, "
            + COLUMN_POPULARITY + " text, "
            + COLUMN_RELEASE_DATE + " text, "
            + COLUMN_GENRES + " text, "
            + COLUMN_POSTER_PATH + " text , "
            + COLUMN_WEBSITE + " text, "
            + COLUMN_MOVIE_TYPE + " text, "
            + COLUMN_VOTE_AVERAGE + " text);";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @DebugLog
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //ok nothing
    }

    public void insertMovie(long id, String title, double popular, String releaseDate, String posterPath, MovieSortType type) {
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.query(TABLE_MOVIES, null, COLUMN_MOVIE_ID + "=" + "'" + id + "'", null, null, null, null);

        if (cursor.moveToFirst()) {
            MovieModel movieModel = getMovieModelFrom(cursor);
            if (movieModel != null) {
                database.close();
                cursor.close();
                return; //ok we have one in the database already
            }
        }

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_MOVIE_ID, id);
        cv.put(COLUMN_NAME, title);
        cv.put(COLUMN_POPULARITY, popular);
        cv.put(COLUMN_RELEASE_DATE, releaseDate);
        cv.put(COLUMN_POSTER_PATH, posterPath);
        cv.put(COLUMN_MOVIE_TYPE, type.name());

        database.insert(TABLE_MOVIES, null, cv);
        cursor.close();
        database.close();
    }

    public void updateMovie(long id, String description, String homepage, String genres) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_WEBSITE, homepage);
        cv.put(COLUMN_GENRES, genres);

        SQLiteDatabase database = getWritableDatabase();
        database.update(TABLE_MOVIES, cv, COLUMN_MOVIE_ID + "='" + id + "'", null);
        database.close();
    }

    @Nullable
    public MovieModel getMovie(long id) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_MOVIES, null, COLUMN_MOVIE_ID + "=" + "'" + id + "'", null, null, null, null);
        if (cursor == null) {
            return null;
        }

        cursor.moveToFirst();
        MovieModel movieModel = getMovieModelFrom(cursor);
        cursor.close();
        database.close();

        return movieModel;
    }

    public List<MovieModel> getMovies(MovieSortType sortType) {
        List<MovieModel> movies = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_MOVIES, null, COLUMN_MOVIE_TYPE + "='" + sortType.name() + "'", null, null, null, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            MovieModel movie = getMovieModelFrom(cursor);
            movies.add(movie);
        }

        database.close();
        cursor.close();
        return movies;
    }

    public void removeMoviesFrom(MovieSortType movieSortType) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete(TABLE_MOVIES, COLUMN_MOVIE_TYPE + " = " + "'" + movieSortType.name() + "'", null);
        database.close();
    }

    private MovieModel getMovieModelFrom(Cursor cursor) {
        long movieId = cursor.getLong(cursor.getColumnIndex(COLUMN_MOVIE_ID));
        String title = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
        String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
        String posterPath = cursor.getString(cursor.getColumnIndex(COLUMN_POSTER_PATH));
        String genres = cursor.getString(cursor.getColumnIndex(COLUMN_GENRES));
        String releaseDate = cursor.getString(cursor.getColumnIndex(COLUMN_RELEASE_DATE));
        String websiteUrl = cursor.getString(cursor.getColumnIndex(COLUMN_WEBSITE));
        double voteAverage = cursor.getDouble(cursor.getColumnIndex(COLUMN_VOTE_AVERAGE));
        double popularity = cursor.getDouble(cursor.getColumnIndex(COLUMN_POPULARITY));
        return new MovieModel(movieId, popularity, title, description, posterPath, genres, releaseDate, websiteUrl, voteAverage);
    }
}