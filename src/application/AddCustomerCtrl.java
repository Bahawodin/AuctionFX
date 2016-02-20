package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddCustomerCtrl {
	
	RootCtrl rootCtrl;
	
	@FXML
	private TextField dateOfBirth, firstName, lastName, streetName, areaCode, area;
	@FXML
	private Label emptyFormLabel, digitLabel;
	
	@FXML
	private void addCustomer(){
		Connection conn = null;
		CallableStatement cstm = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/auctionsDb", "root", "root");
			
			if(dateOfBirth.getText().isEmpty() || firstName.getText().isEmpty() || lastName.getText().isEmpty() || streetName.getText().isEmpty()
					|| areaCode.getText().isEmpty() || area.getText().isEmpty()) {
				
				emptyFormLabel.setVisible(true);
			}
			else if (areaCode.getText().length()>5) {
				digitLabel.setVisible(true);
			}
			else {
				cstm = conn.prepareCall("{CALL RegisterCustomer (?, ?, ?, ? ,?, ?)}");
				cstm.setString(1, dateOfBirth.getText());
				cstm.setString(2, firstName.getText());
				cstm.setString(3, lastName.getText());
				cstm.setString(4, streetName.getText());
				cstm.setString(5, areaCode.getText());
				cstm.setString(6, area.getText());
				
				cstm.execute();
				rootCtrl.addCustomer();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(cstm != null)
					cstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param rootCtrl the rootCtrl to set
	 */
	public void setRootCtrl(RootCtrl rootCtrl) {
		this.rootCtrl = rootCtrl;
	}

}
