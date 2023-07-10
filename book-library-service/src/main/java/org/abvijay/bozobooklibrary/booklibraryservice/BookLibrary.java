package org.abvijay.bozobooklibrary.booklibraryservice;

import javax.persistence.Entity;
import javax.persistence.Id;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import java.io.Serializable;


@Entity
public class BookLibrary extends PanacheEntityBase implements Serializable{
    @Id
	String bookID;
	
	@Id
    String userID;

	public String getBookID() {
		return this.bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getUserID() {
		return this.userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "BookLibrary [bookID=" + bookID + ", userID=" + userID + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookID == null) ? 0 : bookID.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookLibrary other = (BookLibrary) obj;
		if (bookID == null) {
			if (other.bookID != null)
				return false;
		} else if (!bookID.equals(other.bookID))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

    
}
