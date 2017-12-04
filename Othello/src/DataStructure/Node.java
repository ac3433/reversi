
package DataStructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import Othello.*;

public class Node {

	private OthelloState currentState;
	private Node parent;
	private List<Node> children;
	//this is to collective count the chance of wins and loss on the side of the tree
	private int totalWin = 0;
	private int visitCount = 0;

	public Node(OthelloState currentState)
	{
		this.currentState = currentState;
		children = new ArrayList<Node>();
	}
	
	public Node(OthelloState currentState, Node parent)
	{
		this(currentState);
		this.parent = parent;
	}
	
	public void addChild(Node child)
	{
		children.add(child);
	}
	
	public List<Node> getChildren()
	{
		return children;
	}
	
	public void incrementWin() { totalWin++; }
	public void decrementWin() {totalWin--; }
	public int getTotalWin() { return totalWin; }
	public void incrementVisit() { visitCount++; }
	public void addVisit(int visitCount) { this.visitCount += visitCount; }
	public int getVisitCount() { return visitCount; }
	public void addTotalWin(int total) { totalWin += total; }
	public Node getParent() { return parent; }
	public OthelloState getCurrentState() { return currentState; }
}
