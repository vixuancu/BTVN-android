package com.example.bt_serviceb1;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class MyBackgroundService extends Service {
    private static final String TAG = "MyBackgroundService";
    private Handler handler;
    private Runnable runnable;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service Created");
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Service is running...");
                handler.postDelayed(this, 5000); // Lặp lại mỗi 5 giây
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Khoi chay Service");
        handler.post(runnable);
        return START_STICKY; // Để service tiếp tục chạy ngay cả khi bị hủy
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service Destroyed");
        handler.removeCallbacks(runnable); // Dừng handler khi service bị hủy
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Service này không hỗ trợ binding
        return null;
    }
}
