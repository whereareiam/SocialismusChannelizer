package me.whereareiam.socialismus.module.channelizer;

import com.google.inject.*;
import com.google.inject.Module;
import lombok.RequiredArgsConstructor;
import me.whereareiam.socialismus.api.output.module.SocialisticModule;
import me.whereareiam.socialismus.api.output.resource.ResourceProvider;
import me.whereareiam.socialismus.api.output.resource.sync.SyncService;
import me.whereareiam.socialismus.api.type.PlatformType;
import me.whereareiam.socialismus.api.type.ResourceType;
import me.whereareiam.socialismus.module.channelizer.api.ParentInjector;
import me.whereareiam.socialismus.module.channelizer.platform.bukkit.BukkitInjectorConfiguration;
import me.whereareiam.socialismus.module.channelizer.platform.bukkit.CommonConfiguration;

import java.util.Map;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class SocialismusChannelizer extends SocialisticModule implements ResourceProvider {
	private final Injector parentInjector;
	private Injector injector;

	@Override
	public void onLoad() {
		ParentInjector.setInjector(parentInjector);
		injector =
				Guice.createInjector(
						new SocialismusChannelizerInjectorConfiguration(),
						new CommonConfiguration(),
						platformModule()
				);
	}

	@Override
	public void onEnable() {
	}

	@Override
	public void onDisable() {
	}

	@Override
	public void onUnload() {
	}

	@Override
	public Map<ResourceType, ?> provideResources() {
		return Map.of(
				ResourceType.SYNC, injector.getInstance(SyncService.class)
		);
	}

	private Module platformModule() {
		if (PlatformType.isGameServer())
			return new BukkitInjectorConfiguration();

		return new AbstractModule() {
			@Override
			protected void configure() {
				throw new UnsupportedOperationException("Platform not supported: " + PlatformType.getType().name());
			}
		};
	}
}
