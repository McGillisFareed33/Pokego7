package game_project;


public class MoveOrderStack {
	MoveOrderStackNode top;
	public MoveOrderStack() {
	}
	public void clear()
	{
		top = null;
	}
	public void push(MoveOrderNode node)
	{ 
		MoveOrderStackNode newNode = new MoveOrderStackNode(node ,top);
		top = newNode;
	}
	public boolean isEmpty()
	{
		return top == null;
	}
	public MoveOrderNode pop()
	{
		if(top == null)
		{
			return null;
		}
		MoveOrderStackNode newNode = top;
		top = top.nextNode;
		return newNode.data; 
	}
	public int getSize()
	{
		int size = 0;
		MoveOrderStackNode node = top;
		while(node != null)
		{
			node = node.nextNode;
			size++;
		}
		return size;
	}
}
