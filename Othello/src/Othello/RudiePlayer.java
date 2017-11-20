package Othello;

import DataStructure.Minimax;

public class RudiePlayer extends OthelloPlayer{

	private int depth;
	
	
	public RudiePlayer(int depth)
	{
		this.depth = depth;
	}

	@Override
	public OthelloMove getMove(OthelloState state) {
		
		Minimax mx = new Minimax();
		
		
		
		return null;
	}
	
	
}
