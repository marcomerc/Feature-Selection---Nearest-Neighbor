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
            for(int k = 0; k < Labels.size();k++){
              double p = 0.0;
              int loc = -1;

              // getting the features that we will use
              for(int j = 0; j < features.size();j++){
                // System.out.println(features.get(j));
                p = p + data[k][features.get(j)];
                // System.out.println(p);
              }
              double minD = 0.0;
              for(int i = 0; i <Labels.size(); i++){
              double distance = 0.0;
              //geting the disatance for each other point and comparing
              for(int w = 0; w < features.size(); w++){
                distance = distance + data[i][features.get(w)];
                // System.out.println(distance);
              }
                if(k != i){
                  if (i ==  1){
                    minD = Math.abs(distance - p);
                    loc = i;
                  }else if (Math.abs(distance - p) < minD ){
                     loc = i;
                     minD = Math.abs(distance - p);
                   }
                 }

              if(loc != -1){
                cross[k] = Labels.get(loc);
                // System.out.println(Labels.get(loc));
              }
            }
          }




            int count = 0;
            for(int i = 0; i < Labels.size();i++){
              if (Labels.get(i) ==  cross[i]){
                count += 1;
              }
            }
            System.out.println(Labels.size());
              System.out.println(count);
            return count/ Labels.size();
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
                  System.out.println(j);
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
          // System.out.println(previousPer);
          features.add(loc);
        }
        return previousPer;

     }


      public  static void main (String args[]){
      List<Double> Labels = new ArrayList<Double>();
      List<String> lines =  new ArrayList<String>();
      double mean  = 0;
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
              mean= Double.parseDouble( firstLine[j] ) +mean;
              features[i][j-2] = Double.parseDouble( firstLine[j] );
            }
        }
        // the above gets it into an array dont mess with it !
        mean = mean/ ((FeatureSize.length - 2) * lines.size());
        double stDiv = 0;
        // subtraacting mean and then squaring it
        for(int i = 0; i < Labels.size(); i++){
          for(int j = 2; j < sizeF; j++){
            stDiv = stDiv + Math.pow(features[i][j-2] - mean,2.0);
          }
        }
        //finishing getting the standrd deviation
        stDiv = stDiv / ((FeatureSize.length - 2) * lines.size());
        stDiv = Math.sqrt (stDiv);

        for(int i = 0; i < Labels.size(); i++){
          for(int j = 2; j < sizeF; j++){
            stDiv = stDiv + Math.pow(features[i][j-2] - mean,2.0);
          }
        }
        // z normalization
        for(int i = 0; i < Labels.size(); i++){
          for(int j = 2; j < sizeF; j++){
            features[i][j-2]= (features[i][j-2] - mean)/ stDiv;
          }
        }

        List<Integer> fa = new ArrayList<Integer>();
        // fa.add(4);
        // fa.add(8);
        double wow = NearestNeighbor(features, fa, Labels );
        // forward(features,sizeF-2,Labels);





        System.out.print(wow);
    }
  }
