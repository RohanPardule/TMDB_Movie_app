package com.example.tmdbmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;

import com.example.tmdbmovie.adapters.MovieRecyclerView;
import com.example.tmdbmovie.adapters.OnMovieListener;
import com.example.tmdbmovie.models.MovieModel;
import com.example.tmdbmovie.request.Servicey;
import com.example.tmdbmovie.response.MovieSearchResponse;
import com.example.tmdbmovie.utils.Credentials;
import com.example.tmdbmovie.utils.MovieApi;
import com.example.tmdbmovie.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {
private RecyclerView recyclerView;
private MovieRecyclerView movieRecyclerAdapter;



    // ViewModel

    private MovieListViewModel movieListViewModel;

    boolean isPopular=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //SearchView
        setupSearchView();
          recyclerView=findViewById(R.id.recyclerView);

        movieListViewModel= new ViewModelProvider(this).get(MovieListViewModel.class);

       
        ConfigureRecyclerView();
        ObserveAnyChange();
        ObservePopularMovies();

        movieListViewModel.searchMoviePop(1);



    }

    private void ObservePopularMovies() {
        movieListViewModel.getPop().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                //observing for any data change
                if(movieModels != null)
                {
                    for(MovieModel movieModel: movieModels){
                        Log.v("Tag", movieModel.getTitle());
                        movieRecyclerAdapter.setmMovies(movieModels);
                    }
                }
            }
        });
    }


    //observing any data change

    private void ObserveAnyChange()
    {
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                //observing for any data change
if(movieModels != null)
{
    for(MovieModel movieModel: movieModels){
        Log.v("Tag", movieModel.getTitle());
        movieRecyclerAdapter.setmMovies(movieModels);
    }
}
            }
        });
    }




    private void ConfigureRecyclerView(){
        movieRecyclerAdapter=new MovieRecyclerView(this);
        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
if(!recyclerView.canScrollVertically(1))
{
    //here we need to display next search results
    movieListViewModel.searchnextPage();

}
            }
        });
    }

    @Override
    public void onMovieClick(int position) {
        Intent intent = new Intent(this, MovieDetails.class);
        intent.putExtra("movie", movieRecyclerAdapter.getSelectedMovie(position));
        startActivity(intent);

    }

    @Override
    public void onCategoryClick(String category) {

    }
    private void setupSearchView() {
        final SearchView searchView=findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovieApi(query,1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPopular=false;
            }
        });
    }



//    private void GetRetrofitResponse() {
//    MovieApi movieApi=Servicey.getMovieApi();
//
//    Call<MovieSearchResponse> responseCall=
//            movieApi.searchMovies(
//                    Credentials.API_KEY,
//                     "Action",
//                    1);
//
//    responseCall.enqueue(new Callback<MovieSearchResponse>() {
//        @Override
//        public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
//            if(response.code()==200)
//            {
////                Log.v("tag","the response"+ response.body().toString());
//
//                List<MovieModel> movies =new ArrayList<>(response.body().getMovies());
//                for (MovieModel movie: movies)
//                {
//                    Log.v("tag","the releasedate "+ movie.getTitle());
//
//                }
//            }
//            else {
//                try{
//                    Log.v("tag","error"+ response.errorBody().toString());
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//
//        @Override
//        public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
//
//        }
//    });
//
//    }
//
//    private  void  GetRetrofitResponseAccordingToID()
//    {
//        MovieApi movieApi=Servicey.getMovieApi();
//
//        Call<MovieModel> responseCall=movieApi.getMovie(550,
//                Credentials.API_KEY);
//
//        responseCall.enqueue(new Callback<MovieModel>() {
//            @Override
//            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
//                if (response.code()==200)
//                {
//
//                    MovieModel movie=response.body();
//                    Log.v("Tag", "The Response "+ movie.getTitle());
//                }
//                else{
//                    try{
//                        Log.v("Tag","Error"+ response.errorBody());
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieModel> call, Throwable t) {
//
//            }
//        });
//    }






}