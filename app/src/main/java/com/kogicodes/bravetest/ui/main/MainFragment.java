package com.kogicodes.bravetest.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.google.android.material.textfield.TextInputEditText;
import com.kogicodes.bravetest.R;
import com.kogicodes.bravetest.adapters.AirportListAdapter;
import com.kogicodes.bravetest.adapters.ScheduleListAdapter;
import com.kogicodes.bravetest.models.airports.Input;
import com.kogicodes.bravetest.models.basic.Resource;
import com.kogicodes.bravetest.models.basic.Status;
import com.kogicodes.bravetest.models.room.AirportModel;
import com.kogicodes.bravetest.models.schedules.Schedule;
import com.kogicodes.bravetest.ui.map.Const;
import com.kogicodes.bravetest.ui.map.MapsActivity;
import com.kogicodes.bravetest.utils.DateTimeUtils;
import com.kogicodes.bravetest.utils.OnclickRecyclerListener;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainFragment extends Fragment {

    MaterialDialog dialog;
    private MainViewModel mViewModel;
    private TextView txtStatus, txtNetwork;
    private TextInputEditText edtOrigin, edtDestination, edtSearch, edtOriginCode, edtDestinationCode;
    private View view;
    private RecyclerView recyclerView, recyclerViewAirports;
    private List<AirportModel> airportModels;
    private AirportListAdapter airportListAdapter;
    private ScheduleListAdapter scheduleListAdapter;
    private List<Schedule> schedules;
    private Input selectedInput = Input.NONE;
    private AirportModel airportModelOrigin;
    private AirportModel airportDestination;


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        edtOrigin = view.findViewById(R.id.edt_origin);
        edtDestination = view.findViewById(R.id.edt_destination);
        edtOriginCode = view.findViewById(R.id.edt_origin_code);
        edtDestinationCode = view.findViewById(R.id.edt_destination_code);
        recyclerView = view.findViewById(R.id.recyclerView);
        txtStatus = view.findViewById(R.id.txt_status);
        txtNetwork = view.findViewById(R.id.txt_network);
        txtStatus.setVisibility(View.GONE);

        setUpActions();
        scheduleList();
        initConnectivityListener();


    }


    private void setUpActions() {
        displayAirports();
        edtOrigin.setOnClickListener(v -> {

            dialog.show();
            selectedInput = Input.ORIGIN;

        });
        edtDestination.setOnClickListener(v -> {

            dialog.show();
            selectedInput = Input.DESTINATION;
        });
    }

    private void displayAirports() {

        dialog = new MaterialDialog.Builder(getContext())

                .customView(R.layout.dialog_search_airports, true)

                .negativeText(android.R.string.cancel)
                .onNegative((dialog, which) -> dialog.dismiss())
                .build();

        recyclerViewAirports = dialog.getCustomView().findViewById(R.id.recyclerView_airports);
        edtSearch = dialog.getCustomView().findViewById(R.id.edt_search);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s != null) {
                    mViewModel.getAirports(s.toString());
                }
            }
        });
        airportListAdapter = new AirportListAdapter(getContext(), airportModels, new OnclickRecyclerListener() {
            @Override
            public void onClickListener(int position) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                setText(airportModels.get(position));

            }

            @Override
            public void onLongClickListener(int position) {

            }
        });

        StaggeredGridLayoutManager mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewAirports.setLayoutManager(mStaggeredLayoutManager);
        recyclerViewAirports.setItemAnimator(new DefaultItemAnimator());
        airportListAdapter.notifyDataSetChanged();
        recyclerViewAirports.setAdapter(airportListAdapter);


        //  dialog.show();
    }

    private void setText(AirportModel airportModel) {
        if (airportModel != null) {
            switch (selectedInput) {
                case NONE:

                    break;
                case ORIGIN:
                    edtOrigin.setText(airportModel.getAirportNames());
                    edtOriginCode.setText(airportModel.getAirportCode());
                    airportModelOrigin = airportModel;
                    break;
                case DESTINATION:
                    edtDestination.setText(airportModel.getAirportNames());
                    edtDestinationCode.setText(airportModel.getAirportCode());
                    airportDestination = airportModel;
                    break;

                default:
            }
        }
        if (!TextUtils.isEmpty(edtOriginCode.getText()) && !TextUtils.isEmpty(edtDestinationCode.getText())) {
            mViewModel.getSchedules(edtOriginCode.getText().toString(), edtDestinationCode.getText().toString(), new DateTimeUtils().getToday());
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mViewModel.getAirports();

        mViewModel.getairportListObservable().observe(this, (Resource<List<AirportModel>> airports) -> {
            if (airports != null) {
                this.airportModels = airports.data;
                airportListAdapter.setData(airportModels);
            }
        });
        mViewModel.getScheduleListObservable().observe(this, (Resource<List<Schedule>> schedules) -> {

            if (schedules.status == Status.LOADING) {
                txtStatus.setVisibility(View.VISIBLE);
                txtStatus.setText("...Loading Data....");
            } else if (schedules.status == Status.ERROR) {
                txtStatus.setText("Error loading Data ..");
                txtStatus.setVisibility(View.VISIBLE);
                txtStatus.setText(schedules.message);
            } else if (schedules.status == Status.SUCCESS) {
                txtStatus.setText("");
                txtStatus.setVisibility(View.GONE);

            }
            filterSchedules(schedules);
        });
        mViewModel.getAirports("");


    }

    private void filterSchedules(Resource<List<Schedule>> schedules) {

        this.schedules = schedules.data;
        scheduleListAdapter.setData(this.schedules);
    }

    private void scheduleList() {
        if (schedules == null) {
            schedules = new LinkedList<>();
        }
        scheduleListAdapter = new ScheduleListAdapter(getContext(), schedules, new OnclickRecyclerListener() {
            @Override
            public void onClickListener(int position) {

                Intent intent = new Intent(getActivity(), MapsActivity.class);
                intent.putExtra(Const.KEY_ORIGIN, airportModelOrigin);
                intent.putExtra(Const.KEY_DESTINATION, airportDestination);
                startActivity(intent);


            }

            @Override
            public void onLongClickListener(int position) {

            }
        });

        StaggeredGridLayoutManager mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        scheduleListAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(scheduleListAdapter);

    }

    private void initConnectivityListener() {
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnectedToInternet -> {

                    if (isConnectedToInternet) {
                        this.txtNetwork.setText("Internet Connection is Okay");
                        if (airportModels == null || airportModels.size() < 1) {
                            mViewModel.getAirports();
                        }
                    } else {
                        this.txtNetwork.setText("No internet Connection");

                    }

                });
    }

}
