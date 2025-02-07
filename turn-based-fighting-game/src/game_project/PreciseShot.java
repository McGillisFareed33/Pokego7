package game_project;

import java.util.ArrayList;

public class PreciseShot extends Action {

	public PreciseShot() {
		this.Name = "Precise Shot";
		this.Energy = 10;
		this.MaxEnergy = 10;
		this.Power = 15;
		this.Priority = 1;
		this.TargetsEnemy = 1;
	}

	@Override
	public void Execute(Character caster, Character opponent, ArrayList<Character> allies,
			ArrayList<Character> opponents) {
		battleFunctions.calculatePhysicalDamage(caster, opponent, Power);
	}

}
