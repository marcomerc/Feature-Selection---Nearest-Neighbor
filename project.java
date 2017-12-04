// Java program to demonstrate positional access
// operations on List interface
import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

public class project{

    public static double NearestNeighbor(Double[][] data,List<Integer> features,List<Double> Labels ) {
            /// redo the distance formula for the NearestNeighbor
            double[] cross = new double[Labels.size()];
            // for all labesl
            for(int k = 0; k < Labels.size();k++){
                int loc = 0;
                double minD = -1;
                for(int i = 0; i <Labels.size(); i++){
                  double distance = 0.0;
                  if(k != i){
                      for(int w = 0; w < features.size(); w++){
                          distance = distance + Math.pow(data[k][features.get(w)] - data[i][features.get(w)],2);
                      }
                      distance = Math.sqrt(distance);
                      if(minD == -1){
                        minD = distance;
                        loc = i;
                      }else if(distance < minD ){
                        minD = distance;
                        loc = i;
                      }
                  }
                }
                cross[k] = Labels.get(loc);
          }
            int count = 0;
            for(int i = 0; i < Labels.size();i++){
              if (Labels.get(i) ==  cross[i]){
                count = count+ 1;
              }
            }
            double coun = count;
            coun = coun/ Labels.size();
            return coun;
     }

     public static double forward(Double[][] data,int featuresSize,List<Double> Labels ) {
        List<Integer> features =  new ArrayList<Integer>();
        List<Integer> percent =  new ArrayList<Integer>();
        int loc =0;
        double previousPer = 0.0;
        for(int i = 0; i < featuresSize; i++){
          for(int j = 0; j < featuresSize; j++){
                 if ( !features.contains(j)){
                       features.add(j);
                  // System.out.println(j);
                double percentageRight = NearestNeighbor(data,features,Labels);
                      if (j == 0){
                        loc = j;
                        previousPer = percentageRight;
                }
                else if(previousPer < percentageRight){
                        loc = j;
                        previousPer = percentageRight;
                        System.out.println(previousPer);

                      }
                      features.remove(j);
          }
        }
          features.add(loc);
        }
        return previousPer;

     }


      public  static void main (String args[]){
      List<Double> Labels = new ArrayList<Double>();
      List<String> lines =  new ArrayList<String>();
      int sizeF = 0;
      // Scanner scan = new Scanner(System.in);
      // String fileName = scan.nextLine();
      String fileName = "CS170Smalltestdata__22.txt";
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
        // getting the features in to a n by
        for(int i = 0; i < lines.size(); i++){
            String temp = lines.get(i);
            String[] firstLine =   temp.split("\\s+");
            sizeF = firstLine.length;
            for(int j = 2; j < firstLine.length; j++){
              features[i][j-2] = Double.parseDouble( firstLine[j] );
            }
        }
        sizeF = FeatureSize.length - 2;
        for(int i  = 0;  i< sizeF; i++){
            double mean = 0.0;
            // calculates the mean
            for (int j = 0; j < Labels.size(); j++){
                // System.out.println(features[j][i]);
                mean = mean  + features[j][i];
            }
            mean = mean / Labels.size();
            System.out.println("mean");
            System.out.println(mean);
            double stDiv = 0.0;
            for (int j = 0; j < Labels.size(); j++){
                stDiv = stDiv + Math.pow(features[j][i] - mean,2);
            }
            stDiv = Math.sqrt(stDiv/ Labels.size());
            System.out.println("stDiv");
            System.out.println(stDiv);

            for (int j = 0; j < Labels.size(); j++){
                features[j][i] = (features[j][i] - mean)/stDiv;
            }



        }
        System.out.println(features[0][0]);
        System.out.println(features[1][1]);



        List<Integer> fa = new ArrayList<Integer>();
        fa.add(3);
        fa.add(7);
        double wow = NearestNeighbor(features, fa, Labels );
        forward(features,sizeF,Labels);





        System.out.print(wow);
    }
  }
