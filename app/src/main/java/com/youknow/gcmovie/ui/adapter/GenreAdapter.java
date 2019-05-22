package com.youknow.gcmovie.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.youknow.gcmovie.R;
import com.youknow.gcmovie.data.model.Genre;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreHolder> {

    private final List<Genre> genres;

    public GenreAdapter(List<Genre> genres) {
        this.genres = genres;
    }

    @NonNull
    @Override
    public GenreHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new GenreHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_genre, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GenreHolder holder, int position) {
        holder.tvGenre.setText(genres.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    class GenreHolder extends RecyclerView.ViewHolder {

        final TextView tvGenre;

        GenreHolder(@NonNull View itemView) {
            super(itemView);

            tvGenre = itemView.findViewById(R.id.tvGenre);
        }

    }
}
