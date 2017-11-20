package Othello;

public class RudiePlayer extends OthelloPlayer{

	private int depth;
	
	
	public RudiePlayer(int depth)
	{
		this.depth = depth;
	}

	@Override
	public OthelloMove getMove(OthelloState state) {
		
		return null;
	}
	
	
}
