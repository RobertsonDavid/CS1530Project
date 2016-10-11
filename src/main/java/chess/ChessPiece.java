package chess;

public class ChessPiece {
	
	private String type;
	//true if piece is white, false if piece is black
	private boolean side;
	
	public ChessPiece(String type, boolean side) {
		this.type = type;
		this.side = side;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean getSide() {
		return side;
	}
}
