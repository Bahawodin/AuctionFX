package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddSupplierCtrl {
	
	RootCtrl rootCtrl;
	
	@FXML
	private TextField compNameTF, orgnr, rateTF, firstnameTF, lastnameTF, emailTF, phoneTF;
	@FXML
	private Label emptyFormLabel, rateNFELabel;
	
	@FXML
	private void addSupplier(){
		Connection conn = null;
		CallableStatement cstm = null;
		
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/auctionsDb", "root", "root");
			
			if (orgnr.getText().isEmpty() || compNameTF.getText().isEmpty() || rateTF.getText().isEmpty() || firstnameTF.getText().isEmpty()
					|| lastnameTF.getText().isEmpty() || emailTF.getText().isEmpty() || phoneTF.getText().isEmpty()) {
				
				emptyFormLabel.setVisible(true);
			}
			else if (isNotNumeric(rateTF.getText())) {
				rateNFELabel.setVisible(true);
			}
			else {
				cstm = conn.prepareCall("{CALL RegisterSupplier (?, ?, ?, ? ,?, ?, ?)}");
				cstm.setString(1, orgnr.getText());
				cstm.setString(2, compNameTF.getText());
				cstm.setFloat(3, Float.valueOf(rateTF.getText()));
				cstm.setString(4, firstnameTF.getText());
				cstm.setString(5, lastnameTF.getText());
				cstm.setString(6, emailTF.getText());
				cstm.setString(7, phoneTF.getText());
				
				cstm.execute();
				rootCtrl.addSupplier();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (cstm != null)
					cstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private boolean isNotNumeric(String str){
	    try {
	      double d = Double.parseDouble(str);
	    } catch(NumberFormatException nfe) {
	      return true;
	    }
	    return false;
	  }

	/**
	 * @param rootCtrl the rootCtrl to set
	 */
	public void setRootCtrl(RootCtrl rootCtrl) {
		this.rootCtrl = rootCtrl;
	}

}
