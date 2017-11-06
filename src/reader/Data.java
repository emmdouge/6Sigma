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
	private double[] allPoints;
	private ArrayList<double[]> cols;
	private ArrayList<Integer> colOffsets;
	private ArrayList<String> yNames;
	private String type;
	private boolean useCols;
	
	public Data(String filename) throws IOException {
		this(filename, false);
	}
	
	public Data(String filename, boolean useCols) throws IOException {
		this.filename = filename;
		this.useCols = useCols;
	}
	
	public boolean getUseCols() {
		return this.useCols;
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
	
	public ArrayList<double[]> getCols() {
		return this.cols;
	}
	
	public void setCols(ArrayList<double[]> cols) {
		this.cols = cols;
	}
	
	public void setColOffsets(ArrayList<Integer> colOffsets) {
		this.colOffsets = colOffsets;
	}
	
	public ArrayList<Integer> getColOffsets() {
		return this.colOffsets;
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
	
	public double[] getAllPoints() {
		return this.allPoints;
	}
	
	public void setAllPoints(double[] allPoints) {
		this.allPoints = allPoints;
	}

	public void setYNames(ArrayList<String> yNames) {
		this.yNames = yNames;
	}
	
	public ArrayList<String> getYNames() {
		return this.yNames;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
