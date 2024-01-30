package com.example.tmdbmovie.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tmdbmovie.models.MovieModel;
import com.example.tmdbmovie.request.MovieApiCLient;

import java.util.List;

public class MovieRepository {
    //this class is acting as repository

    private static MovieRepository instance;
 private MovieApiCLient movieApiCLient;
 private String mQuery;
 private int mpageNumber;

    public static MovieRepository getInstance(){
        if (instance==null){
            instance =new MovieRepository();
        }
        return instance;
    }
    private MovieRepository(){

     movieApiCLient=MovieApiCLient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){

        return movieApiCLient.getMovies();
    }
    public LiveData<List<MovieModel>> getPop(){

        return movieApiCLient.getMoviesPop();
    }
    //calling the method
    public void searchMovieApi(String query,int pageNumber){


        mQuery=query;
        mpageNumber=pageNumber;
        movieApiCLient.searchMoviesApi(query,pageNumber);
    }
    public void searchMoviePop(int pageNumber){



        mpageNumber=pageNumber;
        movieApiCLient.searchMoviesPop(pageNumber);
    }


    public void searchnextPage(){
        searchMovieApi(mQuery,mpageNumber+1);
    }

}
