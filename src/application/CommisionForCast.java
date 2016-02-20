package application;

import java.time.LocalDate;

/**
 * 
 * @author Stefan Gustafsson
 *
 */

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CommisionForCast {

	private IntegerProperty auction_id;
	private StringProperty product;
	private ObjectProperty<LocalDate> start_date;
	private ObjectProperty<LocalDate> end_date;
	private IntegerProperty commision;

	public CommisionForCast(int auction_id, String product, LocalDate start_date, LocalDate end_date, int commision) {

		this.auction_id = new SimpleIntegerProperty(auction_id);
		this.product = new SimpleStringProperty(product);
		this.start_date = new SimpleObjectProperty<LocalDate>(start_date);
		this.end_date = new SimpleObjectProperty<LocalDate>(end_date);
		this.commision = new SimpleIntegerProperty(commision);
	}

	public IntegerProperty getAuction_id() {
		return auction_id;
	}

	public void setAuction_id(IntegerProperty auction_id) {
		this.auction_id = auction_id;
	}

	public StringProperty getProduct() {
		return product;
	}

	public void setProduct(StringProperty product) {
		this.product = product;
	}

	public ObjectProperty<LocalDate> getStart_date() {
		return start_date;
	}

	public void setStart_date(ObjectProperty<LocalDate> start_date) {
		this.start_date = start_date;
	}

	public ObjectProperty<LocalDate> getEnd_date() {
		return end_date;
	}

	public void setEnd_date(ObjectProperty<LocalDate> end_date) {
		this.end_date = end_date;
	}

	public IntegerProperty getCommision() {
		return commision;
	}

	public void setCommision(IntegerProperty commision) {
		this.commision = commision;
	}

}
