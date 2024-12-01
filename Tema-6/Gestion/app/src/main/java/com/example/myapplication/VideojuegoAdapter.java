package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class VideojuegoAdapter extends RecyclerView.Adapter<VideojuegoAdapter.VideojuegoViewHolder> {

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    private List<Videojuego> videojuegos;
    private OnItemLongClickListener longClickListener;

    public VideojuegoAdapter(List<Videojuego> videojuegos, OnItemLongClickListener longClickListener) {
        this.videojuegos = videojuegos;
        this.longClickListener = longClickListener;
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
        holder.imgPortada.setImageResource(videojuego.getPortadaResId());

        // Configurar long click para mostrar menú contextual
        holder.itemView.setOnLongClickListener(v -> {
            longClickListener.onItemLongClick(holder.getAdapterPosition());
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return videojuegos.size();
    }

    static class VideojuegoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvDescripcion;
        ImageView imgPortada;

        VideojuegoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            imgPortada = itemView.findViewById(R.id.imgPortada);
        }
    }
}
