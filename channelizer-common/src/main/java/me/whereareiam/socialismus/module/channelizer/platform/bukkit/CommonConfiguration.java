package me.whereareiam.socialismus.module.channelizer.platform.bukkit;

import com.google.inject.AbstractModule;
import me.whereareiam.socialismus.api.output.resource.sync.SyncService;
import me.whereareiam.socialismus.module.channelizer.platform.bukkit.service.ChannelSyncService;

public class CommonConfiguration extends AbstractModule {
	@Override
	protected void configure() {
		bind(SyncService.class).to(ChannelSyncService.class);
	}
}
