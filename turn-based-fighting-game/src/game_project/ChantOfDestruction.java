package game_project;

import java.util.ArrayList;

public class ChantOfDestruction extends Action {

	public ChantOfDestruction() {
		this.Name = "Chant Of Destruction";
		this.Energy = 5;
		this.MaxEnergy = 5;
		this.Power = 15;
		this.Priority = -1;
		this.TargetsEnemy = 2;
	}
	@Override
	public void Execute(Character caster, Character opponent, ArrayList<Character> allies,
			ArrayList<Character> opponents) {
		for (Character character : opponents) {
			battleFunctions.calculateSpecialDamage(caster, character, Power);
		}
		battleFunctions.ReduceStat(caster, Stats.SPATK, -1);
		battleFunctions.ReduceStat(caster, Stats.SPD, -1);
	}

}
