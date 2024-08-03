package gg.projecteden.uptime;

import gg.projecteden.api.common.utils.ReflectionUtils;
import gg.projecteden.api.common.utils.Utils;
import gg.projecteden.api.discord.appcommands.AppCommandRegistry;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;
import java.util.stream.Stream;

public class Discord {
	public static JDA JDA;

	public static void init() {
		jda();
	}

	@SneakyThrows
	private static void jda() {
		JDA = JDABuilder.createDefault(Config.DISCORD_TOKEN)
			.addEventListeners(getListeners().toArray())
			.build()
			.awaitReady();

		final String commandsPackage = Uptime.class.getPackageName() + ".commands";
		new AppCommandRegistry(JDA, commandsPackage).setDebug(true).registerAll();
	}


	private static Stream<? extends ListenerAdapter> getListeners() {
		return ReflectionUtils.subTypesOf(ListenerAdapter.class, Uptime.class.getPackageName()).stream()
			.map(listener -> {
				try {
					if (Utils.canEnable(listener))
						return listener.getConstructor().newInstance();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return null;
			}).filter(Objects::nonNull);
	}

}
