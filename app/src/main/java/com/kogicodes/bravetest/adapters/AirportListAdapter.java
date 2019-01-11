package com.kogicodes.bravetest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kogicodes.bravetest.R;
import com.kogicodes.bravetest.models.room.AirportModel;
import com.kogicodes.bravetest.utils.OnclickRecyclerListener;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AirportListAdapter extends RecyclerView.Adapter<AirportListAdapter.ViewHolder> {

    List<AirportModel> airportModels;
    private Context context;
    private OnclickRecyclerListener recyclerListener;


    public AirportListAdapter(Context context, List<AirportModel> airportModels, OnclickRecyclerListener listener) {
        this.context = context;
        this.recyclerListener = listener;

        this.airportModels = airportModels;


    }


    @Override
    public AirportListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.airport_list_item, parent, false);
        return new ViewHolder(itemView, recyclerListener);

    }

    @Override
    public void onBindViewHolder(AirportListAdapter.ViewHolder holder, int position) {
        final AirportModel airportModel = airportModels.get(position);

        holder.txtAirportCode.setText(airportModel.getAirportCode());
        holder.txtAirportName.setText(airportModel.getAirportNames());
        holder.txtCounrtyCode.setText(airportModel.getAirportCountryCode());


    }

    @Override
    public int getItemCount() {
        return airportModels != null ? airportModels.size() : 0;
    }

    public void setData(List<AirportModel> airports) {
        if (airportModels == null) {
            airportModels = new LinkedList<>();
        }
        this.airportModels.clear();
        if (airports != null) {
            this.airportModels.addAll(airports);
        }
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtAirportCode, txtAirportName, txtCounrtyCode;

        private WeakReference<OnclickRecyclerListener> listenerWeakReference;


        public ViewHolder(View itemView, OnclickRecyclerListener listener) {
            super(itemView);
            listenerWeakReference = new WeakReference<>(listener);


            txtAirportCode = itemView.findViewById(R.id.txt_airport_code);
            txtAirportName = itemView.findViewById(R.id.txt_airport_name);
            txtCounrtyCode = itemView.findViewById(R.id.txt_country_code);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            listenerWeakReference.get().onClickListener(getAdapterPosition());
        }
    }
}