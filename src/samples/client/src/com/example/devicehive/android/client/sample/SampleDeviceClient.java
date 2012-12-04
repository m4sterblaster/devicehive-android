package com.example.devicehive.android.client.sample;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.dataart.android.devicehive.Command;
import com.dataart.android.devicehive.DeviceData;
import com.dataart.android.devicehive.Notification;
import com.dataart.android.devicehive.client.DeviceClient;

public class SampleDeviceClient extends DeviceClient {

	private static final String TAG = "SampleDeviceClient";

	private final List<NotificationsListener> notificationListeners = new LinkedList<NotificationsListener>();

	public SampleDeviceClient(Context context, DeviceData deviceData) {
		super(context, deviceData);
	}

	public interface NotificationsListener {
		void onReceviceNotification(Notification notification);
	}

	public void addNotificationsListener(NotificationsListener listener) {
		notificationListeners.add(listener);
	}

	public void removeNotificationsListener(NotificationsListener listener) {
		notificationListeners.remove(listener);
	}

	@Override
	protected void onReceiveNotification(final Notification notification) {
		Log.d(TAG, "onReceiveNotification: " + notification);
		notifyNotificationListeners(notification);
	}

	@Override
	protected boolean shouldReceiveNotificationAsynchronously(
			Notification notification) {
		return false;
	}

	@Override
	protected void onStartReceivingNotifications() {
		Log.d(TAG, "onStartReceivingNotifications");
	}

	@Override
	protected void onStopReceivingNotifications() {
		Log.d(TAG, "onStopReceivingNotifications");
	}

	@Override
	protected void onStartSendingCommand(Command command) {
		Log.d(TAG, "onStartSendingCommand: " + command);
	}

	@Override
	protected void onFinishSendingCommand(Command command) {
		Log.d(TAG, "onFinishSendingCommand: " + command);
	}

	@Override
	protected void onFailSendingCommand(Command command) {
		Log.d(TAG, "onFailSendingCommand: " + command);
	}

	private void notifyNotificationListeners(Notification notification) {
		for (NotificationsListener listener : notificationListeners) {
			listener.onReceviceNotification(notification);
		}
	}

}