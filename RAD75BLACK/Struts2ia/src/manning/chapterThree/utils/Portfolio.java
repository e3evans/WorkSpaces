package manning.chapterThree.utils;

import java.util.HashSet;
import java.util.Set;

/*
 * The Portfolio class represents a single portfolio of one of the users.
 * 
 * At this stage of the Struts 2 Porfolio development.  We haven't integrated with 
 * a persistence technology.  Later in the book, we will show how to use hibernate
 * for persisting this data.  For now, the uniqueness of portfolio object depends upon
 * its name being unique to the owner ( who is one of the users ).  These business
 * rules allow us to identify and retrieve a individual portfolio based upon the
 * owner property in combination with the name property.  When we integrate with 
 * "real" data persistence, we will use numeric keys that come from the database.  
 */

public class Portfolio {
	
	private String name;
	private String owner;
	private Set pics = new HashSet<String>();

	public Set getPics() {
		return pics;
	}

	public void setPics(Set pics) {
		this.pics = pics;
	}

	public void addPic ( String pic ) {
		pics.add( pic );
	}
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
