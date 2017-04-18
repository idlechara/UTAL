package engine;
import java.util.Comparator;
public class Move implements Comparator {
	Coord src;
	Coord dest;
	Coord capture;
	boolean shortcastle;
	boolean longcastle;
	boolean resign;
	int promotion;

	Move () {
		src=null;
		dest=null;
		capture=null;
		shortcastle=false;
		longcastle=false;
		resign=false;
		promotion=0;
	}
	
	Move (Coord src, Coord dest) {
		this.src=src;
		this.dest=dest;
		this.capture=null;
		shortcastle=false;
		longcastle=false;
		resign=false;
		promotion=0;
	}

	Move (Coord src, Coord dest, int promotion) {
		this.src=src;
		this.dest=dest;
		this.capture=null;
		shortcastle=false;
		longcastle=false;
		resign=false;
		this.promotion=promotion;
	}


	Move (Coord src, Coord dest, Coord capture) {
		this.src=src;
		this.dest=dest;
		this.capture=capture;
		shortcastle=false;
		longcastle=false;
		resign=false;
		promotion=0;
	}


	Move (Coord src, Coord dest, Coord capture, int promotion) {
		this.src=src;
		this.dest=dest;
		this.capture=capture;
		shortcastle=false;
		longcastle=false;
		resign=false;
		this.promotion=promotion;
	}


	Move (Coord src, Coord dest, Coord capture, boolean shortcastle, boolean longcastle, boolean resign, int promotion) {
		this.src=null;
		this.dest=null;
		this.capture=null;
		this.shortcastle=shortcastle;
		this.longcastle=longcastle;
		this.promotion=promotion;
	}

	public int compare(Object o1, Object o2) {
	  if ((o1 instanceof Move) && (o2 instanceof Move))
	  return ((Move)o1).rankMove() - ((Move)o2).rankMove();
	  return 0;
	}

	public int rankMove() 
	{
	    int score=0;
	    if (getCapture()!=null) score+=6;
	    if (getPromotion()!=0) score+=10;
	    return score;
	}


	public boolean fromString (String st, int turn) {
	  
		if (st.equalsIgnoreCase("ENROQUEC")) 
		{
			shortcastle=true;
		} 
		else if (st.equalsIgnoreCase("ENROQUEL")) 
		{
			longcastle=true;
		} else if (st.equalsIgnoreCase("RESIGN"))
		{ 	
			resign=true;
		} else
		{
			String[] result = st.split("\\s"); //split at white space
			//check for promotion
     			if (result.length==3) {
				String coords[] = result[0].split("-");
				src=new Coord(coords[0]);
				dest=new Coord(coords[1]);
				//get promotion
				if (result[1].equalsIgnoreCase("PROM")) {
					switch (result[2].charAt(0)) {
						case 'R': 
						case 'D':
						case 'r':
						case 'd':
							promotion=5;
							break;
						case 'A':
						case 'a':
							promotion=3;
							break;
						case 'C':
						case 'c':
							promotion=2;
							break;
						case 'T':
						case 't':
							promotion=4;
							break;
						default:
							return false;
					}
				} else return false;
			} else if (result.length==1) {
				String coords[] = result[0].split("-");
				src=new Coord(coords[0]);
				dest=new Coord(coords[1]);
			} else return false;
			
		}
		return true;
	}
	public Move clone() {
		return new Move(src,dest, capture, shortcastle, longcastle, resign, promotion);
	}

	public boolean equals(Object o) {
		if (o instanceof Move) {
			Move m=(Move)o;
			boolean same;
			if (m.getSrc()==null && src!=null) return false;
			if (m.getDest()==null && dest!=null) return false;
			if (m.getDest()!=null && dest==null) return false;
			if (m.getSrc()!=null && src==null) return false;


			if (  ((src==null && m.getSrc()==null && dest==null && m.getDest()==null) || (src.equals(m.getSrc()) && dest.equals(m.getDest()) ) ) && shortcastle==m.getShortCastle() && longcastle==m.getLongCastle() && promotion==m.getPromotion()) return true;
		}
		return false;
	}
	
	public Coord getDest() {
		return dest;
	}
	
	public Coord getSrc() {
		return src;
	}

	public Coord getCapture() {
		return capture;
	}

	public boolean getLongCastle() {
		return longcastle;
	}

	public boolean getShortCastle() {
		return shortcastle;
	}

	public boolean getResign() 
	{
		return resign;
	}
	public int getPromotion() {
		return promotion;
	}

	public void setShortCastle(boolean shortcastle) {
		this.shortcastle=shortcastle;
	}
	
	public void setLongCastle(boolean longcastle) {
		this.longcastle=longcastle;
	}
	public void setResign(boolean resign) 
	{
		this.resign=resign;
	}

	public String toString() {
		String s="";
		if (longcastle) {
			s="ENROQUEL";
		} else if (shortcastle) {
			s="ENROQUEC";
		} else if (resign) 
		{
			s="RESIGN";
		} else 
		{
		
		s+=src+"-"+dest;
		char charprom;
		switch (promotion) {
			case 2: 
				charprom='C';
				break;
			case 3: 
				charprom='A';
				break;
			case 4: 
				charprom='T';
				break;
			case 5: 
				charprom='R';
				break;
			default:
				charprom='\n';
		}
		if (promotion!=0)
			s+=" PROM "+charprom;
		}
		return s;
	}
}	