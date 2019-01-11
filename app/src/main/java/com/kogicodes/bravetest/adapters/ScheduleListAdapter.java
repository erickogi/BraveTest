package com.kogicodes.bravetest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kogicodes.bravetest.R;
import com.kogicodes.bravetest.models.schedules.Schedule;
import com.kogicodes.bravetest.utils.OnclickRecyclerListener;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ViewHolder> {

    List<Schedule> schedules;
    private Context context;
    private OnclickRecyclerListener recyclerListener;


    public ScheduleListAdapter(Context context, List<Schedule> schedules, OnclickRecyclerListener listener) {
        this.context = context;

        this.recyclerListener = listener;
        this.schedules = schedules;


    }


    @Override
    public ScheduleListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shcedule_item, parent, false);
        return new ViewHolder(itemView, recyclerListener);

    }

    @Override
    public void onBindViewHolder(ScheduleListAdapter.ViewHolder holder, int position) {
        final Schedule schedule = schedules.get(position);


        holder.txtDuration.setText("Duration : " + schedule.getTotalJourney().getDuration());
        FlightListAdapter flightListAdapter = new FlightListAdapter(context, schedule.getFlight());

        StaggeredGridLayoutManager mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        holder.recyclerView.setLayoutManager(mStaggeredLayoutManager);
        holder.recyclerView.setItemAnimator(new DefaultItemAnimator());
        flightListAdapter.notifyDataSetChanged();
        holder.recyclerView.setAdapter(flightListAdapter);


    }

    @Override
    public int getItemCount() {
        return schedules != null ? schedules.size() : 0;
    }

    public void setData(List<Schedule> fligts) {
        if (schedules == null) {
            schedules = new LinkedList<>();
        }
        this.schedules.clear();
        if (fligts != null) {
            this.schedules.addAll(fligts);
        }
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtDuration;
        RecyclerView recyclerView;
        CardView cardView;

        private WeakReference<OnclickRecyclerListener> listenerWeakReference;


        ViewHolder(View itemView, OnclickRecyclerListener listener) {
            super(itemView);
            listenerWeakReference = new WeakReference<>(listener);


            cardView = itemView.findViewById(R.id.card);
            txtDuration = itemView.findViewById(R.id.txt_duration);
            recyclerView = itemView.findViewById(R.id.recyclerView_flights);
            itemView.setOnClickListener(this);
            recyclerView.setOnClickListener(this::onClick);
            cardView.setOnClickListener(this);
            recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                @Override
                public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                    return false;
                }

                @Override
                public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                    listenerWeakReference.get().onClickListener(getAdapterPosition());

                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                }
            });
        }


        @Override
        public void onClick(View v) {

            listenerWeakReference.get().onClickListener(getAdapterPosition());
        }
    }
}