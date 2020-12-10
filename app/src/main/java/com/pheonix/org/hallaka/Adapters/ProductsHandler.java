package com.pheonix.org.hallaka.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pheonix.org.hallaka.Models.ProductDataModel;
import com.pheonix.org.hallaka.R;
import com.squareup.picasso.Picasso;

public class ProductsHandler extends RecyclerView.Adapter<ProductsViewHolder> {

    List<ProductDataModel> list;

    public ProductsHandler(List<ProductDataModel> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_lay, viewGroup, false);

        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductsViewHolder v, final int i) {

        v.price.setText(list.get(i).getPrice()+" RS");
        v.name.setText(list.get(i).getName());

        Picasso.get().load(list.get(i).getImage()).into(v.img);

    }

    @Override
    public int getItemCount() {

        return list.size();
    }
}

class ProductsViewHolder extends RecyclerView.ViewHolder {

    ImageView img;
    TextView name,price;
    public ProductsViewHolder(@NonNull final View v) {
        super(v);

        img=v.findViewById(R.id.productImageProLay);
        name=v.findViewById(R.id.productNameProLay);
        price=v.findViewById(R.id.productPriceProLay);

    }

}
