package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {

	private IntegerProperty categoryId;
	private StringProperty categoryName;

	public Category(int categoryId, String categoryName) {

		this.categoryId = new SimpleIntegerProperty(categoryId);
		this.categoryName = new SimpleStringProperty(categoryName);
	}

	/**
	 * @return the categoryId
	 */

	public IntegerProperty getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */

	public void setCategoryId(IntegerProperty categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the categoryName
	 */

	public StringProperty getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */

	public void setCategoryName(StringProperty categoryName) {
		this.categoryName = categoryName;
	}
	
	@Override
	public String toString(){
		return categoryName.getValue();
	}

}