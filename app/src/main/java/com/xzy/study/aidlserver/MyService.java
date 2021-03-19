package com.xzy.study.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import aidl.xzy.aidl.IPerson;


public class MyService extends Service {
	private final IPerson.Stub iPerson = new PersonImpl();

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i("service", "onBind...");
		return iPerson;
	}

}
