package gg.projecteden.uptime;

import gg.projecteden.api.common.EdenAPI;
import gg.projecteden.api.common.utils.Env;
import it.sauronsoftware.cron4j.Scheduler;
import lombok.Getter;

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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public Env getEnv() {
		return Env.PROD;
	}

	@Override
	public void shutdown() {}

}
