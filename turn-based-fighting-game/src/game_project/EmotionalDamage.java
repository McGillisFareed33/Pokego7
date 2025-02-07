package game_project;

import java.util.ArrayList;

public class EmotionalDamage extends Action {

	public EmotionalDamage() {
		this.Name = "Emotional Damage";
		this.Energy = 5;
		this.MaxEnergy = 5;
		this.Power = 0;
		this.Priority = 1;
		this.TargetsEnemy = 1;
	}
	@Override
	public void Execute(Character caster, Character opponent, ArrayList<Character> allies,
			ArrayList<Character> opponents) {
		battleFunctions.ReduceStat(opponent,Stats.DEF,-2 );
		battleFunctions.ReduceStat(opponent,Stats.SPDEF,-2 );
	}
}
