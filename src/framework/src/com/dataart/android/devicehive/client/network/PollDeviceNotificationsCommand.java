package com.dataart.android.devicehive.client.network;

import android.os.Parcel;
import android.os.Parcelable;

import com.dataart.android.devicehive.DeviceData;
import com.dataart.android.devicehive.Notification;

/**
 * Get all existing device {@link Notification}s starting from given timestamp.
 * This request returns immediately if there have been any notifications since
 * given timestamp. In the case when no notifications were found, the method
 * blocks until new notification is received. The blocking period is limited
 * (currently 30 seconds). As a result returns list of {@link Notification}.
 */
public class PollDeviceNotificationsCommand extends
		DeviceNotificationsRetrivalCommand {

	/**
	 * Construct a new command.
	 * 
	 * @param deviceData
	 *            {@link DeviceData} instance.
	 * @param lastNotificationPollTimestamp
	 *            Timestamp which defines starting point in the past for
	 *            notifications.
	 */
	public PollDeviceNotificationsCommand(DeviceData deviceData,
			String lastNotificationPollTimestamp) {
		super(deviceData, lastNotificationPollTimestamp);
	}

	@Override
	protected String getRequestPath() {
		String requestPath = String.format("device/%s/notification/poll",
				encodedString(deviceData.getId()));
		if (lastNotificationPollTimestamp != null) {
			requestPath += "?timestamp="
					+ encodedString(lastNotificationPollTimestamp);
		}
		return requestPath;
	}

	public static Parcelable.Creator<PollDeviceNotificationsCommand> CREATOR = new Parcelable.Creator<PollDeviceNotificationsCommand>() {

		@Override
		public PollDeviceNotificationsCommand[] newArray(int size) {
			return new PollDeviceNotificationsCommand[size];
		}

		@Override
		public PollDeviceNotificationsCommand createFromParcel(Parcel source) {
			return new PollDeviceNotificationsCommand(
					(DeviceData) source.readParcelable(CLASS_LOADER),
					source.readString());
		}
	};
}
