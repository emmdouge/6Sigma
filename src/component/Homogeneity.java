package component;

import java.util.ArrayList;

import reader.Data;
import umontreal.iro.lecuyer.probdist.ChiSquareDist;;

public class Homogeneity {

	public static void test(Data d) throws Exception {
		ArrayList<double[]> data = new ArrayList<double[]>();
		ArrayList<Double> var = new ArrayList<Double>();  
		ArrayList<Double> logPortion = new ArrayList<Double>();  
		ArrayList<Double> divPortion = new ArrayList<Double>();  
		int numPopulations = d.getPointsPerRow();
		int numPoints = d.getAllPoints().size()/numPopulations;
		for(int i = 0; i < numPopulations; i++) {
			data.add(new double[numPoints]);
		}
		System.out.println("num populations: "+data.size());
		System.out.println("points per population: "+numPoints);
		for(int i = 0; i < data.size(); i++) {
			for(int j = 0; j < numPoints; j++) {
				data.get(i)[j] = d.getAllPoints().get((i*data.size())+j);
			}
		}
		int dof = 0;
		double wAvgVar = 0;
		double wSumInVar = 0;
		double divSum = 0;
		int nm1 = data.size()-1;
		for(int i = 0; i < data.size(); i++) {
			var.add(getVariance(data.get(i)));
			logPortion.add((data.get(i).length-1)*Math.log(var.get(i)));
			divPortion.add(1/(data.get(i).length-1.0));
			dof += data.get(i).length;
			wAvgVar += var.get(i);
			wSumInVar += logPortion.get(i);
			divSum += divPortion.get(i);
		}
		dof -= data.size();
		wAvgVar /= dof;
		double testStatistic = dof*wAvgVar-wSumInVar;
		double correctionFactor = 1+(1/(3*(nm1)))*(divSum)-(1/dof);
		double correctedTestStat = testStatistic/correctionFactor;
		double p = ChiSquareDist.barF(nm1, 2, correctedTestStat);
		if(p < .05) {
			throw new Exception("NOT HOMOGENEOUS!");
		}
	}
	
    public static double getVariance(double[] data)
    {
    	double mean = 0;
		for(int i = 0; i < data.length; i++) {
			mean += data[i];
		}
		mean = mean/data.length;
        double variance = 0;
        for(double point :data) {
            variance += (point-mean)*(point-mean);
        }
        return variance/(data.length-1);
    }
}
