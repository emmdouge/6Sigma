package view;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SigmaSix extends Application {

    private Desktop desktop = Desktop.getDesktop();
    private Stage primaryStage;
    private static BorderPane mainLayout;

    /**
     * fxml tag allows this to be seen by scene builder to bind it
     * to choicebox within scene builder
     */
    @FXML
    private ChoiceBox<String> lineTypes;

    @FXML
    private Button fileButton;

    @Override
    public void start(final Stage stage) throws IOException {
    	this.primaryStage = stage;
    	this.primaryStage.setTitle("Sigma Six");

    	showMainView();
    	showSingleLineForm();
    }

    /**
     * JavaFX already allocates memory for instance vars tagged with @FXML
     * automatically calls this method(?)
     */
    @FXML
    private void initialize() {
    	ArrayList<String> types = new ArrayList<String>();
    	types.add("Single-Line");
    	types.add("Multi-Line");
    	ObservableList<String> list = FXCollections.observableArrayList(types);
    	lineTypes.setItems(list);
    	lineTypes.setValue("Single-Line");

        lineTypes.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number preChangeIndex, Number postChangeIndex) {
	              try {
					switchForm(postChangeIndex);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
          });


        final FileChooser fileChooser = new FileChooser();

        fileButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(primaryStage);
                    if (file != null) {
                        openFile(file);
                    }
                }
            });
    }

    private void switchForm(Number index) throws IOException {
    	System.out.println(lineTypes.getValue());
    	if(index.intValue() == 0){
    		showSingleLineForm();
    	} else {
    		showMultiLineForm();;
    	}
    }

    private void showSingleLineForm() throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(SigmaSix.class.getResource("/view/SingleLineForm.fxml"));
    	BorderPane singleLineForm = loader.load();
    	mainLayout.setCenter(singleLineForm);
	}

    private void showMultiLineForm() throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(SigmaSix.class.getResource("/view/MultiLineForm.fxml"));
    	BorderPane singleLineForm = loader.load();
    	mainLayout.setCenter(singleLineForm);
	}

	private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(SigmaSix.class.getResource("/view/MainView.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private void openFile(File file) {
//        try {
            System.out.println(file.getAbsolutePath());
//        }
//        catch (IOException ex) {
//            Logger.getLogger(
//                FileChooser.class.getName()).log(
//                    Level.SEVERE, null, ex
//                );
//        }
    }
}