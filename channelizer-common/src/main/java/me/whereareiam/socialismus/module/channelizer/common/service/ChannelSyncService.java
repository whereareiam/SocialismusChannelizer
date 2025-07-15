package me.whereareiam.socialismus.module.channelizer.common.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.RequiredArgsConstructor;
import me.whereareiam.socialismus.api.output.resource.sync.SyncService;
import me.whereareiam.socialismus.api.output.resource.sync.SyncSubscriber;

@Singleton
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class ChannelSyncService implements SyncService {
	@Override
	public void publish(String channel, byte[] payload) {
	}

	@Override
	public void subscribe(String channel, SyncSubscriber sub) {
	}
}
