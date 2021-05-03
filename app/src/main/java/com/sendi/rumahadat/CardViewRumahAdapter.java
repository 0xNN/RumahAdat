package com.sendi.rumahadat;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

public class CardViewRumahAdapter extends RecyclerView.Adapter<CardViewRumahAdapter.ViewHolder> {

    private ArrayList<RumahAdat> listRumahAdat;

    int lastPosition = -1;

    public CardViewRumahAdapter(ArrayList<RumahAdat> listRumahAdat) {
        this.listRumahAdat = listRumahAdat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final RumahAdat rumahAdat = listRumahAdat.get(position);

        final String namaRumah = listRumahAdat.get(holder.getAdapterPosition()).getNama();
        final String detail = listRumahAdat.get(holder.getAdapterPosition()).getDetail();
        final String provinsi = listRumahAdat.get(holder.getAdapterPosition()).getProvinsi();
        final String gambar = listRumahAdat.get(holder.getAdapterPosition()).getFoto();
        final String space = " | ";

        Glide.with(holder.itemView.getContext())
                .load(rumahAdat.getFoto())
                .apply(new RequestOptions().override(350,350))
                .placeholder(R.drawable.ic_error_black_24dp)
                .error(R.drawable.ic_error_black_24dp)
                .into(holder.imgFoto);

        holder.tvName.setText(rumahAdat.getProvinsi());
        holder.tvProvinsi.setText(String.format("%s", rumahAdat.getNama()));
        holder.tvName.setTextColor(Color.rgb(240, 128, 128));
        holder.tvDetail.setText(rumahAdat.getDetail());

        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), Detail.class);
                intent.putExtra("nama", namaRumah);
                intent.putExtra("detail", detail);
                intent.putExtra("image", gambar);
                intent.putExtra("provinsi", provinsi);
                holder.itemView.getContext().startActivity(intent);
                Toast.makeText(holder.itemView.getContext(), "Rumah Adat " +
                        listRumahAdat.get(holder.getAdapterPosition()).getNama(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), Detail.class);
                intent.putExtra("nama", namaRumah);
                intent.putExtra("detail", detail);
                intent.putExtra("image", gambar);
                intent.putExtra("provinsi", provinsi);
                holder.itemView.getContext().startActivity(intent);
                Toast.makeText(holder.itemView.getContext(), "Rumah Adat " +
                        listRumahAdat.get(holder.getAdapterPosition()).getNama(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(),
                    (position > lastPosition) ? R.anim.down_to_up: R.anim.up_to_down);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    @Override
    public int getItemCount() {
        return listRumahAdat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFoto;
        TextView tvName, tvDetail, tvProvinsi;
        Button btnShare;
        ViewHolder(View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDetail = itemView.findViewById(R.id.tv_item_detail);
            tvProvinsi = itemView.findViewById(R.id.tv_item_prov);
            btnShare = itemView.findViewById(R.id.btn_set_share);
        }
    }
}
