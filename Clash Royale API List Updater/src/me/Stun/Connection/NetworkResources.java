package me.Stun.Connection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.json.simple.parser.ParseException;

public class NetworkResources {

	public static void getPlayerData() throws IOException, ParseException {

		NetworkManagement.playerTags = readFile("ressources/PlayerTag.txt");
		LinkedList<String> playerTags = getPlayerTags();
		me.Stun.IO.General.getUsedTags();

		for (NetworkManagement.playerTagCounter = 0; NetworkManagement.playerTagCounter < playerTags.size()
				- 1; NetworkManagement.playerTagCounter++) {

			// read battle history from clash royale api
			try {
				Long connectionStart = System.nanoTime();
				NetworkManagement.getBattleHistory(playerTags.get(NetworkManagement.playerTagCounter));
				Long connectionEnd = System.nanoTime();

				Long start = System.nanoTime();
				LinkedList<String[]> matches = me.Stun.IO.ParseJsonFiles.readBattleHistory();

				me.Stun.IO.General.addNewMatches(matches);
				System.out.print("\t" + NetworkManagement.playerTagCounter + "/" + playerTags.size()
						+ "\t addedmatches.size() = " + NetworkManagement.addedMatches.size());
				System.out.print("\tcalc = " + (System.nanoTime() - start) / 1e+9 + "s");
				System.out.print("\tresponse = " + (connectionEnd - connectionStart) / 1e+9 + "s");

			} catch (Exception e) {
				System.out.print("\trequest failed: ");
				e.printStackTrace();
			}

		}

	}

	public static LinkedList<String> getPlayerTags() throws IOException, ParseException {

		LinkedList<String> output = new LinkedList<String>();
		NetworkManagement.getPlayerClanTag(NetworkManagement.playerTags[0]);
		String clanTag = me.Stun.IO.ParseJsonFiles.readClanTag();

		NetworkManagement.getClanPlayers(clanTag);
		LinkedList<String> firstTags = me.Stun.IO.ParseJsonFiles.readClanPlayers();

		for (int i = 0; i < firstTags.size(); i++) {

			try {

				NetworkManagement.getBattleHistory(firstTags.get(i));
				LinkedList<String> battleTags = me.Stun.IO.ParseJsonFiles.readBattleHistoryTags();

				for (int k = 0; k < battleTags.size(); k++) {
					output.add(battleTags.get(k));
				}

			} catch (Exception e) {
				System.out.println("parsing failed");
			}
			System.out.println("getting players " + i + "/50");
		}

		System.out.println("playerTags.size() = " + output.size());

		return output;

	}

	public static String getApiKey() throws IOException {

		FileReader fr = new FileReader("ressources/ApiKey.txt");

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);

		return br.readLine();

	}

	public static String getPlayerTag() throws IOException {

		FileReader fr = new FileReader("ressources/PlayerTag.txt");

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);

		return br.readLine();

	}

	public static String[] readFile(String path) {
		try {
			FileReader f = new FileReader(path);
			BufferedReader b = new BufferedReader(f);

			String[] output = new String[me.Stun.IO.General.countLines(path)];

			for (int i = 0; i < me.Stun.IO.General.countLines(path); i++) {
				String line = b.readLine();
				if (line == null) {
					output[i] = "";
				} else {
					output[i] = line;
				}
			}

			b.close();
			f.close();

			return output;

		} catch (Exception e) {
			System.out.println("File '" + path + "' could not be found");
			return null;
		}

	}

}
