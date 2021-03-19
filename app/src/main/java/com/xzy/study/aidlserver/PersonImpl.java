package com.xzy.study.aidlserver;

import android.os.RemoteException;

import aidl.xzy.aidl.IPerson;

public class PersonImpl extends IPerson.Stub {
	private String name;

	@Override
	public String getName() throws RemoteException {
		return name;
	}

	@Override
	public void setName(String name) throws RemoteException {
		this.name = name;
	}
}
