package me.whereareiam.socialismus.module.channelizer.platform.bukkit;

import com.google.inject.AbstractModule;
import me.whereareiam.socialismus.module.channelizer.api.output.PlatformMessageBus;

public class BukkitInjectorConfiguration extends AbstractModule {
	@Override
	protected void configure() {
		bind(PlatformMessageBus.class).to(BukkitMessageBus.class);
	}
}
