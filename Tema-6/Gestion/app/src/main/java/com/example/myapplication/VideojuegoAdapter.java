package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class VideojuegoAdapter extends RecyclerView.Adapter<VideojuegoAdapter.VideojuegoViewHolder> {

    private List<Videojuego> videojuegos;

    public VideojuegoAdapter(List<Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }

    @NonNull
    @Override
    public VideojuegoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.videojuego_item, parent, false);
        return new VideojuegoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideojuegoViewHolder holder, int position) {
        Videojuego videojuego = videojuegos.get(position);
        holder.tvNombre.setText(videojuego.getNombre());
        holder.tvDescripcion.setText(videojuego.getDescripcion());
        holder.ratingBar.setRating(videojuego.getValoracion());
        holder.imgPortada.setImageResource(videojuego.getPortadaResId()); // Asigna la imagen
    }

    @Override
    public int getItemCount() {
        return videojuegos.size();
    }

    static class VideojuegoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvDescripcion;
        RatingBar ratingBar;
        ImageView imgPortada;

        VideojuegoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imgPortada = itemView.findViewById(R.id.imgPortada); // Referencia a la imagen
        }
    }
}
