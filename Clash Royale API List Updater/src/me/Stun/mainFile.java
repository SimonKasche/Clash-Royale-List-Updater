
package me.Stun;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class mainFile {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {

		Long start = System.nanoTime();

		new me.Stun.Startup.Console();
		System.out.println("reading match data..");
		me.Stun.Connection.NetworkManagement.totalMatches = me.Stun.IO.General.readPreviousMatchesArray();
		me.Stun.Connection.NetworkResources.getPlayerData();

		String[][] matches = me.Stun.IO.General.addMatchesToArray(me.Stun.Connection.NetworkManagement.addedMatches,
				me.Stun.Connection.NetworkManagement.totalMatches);
		System.out.print("\nsaving matches to matches.txt.. ");
		me.Stun.IO.General.writeArray(matches);

		System.out.println("\nsuccessfully added " + me.Stun.Connection.NetworkManagement.addedMatches.size()
				+ " matches to dataset, time = " + (System.nanoTime() - start) / 6e+10 + "min\n");
		//System.out.println(me.Stun.IO.General.counter2);

	}

}
