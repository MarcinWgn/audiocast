package com.example.audiocast;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.images.WebImage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RlistAdapter extends RecyclerView.Adapter<RlistAdapter.ViewHolder> {

    private final List<RadioStation> radioStations;
    private final ClickListener clickListener;


    public RlistAdapter(List<RadioStation>radioStations, ClickListener clickListener){
        this.radioStations = radioStations;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.radio_row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get().load(radioStations.get(position).getImg()).into(holder.imageView);
        holder.name.setText(radioStations.get(position).getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return radioStations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView name;
        private final View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_text_view);
            view = itemView.findViewById(R.id.row_view);
            imageView = itemView.findViewById(R.id.station_image_view);
        }
    }
}
