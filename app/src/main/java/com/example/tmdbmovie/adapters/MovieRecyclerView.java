package com.example.tmdbmovie.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tmdbmovie.R;
import com.example.tmdbmovie.models.MovieModel;
import com.example.tmdbmovie.utils.Credentials;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

private List<MovieModel> mMovies;
private OnMovieListener onMovieListener;
    private static final int DISPLAY_POP = 1;
    private static final int DISPLAY_SEARCH = 2;


    public MovieRecyclerView(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
   View view=null;
   if (viewType == DISPLAY_SEARCH){
       view=LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
   return new MovieViewHolder(view,onMovieListener);
   }
   else {
       view=LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_layout,parent,false);
       return new Popular_view_holder(view,onMovieListener);

   }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
           int itemViewType=getItemViewType(position);
        if (itemViewType == DISPLAY_SEARCH){

            // vote average is over 10, and our rating bar is over 5 stars: dividing by 2
            ((MovieViewHolder)holder).ratingBar.setRating((mMovies.get(position).getVote_average())/2);

            // ImageView: Using Glide Library
            Glide.with(holder.itemView.getContext())
                    .load( "https://image.tmdb.org/t/p/w500/"
                            +mMovies.get(position).getPoster_path())
                    .into(((MovieViewHolder)holder).imageView);

        }else{
            ((Popular_view_holder)holder).ratingBar_pop.setRating(mMovies.get(position).getVote_average());

            // ImageView: Using Glide Library
            Glide.with(holder.itemView.getContext())
                    .load( "https://image.tmdb.org/t/p/w500/"
                            +mMovies.get(position).getPoster_path())
                    .into(((Popular_view_holder)holder).imageView_pop);

        }

    }

    @Override
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        }
        return 0;
    }

    public void setmMovies(List<MovieModel> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }
    // Getting the id of the movie clicked
    public MovieModel getSelectedMovie(int position){
        if (mMovies != null){
            if (mMovies.size() > 0){
                return mMovies.get(position);
            }
        }
        return  null;
    }

    @Override
    public int getItemViewType(int position) {
        if (Credentials.POPULAR){
            return DISPLAY_POP;
        }
        else
            return DISPLAY_SEARCH;
    }
}
