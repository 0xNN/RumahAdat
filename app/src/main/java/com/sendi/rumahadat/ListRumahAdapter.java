package com.sendi.rumahadat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

public class ListRumahAdapter extends RecyclerView.Adapter<ListRumahAdapter.ListViewHolder> {

    private ArrayList<RumahAdat> listRumah;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListRumahAdapter(ArrayList<RumahAdat> list) {
        this.listRumah = list;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rumah_adat, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        RumahAdat rumahAdat = listRumah.get(position);
        Glide.with(holder.itemView.getContext())
                .load(rumahAdat.getFoto())
                .apply(new RequestOptions().override(55,55))
                .into(holder.imgFoto);
        holder.tvNama.setText(rumahAdat.getNama());
        holder.tvDetail.setText(rumahAdat.getDetail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listRumah.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRumah == null ? 0: listRumah.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView imgFoto;
        TextView tvNama, tvDetail;

        ListViewHolder(View itemView) {
            super(itemView);

            imgFoto = itemView.findViewById(R.id.img_item_photo);
            tvNama = itemView.findViewById(R.id.tv_item_name);
            tvDetail = itemView.findViewById(R.id.tv_item_detail);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(RumahAdat data);
    }
}
