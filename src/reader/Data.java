package reader;
import java.io.IOException;
import java.util.ArrayList;

import reader.TxtFileReader;

public class Data {
	
	private String filename;
	private double[][] data;
	private String rowName;
	private String[] colNames;
	private int numRows;
	private int pointsPerRow;
	private ArrayList<Double> allPoints;
	
	public Data(String filename) throws IOException {
		this.filename = filename;
	}
	
	public String getFilename() {
		return this.filename;
	}
	
	public void setData(double[][] data) {
		this.data = data;
	}
	
	public double[][] getData() {
		return this.data;
	}
	
	protected void setRowName(String name) {
		this.rowName = name;
	}
	
	public String getRowName() {
		return this.rowName;
	}
	
	protected void setColNames(String[] names) {
		this.colNames = names;
	}
	
	public String[] getColNames() {
		return this.colNames;
	}

	public void setNumRows(int rows) {
		this.numRows = rows;
	}
	
	public int getNumRows() {
		return this.numRows;
	}

	public void setPointsPerRow(int pointsPerRow) {
		this.pointsPerRow = pointsPerRow;
	}
	
	public int getPointsPerRow() {
		return this.pointsPerRow;
	}
	
	public ArrayList<Double> getAllPoints() {
		return this.allPoints;
	}
	
	public void setAllPoints(ArrayList<Double> points) {
		this.allPoints = points;
	}
}
