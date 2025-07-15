package me.whereareiam.socialismus.module.channelizer.common;

import com.google.inject.AbstractModule;
import me.whereareiam.socialismus.api.output.resource.sync.SyncService;
import me.whereareiam.socialismus.module.channelizer.common.service.RedisSyncService;

public class CommonConfiguration extends AbstractModule {
	@Override
	protected void configure() {
		bind(SyncService.class).to(RedisSyncService.class);
	}
}
