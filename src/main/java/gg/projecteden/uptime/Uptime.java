package gg.projecteden.uptime;

import gg.projecteden.api.common.EdenAPI;
import gg.projecteden.api.common.utils.Env;
import gg.projecteden.api.common.utils.Utils;
import gg.projecteden.api.discord.appcommands.exceptions.AppCommandException;
import it.sauronsoftware.cron4j.Scheduler;
import lombok.Getter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class Uptime extends EdenAPI {
	@Getter
	private static final Scheduler cron = new Scheduler();

	public static void main(String[] args) {
		new Uptime();
	}

	public Uptime() {
		instance = this;

		try {
			cron.start();
			Discord.init();
			echo();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void echo() {
		final File folder = folder("smp");
		Utils.bash("mark2 send -n smp echo Uptime started", folder);
	}

	@Override
	public Env getEnv() {
		return Env.PROD;
	}

	@Override
	public void shutdown() {}

	public static File folder(String server) {
		final Path path = Path.of("/home/minecraft/servers", server);
		if (!Files.exists(path))
			throw new AppCommandException("Server " + server + " does not exist");
		return path.toFile();
	}

}
