package com.youknow.gcmovie.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youknow.gcmovie.R;
import com.youknow.gcmovie.data.model.Result;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder> {

    private Context context;
    private List<Result> movies = new ArrayList<>();

    public MoviesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MovieHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int i) {
        final Result movie = movies.get(i);

        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + movie.getPosterPath()).into(holder.ivPoster);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvReleaseDate.setText(movie.getReleaseDate());
        holder.tvRatingAvg.setText(String.valueOf(movie.getVoteAverage()));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(List<Result> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    class MovieHolder extends RecyclerView.ViewHolder {

        ImageView ivPoster;
        TextView tvTitle;
        TextView tvReleaseDate;
        TextView tvRatingAvg;

        MovieHolder(View itemView) {
            super(itemView);

            ivPoster = itemView.findViewById(R.id.ivPoster);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate);
            tvRatingAvg = itemView.findViewById(R.id.tvRatingAvg);
        }
    }
}
