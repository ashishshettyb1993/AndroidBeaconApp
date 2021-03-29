package com.example.androidbeaconapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidbeaconapp.Service.BackGroundService;
import com.example.androidbeaconapp.Service.WifiReceiver;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.startup.BootstrapNotifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements BeaconConsumer,RangeNotifier {
    ScheduledExecutorService scheduleTaskExecutor = Executors.newSingleThreadScheduledExecutor();
    Future<?> future;
    private WifiManager wifiManager;
    protected static final String TAG = "RangingActivity";
//    private BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);
    private BeaconManager beaconManager=null;
    private Region beaconregion=null;
    public static final String ALTBEACON_LAYOUT="m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25";

    public static MainActivity from(@NonNull Context context) {
        return (MainActivity) context.getApplicationContext();
    }

//    public ApplicationComponent getComponent() {
//        return applicationComponent;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        beaconManager = MainActivity.from(this).getComponent().beaconManager();


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 656);
                if(checkSelfPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 87);
                }
            }
        }

//        beaconManager = BeaconManager.getInstanceForApplication(this);;
//        beaconregion = new Region(getDefaultRegionName(getApplicationContext()), null, null, null);
//        beaconManager.bind(this);
//        beaconManager.addRangeNotifier(this);




//        beaconManager = BeaconManager.getInstanceForApplication(this);
//        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(ALTBEACON_LAYOUT));
//        beaconManager.bind(this);
//        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        registerReceiver(mWifiScanReceiver,
//                new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    public void StartClick(View view) {
        Log.i(TAG,"yffhfg");
//        startService(new Intent(this, BackGroundService.class));
//        Toast.makeText(MainActivity.this, "background", Toast.LENGTH_SHORT).show();
//                try {
//                    Log.i("WifiBack","fgrtyrt");
//                    beaconManager.startRangingBeaconsInRegion(new Region(getDefaultRegionName(getApplicationContext()), null, null, null));

//                    beaconregion=new Region("MyBeacons", Identifier.parse("0AC59CA4-DFA6-442C-8C65-22247851344C"),Identifier.parse("4"),Identifier.parse("200"));
//                    beaconManager.startRangingBeaconsInRegion(beaconregion);
//                    beaconManager.bind(this);
//                }catch (RemoteException e) {
//                    Log.d("Constants.TAG", "Start scan beacon problem", e);
//                }

//        future = scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
//            public void run() {
//                Log.i("Regions",getDefaultRegionName(getApplicationContext()));
////                wifiManager.startScan();
//
//
//            }
//        }, 0, 2, TimeUnit.SECONDS);




//        try {
////            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/mytextfile.txt";
//            File dir = new File(Environment.getExternalStorageDirectory() +"/"+ "Download/yyyyy");
//            if(!dir.exists()){
//                dir.mkdirs();
//            }
//
//            File outputfile = new File(dir,"abc.csv");
//            if(!outputfile.exists()){
//                outputfile.createNewFile();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
//        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,
//                "MyApp::MyWakelockTag");
//        wakeLock.acquire();

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
    }

    public static String getDefaultRegionName(Context context) {
        return getSharedPreferences(context).getString("scan_default_region_text","default");
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void StopClick(View view) {
        unregisterReceiver(mWifiScanReceiver);
//        future.cancel(true);
    }

    private final BroadcastReceiver mWifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent intent) {
            if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                List<ScanResult> mScanResults = wifiManager.getScanResults();
                for (ScanResult scanResult : mScanResults) {
                    Log.i("WifiBack"+scanResult.SSID+ " - ",scanResult.capabilities);
                }
            }
        }
    };

    @Override
    public void onBeaconServiceConnect() {
        Log.d(TAG, "onBeaconServiceConnect");
        RangeNotifier rangeNotifier = new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    Log.d(TAG, "didRangeBeaconsInRegion called with beacon count:  "+beacons.size());
                    Beacon firstBeacon = beacons.iterator().next();
                    Log.i(TAG,"The first beacon " + firstBeacon.toString() + " is about " + firstBeacon.getDistance() + " meters away.");
                }
            }

        };
        try {
            beaconManager.startRangingBeaconsInRegion(new Region(getDefaultRegionName(getApplicationContext()), null, null, null));
            beaconManager.addRangeNotifier(rangeNotifier);
            beaconManager.bind(this);
        } catch (RemoteException e) {   }
    }

    @Override
    public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
        if (beacons.size() > 0) {
            Log.d(TAG, "didRangeBeaconsInRegion called with beacon count:  "+beacons.size());
            Beacon firstBeacon = beacons.iterator().next();
            Log.i(TAG,"The first beacon " + firstBeacon.toString() + " is about " + firstBeacon.getDistance() + " meters away.");
        }
    }

//    @Override
//    public void onBeaconServiceConnect() {
//        Log.d("onBeaconServiceConnect", "onBeaconServiceConnect");
//        beaconManager.setRangeNotifier(new RangeNotifier() {
//            @Override
//            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
//                for (Beacon beacon : beacons) {
//                    Log.d("MainActivity", "I see a beacon that is about "+region.getUniqueId()+" meters away.");
//                }
//            }
//        });
//    }
}