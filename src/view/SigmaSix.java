package view;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import component.ChartFactory;
import component.Range;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import line.MultiLine;
import line.SingleLine;
import reader.Data;
import reader.TxtFileReader;

public class SigmaSix extends Application {

    private Desktop desktop = Desktop.getDesktop();
    private Stage primaryStage;
    private static BorderPane mainLayout;
    private static Data d;

    /**
     * fxml tag allows this to be seen by scene builder to bind it
     * to choicebox within scene builder
     */
    @FXML
    private ChoiceBox<String> lineTypes;
    @FXML
    private ChoiceBox<String> chartTypes;
    @FXML
    private TextField sampleSize;
    private static int sampleSizeInt;

    @FXML
    private TextField rowsPerSample;
    private static int rowsPerSampleInt;

    @FXML
    private Button fileButton;

    @FXML
    private Label filename;

    @Override
    public void start(final Stage stage) throws IOException {
    	this.primaryStage = stage;
    	this.primaryStage.setTitle("Sigma Six");

    	showMainView();
    	showSingleLineForm();
    }

    /**
     * called on the form loaded which may not contain all the fxml vars,
     * hence null checks
     */
    @FXML
    private void initialize() {
    	ArrayList<String> types = new ArrayList<String>();
    	ObservableList<String> list = FXCollections.observableArrayList(types);
    	if(lineTypes != null) {
	    	types.add("INVALID");
	    	types.add("Single-Line");
	    	types.add("Multi-Line");
	    	list = FXCollections.observableArrayList(types);
	    	lineTypes.setItems(list);
	    	lineTypes.setValue("INVALID");

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
    	}

    	if(chartTypes != null) {
	    	types = new ArrayList<String>();
	    	types.add("X-Bar");
	    	types.add("Range");
	    	types.add("Moving Mean");
	    	types.add("Moving Range");
	    	types.add("CUSUM");
	    	list = FXCollections.observableArrayList(types);
	    	chartTypes.setItems(list);
	    	chartTypes.setValue("X-Bar");
    	}


        final FileChooser fileChooser = new FileChooser();

        if(fileButton != null) {
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
        //if sampleSize is on loaded form
        if(sampleSize != null) {
        	numbersOnly(sampleSize);
        	sampleSize.textProperty().addListener((observable, oldValue, newValue) -> {
        	    System.out.println("textfield changed from " + oldValue + " to " + newValue);
        	    sampleSizeInt = Integer.parseInt(newValue);
        	});
        }
        if(rowsPerSample != null) {
        	numbersOnly(rowsPerSample);
        	rowsPerSample.textProperty().addListener((observable, oldValue, newValue) -> {
        	    System.out.println("textfield changed from " + oldValue + " to " + newValue);
        	    rowsPerSampleInt = Integer.parseInt(newValue);
        	});
        }
    }

    private void numbersOnly(TextField field) {
    	field.textProperty().addListener(new ChangeListener<String>() {
    	    @Override
    	    public void changed(ObservableValue<? extends String> observable, String oldValue,
    	        String newValue) {
    	        if (!newValue.matches("\\d*")) {
    	            field.setText(newValue.replaceAll("[^\\d]", ""));
    	        }
    	    }
    	});
    }

    private void switchForm(Number index) throws IOException {
    	System.out.println(lineTypes.getValue());
    	if(index.intValue() == LINETYPE.NA.ordinal() || d == null) {
    		lineTypes.getSelectionModel().select(LINETYPE.NA.ordinal());
    		mainLayout.setCenter(null);
    	}
    	else {
	    	if(d != null) {
	    		int numCols = d.getCols().size();
	    		System.out.println(numCols);
	    		if (numCols > 1) {
			    	if(index.intValue() == LINETYPE.SINGLE_LINE.ordinal()) {
			    		lineTypes.getSelectionModel().select(LINETYPE.SINGLE_LINE.ordinal());
	    				d.useCols(false);
		    			showSingleLineForm();
			    	} else if(index.intValue() == LINETYPE.MULTI_LINE.ordinal())  {
			    		lineTypes.getSelectionModel().select(LINETYPE.MULTI_LINE.ordinal());
	    				d.useCols(true);
			    		showMultiLineForm();
			    	}
	    		}
	    		else if (numCols == 1) {
	    			lineTypes.getSelectionModel().select(LINETYPE.SINGLE_LINE.ordinal());
    				d.useCols(false);
	    			showMiniSingleLineForm();
	    		}
	    	}
    	}
    }

    private void showMiniSingleLineForm() throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(SigmaSix.class.getResource("/view/MiniSingleLineForm.fxml"));
    	BorderPane miniSingleLineForm = loader.load();
    	mainLayout.setCenter(miniSingleLineForm);
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
    	BorderPane multiLineForm = loader.load();
    	mainLayout.setCenter(multiLineForm);
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
        try {
    		String path = file.getAbsolutePath();
            System.out.println(path);
            d = TxtFileReader.readFile(path);
            path = path.split("\\\\")[path.split("\\\\").length-1];
            filename.setText(path);
            switchForm(LINETYPE.SINGLE_LINE.ordinal());
        }
        catch (IOException ex) {
            Logger.getLogger(
                FileChooser.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }

    @FXML
    private void previewClicked() throws Exception {
    	String lineType = lineTypes.getValue();
    	String chartType = chartTypes.getValue();
    	System.out.println(lineType+" "+chartType);
    	if(d != null && !lineType.equals("INVALID")) {
    		if(d.getCols().size() == 1) {
    			new SingleLine(sampleSizeInt, sampleSizeInt, ChartFactory.build(1, chartType, d));
    		} else {
    			if (lineType.equals("Single-Line") || lineType.equals("CUSUM")) {
    				new SingleLine(sampleSizeInt, rowsPerSampleInt, ChartFactory.build(1, chartType, d));
    			} else if (lineType.equals("Multi-Line")) {
    				new MultiLine(sampleSizeInt, ChartFactory.build(rowsPerSampleInt, chartType, d));
    			}
    		}
    	}
    	System.out.println("CLICKED!");
    }
}

enum LINETYPE {
	NA,
	SINGLE_LINE,
	MULTI_LINE
}