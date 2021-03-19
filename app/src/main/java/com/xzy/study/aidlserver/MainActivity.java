package com.xzy.study.aidlserver;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import aidl.xzy.aidl.IPerson;


/**
 * AIDL 服务端
 *
 * @author xzy
 */
public class MainActivity extends Activity {

    private final ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d("xzy","server - onServiceDisconnected");
        }

        @Override
        public void onServiceConnected(ComponentName arg0, IBinder binder) {
            IPerson iPerson = IPerson.Stub.asInterface(binder);
            Log.d("xzy","server - onServiceConnected");
            if (iPerson != null) {
                try {
                    iPerson.setName("My name is 'Server AIDL'");
                    Toast.makeText(MainActivity.this, "赋值成功!",
                            Toast.LENGTH_LONG).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "赋值失败!",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bindBtn = (Button) findViewById(R.id.button1);
        Button unbindBtn = (Button) findViewById(R.id.button2);
        bindBtn.setOnClickListener(listener);
        unbindBtn.setOnClickListener(listener);
    }

    private OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button1:
                    //本应用中需要在manifest中配置RemoteService
                    Toast.makeText(MainActivity.this, "bind service!",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent("forServiceAidl");
                    intent.setPackage(getApplication().getPackageName());
                    bindService(intent, conn, Service.BIND_AUTO_CREATE);
                    break;
                case R.id.button2:
                    unbindService(conn);
                    Toast.makeText(MainActivity.this, "unbind!",
                            Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    };

}
