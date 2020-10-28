package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    List<Movie>movieList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        movieList= new ArrayList<>();
        adapter = new Adapter(getApplicationContext(),movieList);
        recyclerView.setAdapter(adapter);


        ApiInterface apiService = ApiCLient.getClient().create(ApiInterface.class);
        Call<List<Movie>> call = apiService.getMovies();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
               movieList=response.body();
                Log.d("TAG","Response = "+movieList);
                adapter.setMovieList(movieList);

            }
            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }

}