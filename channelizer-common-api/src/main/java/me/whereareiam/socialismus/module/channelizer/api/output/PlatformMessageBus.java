package me.whereareiam.socialismus.module.channelizer.api.output;

import me.whereareiam.socialismus.api.output.resource.sync.SyncSubscriber;

public interface PlatformMessageBus {
	void publish(String channel, byte[] payload);
	void subscribe(String channel, SyncSubscriber subscriber);
}
