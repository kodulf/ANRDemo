package bjpkten.anrdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver mBroadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startTrace();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("myBroadcastReceiver");

        mBroadCastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("kodulf","收到了");
                Toast.makeText(MainActivity.this,"收到了",Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(1000 * 6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("kodulf","收到了 --- end ");
            }
        };
        registerReceiver(mBroadCastReceiver, intentFilter);
    }

    public void trickWhileTrue(View view) {
        while (true){

        }
    }

    public void trickSleep10Seconds(View view) {
        Debug.startMethodTracing("itcast");
        try {
            Thread.sleep(6*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Debug.stopMethodTracing();
    }

    private void test() {
        Debug.startMethodTracing("custom");
        startTrace();
        Debug.stopMethodTracing();
    }

    /**
     * jie1()和jie2()没有调用关系是兄弟关系
     */
    private void startTrace() {
        jie1();
        jie2();
        startThread();
    }

    /**
     * jie2()中两次调用jie3()，其中jie3(0)直接return，不产生递归也不会调用jie4()
     * jie3(3)会先调用一次jie4()再产生3次递归调用
     */
    private void jie2() {
        jie3(0);
        jie3(3);
    }

    private void startThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void jie3(int count) {
        if (count == 3) {
            jie4();
        }
        if (count == 0) {
            return;
        } else {
            jie3(count - 1);
        }
    }

    /**
     * 故意做比较耗时的操作：用于区分Excl和Incl的关系
     */
    private void jie4() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                int k = i + j;
            }
        }
    }

    private void jie1() {

    }

    public void startDebugTrace(View view) {
        test();
    }

    public void startSendBroadcast(View view) {
        Intent intent = new Intent();
        intent.setAction("myBroadcastReceiver");
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBroadCastReceiver!=null)
        unregisterReceiver(mBroadCastReceiver);
    }

    public void startMyService(View view) {
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
    }
}
