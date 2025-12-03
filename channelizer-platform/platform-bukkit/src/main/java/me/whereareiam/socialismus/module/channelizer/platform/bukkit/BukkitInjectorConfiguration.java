package me.whereareiam.socialismus.module.channelizer.platform.bukkit;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.sun.source.util.Plugin;
import lombok.RequiredArgsConstructor;
import me.whereareiam.socialismus.module.channelizer.api.PlatformMessageBus;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class BukkitInjectorConfiguration extends AbstractModule {
	private final Plugin plugin;

	@Override
	protected void configure() {
		bind(Plugin.class).toInstance(plugin);
		bind(PlatformMessageBus.class).to(BukkitMessageBus.class);
	}
}
