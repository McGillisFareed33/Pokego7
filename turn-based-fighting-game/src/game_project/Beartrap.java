package game_project;

import java.util.ArrayList;

public class Beartrap extends Action {

	public Beartrap() {
		this.Name = "Beartrap";
		this.Energy = 10;
		this.MaxEnergy = 10;
		this.Power = 5;
		this.Priority = 1;
		this.TargetsEnemy = 1;
	}
	@Override
	public void Execute(Character caster, Character opponent, ArrayList<Character> allies,
			ArrayList<Character> opponents) {
		battleFunctions.calculatePhysicalDamage(caster, opponent, Power);
		battleFunctions.ReduceStat(opponent, Stats.SPD, -2);

	}

}
