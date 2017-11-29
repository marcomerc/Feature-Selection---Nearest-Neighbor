// Java program to demonstrate positional access
// operations on List interface
import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.lang.Math;

public class project{

    public double NearestNeighbor(Double[][] data,int []features) {

      double foo = 0.0;


      return foo;
         // Do something here
     }


      public static void main (String args[]){
      List<Double> Labels = new ArrayList<Double>();
      List<String> lines =  new ArrayList<String>();
      double mean  = 0;
      int sizeF = 0;
      Scanner scan = new Scanner(System.in);
      String fileName = scan.nextLine();
      try {
      			File file = new File(fileName);
      			FileReader fileReader = new FileReader(file);
      			BufferedReader bufferedReader = new BufferedReader(fileReader);
      			StringBuffer stringBuffer = new StringBuffer();
      			String line;
      			while ((line = bufferedReader.readLine()) != null) {
              String[] data = line.split("\\s+");
              lines.add(line);
              Labels.add(Double.parseDouble(data[1]));
      	    }
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        String forSize = lines.get(0);
        String[] FeatureSize =   forSize.split("\\s+");
        Double[][] features = new Double[lines.size()][FeatureSize.length - 2];

        for(int i = 0; i < lines.size(); i++){
            String temp = lines.get(i);
            String[] firstLine =   temp.split("\\s+");
            sizeF = firstLine.length;
            for(int j = 2; j < firstLine.length; j++){
              mean= Double.parseDouble( firstLine[j] ) +mean;
              features[i][j-2] = Double.parseDouble( firstLine[j] );
            }
        }
        mean = mean/ ((FeatureSize.length - 2) * lines.size());
        double stDiv = 0;
        for(int i = 0; i < Labels.size(); i++){
          for(int j = 2; j < sizeF; j++){
            stDiv = stDiv + Math.pow(features[i][j-2] - mean,2.0);
          }
        }
        stDiv = stDiv / ((FeatureSize.length - 2) * lines.size());
        stDiv = Math.sqrt (stDiv);
        for(int i = 0; i < Labels.size(); i++){
          for(int j = 2; j < sizeF; j++){
            stDiv = stDiv + Math.pow(features[i][j-2] - mean,2.0);
          }
        }
        for(int i = 0; i < Labels.size(); i++){
          for(int j = 2; j < sizeF; j++){
            features[i][j-2]= (features[i][j-2] - mean)/ stDiv;
          }
        }

        // int[] f =  new int[
        // NearestNeighbor(features, )






        System.out.print(mean);
    }
  }
