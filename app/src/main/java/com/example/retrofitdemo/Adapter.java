package com.example.retrofitdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    List<Movie> movieList;
    Context context;

    public Adapter(Context context, List<Movie> movieList) {
        this.movieList = movieList;
        this.context=context;
    }
    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.showdata,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie movie=movieList.get(position);
        holder.title.setText(movie.getTitle());
        holder.id.setText(String.valueOf(movie.getId()));
        holder.name.setText(movie.getName());
        Glide.with(context).load(movie.getImageUrl())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

   public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,id,name;
        CircleImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
          image=(CircleImageView) itemView.findViewById(R.id.img);
          title = (TextView) itemView.findViewById(R.id.title);
            id = (TextView) itemView.findViewById(R.id.id);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
