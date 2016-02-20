package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Melker Holmgren
 *
 */


public class ReportNoBids {
	
	
		private IntegerProperty auction_id;
		private StringProperty productName;

		public ReportNoBids(int auction_id, String productName) {

			this.auction_id = new SimpleIntegerProperty(auction_id);
			this.productName = new SimpleStringProperty(productName);
		}

		public IntegerProperty getAuction_id() {
			return auction_id;
		}

		public void setAuction_id(IntegerProperty auction_id) {
			this.auction_id = auction_id;
		}

		public StringProperty getProductName() {
			return productName;
		}

		public void setProductName(StringProperty productName) {
			this.productName = productName;
		}
	
		
		
		
		

}
