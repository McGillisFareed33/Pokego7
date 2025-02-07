package game_project;

import java.util.Comparator;

public class SortBySpeed implements Comparator<Character>{
	
	@Override
	public int compare(Character o1, Character o2) {
		// TODO Auto-generated method stub
		return o1.SPD - o2.SPD;
	}

}
