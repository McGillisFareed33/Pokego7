package game_project;

import java.util.ArrayList;

public class SongOfPeace extends Action {

	public SongOfPeace() {
		this.Name = "SongOfPeace";
		this.Energy = 10;
		this.MaxEnergy = 10;
		this.Power = 20;
		this.Priority = 1;
		this.TargetsEnemy = 2;
	}
	
	@Override
	public void Execute(Character caster, Character opponent, ArrayList<Character> allies,
			ArrayList<Character> opponents) {
		for (Character character : opponents) {
			battleFunctions.ReduceStat(character, Stats.ATK, -1);

			}
	}

}
