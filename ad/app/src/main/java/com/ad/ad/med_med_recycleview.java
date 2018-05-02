package com.ad.ad;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

import static com.ad.ad.med_med_activity.med_active1;
import static com.ad.ad.med_med_activity.med_names;
import static com.ad.ad.med_med_activity.med_durationmeds;
import static com.ad.ad.med_med_activity.med_notess;
import static com.ad.ad.med_med_activity.med_rangehs;
import static com.ad.ad.med_med_activity.med_timetakens;
import static com.ad.ad.med_med_activity.ffl;
import static com.ad.ad.pharmacy_Login.pharid;
//this class is for recycle of med
public class med_med_recycleview extends RecyclerView.Adapter<med_med_recycleview.MyViewHolder> {

        public static int dep_id;
    private Context mContext ;
    ArrayList<String> med_name=med_names;
    ArrayList<String> med_durationmed=med_durationmeds;
    ArrayList<String> med_timetaken=med_timetakens;
    ArrayList<String> med_rangeh=med_rangehs;
    ArrayList<String> med_notes=med_notess;
    ArrayList<String> ffll=ffl;
    ArrayList<String> med_active=med_active1;
    String idpres;

    public med_med_recycleview(String idpres, Context mContext, ArrayList<String> med_name, ArrayList<String> med_durationmed,
                               ArrayList<String> med_timetaken, ArrayList<String> med_rangeh,ArrayList<String>med_notes,
                               ArrayList<String> ffll, ArrayList<String> ggll) {
        Log.d("MMMMMMMMM", "11 idiiiiiiiiMMMMMMMMMMM000000000000000000000000000000000000000000000000000000MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
       this.idpres=idpres;
        this.mContext = mContext;
        this.med_name=med_name;
        this.med_durationmed=med_durationmed;
        this.med_timetaken=med_timetaken;
        this.med_rangeh=med_rangeh;
        this.med_notes=med_notes;
        this.ffll=ffll;
        this.med_active=ggll;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.med_med_recycleview_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        Log.d("id", ""+med_name.get(position));

        holder.textViewmedname.setText(med_name.get(position));
        holder.textViewdurmed.setText(med_durationmed.get(position));
        holder.textViewtimetaken.setText(med_timetaken.get(position));
        holder.textViewrangeh.setText(med_rangeh.get(position));
        holder.textViewnotesmed.setText(med_notes.get(position));
        String active_med=med_active.get(position); //took the Arraylist based on position and store one med

        if(active_med.equals("not active")){
            holder.active.setClickable(true); //button
            holder.active.setText(active_med);
            //int out_color = Color.rgb(50, 0, 0);

        }
        else{
            holder.active.setClickable(true);
            holder.active.setText(active_med);
            // int out_color = Color.rgb(0, 50, 0);
        }


        holder.active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String s=holder.active.getText().toString(); //take the status of active and store it in a string
                if(s.equals("not active")){ //if this string is not active

                    updateActive("active",med_name.get(position),pharid); //method
                    holder.active.setText("active");
                }
                else
                {
                    updateActive("not active",med_name.get(position),pharid);
                    holder.active.setText("not active");
                }



            }
        });



    }

    public void updateActive(String status11,String med_n,String pharid){ //this method is for backgroundworker
        String type="login";
        pharActiveUpdate backgroundWorker=new pharActiveUpdate(this);
        backgroundWorker.execute(type,idpres,med_n,status11,pharid);

    }
    @Override
    public int getItemCount() {
        return med_name.size();    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewmedname, textViewdurmed, textViewtimetaken, textViewrangeh, textViewnotesmed;
        CardView cardView;
        Button active;

        public MyViewHolder(View itemView) {
            super(itemView);
            Log.d("MMMMMMMMM", "13 MoooooooooooooooooooooooooooooooooooooooooMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");

            textViewmedname = (TextView)itemView.findViewById(R.id.medname_id);
            textViewdurmed = (TextView)itemView.findViewById(R.id.durmed);
            textViewtimetaken =(TextView) itemView.findViewById(R.id.timetaken);
            textViewrangeh = (TextView)itemView.findViewById(R.id.hrange);
            textViewnotesmed =(TextView) itemView.findViewById(R.id.notesmed);
            cardView = (CardView) itemView.findViewById(R.id.med_cardView);
            active= (Button) itemView.findViewById(R.id.activBtn);
            Log.d("MMMMMMMMM", "14 MeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");

        }
    }
}
