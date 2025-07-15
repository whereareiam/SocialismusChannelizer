package me.whereareiam.socialismus.module.channelizer.platform.bukkit;

import com.google.inject.Singleton;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.*;
import me.whereareiam.socialismus.api.output.resource.sync.SyncSubscriber;
import me.whereareiam.socialismus.module.channelizer.api.ParentInjector;
import me.whereareiam.socialismus.module.channelizer.api.output.PlatformMessageBus;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

@Singleton
public final class BukkitMessageBus implements PlatformMessageBus, PluginMessageListener {
    private static final String PM_CHANNEL = "bungeecord:main";

    private final Plugin plugin;
    private final Map<String,List<SyncSubscriber>> listeners = new HashMap<>();

    public BukkitMessageBus() {
        this.plugin = ParentInjector.getInjector().getInstance(Plugin.class);
        plugin.getServer().getMessenger()
              .registerOutgoingPluginChannel(plugin, PM_CHANNEL);
        plugin.getServer().getMessenger()
              .registerIncomingPluginChannel(plugin, PM_CHANNEL, this);
    }

    @Override
    public void publish(String channel, byte[] payload) {
        Player carrier = Bukkit.getOnlinePlayers().stream().findFirst().orElse(null);
        if (carrier == null) return;

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Forward");
        out.writeUTF("ALL");
        out.writeUTF(channel);
        out.writeShort(payload.length);
        out.write(payload);

        carrier.sendPluginMessage(plugin, PM_CHANNEL, out.toByteArray());
    }

    @Override
    public void subscribe(String channel, SyncSubscriber subscriber) {
        listeners.computeIfAbsent(channel, k -> new ArrayList<>()).add(subscriber);
    }

    @Override
    public void onPluginMessageReceived(String transportChannel, Player player, byte[] message) {
        if (!PM_CHANNEL.equals(transportChannel)) return;

        ByteArrayDataInput in = ByteStreams.newDataInput(message);

        String subchannel = in.readUTF();
        if (!"Forward".equals(subchannel)) return;

        String logicalChannel = in.readUTF();

        int len = in.readShort();
        byte[] payload = new byte[len];
        in.readFully(payload);

        listeners.getOrDefault(logicalChannel, List.of())
                .forEach(sub -> sub.onMessage(logicalChannel, payload));
    }

}
