package example.com.jointing.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import example.com.jointing.InputWoActivity;
import example.com.jointing.R;
import example.com.jointing.WoActivity;


/**
 * Created by pc on 18/12/2017.
 */

public class WoAdapter extends RecyclerView.Adapter<WoAdapter.ViewHolder>{

    private ArrayList<String> rvData;
    private Context context;

    public WoAdapter(Context c, ArrayList<String> inputData){
        this.context = c;
        this.rvData = inputData;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v =layoutInflater.inflate(R.layout.activity_wo_item, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(WoAdapter.ViewHolder holder, int position) {
        final String name = rvData.get(position);
        holder.txt_wo.setText(rvData.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar.make(v, "Clicked element "+name, Snackbar.LENGTH_LONG).show();
                Intent input_wo = new Intent(context,InputWoActivity.class);
                input_wo.putExtra("no_wo",name);
               input_wo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(input_wo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rvData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txt_wo;
        public CardView cardView;

        public ViewHolder(View v){
            super(v);
            txt_wo = (TextView)v.findViewById(R.id.txt_wo);
            cardView  = (CardView)v.findViewById(R.id.cardview);
        }
    }

}
