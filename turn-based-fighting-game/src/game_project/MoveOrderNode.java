package game_project;

import java.util.ArrayList;

public class MoveOrderNode {
	MoveOrderNode[] children;
	MoveOrderNode parent;
	Action actionToPerform;
	Character Caster;
	Character targets;
	boolean isSide1;
	
	
	public MoveOrderNode(Action actionToPerform, Character caster, Character targets, boolean isSide1) {
		super();
		this.actionToPerform = actionToPerform;
		Caster = caster;
		this.targets = targets;
		children = new MoveOrderNode[2];
		this.isSide1 = isSide1;
	}
	
	public void Attach(MoveOrderNode parent)
	{
		this.parent = parent;
	}
	
	
	
	
	
	

}
