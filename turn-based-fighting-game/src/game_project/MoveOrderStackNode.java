package game_project;


public class MoveOrderStackNode {
	MoveOrderStackNode nextNode;
	
	MoveOrderNode data;
	public MoveOrderStackNode(MoveOrderNode data ,MoveOrderStackNode node)
	{
		this.data = data;
		nextNode = node;
	}
	public MoveOrderStackNode(MoveOrderStackNode node) {
		nextNode = node;
	}
	public MoveOrderStackNode getNextNode()
	{
		return nextNode;
	}
}
