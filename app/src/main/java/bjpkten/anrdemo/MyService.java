package bjpkten.anrdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("kodulf","start service on bind");
        try {
            Thread.sleep(1000*50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("kodulf","start service on bind end");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("kodulf","start service on onStartCommand");
        try {
            Thread.sleep(1000*50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("kodulf","start service on onStartCommand end");
        return super.onStartCommand(intent, flags, startId);
    }
}
