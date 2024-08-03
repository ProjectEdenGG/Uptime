package gg.projecteden.uptime.commands;

import gg.projecteden.api.common.utils.Utils;
import gg.projecteden.api.discord.appcommands.AppCommand;
import gg.projecteden.api.discord.appcommands.AppCommandEvent;
import gg.projecteden.api.discord.appcommands.annotations.Command;
import gg.projecteden.api.discord.appcommands.annotations.GuildCommand;
import gg.projecteden.api.discord.appcommands.annotations.RequiredRole;
import gg.projecteden.api.discord.appcommands.annotations.Optional;
import gg.projecteden.api.discord.appcommands.exceptions.AppCommandException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@GuildCommand("132680070480396288")
@Command("Manage Project Eden's minecraft servers")
@RequiredRole("Staff")
public class ServerAppCommand extends AppCommand {

	public ServerAppCommand(AppCommandEvent event) {
		super(event);
	}

	@Command("Start a server")
	void start(@Optional("smp") String server) {
		new Thread(() -> Utils.bash("mark2 start -n " + server, validate(server)));
		replyEphemeral("Starting server " + server);
	}

	@Command("Stop a server")
	void stop(@Optional("smp") String server) {
		Utils.bash("mark2 send -n " + server + " maintenance", validate(server));
		replyEphemeral("Stopping server " + server);
	}

	@Command("Reboot a server")
	void reboot(@Optional("smp") String server) {
		Utils.bash("mark2 send -n " + server + " reboot", validate(server));
		replyEphemeral("Rebooting server " + server);
	}

	@Command("Force stop a server")
	void kill(@Optional("smp") String server) {
		Utils.bash("mark2 kill -n " + server, validate(server));
		replyEphemeral("Killing server " + server);
	}

	private static File validate(String server) {
		final Path path = Path.of("/home/minecraft/servers", server);
		if (!Files.exists(path))
			throw new AppCommandException("Server " + server + " does not exist");
		return path.toFile();
	}
}
