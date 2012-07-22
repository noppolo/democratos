package messagehandling;

import java.io.Serializable;

public class Header implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String h;
	public Header(String header) {
		h=header;
	}

	public boolean isEqual(Header h2){
		if ((h2==null) || (this.h==null)) return false;
		return (this.h.compareTo(h2.h)==0);		
	}
	
	public String toString(){
		return h;
	}
}
