/**
 * 
 */
package com.marketplace;

import java.util.*;

import com.marketplace.entity.Command;
import com.marketplace.exception.MarketPlaceException;
import com.marketplace.service.ServiceFactory;

/**
 * The start Up file of MarketPlace App
 * @author arnab
 *
 */
public class MarketPlaceApp {
	
	static Map<String, Command> commandMap = new HashMap<>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		init();
		System.out.println("Welcome to MarketPlace\n");
		try {
			while (true) {
				try {

					System.out.print("# ");
					String command = in.nextLine();
					String[] cmdParts = getParams(command);

					if (commandMap.containsKey(cmdParts[0])) {
						if ("EXIT".equalsIgnoreCase(cmdParts[0])) {
							System.out.println("Exiting application");
							return;
						}
						String result = (String) ServiceFactory.getService(commandMap.get(cmdParts[0]).getType())
														.process(commandMap.get(cmdParts[0]), cmdParts);
						System.out.println(result);
					} else {
						System.out.println("Error - Command not present");
					}
					
				} catch(Exception e) {
					if (e instanceof MarketPlaceException) {
						System.out.println(e.getMessage());
						
					} else {
						System.out.println("System Error : " + e.getMessage());
					}
				}
			}
		} finally {
			in.close();
		}
	}

	/**
	 * Get the command and its parameters entered by user in array
	 * @param command
	 * @return
	 */
	private static String[] getParams(String command) {
		String[] cmdParts = null;
		if (command.contains("'")) {
			String s = "";
			List<String> cmd = new ArrayList<>();
			boolean isSent = false;
			boolean start = false;
			for (char c : command.toCharArray()) {
				if (c == '\'') {
					start = start ? false: true;
					isSent = start ? true: false;
					continue;
				}
				if (c == ' ' && !isSent) {
					if (!"".equals(s))  {
						cmd.add(s);
					}
					s = "";
					continue;
				}
				s = s + c;

			}
			cmd.add(s);
			cmdParts = new String[cmd.size()];
			int i = 0;
			for (String s1 : cmd) {
				cmdParts[i] = s1;
				i++;
			}
		} else {
			cmdParts = command.split("\\s+");
		}
		return cmdParts;
	}

	/**
	 * Initialize the command map
	 */
	private static void init() {
		commandMap.put("REGISTER", new Command("REGISTER", 1, "USER"));
		commandMap.put("CREATE_LISTING", new Command("CREATE_LISTING", 5, "LISTING"));
		commandMap.put("DELETE_LISTING", new Command("DELETE_LISTING", 2, "LISTING"));
		commandMap.put("GET_LISTING", new Command("GET_LISTING", 2, "LISTING"));
		commandMap.put("GET_CATEGORY", new Command("GET_CATEGORY", 2, "CATEGORY"));
		commandMap.put("GET_TOP_CATEGORY", new Command("GET_TOP_CATEGORY", 1, "CATEGORY"));
		commandMap.put("EXIT", new Command("EXIT", 0, "EXIT"));
	}
}
