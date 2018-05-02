package com.ad.ad;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static com.ad.ad.med_activity_recycleview.datepres;
import static com.ad.ad.med_activity_recycleview.doct_idss;
import static com.ad.ad.med_activity_recycleview.durationpres;
import static com.ad.ad.med_activity_recycleview.pat_idss;
import static com.ad.ad.med_activity_recycleview.presc_idss;

//this class if for recyle of prescription
public class med_presc_recycleview extends RecyclerView.Adapter<med_presc_recycleview.MyViewHolder> {
    public static int dep_id;
    private Context mContext ;
    ArrayList<String> pesc_ids=presc_idss;
    ArrayList<String> pat_ids=pat_idss;
    ArrayList<String> doct_ids=doct_idss;
    ArrayList<String> dat=datepres;
    ArrayList<String> durat=durationpres;
String idp;

    public med_presc_recycleview(String idp,Context mContext, ArrayList<String> pesc_ids,ArrayList<String> pat_ids,
                                 ArrayList<String> doct_ids, ArrayList<String> dat,ArrayList<String> durat) {

        this.mContext = mContext;
       this.idp=idp;
        this.pesc_ids=pesc_ids;
        this.doct_ids=doct_ids;
        this.pat_ids=pat_ids;
        this.dat=dat;
        this.durat=durat;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.med_recycleview_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.d("MMMMMMMMM", " idiiiiiiiiMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");

        Log.d("id", ""+position);
        holder.textViewpres.setText(pesc_ids.get(position));
        holder.textViewdoc.setText(doct_ids.get(position));
        holder.textViewdate.setText(dat.get(position));
        holder.textViewduration.setText(durat.get(position));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("prescriptionid", " "+ pesc_ids.get(position));

                Intent intent = new Intent(v.getContext(),med_med_activity.class);
                // passing data to the book activity
                intent.putExtra("prescriptionid",pesc_ids.get(position));
                intent.putExtra("id",idp);
                // start the activity
                mContext.startActivity(intent);



            }
        });
    }

    @Override
    public int getItemCount() {
        return pesc_ids.size();    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewpres, textViewdoc, textViewdate, textViewduration;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textViewpres = (TextView)itemView.findViewById(R.id.presx_id);
            textViewdoc = (TextView)itemView.findViewById(R.id.doct_id);
            textViewdate = (TextView)itemView.findViewById(R.id.date_pre);
            textViewduration =(TextView) itemView.findViewById(R.id.duration_pre);
            cardView = (CardView) itemView.findViewById(R.id.prec_cardView);

        }
    }
}
