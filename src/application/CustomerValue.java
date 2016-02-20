package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Stefan Gustafsson
 *
 */


public class CustomerValue {

	private StringProperty name;
	private IntegerProperty totalValue;
	
	

	public CustomerValue(String name, int totalValue) {
		this.name = new SimpleStringProperty(name);
		this.totalValue = new SimpleIntegerProperty(totalValue);
	}

	public StringProperty getName() {
		return name;
	}

	public void setName(StringProperty name) {
		this.name = name;
	}

	public IntegerProperty getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(IntegerProperty totalValue) {
		this.totalValue = totalValue;
	}

}
