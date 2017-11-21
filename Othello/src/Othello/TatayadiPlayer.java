package Othello;

import java.util.List;

import DataStructure.Minimax;
import DataStructure.Node;

public class TatayadiPlayer extends OthelloPlayer{

	private int depth;
	private Minimax mx;
	
	public TatayadiPlayer(int depth)
	{
		this.depth = depth;
		this.mx = new Minimax();
	}

	@Override
	public OthelloMove getMove(OthelloState state) {

		List<OthelloMove> moves = state.generateMoves();    
		
		if (!moves.isEmpty()) 
		{
			Node root = new Node(state);
			
			long startTime = System.currentTimeMillis();
			for(OthelloMove choice : moves)
			{
				OthelloState s = root.getCurrentState().clone();
				s.applyMoveCloning(choice);
				root.addChild(new Node(s, root));
			}
			
			int bestVal = 0;
			int bestPosition = 0;
			for(int i = 0; i < root.getChildren().size(); i++)
			{
				
				//get the best value depends on the player
				//since we are evaluating based on score. 2nd player want the score to be in the negatives
				//if the player who is executing this is 0, they want positive values so they get the best of max
				if(state.nextPlayerToMove == 0)
				{
					int val = mx.getBestMove(root.getChildren().get(i), depth, true);
					if( val > bestVal)
					{
						bestPosition = i;
						bestVal = val;
					}
				}
				//if the player who is executing this is 1, they are the 2nd player. they want the values to be in the negatives/min
				else
				{
					//setting it to false will make it return the minimal
					int val = mx.getBestMove(root.getChildren().get(i), depth, false);
					if( val < bestVal)
					{
						bestPosition = i;
						bestVal = val;
					}
				}
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Tatayadi Player execution time: " + (endTime - startTime) + " miliseconds");
			return moves.get(bestPosition);
		}
		
		return null;
	}
}
