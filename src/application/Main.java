package application;
	
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Ajmal Bahawodin
 *
 */
public class Main extends Application {
	
	private ObservableList<OngoingAuction> auctionList;
	
	private Stage primaryStage;
	private Scene scene;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void init(){
		auctionList = FXCollections.observableArrayList();
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Nackademiska Auktionsfrämjandet");

		readInAuctionsFromDB();
		
		initRoot();
		
	}
	
	private void readInAuctionsFromDB() {
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/auctionsDb", "root", "root");
			stm = conn.createStatement();
			rs = stm.executeQuery("SELECT * FROM auctionsDb.ongoing_auctions;");
			
			while(rs.next()){
				auctionList.add(new OngoingAuction(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getDate(8).toLocalDate(), rs.getString(9)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stm.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void initRoot() {

		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("root.fxml"));
			BorderPane root = (BorderPane) loader.load();
			scene = new Scene(root, 1200, 700);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			RootCtrl controller = loader.getController();
			controller.setMain(this);
			controller.showOngoingAuctions();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the auctionList
	 */
	public ObservableList<OngoingAuction> getAuctionList() {
		return auctionList;
	}
}
















