package com.example.tmdbmovie.utils;

import com.example.tmdbmovie.models.MovieModel;
import com.example.tmdbmovie.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    //search for movie
//
    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchMovies(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int  page
    );




    // https://api.themoviedb.org/3/movie/popular?api_key=52a18783ed514602a5facb15a0177e61&language=en-US&page=1
    @GET("/3/movie/popular")
    Call<MovieSearchResponse> getPopular(
            @Query("api_key") String key,
            @Query("page") int page

    );



//    making search with id

    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
           @Path("movie_id")int movie_id,
           @Query("api_key") String api_key
    );
}
