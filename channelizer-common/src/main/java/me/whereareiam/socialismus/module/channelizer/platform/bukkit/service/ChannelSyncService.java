package me.whereareiam.socialismus.module.channelizer.platform.bukkit.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.RequiredArgsConstructor;
import me.whereareiam.socialismus.module.channelizer.api.PlatformMessageBus;
import me.whereareiam.socialismus.service.resource.sync.SyncService;
import me.whereareiam.socialismus.service.resource.sync.SyncSubscriber;

@Singleton
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public final class ChannelSyncService implements SyncService {
	private final PlatformMessageBus bus;

	@Override public void publish(String channel, byte[] payload) {
		bus.publish(channel, payload);
	}

	@Override public void subscribe(String channel, SyncSubscriber sub) {
		bus.subscribe(channel, sub);
	}
}
