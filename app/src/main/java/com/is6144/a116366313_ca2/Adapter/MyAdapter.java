package com.is6144.a116366313_ca2.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.is6144.a116366313_ca2.R;
import com.is6144.a116366313_ca2.Features.EditActivity;
import java.util.ArrayList;

//Database and Recycler view element created based on Youtube:SQLite + Android - Create Database Schema (Book Library App) | Part 1
// Youtube Video: https://www.youtube.com/watch?v=VQKq9RHMS_0


//Adapter is used for the recyclerview to communicate with the database
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList cart_id, cart_size, cart_base, cart_sauce, cart_cheese, cart_meat;


    Animation translate_anim;

    public MyAdapter(Activity activity, Context context, ArrayList cart_id, ArrayList cart_size, ArrayList cart_base, ArrayList cart_sauce, ArrayList cart_cheese, ArrayList cart_meat) {

        this.activity = activity;
        this.context = context;
        this.cart_id = cart_id;
        this.cart_size = cart_size;
        this.cart_base = cart_base;
        this.cart_sauce = cart_sauce;
        this.cart_cheese = cart_cheese;
        this.cart_meat = cart_meat;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvCartID.setText((String.valueOf(cart_id.get(position))));
        holder.tvSize.setText((String.valueOf(cart_size.get(position))));
        holder.tvBase.setText((String.valueOf(cart_base.get(position))));
        holder.tvSauce.setText((String.valueOf(cart_sauce.get(position))));
        holder.tvCheese.setText((String.valueOf(cart_cheese.get(position))));
        holder.tvMeat.setText((String.valueOf(cart_meat.get(position))));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("id", String.valueOf(cart_id.get(position)));
                intent.putExtra("size", String.valueOf(cart_size.get(position)));
                intent.putExtra("base", String.valueOf(cart_base.get(position)));
                intent.putExtra("start_date", String.valueOf(cart_sauce.get(position)));
                intent.putExtra("end_date", String.valueOf(cart_cheese.get(position)));
                intent.putExtra("end_time", String.valueOf(cart_meat.get(position)));

                //Automatically refresh results
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cart_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvCartID, tvSize, tvBase, tvSauce, tvCheese, tvMeat;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCartID = itemView.findViewById(R.id.tvCartID);
            tvSize = itemView.findViewById(R.id.tvSize);
            tvBase = itemView.findViewById(R.id.tvBase);
            tvSauce = itemView.findViewById(R.id.tvSauce);
            tvCheese = itemView.findViewById(R.id.tvCheese);
            tvMeat = itemView.findViewById(R.id.tvMeat);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            //Animate Recyclerview
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
