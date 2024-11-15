package com.example.antonio_prueba3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

    private List<Animal> animals;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Animal animal);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    private OnItemLongClickListener longClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.longClickListener = listener;
    }

    public AnimalAdapter(List<Animal> animals) {
        this.animals = animals;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.animal_item, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal animal = animals.get(position);
        holder.tvNombre.setText(animal.getNombre());
        holder.tvDescripcion.setText(animal.getDescripcion());
        holder.imgAnimal.setImageResource(animal.getFoto());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(animal);
                }
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onItemLongClick(position);
                return true;
            }
            return false;
        });

    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    static class AnimalViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvDescripcion;
        ImageView imgAnimal;

        AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            imgAnimal = itemView.findViewById(R.id.imgPortada);
        }
    }
}
