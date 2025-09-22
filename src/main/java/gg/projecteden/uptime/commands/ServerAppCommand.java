package gg.projecteden.uptime.commands;

import gg.projecteden.api.common.utils.Utils;
import gg.projecteden.api.discord.appcommands.AppCommand;
import gg.projecteden.api.discord.appcommands.AppCommandEvent;
import gg.projecteden.api.discord.appcommands.annotations.Command;
import gg.projecteden.api.discord.appcommands.annotations.GuildCommand;
import gg.projecteden.api.discord.appcommands.annotations.Optional;
import gg.projecteden.api.discord.appcommands.annotations.RequiredRole;

import java.io.File;

import static gg.projecteden.uptime.Uptime.folder;

@GuildCommand("132680070480396288")
@Command("Manage Project Eden's minecraft servers")
@RequiredRole("Staff")
public class ServerAppCommand extends AppCommand {

	public ServerAppCommand(AppCommandEvent event) {
		super(event);
	}

	@Command("Start a server")
	void start(@Optional("smp") String server) {
		final File folder = folder(server);
		new Thread(() -> Utils.bash("mark2 start -n " + server, folder)).start();
		reply("Starting server " + server);
	}

	@Command("Stop a server")
	void stop(@Optional("smp") String server) {
		final File folder = folder(server);
		new Thread(() -> Utils.bash("mark2 send -n " + server + " maintenance", folder)).start();
		reply("Stopping server " + server);
	}

	@Command("Reboot a server")
	void reboot(@Optional("smp") String server) {
		final File folder = folder(server);
		new Thread(() -> Utils.bash("mark2 send -n " + server + " reboot", folder)).start();
		reply("Rebooting server " + server);
	}

	@Command("Force stop a server")
	void kill(@Optional("smp") String server) {
		final File folder = folder(server);
		new Thread(() -> Utils.bash("mark2 kill -n " + server, folder)).start();
		reply("Killing server " + server);
	}
}
