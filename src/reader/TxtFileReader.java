package reader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Reads in line separated real numbers from a given txt file
 * @author Emmanuel Douge
 *
 */
public class TxtFileReader {
	/**
	 * Reads in a file and outputs the data in it
	 * @param filename the file containing the data
	 * @return an array of the line separated data
	 * @throws IOException if file not found
	 */
	public static Data readFile(String filename) throws IOException
	{
		filename = "data/"+filename+".csv";
		
		Data d = new Data(filename);
		
		readNumRows(d);
		readPointsPerRow(d);
		readData(d);
		readProps(d);

		return d;
	}

	private static void readData(Data d) throws FileNotFoundException, IOException {

		FileReader pointR =  new FileReader(d.getFilename());
		BufferedReader pointBR = new BufferedReader(pointR);
		String currentLine = null;
		double[][] array = new double[d.getNumRows()][d.getPointsPerRow()];		
		currentLine = pointBR.readLine();
		ArrayList<Double> points  = new ArrayList<Double>();
		while ((currentLine = pointBR.readLine()) != null) {
	        String[] data = currentLine.split(",");
			for(int i = 1; i < data.length; i++) {
				points.add(Double.parseDouble(data[i]));
			}
		}
		
		d.setAllPoints(points);
		
		System.out.println(points.size());
		pointBR.close();
				
		for(int i = 0; i < d.getNumRows(); i++) {
			for(int j = 0; j < d.getPointsPerRow(); j++) {
				System.out.print("("+i+":"+j+":"+((i*d.getPointsPerRow())+j)+")");
				array[i][j] = points.get((i*d.getPointsPerRow())+j); 
				System.out.print(array[i][j]+", ");
			}
			System.out.println();
		}
		
		d.setData(array);
	}

	private static int readPointsPerRow(Data d) throws FileNotFoundException, IOException {
		FileReader pointR =  new FileReader(d.getFilename());
		BufferedReader pointBR = new BufferedReader(pointR);
		String currentLine = null;

		//num of points per row
		int numPoints = 0;
		if ((currentLine = pointBR.readLine()) != null) {
			for(int i = 0; i < currentLine.length(); i++) {
				if(currentLine.charAt(i) == ',') {
					numPoints++;
				}
			}
		}
		System.out.println("numPoints: "+numPoints);
		d.setPointsPerRow(numPoints);
		pointBR.close();
		return numPoints;
	}

	private static void readNumRows(Data d) throws FileNotFoundException, IOException {
		FileReader rowR =  new FileReader(d.getFilename());
		BufferedReader rowBR = new BufferedReader(rowR);

		rowBR.readLine();
		int rows = 0;
		while ((rowBR.readLine()) != null) {
			rows++;
		}
		System.out.println("rows: "+rows);
		d.setNumRows(rows);
		rowBR.close();
	}

	public static void readProps(Data d) throws IOException {
		FileReader pointR =  new FileReader(d.getFilename());
		BufferedReader pointBR = new BufferedReader(pointR);
		String firstLine = null;	
		firstLine = pointBR.readLine();
        String[] props = firstLine.split(",");
		d.setRowName(props[0]);
		String[] colNames = new String[d.getPointsPerRow()];
		for(int i = 0; i < d.getPointsPerRow(); i++) {
			colNames[i] = props[i+1];
		}
		d.setColNames(colNames);
		pointBR.close();
	}
	
}
