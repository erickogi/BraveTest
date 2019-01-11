package com.kogicodes.bravetest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kogicodes.bravetest.R;
import com.kogicodes.bravetest.models.schedules.Flight;
import com.kogicodes.bravetest.utils.DateTimeUtils;

import java.util.LinkedList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class FlightListAdapter extends RecyclerView.Adapter<FlightListAdapter.ViewHolder> {

    List<Flight> flights;
    private Context context;


    public FlightListAdapter(Context context, List<Flight> flights) {
        this.context = context;

        this.flights = flights;


    }


    @Override
    public FlightListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_item, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(FlightListAdapter.ViewHolder holder, int position) {
        final Flight flight = flights.get(position);

        if (flight.getDeparture() != null && flight.getDeparture().getScheduledTimeLocal() != null) {

            //DateFormat.getInstance().format(flight.getDeparture().getScheduledTimeLocal().getDateTime());  //("dd/MM/yy")

            String date = new DateTimeUtils().getDateAndTime(flight.getDeparture().getScheduledTimeLocal().getDateTime());
            holder.txtTime2.setText("Arrival : " + date);
        } else {
            holder.txtTime2.setText("");
        }

        if (flight.getArrival() != null && flight.getArrival().getScheduledTimeLocal() != null) {

            String date = new DateTimeUtils().getDateAndTime(flight.getArrival().getScheduledTimeLocal().getDateTime());
            holder.txtTime1.setText("Departure : " + date);

        } else {
            holder.txtTime1.setText("");
        }


        if (flight.getDeparture() != null && flight.getDeparture().getAirportCode() != null) {

            holder.txtCodeDepature.setText(flight.getDeparture().getAirportCode());
        } else {
            holder.txtCodeDepature.setText("");
        }

        if (flight.getArrival() != null && flight.getArrival().getAirportCode() != null) {

            holder.txtCodeArrival.setText(flight.getArrival().getAirportCode());
        } else {
            holder.txtCodeArrival.setText("");
        }

        if (flight.getEquipment() != null && flight.getEquipment().getAircraftCode() != null) {

            holder.txtFlightDepature.setText("Craft " + flight.getEquipment().getAircraftCode());
        } else {
            holder.txtFlightDepature.setText("");
        }


        if (flight.getArrival() != null && flight.getArrival().getTerminal().getName() != null) {

            holder.txtFlightArrival.setText("Terminal " + flight.getArrival().getTerminal().getName());
        } else {
            holder.txtFlightArrival.setText("");
        }


    }

    @Override
    public int getItemCount() {
        return flights != null ? flights.size() : 0;
    }

    public void setData(List<Flight> fligts) {
        if (flights == null) {
            flights = new LinkedList<>();
        }
        this.flights.clear();
        if (fligts != null) {
            this.flights.addAll(fligts);
        }
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTime1, txtTime2, txtCodeDepature, txtCodeArrival, txtFlightDepature, txtFlightArrival;


        public ViewHolder(View itemView) {
            super(itemView);


            txtTime1 = itemView.findViewById(R.id.txt_time_1);
            txtTime2 = itemView.findViewById(R.id.txt_time_2);
            txtCodeArrival = itemView.findViewById(R.id.txt_code_arrival);
            txtCodeDepature = itemView.findViewById(R.id.txt_code_departure);
            txtFlightArrival = itemView.findViewById(R.id.txt_flight_arrival);
            txtFlightDepature = itemView.findViewById(R.id.txt_flight_depature);
        }


    }
}