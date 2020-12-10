package com.pheonix.org.hallaka.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pheonix.org.hallaka.Models.BarberDataModel;
import com.pheonix.org.hallaka.Models.ProductDataModel;
import com.pheonix.org.hallaka.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SaloonBarbersHandler extends RecyclerView.Adapter<SaloonBarbersViewHolder> {

    List<BarberDataModel> list;

    public SaloonBarbersHandler(List<BarberDataModel> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public SaloonBarbersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.barber_lay, viewGroup, false);

        return new SaloonBarbersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SaloonBarbersViewHolder v, final int i) {

        v.name.setText(list.get(i).getName());
        Picasso.get().load(list.get(i).getImage()).placeholder(R.drawable.ic_person).into(v.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class SaloonBarbersViewHolder extends RecyclerView.ViewHolder {

    ImageView img;
    TextView name;
    public SaloonBarbersViewHolder(@NonNull final View v) {
        super(v);

        img=v.findViewById(R.id.barberPicBarberLay);
        name=v.findViewById(R.id.barberNameBarberLay);

    }

}
