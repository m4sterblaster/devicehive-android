package com.dataart.android.devicehive.client.commands;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Parcel;

import com.dataart.android.devicehive.DeviceData;
import com.dataart.android.devicehive.Notification;
import com.dataart.android.devicehive.network.DeviceHiveResultReceiver;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Abstract base class for commands which retrieve notifications for given
 * device.
 * 
 */
public abstract class DeviceNotificationsRetrivalCommand extends
		DeviceClientCommand {

	private final static String NAMESPACE = DeviceNotificationsRetrivalCommand.class
			.getName();

	private static final String NOTIFICATIONS_KEY = NAMESPACE
			.concat(".NOTIFICATIONS_KEY");

	protected final DeviceData deviceData;
	protected final String lastNotificationPollTimestamp;

	/**
	 * Construct command for given device and last received notification
	 * timestamp.
	 * 
	 * @param deviceData
	 *            {@link DeviceData} instance.
	 * @param lastNotificationPollTimestamp
	 *            Last received notification timestamp.
	 */
	public DeviceNotificationsRetrivalCommand(DeviceData deviceData,
			String lastNotificationPollTimestamp) {
		this.deviceData = deviceData;
		this.lastNotificationPollTimestamp = lastNotificationPollTimestamp;
	}

	@Override
	protected RequestType getRequestType() {
		return RequestType.GET;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeParcelable(deviceData, flags);
		dest.writeString(lastNotificationPollTimestamp);
	}

	@Override
	protected String toJson(Gson gson) {
		return null;
	}

	@Override
	protected int fromJson(final String response, final Gson gson,
			final Bundle resultData) {

		Type listType = new TypeToken<ArrayList<Notification>>() {
		}.getType();

		ArrayList<Notification> notifications = new Gson().fromJson(response,
				listType);
		resultData.putParcelableArrayList(NOTIFICATIONS_KEY, notifications);
		return DeviceHiveResultReceiver.MSG_HANDLED_RESPONSE;
	}

	/**
	 * Get a list of {@link Notification} sent from given {@link DeviceData}
	 * object.
	 * 
	 * @param resultData
	 *            {@link Bundle} object containing required response data.
	 * @return A list of {@link Notification} sent from given {@link DeviceData}
	 *         .
	 */
	public final static List<Notification> getNotifications(Bundle resultData) {
		return resultData.getParcelableArrayList(NOTIFICATIONS_KEY);
	}
}