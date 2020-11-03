package com.pheonix.org.hallaka;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class activityHandler extends RecyclerView.Adapter<activityViewHolderAct> {

    List<Integer> list;

    public activityHandler(List<Integer> list) {
        this.list = list;
    }


    @Override
    public activityViewHolderAct onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_lay, viewGroup, false);

        activityViewHolderAct activityViewHolderRunning = new activityViewHolderAct(view);

        return activityViewHolderRunning;
    }

    @Override
    public void onBindViewHolder(@NonNull final activityViewHolderAct viewHolder, final int i) {



    }

    @Override
    public int getItemCount() {

        return list.size();
    }
}

class activityViewHolderAct extends RecyclerView.ViewHolder {

    


    public activityViewHolderAct(@NonNull final View itemView) {
        super(itemView);



    }

}
