package com.dataart.android.devicehive;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

/* package */abstract class DataContainer implements Parcelable {

	protected Object data;

	protected DataContainer() {

	}

	protected DataContainer(Serializable data) {
		this.data = data;
	}

	/**
	 * Set equipment data, an object with an arbitrary structure that can be
	 * serialized to JSON. Equipment data can only be set/changed before device
	 * registration.
	 * 
	 * @param data
	 *            An object that can be serialized to JSON.
	 */
	public void setData(Serializable data) {
		this.data = data;
	}

	/**
	 * Get device data.
	 * 
	 * @return An object with arbitrary structure. Typically, an object is
	 *         constructed using StringMaps, ArrayLists and primitives.
	 */
	public Object getData() {
		return data;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeSerializable((Serializable) data);
	}

}
