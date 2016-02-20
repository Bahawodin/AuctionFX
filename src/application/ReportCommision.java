package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 *  @author Simon Hansson
 *  
 */


public class ReportCommision {

	private IntegerProperty commision;
	private StringProperty date;

	public ReportCommision(int commision, String date) {

		this.commision = new SimpleIntegerProperty(commision);
		this.date = new SimpleStringProperty(date);
	}

	public IntegerProperty getCommision() {
		return commision;
	}

	public void setCommision(IntegerProperty commision) {
		this.commision = commision;
	}

	public StringProperty getDate() {
		return date;
	}

	public void setDate(StringProperty date) {
		this.date = date;
	}

}