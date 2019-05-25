package com.youknow.gcmovie.ui.details;

import com.youknow.gcmovie.R;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.tvMovieTitle) TextView tvMovieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        String title = getIntent().getStringExtra("MOVIE");
        tvMovieTitle.setText(title);
    }

}
