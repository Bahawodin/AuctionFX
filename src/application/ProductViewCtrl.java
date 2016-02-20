package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Ajmal Bahawodin
 *
 */
public class ProductViewCtrl {
	
	@FXML
	ImageView picView;
	
	@FXML
	Label auctionID, supplier, product, startPrice, acceptPrice, highestBid, endDate, bider;
	
	@FXML
	TextArea descriptionArea;

	public void setRecords(OngoingAuction newValue) {
		descriptionArea.setWrapText(true);
		picView.setImage(new Image("nophoto.jpg"));
		auctionID.setText(newValue.getAuctionID().getValue().toString()); 
		supplier.setText(newValue.getSupplier().getValue());
		product.setText(newValue.getProduct().getValue());
		startPrice.setText(newValue.getStartPrice().getValue().toString());		
		acceptPrice.setText(newValue.getAcceptPrice().getValue().toString());
		highestBid.setText(newValue.getHighestBid().getValue().toString());
		endDate.setText(newValue.getEndDate().getValue().toString());
		bider.setText(newValue.getBider().getValue());
		descriptionArea.setText(newValue.getProductDescription().getValue());
	}

}
