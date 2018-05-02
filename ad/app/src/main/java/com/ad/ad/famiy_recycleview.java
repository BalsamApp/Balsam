package com.ad.ad;

/**
 * Created by arar_ on 23/03/18.
 */


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.ad.ad.FamilyActivity.idds;
import static com.ad.ad.FamilyActivity.namess;

/**
 * Created by Aws on 28/01/2018.
 */

public  class famiy_recycleview extends RecyclerView.Adapter<famiy_recycleview.MyViewHolder> {
public static int dep_id;
    private Context mContext ;
    ArrayList<String> ids=idds;
    ArrayList<String> names=namess;



    public famiy_recycleview(Context mContext,ArrayList<String> ids,ArrayList<String> names) {
        this.mContext = mContext;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.dep_cardview,parent,false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.d("id", ""+position);
        holder.name.setText(names.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ids", ""+position);

                Intent intent = new Intent(mContext,dep_page.class);

               dep_id=Integer.parseInt(ids.get(position));
               // passing data to the book activity
                intent.putExtra("name",names.get(position));
                intent.putExtra("id",ids.get(position));

                // start the activity
                mContext.startActivity(intent);



            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);

           name = (TextView) itemView.findViewById(R.id.fam_name) ;
           cardView = (CardView) itemView.findViewById(R.id.cardview_id);

        }
    }


}