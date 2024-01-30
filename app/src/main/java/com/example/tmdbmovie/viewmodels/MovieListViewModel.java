package com.example.tmdbmovie.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tmdbmovie.models.MovieModel;
import com.example.tmdbmovie.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {




private MovieRepository movieRepository;
    public MovieListViewModel() {
       movieRepository= MovieRepository.getInstance();

    }


    //getter
    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies()  ;
    }
    public LiveData<List<MovieModel>> getPop(){
        return movieRepository.getPop()  ;
    }

// calling method in viewModel
    public void searchMovieApi(String query,int pagenumber)
    {
        movieRepository.searchMovieApi(query, pagenumber);
    }

    public void searchMoviePop(int pagenumber)
    {
        movieRepository.searchMoviePop( pagenumber);
    }
    public void searchnextPage(){

        movieRepository.searchnextPage();
    }
}
