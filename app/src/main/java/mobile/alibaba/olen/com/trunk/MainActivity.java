package mobile.alibaba.olen.com.trunk;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.Process;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.os.RemoteException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "MainActivity";
//    private MyService.MyBinder myBinder;
    private MyAIDLService myAIDLService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Log.i(TAG, "onCreat");
        Log.d("TAG", "process id is " + Process.myPid());

        initView();

//        String str = "123";
//        String str1 = new String("123");
//        int i = 123;
//        User user = new User();
//        Log.i("zhangxl", "str 111 : " + str);
//        Log.i("zhangxl", "i 111 : " + i);
//        Log.i("zhangxl", "user 111 : " + user.getUserId());
//
//        toStr(str, user, i);
//
//        Log.i("zhangxl", "str 222 : " + str);
//        Log.i("zhangxl", "user 222 : " + user.getUserId());

    }
//
//    private void toStr(String str, User user, int i) {
//
//        str ="345";
//        i = 345;
//        user.setUserId("def");
//
//        Log.i("zhangxl", "toStr str : " + str);
//        Log.i("zhangxl", "toStr i : " + i);
//        Log.i("zhangxl", "toStr user : " + user.getUserId());
//    }

//    class User {
//
//        String userId = "abc";
//
//        public String getUserId() {
//            return userId;
//        }
//
//        public void setUserId(String userId) {
//            this.userId = userId;
//        }
//    }

    private void initView() {

        Button toActivity = (Button)findViewById(R.id.to_secondary_activity);
        Button starService = (Button)findViewById(R.id.star_service);
        Button stop_service = (Button)findViewById(R.id.stop_service);
        Button bind_service = (Button)findViewById(R.id.bind_service);
        Button unbind_service = (Button)findViewById(R.id.unbind_service);

        toActivity.setOnClickListener(this);
        starService.setOnClickListener(this);
        stop_service.setOnClickListener(this);
        bind_service.setOnClickListener(this);
        unbind_service.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                Dialog dialog = new AlertDialog.Builder(MainActivity.this);
                                Dialog alertDialog = new AlertDialog.Builder(MainActivity.this).
                                        setTitle("对话框的标题").
                                        setMessage("对话框的内容").
                                        setIcon(R.mipmap.ic_launcher).
                                        create();
                                alertDialog.show();
                            }
                        }).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_secondary_activity:
                Intent intent = new Intent(this, SecondaryActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.star_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);

                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;

            case R.id.bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;

            case R.id.unbind_service:
                try {
                    unbindService(connection);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.i(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i(TAG, "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected");
//            myBinder = (MyService.MyBinder) service;
//            myBinder.todo();

            myAIDLService = MyAIDLService.Stub.asInterface(service);
            try {
                int result = myAIDLService.plus(3, 5);
                String upperStr = myAIDLService.toUpperCase("hello world");
                Log.d("TAG", "result is " + result);
                Log.d("TAG", "upperStr is " + upperStr);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    };
}
