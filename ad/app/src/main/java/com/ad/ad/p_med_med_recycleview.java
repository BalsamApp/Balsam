package com.ad.ad;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


import static com.ad.ad.p_med_med_activity.med_durationmeds;
import static com.ad.ad.p_med_med_activity.med_names;
import static com.ad.ad.p_med_med_activity.med_notess;
import static com.ad.ad.p_med_med_activity.med_rangehs;
import static com.ad.ad.p_med_med_activity.med_timetakens;
import static com.ad.ad.p_med_med_activity.med_phartNames;

/**
 * Created by malak on 05/04/18.
 */

public class p_med_med_recycleview extends RecyclerView.Adapter<p_med_med_recycleview.MyViewHolder> {
    private Context mContext ;
    //Store all the data in the Array for a second Array
    ArrayList<String> med_name=med_names;
    ArrayList<String> med_durationmed=med_durationmeds;
    ArrayList<String> med_timetaken=med_timetakens;
    ArrayList<String> med_rangeh=med_rangehs;
    ArrayList<String> med_notes=med_notess;
    ArrayList<String>med_phartName=med_phartNames;

    String idpres;

    public p_med_med_recycleview(String idpres, Context mContext, ArrayList<String> med_name, ArrayList<String> med_durationmed,
                                 ArrayList<String> med_timetaken, ArrayList<String> med_rangeh, ArrayList<String>med_notes,
                                 ArrayList<String>med_phartName) {
        this.idpres=idpres;
        this.mContext = mContext;
        this.med_name=med_name;
        this.med_durationmed=med_durationmed;
        this.med_timetaken=med_timetaken;
        this.med_rangeh=med_rangeh;
        this.med_notes=med_notes;
        this.med_phartName=med_phartName;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.p_med_med_recycleview_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.d("id", ""+med_name.get(position));
        //Get the data according to the position and assign it in the textview
        holder.textViewmedname.setText(med_name.get(position));
        holder.textViewdurmed.setText(med_durationmed.get(position));
        holder.textViewtimetaken.setText(med_timetaken.get(position));
        holder.textViewrangeh.setText(med_rangeh.get(position));
        holder.textViewnotesmed.setText(med_notes.get(position));
        holder.textViewphstmed.setText(med_phartName.get(position));
    }

    @Override
    public int getItemCount() { return med_name.size();    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewmedname, textViewdurmed, textViewtimetaken, textViewrangeh, textViewnotesmed, textViewphstmed;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            //assign each TextView
            textViewmedname = (TextView)itemView.findViewById(R.id.medname_id);
            textViewdurmed = (TextView)itemView.findViewById(R.id.durmed);
            textViewtimetaken =(TextView) itemView.findViewById(R.id.timetaken);
            textViewrangeh = (TextView)itemView.findViewById(R.id.hrange);
            textViewnotesmed =(TextView) itemView.findViewById(R.id.notesmed);
            textViewphstmed =(TextView) itemView.findViewById(R.id.phtmed);
            cardView = (CardView) itemView.findViewById(R.id.med_cardView);
        }
    }
}