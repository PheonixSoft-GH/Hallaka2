package com.pheonix.org.hallaka;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pheonix.org.hallaka.Models.ProductDataModel;
import com.squareup.picasso.Picasso;

class activityHandler extends RecyclerView.Adapter<activityViewHolderAct> {

    List<ProductDataModel> list;

    public activityHandler(List<ProductDataModel> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public activityViewHolderAct onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_lay, viewGroup, false);

        return new activityViewHolderAct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final activityViewHolderAct v, final int i) {

        v.price.setText(list.get(i).getPrice()+" RS");
        v.name.setText(list.get(i).getName());

        Picasso.get().load(list.get(i).getImage()).into(v.img);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }
}

class activityViewHolderAct extends RecyclerView.ViewHolder {

    ImageView img;
    TextView name,price;
    public activityViewHolderAct(@NonNull final View v) {
        super(v);

        img=v.findViewById(R.id.productImageProLay);
        name=v.findViewById(R.id.productNameProLay);
        price=v.findViewById(R.id.productPriceProLay);

    }

}
