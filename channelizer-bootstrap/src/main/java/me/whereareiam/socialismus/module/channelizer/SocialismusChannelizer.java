package me.whereareiam.socialismus.module.channelizer;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import lombok.RequiredArgsConstructor;
import me.whereareiam.socialismus.api.output.module.SocialisticModule;
import me.whereareiam.socialismus.api.output.resource.ResourceProvider;
import me.whereareiam.socialismus.api.output.resource.sync.SyncService;
import me.whereareiam.socialismus.api.type.ResourceType;
import me.whereareiam.socialismus.module.channelizer.common.CommonConfiguration;

import java.util.Map;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class SocialismusChannelizer extends SocialisticModule implements ResourceProvider {
	private Injector injector;

	@Override
	public void onLoad() {
		injector =
				Guice.createInjector(
						new SocialismusChannelizerInjectorConfiguration(),
						new CommonConfiguration()
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
}
