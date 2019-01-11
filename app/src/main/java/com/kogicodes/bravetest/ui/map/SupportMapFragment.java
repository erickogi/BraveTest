package com.kogicodes.bravetest.ui.map;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzby;
import com.google.android.gms.maps.internal.zzbz;
import com.google.android.gms.maps.model.RuntimeRemoteException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.fragment.app.Fragment;

public class SupportMapFragment extends Fragment {
    private final SupportMapFragment.zzb zzcg = new SupportMapFragment.zzb(this);

    public SupportMapFragment() {
    }

    public static SupportMapFragment newInstance() {
        return new SupportMapFragment();
    }

    public static SupportMapFragment newInstance(GoogleMapOptions var0) {
        SupportMapFragment var1 = new SupportMapFragment();
        Bundle var2;
        (var2 = new Bundle()).putParcelable("MapOptions", var0);
        var1.setArguments(var2);
        return var1;
    }

    public void onAttach(Activity var1) {
        super.onAttach(var1);
        this.zzcg.setActivity(var1);
    }

    public void onInflate(Activity var1, AttributeSet var2, Bundle var3) {
        StrictMode.ThreadPolicy var4 = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder(var4)).permitAll().build());

        try {
            super.onInflate(var1, var2, var3);
            this.zzcg.setActivity(var1);
            GoogleMapOptions var5 = GoogleMapOptions.createFromAttributes(var1, var2);
            Bundle var6;
            (var6 = new Bundle()).putParcelable("MapOptions", var5);
            this.zzcg.onInflate(var1, var6, var3);
        } finally {
            StrictMode.setThreadPolicy(var4);
        }

    }

    @Override
    public void onCreate(Bundle var1) {
        super.onCreate(var1);
        this.zzcg.onCreate(var1);
    }

    @Override
    public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
        View var4;
        (var4 = this.zzcg.onCreateView(var1, var2, var3)).setClickable(true);
        return var4;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.zzcg.onResume();
    }

    @Override
    public void onPause() {
        this.zzcg.onPause();
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
        this.zzcg.onStart();
    }

    @Override
    public void onStop() {
        this.zzcg.onStop();
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        this.zzcg.onDestroyView();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        this.zzcg.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        this.zzcg.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public void onActivityCreated(Bundle var1) {
        if (var1 != null) {
            var1.setClassLoader(SupportMapFragment.class.getClassLoader());
        }

        super.onActivityCreated(var1);
    }

    @Override
    public void onSaveInstanceState(Bundle var1) {
        if (var1 != null) {
            var1.setClassLoader(SupportMapFragment.class.getClassLoader());
        }

        super.onSaveInstanceState(var1);
        this.zzcg.onSaveInstanceState(var1);
    }

    public final void onEnterAmbient(Bundle var1) {
        Preconditions.checkMainThread("onEnterAmbient must be called on the main thread.");
        SupportMapFragment.zzb var2 = this.zzcg;
        if (this.zzcg.getDelegate() != null) {
            var2.getDelegate().onEnterAmbient(var1);
        }

    }

    public final void onExitAmbient() {
        Preconditions.checkMainThread("onExitAmbient must be called on the main thread.");
        SupportMapFragment.zzb var1 = this.zzcg;
        if (this.zzcg.getDelegate() != null) {
            var1.getDelegate().onExitAmbient();
        }

    }

    public void getMapAsync(OnMapReadyCallback var1) {
        Preconditions.checkMainThread("getMapAsync must be called on the main thread.");
        this.zzcg.getMapAsync(var1);
    }

    @Override
    public void setArguments(Bundle var1) {
        super.setArguments(var1);
    }

    @VisibleForTesting
    static class zzb extends DeferredLifecycleHelper<SupportMapFragment.zza> {
        private final Fragment fragment;
        private final List<OnMapReadyCallback> zzbe = new ArrayList();
        private OnDelegateCreatedListener<SupportMapFragment.zza> zzbc;
        private Activity zzbd;

        @VisibleForTesting
        zzb(Fragment var1) {
            this.fragment = var1;
        }

        @Override
        protected final void createDelegate(OnDelegateCreatedListener<SupportMapFragment.zza> var1) {
            this.zzbc = var1;
            this.zzc();
        }

        private final void zzc() {
            if (this.zzbd != null && this.zzbc != null && this.getDelegate() == null) {
                try {
                    MapsInitializer.initialize(this.zzbd);
                    IMapFragmentDelegate var1;
                    if ((var1 = zzbz.zza(this.zzbd).zzc(ObjectWrapper.wrap(this.zzbd))) == null) {
                        return;
                    }

                    this.zzbc.onDelegateCreated(new SupportMapFragment.zza(this.fragment, var1));
                    Iterator var2 = this.zzbe.iterator();

                    while (var2.hasNext()) {
                        OnMapReadyCallback var3 = (OnMapReadyCallback) var2.next();
                        this.getDelegate().getMapAsync(var3);
                    }

                    this.zzbe.clear();
                    return;
                } catch (RemoteException var4) {
                    throw new RuntimeRemoteException(var4);
                } catch (GooglePlayServicesNotAvailableException var5) {
                }
            }

        }

        private final void setActivity(Activity var1) {
            this.zzbd = var1;
            this.zzc();
        }

        public final void getMapAsync(OnMapReadyCallback var1) {
            if (this.getDelegate() != null) {
                this.getDelegate().getMapAsync(var1);
            } else {
                this.zzbe.add(var1);
            }
        }
    }

    @VisibleForTesting
    static class zza implements MapLifecycleDelegate {
        private final Fragment fragment;
        private final IMapFragmentDelegate zzba;

        public zza(Fragment var1, IMapFragmentDelegate var2) {
            this.zzba = Preconditions.checkNotNull(var2);
            this.fragment = Preconditions.checkNotNull(var1);
        }

        @Override
        public final void onInflate(Activity var1, Bundle var2, Bundle var3) {
            GoogleMapOptions var4 = var2.getParcelable("MapOptions");

            try {
                Bundle var5 = new Bundle();
                zzby.zza(var3, var5);
                this.zzba.onInflate(ObjectWrapper.wrap(var1), var4, var5);
                zzby.zza(var5, var3);
            } catch (RemoteException var6) {
                throw new RuntimeRemoteException(var6);
            }
        }

        @Override
        public final void onCreate(Bundle var1) {
            try {
                Bundle var2 = new Bundle();
                zzby.zza(var1, var2);
                Bundle var3;
                if ((var3 = this.fragment.getArguments()) != null && var3.containsKey("MapOptions")) {
                    zzby.zza(var2, "MapOptions", var3.getParcelable("MapOptions"));
                }

                this.zzba.onCreate(var2);
                zzby.zza(var2, var1);
            } catch (RemoteException var4) {
                throw new RuntimeRemoteException(var4);
            }
        }

        @Override
        public final View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
            IObjectWrapper var4;
            try {
                Bundle var5 = new Bundle();
                zzby.zza(var3, var5);
                var4 = this.zzba.onCreateView(ObjectWrapper.wrap(var1), ObjectWrapper.wrap(var2), var5);
                zzby.zza(var5, var3);
            } catch (RemoteException var6) {
                throw new RuntimeRemoteException(var6);
            }

            return (View) ObjectWrapper.unwrap(var4);
        }

        @Override
        public final void onStart() {
            try {
                this.zzba.onStart();
            } catch (RemoteException var2) {
                throw new RuntimeRemoteException(var2);
            }
        }

        @Override
        public final void onResume() {
            try {
                this.zzba.onResume();
            } catch (RemoteException var2) {
                throw new RuntimeRemoteException(var2);
            }
        }

        @Override
        public final void onPause() {
            try {
                this.zzba.onPause();
            } catch (RemoteException var2) {
                throw new RuntimeRemoteException(var2);
            }
        }

        @Override
        public final void onStop() {
            try {
                this.zzba.onStop();
            } catch (RemoteException var2) {
                throw new RuntimeRemoteException(var2);
            }
        }

        @Override
        public final void onDestroyView() {
            try {
                this.zzba.onDestroyView();
            } catch (RemoteException var2) {
                throw new RuntimeRemoteException(var2);
            }
        }

        @Override
        public final void onDestroy() {
            try {
                this.zzba.onDestroy();
            } catch (RemoteException var2) {
                throw new RuntimeRemoteException(var2);
            }
        }

        @Override
        public final void onLowMemory() {
            try {
                this.zzba.onLowMemory();
            } catch (RemoteException var2) {
                throw new RuntimeRemoteException(var2);
            }
        }

        @Override
        public final void onSaveInstanceState(Bundle var1) {
            try {
                Bundle var2 = new Bundle();
                zzby.zza(var1, var2);
                this.zzba.onSaveInstanceState(var2);
                zzby.zza(var2, var1);
            } catch (RemoteException var3) {
                throw new RuntimeRemoteException(var3);
            }
        }

        @Override
        public final void getMapAsync(OnMapReadyCallback var1) {
            try {
                this.zzba.getMapAsync(new SupportMapCallback(this, var1));
            } catch (RemoteException var3) {
                throw new RuntimeRemoteException(var3);
            }
        }

        public final void onEnterAmbient(Bundle var1) {
            try {
                Bundle var2 = new Bundle();
                zzby.zza(var1, var2);
                this.zzba.onEnterAmbient(var2);
                zzby.zza(var2, var1);
            } catch (RemoteException var3) {
                throw new RuntimeRemoteException(var3);
            }
        }

        public final void onExitAmbient() {
            try {
                this.zzba.onExitAmbient();
            } catch (RemoteException var2) {
                throw new RuntimeRemoteException(var2);
            }
        }
    }
}