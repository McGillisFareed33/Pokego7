package game_project;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class actionResult {
	public String text;
	public HashMap<Character, Integer> changes;
	public actionResult() {
		changes = new HashMap<Character, Integer>();
	}
	public actionResult(String text) {
		changes = new HashMap<Character, Integer>();
		this.text = text;
	}

}
