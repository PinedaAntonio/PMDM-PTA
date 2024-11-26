package com.example.antoniopinedaprueba4;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PintorAdapter extends RecyclerView.Adapter<PintorAdapter.PintorViewHolder> {

    private List<Pintor> pintors;

    public PintorAdapter(List<Pintor> pintors) {
        this.pintors = pintors;
    }

    @NonNull
    @Override
    public PintorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pintor_item, parent, false);
        return new PintorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PintorViewHolder holder, int position) {
        Pintor pintor = pintors.get(position);

        holder.tvNombre.setText(pintor.getNombre());
        holder.imgPintor.setImageResource(pintor.getFoto());

        holder.spinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 0) {
                    Intent intent = new Intent(holder.itemView.getContext(), WebViewActivity.class);
                    String wikiUrl = getWikipediaUrl(pintor.getNombre());
                    intent.putExtra("URL", wikiUrl);
                    holder.itemView.getContext().startActivity(intent);
                    //Esta línea carga la wikipedia, pero hay un error al cargar, por eso está comentada
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });
    }

    @Override
    public int getItemCount() {
        return pintors.size();
    }

    private String getWikipediaUrl(String nombre) {
        switch (nombre.toUpperCase()) {
            case "CARAVAGGIO":
                return "http://es.wikipedia.org/wiki/Caravaggio";
            case "RAFAEL SANZIO":
                return "http://es.wikipedia.org/wiki/Rafael_Sanzio";
            case "VELAZQUEZ":
                return "http://es.wikipedia.org/wiki/Diego_Vel%C3%A1zquez";
            case "MIGUEL ANGEL":
                return "http://es.wikipedia.org/wiki/Miguel_%C3%81ngel";
            case "REMBRANDT":
                return "http://es.wikipedia.org/wiki/Rembrandt";
            case "BOTICELLI":
                return "http://es.wikipedia.org/wiki/Sandro_Botticelli";
            case "LEONARDO DA VINCI":
                return "http://es.wikipedia.org/wiki/Leonardo_da_Vinci";
            case "RENOIR":
                return "http://es.wikipedia.org/wiki/Pierre-Auguste_Renoir";
            default:
                return "http://es.wikipedia.org";
        }
    }

    static class PintorViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        ImageView imgPintor;
        Spinner spinnerOptions;

        PintorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            imgPintor = itemView.findViewById(R.id.imgPortada);
            spinnerOptions = itemView.findViewById(R.id.spinnerOptions);
        }
    }
}
