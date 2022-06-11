package me.Stun.Connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

public class NetworkManagement {

	public static String[][] totalMatches;
	public static LinkedList<String[]> addedMatches = new LinkedList<String[]>();
	public static boolean failure = false;
	public static String[] playerTags;
	public static int playerTagCounter = 0;

	private static String baseURL = "https://api.clashroyale.com/v1";

	public static void getBattleHistory(String playerTag) throws IOException {

		HttpURLConnection connection;
		URL url = new URL(baseURL + "/players/" + playerTag + "/battlelog");

		String ApiKey = NetworkResources.getApiKey();
		String auth = "Bearer " + ApiKey;

		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization", auth);
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);

		int status = connection.getResponseCode();

		if (status > 299) {
			try {
				System.out.println("connection failed, exit code " + status + "\n");
				failure = true;

				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				LinkedList<String> response = new LinkedList<String>();
				String line;
				while ((line = reader.readLine()) != null) {
					response.add(line);
				}

				for (int i = 0; i < response.size(); i++) {
					me.Stun.Startup.Console.TextArea.append(response.get(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else {

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			BufferedWriter bw = new BufferedWriter(new FileWriter("ressources/output.json"));
			bw.write(reader.readLine());
			bw.close();

			connection.disconnect();
		}
	}
	

	public static void getPlayerClanTag(String playerTag) throws IOException {

		HttpURLConnection connection;
		URL url = new URL(baseURL + "/players/" + playerTag);

		String ApiKey = NetworkResources.getApiKey();
		String auth = "Bearer " + ApiKey;

		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization", auth);
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);

		int status = connection.getResponseCode();
		
		if (status > 299) {
			try {
				System.out.println("connection failed, exit code " + status + "\n");
				failure = true;

				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				LinkedList<String> response = new LinkedList<String>();
				String line;
				while ((line = reader.readLine()) != null) {
					response.add(line);
				}

				for (int i = 0; i < response.size(); i++) {
					me.Stun.Startup.Console.TextArea.append(response.get(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else {

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			BufferedWriter bw = new BufferedWriter(new FileWriter("ressources/output.json"));
			String response = reader.readLine();
			bw.write(response);
			bw.close();

			connection.disconnect();
		}
	}

	public static void getClanPlayers(String clanTag) throws IOException {

		HttpURLConnection connection;
		URL url = new URL(baseURL + "/clans/" + clanTag);

		String ApiKey = NetworkResources.getApiKey();
		String auth = "Bearer " + ApiKey;

		connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization", auth);
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);

		int status = connection.getResponseCode();
		
		if (status > 299) {
			try {
				System.out.println("connection failed, exit code " + status + "\n");
				failure = true;

				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				LinkedList<String> response = new LinkedList<String>();
				String line;
				while ((line = reader.readLine()) != null) {
					response.add(line);
				}

				for (int i = 0; i < response.size(); i++) {
					me.Stun.Startup.Console.TextArea.append(response.get(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else {

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			BufferedWriter bw = new BufferedWriter(new FileWriter("ressources/output.json"));
			String response = reader.readLine();
			bw.write(response);
			bw.close();

			connection.disconnect();
		}
	}

}
