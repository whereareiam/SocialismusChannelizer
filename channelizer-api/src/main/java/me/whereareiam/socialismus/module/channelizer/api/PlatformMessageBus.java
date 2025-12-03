package me.whereareiam.socialismus.module.channelizer.api;

import me.whereareiam.socialismus.service.resource.sync.SyncSubscriber;

public interface PlatformMessageBus {
	void publish(String channel, byte[] payload);
	void subscribe(String channel, SyncSubscriber subscriber);
}
