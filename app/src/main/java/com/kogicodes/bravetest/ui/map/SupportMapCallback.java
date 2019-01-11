package com.kogicodes.bravetest.ui.map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;

final class SupportMapCallback extends zzaq {
    private OnMapReadyCallback mMapCallback;

    SupportMapCallback(SupportMapFragment.zza var1, OnMapReadyCallback var2) {
        mMapCallback = var2;
    }

    public final void zza(IGoogleMapDelegate var1) {
        mMapCallback.onMapReady(new GoogleMap(var1));
    }
}