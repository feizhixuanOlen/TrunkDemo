package mobile.alibaba.olen.com.trunk;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zhangxl on 16/8/26.
 */
public class MyService extends Service {
    public static final String TAG = "MyService";
    private MyBinder mBinder = new MyBinder();


    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate() executed");
        super.onCreate();
        Log.d("TAG", "process id is " + Process.myPid());

//        Notification notification = new Notification(R.drawable.ic_launcher,
//                "有通知到来", System.currentTimeMillis());
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
//                notificationIntent, 0);
//        notification.setLatestEventInfo(this, "这是通知的标题", "这是通知的内容",
//                pendingIntent);
//        startForeground(1, notification);

//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(TAG, "onStart() executed");
        super.onStart(intent, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() executed");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind() executed");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() executed");
        super.onDestroy();
    }

    class MyBinder extends Binder {

        public void todo() {
            Log.d("TAG", "MyBinder_todo executed");
            // 执行具体的下载任务
        }
    }

//    MyAIDLService.Stub mBinder = new MyAIDLService.Stub() {
//
//        @Override
//        public String toUpperCase(String str) throws RemoteException {
//            if (str != null) {
//                return str.toUpperCase();
//            }
//            return null;
//        }
//
//        @Override
//        public int plus(int a, int b) throws RemoteException {
//            return a + b;
//        }
//    };

}
