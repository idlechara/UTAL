package engine;
import java.util.ArrayList;

public class Board {
	int b[][];

	boolean shortcastle_b; 	/// Flag for black short castle (enroque)
	boolean longcastle_b; 	/// Flag for black long castle (enroque)
	boolean shortcastle_w;	/// Flag for white short castle (enroque)
	boolean longcastle_w; 	/// Flag for white long castle

	Coord enpassent;  	/// Coordinate of the En Passent (al Paso) capture

	int movestodraw; 	/// number of movements that can be made until the game is declared a draw
	public int turn;	/// Who's turn is it?

	//ArrayList<Board> RepeatedBoards;

	ArrayList<Coord> blackPieces;	/// The list of black pieces on the board
	ArrayList<Coord> whitePieces;	/// The list of white pieces on the board
	
	ArrayList<Move> movelist;	/// The list of movements available

	Coord reyBlack;			/// The coordinate of the black king
	Coord reyWhite;			/// The coordinate of the white king
	

	/// Constants to make the code understandable

	static final int BOARDSIZE=8;	/// The size of the board

	static final int EMPTY=0;	/// An empty square

	/// Piece representations
	static final int BLACK_KING=-6;
	static final int WHITE_KING=6;

	static final int BLACK_QUEEN=-5;
	static final int WHITE_QUEEN=5;

	static final int BLACK_PAWN=-1;
	static final int WHITE_PAWN=1;

	static final int BLACK_BISHOP=-3;
	static final int WHITE_BISHOP=3;
	
	static final int BLACK_KNIGHT=-2;
	static final int WHITE_KNIGHT=2;

	static final int BLACK_ROOK=-4;
	static final int WHITE_ROOK=4;
	
		
	static final int TURNBLACK=-1; 	/// Black turn is -1, White turn is 1 (corresponds to the sign of the pieces)
	static final int TURNWHITE=1;

	/**	Constructor with default values
	**/
	public Board() {
		b=new int[BOARDSIZE][BOARDSIZE];
		int[][] board={{-4,-2,-3,-5,-6,-3,-2,-4},
				 {-1,-1,-1,-1,-1,-1,-1,-1},
				 {0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0},
 				 {0,0,0,0,0,0,0,0},
				 {0,0,0,0,0,0,0,0},
	  			 {1,1,1,1,1,1,1,1},
				 {4,2,3,5,6,3,2,4}};
		fromArray(board);
		shortcastle_b=true;
		longcastle_b=true;
		shortcastle_w=true;
		longcastle_w=true;
		enpassent=null;
		movestodraw=100;
		//RepeatedBoards=new ArrayList<Board>();
		turn=TURNWHITE;
		blackPieces=new ArrayList<Coord>();
		whitePieces=new ArrayList<Coord>();
		movelist=new ArrayList<Move>();
		buildPieceList();
	}
	

	/**	Constructor with all parameters (used in clone)
	**/
	Board(int b[][], boolean shortcastle_b,boolean  longcastle_b,boolean shortcastle_w,boolean  longcastle_w, int movestodraw, Coord enpassent,  ArrayList bp, ArrayList wp, ArrayList mvlist, int turn, Coord reyW, Coord reyB) {
		this.b=new int[BOARDSIZE][BOARDSIZE];
		for (int i=0; i<BOARDSIZE; i++)
			for (int j=0; j<BOARDSIZE; j++)
				this.b[i][j]=b[i][j];
		this.shortcastle_b=shortcastle_b;
		this.longcastle_b=longcastle_b;
		this.shortcastle_w=shortcastle_w;
		this.longcastle_w=longcastle_w;
		this.movestodraw=movestodraw;
		this.enpassent=enpassent;
		//this.RepeatedBoards=new ArrayList<Board>(RepeatedBoards);
		this.blackPieces=new ArrayList<Coord>(bp);
		this.whitePieces=new ArrayList<Coord>(wp);
		this.movelist=new ArrayList<Move>(mvlist);
		this.turn=turn;
		reyWhite=reyW;
		reyBlack=reyB;
	}
	
	/**	Convert a given board into a string
	**	@return a string representing the board state
	**/
	public String toString() {
	       //TODO: fix the enroques
		String endl=System.getProperty("line.separator");
		String s="";
		for (int i=0; i<BOARDSIZE;i++) {
			for (int j=0; j<BOARDSIZE;j++) {
				  	s+=b[i][j]+" ";
				
			}
			s+=endl;
		}
		if (turn==TURNBLACK) s+="N"+endl; else s+="B"+endl;
		s+="MovsHastaEmpate "+movestodraw+endl;
		if (getShortCastle(TURNBLACK)) s+="EnroqueC_N"+endl;
		if (getLongCastle(TURNBLACK)) s+="EnroqueL_N"+endl;
		if (getShortCastle(TURNWHITE)) s+="EnroqueC_B"+endl;
		if (getLongCastle(TURNWHITE)) s+="EnroqueL_B"+endl;
		if (enpassent!=null) s+="AlPaso "+enpassent+endl;
		return s;
	}


	/**	Make an exact copy of this board
	**	@return A copy of this board
	**/

	public Board clone() {
		return new Board(b, shortcastle_b,longcastle_b,shortcastle_w,longcastle_w, movestodraw, enpassent, blackPieces, whitePieces, movelist, turn, reyWhite, reyBlack);
	}


	/**	Set a flag to determine if en passent pawn capture is valid
	**	@param enpassent the value to be established
	**/
	public void setEnPassent(Coord enpassent) {
		this.enpassent=enpassent;
	}

	/**	Set turn
	**	@param turn the value to be established
	**/
	public void setTurn(int turn) {
		this.turn=turn;
	}


	/**	Build lists of black pieces and white pieces on the board. Store them in the appropriate vectors
	**/
	public void buildPieceList() 
	{
		blackPieces=new ArrayList<Coord>();
		whitePieces=new ArrayList<Coord>();
		for (int i=0; i<BOARDSIZE; i++)
			for (int j=0; j<BOARDSIZE; j++)
			{
				if (b[i][j]==BLACK_KING)
				{
					reyBlack=new Coord(i,j);
				} else if (b[i][j]==WHITE_KING)	{
					reyWhite=new Coord(i,j);
				}
				//add all black and white pieces to the corresponding vectors
				if (b[i][j]<0)   //black pieces 
					blackPieces.add(new Coord(i,j));
				else if (b[i][j]>0) 
					whitePieces.add(new Coord(i,j));			
			}	
	}

	/**	Convert a ArrayList of moves into an array of moves
	**	@param v A movelist in the form of a vector
	**	@return an array of moves
	**/
	private Move[] vectorToMove(ArrayList<Move> v) {

		Move[] m=new Move[v.size()];
		for (int i=0; i<v.size(); i++)
			m[i]=(Move)v.get(i);
		return m;
	}


	/**	Determine if a given coordinate is within the bounds of the board
	**	@param c The coordinate to check
	**	@return true if c is greater than or equal to 0 and less than the size of the board
	**/
	private boolean inBounds(Coord c) {
		if (c.getX()>=BOARDSIZE || c.getX()<0 || c.getY()>=BOARDSIZE || c.getY()<0)
			return false;
		return true;
	}

	/**	Determine if a given coordinate is empty
	**	@param c The coordinate to check
	**	@return true if c is empty
	**/
	private boolean isEmpty(Coord c) {
		if (b[c.x][c.y]==EMPTY)
			return true;
		return false;
	}


	/**	Determine if a given coordinate represents a Pawn of either color
	**	@param c The coordinate to check
	**	@return true if c is a pawn
	**/
	private boolean isPawn(Coord c) {
		if (b[c.x][c.y]==WHITE_PAWN || b[c.x][c.y]==BLACK_PAWN) return true;
			else return false;
	}

	/**	Determine if a given coordinate represents a Knight of either color
	**	@param c The coordinate to check
	**	@return true if c is a Knight
	**/
	private boolean isKnight(Coord c) {
		if (b[c.x][c.y]==WHITE_KNIGHT || b[c.x][c.y]==BLACK_KNIGHT) return true;
			else return false;
	}


	/**	Determine if a given coordinate represents a King of either color
	**	@param c The coordinate to check
	**	@return true if c is a King
	**/
	private boolean isKing(Coord c) {
		if (b[c.x][c.y]==WHITE_KING || b[c.x][c.y]==BLACK_KING) return true;
			else return false;
	}


	/**	Determine if a given coordinate represents a Rook of either color
	**	@param c The coordinate to check
	**	@return true if c is a rook
	**/
	private boolean isRook(Coord c) {
		if (b[c.x][c.y]==WHITE_ROOK || b[c.x][c.y]==BLACK_ROOK) return true;
			else return false;
	}

	/**	Determine if a given coordinate represents a black piece
	**	@param c The coordinate to check
	**	@return true if c is a black piece
	**/
	private boolean isBlack(Coord c) {
		if (b[c.x][c.y]<0) return true;
		else return false;
	}

	/**	Determine if a given coordinate represents a white piece
	**	@param c The coordinate to check
	**	@return true if c is a white piece
	**/
	private boolean isWhite(Coord c) {
		if (b[c.x][c.y]>0) return true;
		else return false;
	}

	/**	Determine if two pieces are opposite colors
	**	@param pos1 Coordinate A
	**	@param pos2 Coordinate B
	**	@return true if A and B are opposite colors
	**/
	private boolean isOpposite(Coord pos1, Coord pos2) {
		if ((isBlack(pos1) && isWhite(pos2)) || (isWhite(pos1) && isBlack(pos2))) return true;
		else return false;

	}


	/**	Determine if a coordinate is in the next to last rank
	**	@param pos The coordinate of a move
	**	@param turn Who's turn 
	**	@return whether the coordinate is in the next to last rank
	**/
	private boolean lastRank(Coord pos, int turn) {
		if ((isBlack(pos) && pos.getX()==BOARDSIZE-2) || (isWhite(pos) && pos.getX()==1) ) return true;
		else return false;
	}

	/**	Determine if a coordinate is the destination of a move within a given array of moves.
	**	@param dest the destination coordinate of a move
	**	@param moves The list of moves to check
	**	@return whether pos appears in the destination of any of the elements within moves
	**/
	public boolean inMoveList(Coord dest, Move[] moves) {
		for (int i=0; i<moves.length; i++) {
			if (moves[i].getDest().equals(dest)) return true;
		}
		return false;
	}

	/**	Determine if a move is within a given array of moves.
	**	@param move the destination coordinate of a move
	**	@param moves The list of moves to check
	**	@return whether pos appears in the destination of any of the elements within moves
	**/
	public boolean inMoveList(Move move, Move[] moves) {
		for (int i=0; i<moves.length; i++) {
			if (moves[i].equals(move)) return true;
		}
		return false;
	}


	public Coord getKing(int turn) {
		if (turn==TURNBLACK)
			return reyBlack;
		else return reyWhite;
	}


	/**	Determine if a piece can move to another position
	**	@param src The source coordinate of a move
	**	@param dest The destination coordiante of a move
	**	@return whether the piece can move to the destination
	**/
	public boolean canMove(Coord src, Coord dest) {
		int turn;
		if (isBlack(src)) turn=TURNBLACK;
		else turn=TURNWHITE;
		if (!isEmpty(src) && inMoveList(dest, getPieceMoves(src, turn ))) return true;
		
		return false;
	}


	/**	Determine if a piece can block the piece threatening the king
	**	@param pieces A list of pieces that could move to block
	**	@param king The coordinate of the king
	**	@param threat The coordinate of the piece that is threatening the king
	**	@return true if one of the pieces in the list can block the threat
	**/
	public boolean canBlock(Coord[] pieces, Coord king, Coord threat) {
		//run through a list of pieces, making sure none of them can move to block a threat.
		//can't block pawn or knight threats, discard them immediately 
		
		
		if (!isPawn(threat) && !isKnight(threat)) {
			int xdiff=king.getX()-threat.getX();
			int ydiff=king.getY()-threat.getY();
			int xmod;
			int ymod;
			//determine the direction of the threat
			if (xdiff>0) xmod=-1;
			else if (xdiff<0) xmod=1;
			else xmod=0;
			if (ydiff>0) ymod=-1;
			else if (ydiff<0) ymod=1;
			else ymod=0;
			
			//start at king, work through coordinates to see if any ally piece can move there
			Coord current=new Coord(king.getX(), king.getY());
			
			do {
			
				current=new Coord(current.getX()+xmod, current.getY()+ymod);
				
				for (Coord c:pieces) {
					if (canMove(c, current) && validMove(new Move(c,current))) return true;
				}
				
			} while (!current.equals(threat));


		} else {
		  //but perhaps pawns and knights can be eaten!
		     for (Coord c:pieces) {
		       if (canMove(c, threat) && validMove(new Move(c,threat))) return true;
		     }
		}  

		return false;
	}


	/**	Get the appropriate flag for short castle, based on turn
	**	@param turn which player should we set
	**	@return whether a short castle is available or not
	**/
	public boolean getShortCastle(int turn) {
		if (turn==TURNBLACK)
			return shortcastle_b;
		else return shortcastle_w;
	}

	/**	Get the appropriate flag for long castle, based on turn
	**	@param turn which player should we set
	**	@return whether a long castle is available or not
	**/
	public boolean getLongCastle(int turn) {
		if (turn==TURNBLACK)
			return longcastle_b;
		else return longcastle_w;
	}


	/**	Set the appropriate flag for short castle, based on turn
	**	@param turn which player should we set
	**	@param shortcastle set whether a short castle is available or not
	**/
	public void setShortCastle(int turn, boolean shortcastle) {
		if (turn==TURNBLACK)
			this.shortcastle_b=shortcastle;
		else this.shortcastle_w=shortcastle;
	}

	/**	Set the appropriate flag for long castle, based on turn
	**	@param turn which player should we set
	**	@param longcastle set whether a long castle is available or not
	**/
	public void setLongCastle(int turn, boolean longcastle) {
		if (turn==TURNBLACK)
			this.longcastle_b=longcastle;
		else this.longcastle_w=longcastle;
	}

	/**	Set piece
	**	@param c the coordinate
	**	@param type Which piece type to set
	**/
	public void setPiece(Coord c, int type) {
		b[c.getX()][c.getY()]=type;
	}

	/**	Get piece
	**	@param c the coordinate
	**/
	public int getPiece(Coord c) {
		return b[c.getX()][c.getY()];
	}


	/**	Get the list of black pieces on the board
	** 	@return an array of Coord objects that represent all the black pieces on the board.
	**/
	public ArrayList<Coord> getBlackPieces() {
		return blackPieces;
	}
	
	/**	Get the list of white pieces on the board
	** 	@return an array of Coord objects that represent all the white pieces on the board.
	**/
	public ArrayList<Coord> getWhitePieces() {
		return whitePieces;
	}
	

	/**	Removes a piece from the board and appropriate list of pieces
	** 	@param piece The piece to be removed
	**/
	public void removePiece(Coord piece) 
	{
		if (!isEmpty(piece)) 
		{
			if (isWhite(piece)) 
				whitePieces.remove(whitePieces.indexOf(piece));
			else 
				blackPieces.remove(blackPieces.indexOf(piece));
		}
		b[piece.getX()][piece.getY()]=EMPTY;
	}

	/**	Moves a piece on the board and updates the appropriate list of pieces
	** 	@param piece the piece to be move 
	** 	@param dest where to move it
	**/
	public void movePiece(Coord piece, Coord dest) 
	{
		if (!isEmpty(piece)) 
		{
			if (isWhite(piece)) 
			{
				whitePieces.remove(whitePieces.indexOf(piece));
				whitePieces.add(dest);
				if (isKing(piece))
					reyWhite=dest;
			} else 
			{
				blackPieces.remove(blackPieces.indexOf(piece));
				blackPieces.add(dest);
				if (isKing(piece))
					reyBlack=dest;
			}
			b[dest.getX()][dest.getY()]=b[piece.getX()][piece.getY()];
			b[piece.getX()][piece.getY()]=EMPTY;
		}
		
	}

	/**	Get the possible movements of a given piece
	**	@param pos a position on the board(should represent a piece and not an empty space)
	**	@param turn who's turn it is.
	** 	@return an array of Move objects that describe the movements available for that piece
	**/
	public Move[] getPieceMoves(Coord pos, int turn) 
	{
		Move[] m=new Move[0];
		switch (Math.abs(b[pos.x][pos.y])) 
		{
			case (WHITE_QUEEN) :
				m=queenMoves(pos, turn);
				break;
			case (WHITE_ROOK) :
				m=rookMoves(pos, turn);
				break;
			case (WHITE_KNIGHT) :
				m=knightMoves(pos, turn);
				break;
			case (WHITE_BISHOP) :
				m=bishopMoves(pos, turn);
				break;
			case (WHITE_PAWN) :
				m=pawnMoves(pos, turn);
				break;
			case (WHITE_KING) :
				m=kingMoves(pos, turn);
				break;
		}
		return m;
	}

	

	/**	Get the pieces that threaten this position 
	**	@param pos a position on the board
	**	@param pieces A list of coordinates where the pieces to be checked are
	**	@param turn who's turn it is.
	** 	@return an array of Coord objects that actually threaten pos
	**/
	public Coord[] getThreats(Coord pos,ArrayList<Coord> pieces, int turn) 
	{
		// run through a list of pieces to see if any threaten pos
		ArrayList<Coord> piecethreats=new ArrayList<Coord>();
		for (Coord c : pieces) 
		{
			if (!isKing(c)) 
			{
				if (inMoveList(pos,getPieceMoves(c,turn))) 
					piecethreats.add(c);
				
			}
			else if (inMoveList(pos,getKingThreats(c))) 
				piecethreats.add(c);
			
		}
		
		Coord[] threats=new Coord[piecethreats.size()];
		for (int i=0; i<piecethreats.size(); i++)
			threats[i]=(Coord)piecethreats.get(i);
		return threats;
	}

	/**	Check to see if a move leaves the king in check
	**	
	**	@param m the move to be validated
	**	@return true if the king is check after the move
	**/
	public boolean validateMove(Move m) 
	{
		Board validate=this.clone();
		validate.makeMove(m);
		//check King moves here (impossible to do otherwise)
		return !validate.isCheck(turn);
	}

	public Move[] getValidMoves() 
	{
		ArrayList<Move> moves=new ArrayList<Move>();
		ArrayList<Coord> pieces;
		
		if (turn==TURNBLACK) pieces=blackPieces;
		else pieces=whitePieces;
		for (Coord c : pieces) 
		{
			Move[] piecemoves=getPieceMoves(c,turn);
			for (Move m : piecemoves) 
				if (validateMove(m)) moves.add(m);	
		}
		
		return vectorToMove(moves);
	}
	
	public boolean validMove(Move m) 
	{
		Move[] ms=getValidMoves();	
		for (int i=0; i<ms.length; i++) 
		  if (ms[i].equals(m)) return true;
		return false;
	}

	/**	Check if a position is threatened 
	**	@param pos a position on the board
	**	@param turn who's turn it is.
	** 	@return true or false, depending if another piece can move into that position or not.
	**/
	public boolean isThreatened(Coord pos, int turn) {
		Coord[] c;
		if (turn==TURNBLACK) 
			c=getThreats(pos, whitePieces, TURNWHITE);
		else 
			c=getThreats(pos, blackPieces, TURNBLACK);
		//for (Coord threat:c) 
		//	System.out.println(threat);
		if (c!=null && c.length >0) return true;
		else return false;
	}
	
	/**	Check if the King is in Check on the current board. 
	** 	@return true or false, depending if the King for the current player is in check
	**/
	public boolean isCheck(int turn) {
		//if the king is threatened, the king is in check.
		Coord king;
		if (turn==TURNBLACK) 
			king=reyBlack;
		else king=reyWhite;
		return isThreatened(king, turn);
	}	

	/**	Check if the King is in Checkmate on the current board. 
	** 	@return true or false, depending if the King for the current player is in checkmate
	**/
	public boolean isCheckMate() {
		
		Coord[] c;
		Move[] m;
		Coord king;
		Coord[] pieces=new Coord[0];
		
		if (isCheck(turn)) {
		        
			if (turn==TURNBLACK)
			{
				c=getThreats(reyBlack, whitePieces, TURNWHITE);
				m=getPieceMoves(reyBlack, turn);
				king=reyBlack;
				pieces=getBlackPieces().toArray(pieces);
			}
			else 
			{
				c=getThreats(reyWhite, blackPieces, TURNBLACK);
				m=getPieceMoves(reyWhite, turn);
				king=reyWhite;
				pieces=getWhitePieces().toArray(pieces);
			}
			
			//if the king is threatened by more than two pieces and has no legal moves, it is checkmate
			if (m.length==0 && c.length >1) {
				return true;
			} 

			
			//if the king has no legal moves, and no ally can block the check, then it is checkmate
			if (m.length==0) {
				return !canBlock(pieces, king, c[0]);
			}			
			
		}
		
		return false;
	}	

	/**	Check to see if the board is a stalemate
	** 	@return true or false, depending if the King for the current player is in check
	**/
	public boolean isStalemate() {
		Coord[] pieces=new Coord[0];
		if (turn==TURNBLACK) {
			pieces=getBlackPieces().toArray(pieces);
		} else {
			pieces=getWhitePieces().toArray(pieces);
		}
		//if there are no legal moves, and the king is not in check, the board is a stalemate
		Move[] movelist=getValidMoves();
		boolean hasmoves;
		if (movelist.length==0) hasmoves=false; 
		else hasmoves=true;
			
		
		//if 100 moves have been made without a capture or pawn move, the board is a stalemate.
		if (isCheck(turn) || (hasmoves && movestodraw>0)) return false;
		else return true;
		//if this board has been repeated three times, board is a stalemate.

	}	



	/**	Build a board from an array
	**	After this method is run, the Board associated will be the same as the array. 
	**  	The piece list will be built but the various flags will not be set. It is up to the programmer to set them.
	**	
	**	@param b a board represented as an array of integers
	**/
	public void fromArray(int b[][]) {
		this.b=new int[BOARDSIZE][BOARDSIZE];
		for (int i=0; i<BOARDSIZE; i++)
			for (int j=0; j<BOARDSIZE; j++)
				this.b[i][j]=b[i][j];
		buildPieceList();
	}

	/**	Generate a list of pawn moves
	**	@param src The coordinates of the pawn
	**	@param turn Who's turn it is
	**	@return A list of Move objects that represent the movements a pawn can make 
	**/
	public Move[] pawnMoves(Coord src, int turn) {		
		// direction is reversed (white moves are -1, black moves are 1)
		int direction=-1*turn;

		ArrayList<Move> moves=new ArrayList<Move>();
		if (b[src.x][src.y]==WHITE_PAWN*turn) {
			//normal move
			Coord normalmove=new Coord(src.getX()+direction, src.getY());
			if (inBounds(normalmove) && isEmpty(normalmove)) {
				//promotions
				if (lastRank(src, turn)) {
					for (int promotion=WHITE_KNIGHT; promotion<WHITE_KING; promotion++) {
						moves.add(new Move(src,normalmove, promotion));
					}
				}
				else moves.add(new Move(src,normalmove));

			}
			
			
			//two space move 
			if ( (src.getX()==1 && b[src.x][src.y]==BLACK_PAWN) || (src.getX()==6 && b[src.x][src.y]==WHITE_PAWN)) {
				Coord twospace=new Coord(src.getX()+direction*2, src.getY());
				if (isEmpty(twospace)&&isEmpty(new Coord(twospace.x-direction, twospace.y)))
					moves.add(new Move(src,twospace));
			}
			
			Coord captureleft=new Coord(src.getX()+direction, src.getY()-1);
			Coord captureright=new Coord(src.getX()+direction, src.getY()+1);

			//normal capture
			if (inBounds(captureleft) && isOpposite(src,captureleft))  {
				if (lastRank(src, turn)) {
					for (int promotion=WHITE_KNIGHT; promotion<WHITE_KING; promotion++) {
						moves.add(new Move(src,captureleft, captureleft, promotion));
					}
				} else
				moves.add(new Move(src,captureleft, captureleft));
			}

			if (inBounds(captureright) && isOpposite(src,captureright)) {
				if (lastRank(src, turn)) {
					for (int promotion=WHITE_KNIGHT; promotion<WHITE_KING; promotion++) {
						moves.add(new Move(src,captureright, captureright, promotion));
					}
				} else
				moves.add(new Move(src,captureright,captureright));
			}

			//en passent moves
			if (enpassent!=null) {
				Coord possiblecaptureright=new Coord(captureright.x-direction, captureright.y);
				Coord possiblecaptureleft=new Coord(captureleft.x-direction, captureleft.y);
				
				if (possiblecaptureright.equals(enpassent))
					moves.add(new Move(src,captureright,possiblecaptureright));
				if (possiblecaptureleft.equals(enpassent))
					moves.add(new Move(src,captureleft,possiblecaptureleft));
			}

		}

		

		return vectorToMove(moves);
	}


	/**	Generate a list of rook moves
	**	@param src The coordinates of the rook
	**	@param turn Who's turn it is
	**	@return A list of Move objects that represent the movements the rook can make 
	**/
	public Move[] rookMoves(Coord src, int turn) {
		// here we don't care if it's actually a rook or not, just return the possible moves assuming it is (helpful with queen)
		ArrayList<Move> moves=new ArrayList<Move>();

		int xdest=src.x; int ydest=src.y;
		int xmod=1; int ymod=0;
		boolean stop=false;
		while (!stop) {
			xdest+=xmod; ydest+=ymod;
			Coord dest=new Coord(xdest,ydest);
			
			if (inBounds(dest) && isEmpty(dest)) {
					moves.add(new Move(src, dest));
			} else {
				if (inBounds(dest) && isOpposite(src,dest)) {
					//capture
					moves.add(new Move(src, dest, dest));
				} 
				//blocked or out of bounds, now reset the position to the piece and test in a different direction
				if (xmod==1) 
					xmod=-1;
				else if (xmod==-1) 
				{
					xmod=0;
					ymod=1;
				}	
				else if (ymod==1) 
					ymod=-1;
				else stop=true;
				xdest=src.x; ydest=src.y;
			}

		}
		return vectorToMove(moves);
	}


	/**	getKingThreats
	**	@param src The coordinates of the king
	**	@return A list of Move objects that represent where the king threatens
	**/
	public Move[] getKingThreats(Coord src) {
		Coord[] km=new Coord[8];
		km[0]=new Coord(src.x+1, src.y+1);
		km[1]=new Coord(src.x+1, src.y-1);
		km[2]=new Coord(src.x, src.y+1);
		km[3]=new Coord(src.x, src.y-1);
		km[4]=new Coord(src.x-1, src.y+1);
		km[5]=new Coord(src.x-1, src.y-1);
		km[6]=new Coord(src.x+1, src.y);
		km[7]=new Coord(src.x-1, src.y);
		Move[] m=new Move[8];
		for (int i=0;i<8;i++) {
			m[i]=new Move(src, km[i]);
		}
		return m;
	}

	/**	Generate a list of king moves
	**	@param src The coordinates of the king
	**	@param turn Who's turn it is
	**	@return A list of Move objects that represent the movements the king can make 
	**/
	public Move[] kingMoves(Coord src, int turn) {
		
		ArrayList<Move> moves=new ArrayList<Move>();
		Coord[] km=new Coord[8];
		km[0]=new Coord(src.x+1, src.y+1);
		km[1]=new Coord(src.x+1, src.y-1);
		km[2]=new Coord(src.x, src.y+1);
		km[3]=new Coord(src.x, src.y-1);
		km[4]=new Coord(src.x-1, src.y+1);
		km[5]=new Coord(src.x-1, src.y-1);
		km[6]=new Coord(src.x+1, src.y);
		km[7]=new Coord(src.x-1, src.y);
		//check the normal King moves for validity by moving the king, then checking to see if it is in check.
		for (int i=0; i<8; i++) {
			if (inBounds(km[i]) ) {
				if (isOpposite(src,km[i]) || isEmpty(km[i])) {
					
					Move m=new Move(src, km[i]);
					Board validate=this.clone();
					validate.makeMove(m);
					if (!validate.isThreatened(km[i],turn)) {
						if (isEmpty(km[i])) 
							moves.add(m);
						else moves.add(new Move(src, km[i],km[i]));
					}
				}
			}
		}

		//check the castling moves
		Coord intermediateSpaces[];
		int row;
		if (turn==TURNBLACK) row=0; 
		else row=7;
		
		if (!isThreatened(src,turn)) {
			if (getLongCastle(turn)) {
				intermediateSpaces = new Coord[3];
				for (int i=0; i<3; i++) 
					intermediateSpaces[i]=new Coord(row,1+i);
				if (isEmpty(intermediateSpaces[0]) && isEmpty(intermediateSpaces[1]) && isEmpty(intermediateSpaces[2])) {
					Move m=new Move(); //create a castling move
					m.setLongCastle(true);
					Board validate=this.clone();
					validate.makeMove(m);

					//validate that after this move none of the intermediate spaces are in check.
					if (!validate.isThreatened(intermediateSpaces[0], turn) && 
						!validate.isThreatened(intermediateSpaces[1], turn) && !validate.isThreatened(intermediateSpaces[2], turn) ) 
						moves.add(m);
				}			
			}
	
			if (getShortCastle(turn)) {
				intermediateSpaces = new Coord[2];
				for (int i=0; i<2; i++) 
					intermediateSpaces[i]=new Coord(row,5+i);
				if (isEmpty(intermediateSpaces[0]) && isEmpty(intermediateSpaces[1]) ) {
					Move m=new Move(); //create a castling move
					m.setShortCastle(true);

					//validate that after this move none of the intermediate spaces are in check.
					Board validate=this.clone();
					validate.makeMove(m);
// 
					if (!validate.isThreatened(intermediateSpaces[0], turn) && 
						!validate.isThreatened(intermediateSpaces[1], turn) ) 
						moves.add(m);
				}			
			}
		}
		return vectorToMove(moves);
	}
	
	/**	Generate a list of knight moves
	**	@param src The coordinates of the knight
	**	@param turn Who's turn it is
	**	@return A list of Move objects that represent the movements the knight can make 
	**/
	public Move[] knightMoves(Coord src, int turn) {
		ArrayList<Move> moves=new ArrayList<Move>();
			// create the 8 moves then test for captures && legality
		Coord[] km=new Coord[8];
		km[0]=new Coord(src.x+2, src.y+1);
		km[1]=new Coord(src.x+2, src.y-1);
		km[2]=new Coord(src.x+1, src.y+2);
		km[3]=new Coord(src.x+1, src.y-2);
		km[4]=new Coord(src.x-1, src.y+2);
		km[5]=new Coord(src.x-1, src.y-2);
		km[6]=new Coord(src.x-2, src.y+1);
		km[7]=new Coord(src.x-2, src.y-1);

		for (int i=0; i<8; i++) {
			if (inBounds(km[i])) {
				if (isOpposite(src,km[i])) 
					moves.add(new Move(src, km[i], km[i]));
				if (isEmpty(km[i]))
					moves.add(new Move(src, km[i]));
			}
		}
		return vectorToMove(moves);
	}
	

	/**	Generate a list of bishop moves
	**	@param src The coordinates of the bishop
	**	@param turn Who's turn it is
	**	@return A list of Move objects that represent the movements the bishop can make 
	**/
	public Move[] bishopMoves(Coord src, int turn) {
		// here we don't care if it's actually a bishop or not, just return the possible moves assuming it is (helpful with queen)
		ArrayList<Move> moves=new ArrayList<Move>();

		//run the diagonals until we hit another piece or the edge of board.

		int xdest=src.x; int ydest=src.y;
		int xmod=1; int ymod=1;
		boolean stop=false;
		while (!stop) {
			xdest+=xmod; ydest+=ymod;
			Coord dest=new Coord(xdest,ydest);

			if (inBounds(dest) && isEmpty(dest)) {
					moves.add(new Move(src, dest));
			} else {
				if (inBounds(dest) && isOpposite(src,dest)) {
					//capture
					moves.add(new Move(src, dest, dest));
				}
				
				//blocked or out of bounds, now reset the position to the piece and test in a different direction
				if (xmod==1 && ymod==1) 
					xmod=-1; 
				else if (xmod==-1 && ymod==1) 
					ymod=-1;
				else if (xmod==-1 && ymod==-1) 
					xmod=1;
				else stop=true;
				xdest=src.x; ydest=src.y;
				
			}
			
		}
		
		return vectorToMove(moves);
	}
	

	/**	Generate a list of queen moves
	**	@param src The coordinates of the queen
	**	@param turn Who's turn it is
	**	@return A list of Move objects that represent the movements the queen can make 
	**/
	public Move[] queenMoves(Coord src, int turn) {
		Move[] rmoves=rookMoves(src, turn);
		Move[] bmoves=bishopMoves(src, turn);
		
		Move[] qmoves=new Move[rmoves.length+bmoves.length];

		int mv=0;
		for (int i=0; i<rmoves.length; i++) {
			qmoves[mv]=rmoves[i];
			mv++;
		}
		for (int i=0; i<bmoves.length; i++) {
			qmoves[mv]=bmoves[i];
			mv++;
		}
		return qmoves;
	}


	/**	Actually make a move on the board, turn is changed and all associated flags are set correctly.
	**	@param m A valid and legal move. Programmer is responsible for checking validity
	**/
	public void makeMove(Move m) {
		Coord capture=m.getCapture();
		Coord dest=m.getDest();
		Coord src=m.getSrc();
		boolean shortc=m.getShortCastle();
		boolean longc=m.getLongCastle();
		
		//decrement the number of moves to draw (later we reset if needed)
		movestodraw--;
		
		//assume enpassent is not available, if it is we set it later
		enpassent=null;
		
		
		//if it is a pawn, then reset the moves to draw number		
		if (src!=null && isPawn(src)) 
		{
			movestodraw=100;
			
			//check to see if an enpassent move is available, if so, mark it
			
			//check to see if the dest is two rows ahead of the source 
			//(implictly this can only be true when we're moving from our original pawn position)
			int diff=src.getX()-dest.getX();
			if (Math.abs(diff)==2)
			{
			      // check to see if there are any pawns to either side of the final position
			      Coord right = new Coord(dest.getX(),dest.getY()+1);
			      Coord left  = new Coord(dest.getX(),dest.getY()+1);
			      //if all checks out, en passent is available
			      if (inBounds(right) && isPawn(right) && isOpposite(src, right) || 
				  inBounds(left) && isPawn(left) && isOpposite(src, left) ) 
				      enpassent=dest;
			}
		}
		
		// if the king is being moved, invalidate castles
		if (src!=null && isKing(src)) {
			this.setLongCastle(turn, false);
 			this.setShortCastle(turn, false);
		}

		//if a rook is being moved, figure out which one and invalidate the associated castle
		if (src!=null && (this.getLongCastle(turn) || this.getShortCastle(turn)) && isRook(src)) {
			if (src.getY()==0) 
				this.setLongCastle(turn, false);
			else
				this.setShortCastle(turn,false);
		}

		if (dest!=null && !isEmpty(dest)) capture=dest;

		// remove the piece if we have a capture and reset moves to draw.
		if (capture!=null) 
		{
			removePiece(capture);
			movestodraw=100;
		}


		// if we have no castling going on
		if (!(shortc || longc)) 
		{
			if (m.getPromotion()==0) 
				movePiece(src, dest);
			else 
			{
				movePiece(src, dest);
				//do the promotion by hand
				b[dest.getX()][dest.getY()]=m.getPromotion()*turn;
			}
		}
		
		// set the row so we can use the same bit of code to do both black & white castles
		int row;
		if (turn==TURNBLACK) row=0; 
		else row=7;
		
		if (shortc) 
		{
			//swap rook and king
			movePiece(new Coord(row, 6), new Coord(row,4)); //move the King
			movePiece(new Coord(row, 5), new Coord(row,7)); //move the Rook
			//invalidate further castling
			setShortCastle(turn, false); 
			setLongCastle(turn, false);
		}

		if (longc) 
		{
			//swap rook and king
			movePiece(new Coord(row, 2), new Coord(row,4)); //move the King
			movePiece(new Coord(row, 3), new Coord(row,0)); //move the Rook
			//invalidate further castling
			setShortCastle(turn, false); 
			setLongCastle(turn, false);
		}

	
		turn=turn*-1; //change turns
		movelist.add(m);
	}
} 
