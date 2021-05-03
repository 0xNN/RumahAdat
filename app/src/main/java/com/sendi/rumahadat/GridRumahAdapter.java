package com.sendi.rumahadat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

public class GridRumahAdapter extends RecyclerView.Adapter<GridRumahAdapter.GridViewHolder> {

    private ArrayList<RumahAdat> listRumah;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public GridRumahAdapter(ArrayList<RumahAdat> list) {
        this.listRumah = list;
    }
    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_rumah_adat, viewGroup, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GridViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(listRumah.get(position).getFoto())
                .apply(new RequestOptions().override(350,350))
                .into(holder.imgFoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listRumah.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRumah.size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder {

        ImageView imgFoto;

        GridViewHolder(View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.img_item_foto);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(RumahAdat data);
    }
}
