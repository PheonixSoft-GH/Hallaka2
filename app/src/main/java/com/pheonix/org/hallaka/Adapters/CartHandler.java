package com.pheonix.org.hallaka.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.FirebaseDatabase;
import com.pheonix.org.hallaka.Activity.Cart.CartActivity;
import com.pheonix.org.hallaka.Models.CartDataModel;
import com.pheonix.org.hallaka.R;
import com.pheonix.org.hallaka.Utils.Funcs;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class CartHandler extends RecyclerView.Adapter<CartViewHolder> {

    List<CartDataModel> list;

    Activity act;

    public CartHandler(List<CartDataModel> list, Activity act) {
        this.list = list;
        this.act = act;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_lay, viewGroup, false);

        return new CartViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder v, final int i) {
        final CartDataModel d = list.get(i);

        v.name.setText(d.getProductName());
        v.price.setText(d.gettPrice());
        v.quantity.setText("Quantity: " + d.getQuantity());

        Picasso.get().load(d.getProductImage()).into(v.img);

        if (d.getStatus().equalsIgnoreCase("cart")) {
            cartSetUp(v);

            v.deliver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enableLoadingBar();
                    FirebaseDatabase.getInstance().getReference("cart").child(d.getId()).child("status").setValue("delivering").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                disableLoadingBar();
                                showSnackBar(d.getProductName() + " order confirmed!");
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        selfIntent();
                                    }
                                }, 1000);
                            } else {
                                disableLoadingBar();
                                showSnackBar(Objects.requireNonNull(task.getException()).getMessage());
                            }
                        }
                    });
                }
            });

        } else {
            recSetUp(v);

            v.received.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(act).setTitle("Notice").setMessage("Have you received the products?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            enableLoadingBar();
                            FirebaseDatabase.getInstance().getReference("cart").child(d.getId()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        showSnackBar(d.getProductName()+" marked as received!");
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                selfIntent();
                                            }
                                        }, 1000);
                                    }
                                    disableLoadingBar();
                                }
                            });

                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                }
            });

        }

    }

    private void selfIntent() {
        Intent i = new Intent(act, CartActivity.class);
        act.startActivity(i);
        act.finish();
    }

    private void cartSetUp(@NonNull CartViewHolder v) {
        v.received.setVisibility(View.GONE);
        v.deliver.setVisibility(View.VISIBLE);
    }

    private void recSetUp(@NonNull CartViewHolder v) {
        v.received.setVisibility(View.VISIBLE);
        v.deliver.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void disableLoadingBar() {
        act.findViewById(R.id.spin_kit_cart).setVisibility(View.GONE);
        Funcs.enableCurrentScreen(act);
    }

    private void enableLoadingBar() {
        act.findViewById(R.id.spin_kit_cart).setVisibility(View.VISIBLE);
        Funcs.disableCurrentScreen(act);
    }

    private void showSnackBar(String s) {
        Snackbar.make(act.findViewById(R.id.cartMainLay), s, Snackbar.LENGTH_LONG)
                .setBackgroundTint(act.getResources().getColor(R.color.colorBlue))
                .setTextColor(act.getResources().getColor(R.color.colorWhite))
                .show();
    }
}

class CartViewHolder extends RecyclerView.ViewHolder {

    TextView name, price, quantity;
    ImageView img;
    Button deliver, received;

    public CartViewHolder(@NonNull final View v) {
        super(v);

        name = v.findViewById(R.id.productNameCart);
        price = v.findViewById(R.id.productPriceCart);
        quantity = v.findViewById(R.id.productQuantCart);
        img = v.findViewById(R.id.productImageCart);
        deliver = v.findViewById(R.id.deliverCart);
        received = v.findViewById(R.id.receivedCart);
    }

}
