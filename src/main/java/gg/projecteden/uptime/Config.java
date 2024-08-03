package gg.projecteden.uptime;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Config {

	public static Map<String, String> config = Config.read("config.json");

	public static String DISCORD_TOKEN = config.get("DISCORD_TOKEN");

	@SneakyThrows
	public static Map<String, String> read(String file) {
		try {
			String json = String.join("", Files.readAllLines(Paths.get(file)));
			return new Gson().fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
		} catch (Exception ex) {
			ex.printStackTrace();
			return new HashMap<>();
		}
	}

}
