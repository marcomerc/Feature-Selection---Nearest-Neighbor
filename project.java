// Java program to demonstrate positional access
// operations on List interface
import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;//imports vector utility

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
        List<Double> percent =  new ArrayList<Double>();
        List<Integer> ansLoc =  new ArrayList<Integer>();

  double minA = 0;
        int numI = -1;
        for(int i = 0; i < featuresSize; i++){
          int loc =0;
          double previousPer = 0.0;

          for(int j = 0; j < featuresSize; j++){
                 if ( !features.contains(j)){
                     features.add(j);
                     numI = numI+1;
                     double percentageRight = NearestNeighbor(data,features,Labels);

                     System.out.print("Using feature(s) {");
                     for(int m = 0; m < features.size();m++){
                       System.out.print(features.get(m));
                       System.out.print(" ");

                     }
                     System.out.print("} accuracy is ");
                     System.out.print(percentageRight);
                     System.out.print("%\n");
                     if (i == 0  && j == 0){
                       minA = percentageRight;
                       ansLoc.clear();
                       ansLoc.addAll(features);
                     }else if (minA < percentageRight){
                       ansLoc.clear();
                       ansLoc.addAll(features);
                       minA = percentageRight;

                     }
                     if (j == 0){

                            loc = j;
                            previousPer = percentageRight;
                     }
                    if(previousPer < percentageRight){
                            loc = j;
                            previousPer = percentageRight;
                    }

                    features.remove(numI);
                    numI = numI-1;
                  }
          }

        if(!percent.isEmpty() && previousPer < percent.get(percent.size()-1)){
          percent.add(previousPer);
          features.add(loc);
          System.out.print("(Warning, Accuracy has decreased! Continuing search in case of local maxima) \nFeature set { ");
          for(int m = 0; m + 1 < features.size();m++){
            System.out.print(features.get(m) + 1);
            System.out.print(" ");
          }
          System.out.print("} was best, accuracy is  ");
          System.out.print(percent.get(percent.size()-2));
          System.out.print("%\n");
          numI = numI+1;

        } else{
                percent.add(previousPer);
                features.add(loc);
                System.out.print("Feature set {");
                for(int m = 0; m < features.size();m++){
                  System.out.print(features.get(m) + 1);
                  System.out.print(" ");
                }
                System.out.print("} was the best, accuracy is ");
                System.out.print(percent.get(percent.size()-1));
                System.out.print("%\n");
                numI = numI+1;
        }


}
    System.out.print("the features that give the best accuracy are ");
    for(int i =0; i < ansLoc.size(); i++){
      System.out.print(ansLoc.get(i));
      System.out.print(" ");

    }

        // return percent.get(percent.size()-2);
        return minA;
     }

     public static double ThirdAlgorithm(Double[][] data,int featuresSize,List<Double> Labels ) {
        List<Integer> features =  new ArrayList<Integer>();
        List<Double> percent =  new ArrayList<Double>();
        List<Integer> ansLoc =  new ArrayList<Integer>();

        double minA = 0;
        int numI = -1;
        double first = 0.0;
        double second = 0.0;
        int countHills = 0;


        for(int i = 0; i < featuresSize; i++){
          int loc =0;
          double previousPer = 0.0;


          for(int j = 0; j < featuresSize; j++){
                 if ( !features.contains(j)){
                     features.add(j);
                     numI = numI+1;
                     double percentageRight = NearestNeighbor(data,features,Labels);

                     System.out.print("Using feature(s) {");
                     for(int m = 0; m < features.size();m++){
                       System.out.print(features.get(m));
                       System.out.print(" ");

                     }
                     System.out.print("} accuracy is ");
                     System.out.print(percentageRight);
                     System.out.print("%\n");
                     if (i == 0  && j == 0){
                       minA = percentageRight;
                       ansLoc.clear();
                       ansLoc.addAll(features);
                     }else if (minA < percentageRight){
                       ansLoc.clear();
                       ansLoc.addAll(features);
                       minA = percentageRight;

                     }
                     if (j == 0){

                            loc = j;
                            previousPer = percentageRight;
                     }
                    if(previousPer < percentageRight){
                            loc = j;
                            previousPer = percentageRight;
                    }

                    features.remove(numI);
                    numI = numI-1;
                  }
          }
          if (i == 0){
            first = previousPer;
          }else if(i == 1){
            second = previousPer;
          }
          if(first < second && second > previousPer && i >= 2){
            countHills = countHills + 1;
          }else{
            first = second;
            second = previousPer;
          }




        if(!percent.isEmpty() && previousPer < percent.get(percent.size()-1)){
          percent.add(previousPer);
          features.add(loc);
          System.out.print("(Warning, Accuracy has decreased! Continuing search in case of local maxima) \nFeature set { ");
          for(int m = 0; m + 1 < features.size();m++){
            System.out.print(features.get(m) + 1);
            System.out.print(" ");
          }
          System.out.print("} was best, accuracy is  ");
          System.out.print(percent.get(percent.size()-2));
          System.out.print("%\n");
          numI = numI+1;

        } else{
                percent.add(previousPer);
                features.add(loc);
                System.out.print("Feature set {");
                for(int m = 0; m < features.size();m++){
                  System.out.print(features.get(m) + 1);
                  System.out.print(" ");
                }
                System.out.print("} was the best, accuracy is ");
                System.out.print(percent.get(percent.size()-1));
                System.out.print("%\n");
                numI = numI+1;
        }
        if(countHills == 3){
          System.out.println(countHills);
          break;
        }

      }
    System.out.print("the features that give the best accuracy are ");
    for(int i =0; i < ansLoc.size(); i++){
      System.out.print(ansLoc.get(i));
      System.out.print(" ");

    }

        // return percent.get(percent.size()-2);
        return minA;
}




        public static double Backward(Double[][] data,int featuresSize,List<Double> Labels ) {
          double maxAccuracy =  0;
          List<Integer> ansFeatures =  new ArrayList<Integer>();
          List<Integer> features =  new ArrayList<Integer>();
          for(int i  = 0 ; i < featuresSize; i++){
              features.add(i);
          }
          for(int i  = 0 ; i < featuresSize; i++){
              // System.out.println(features.get(i));
          }

          for(int i  = 0 ; i < Labels.size(); i++){
            List<Integer> tempF =  new ArrayList<Integer>(features);
            double[] featLoc = new double[features.size()];
            double[] cross = new double[features.size()];
            // System.out.println(features.size());
            // System.out.println(tempF.size());
            int loc = -1;
            System.out.println("features");
            int sizeTemp = features.size();
            for(int k  = 0 ; k < sizeTemp; k++){


              features.remove(tempF.get(k));
              cross[k]  = NearestNeighbor(data,features,Labels);
              if(i == 0){
                maxAccuracy = cross[k];
                for(int featuresChoosen = 0; featuresChoosen < features.size();featuresChoosen++  ){
                  ansFeatures.add(features.get(featuresChoosen)+1);
                }

              }else if( maxAccuracy < cross[k]){
                ansFeatures.clear();
                maxAccuracy = cross[k];
                for(int featuresChoosen = 0; featuresChoosen < features.size();featuresChoosen++  ){
                  ansFeatures.add(features.get(featuresChoosen)+1);

                }


              }
              features.add(k,tempF.get(k));
              System.out.print(cross[k]);
              System.out.print(" feature ");
              System.out.println(tempF.get(k) + 1);
            }
            double max = -1;


            for(int j  = 0 ; j < tempF.size(); j++){
              if(max == -1){
                  max = cross[j];
                  loc =j;
               }else if (max < cross[j]){
                  max = cross[j];
                  loc = j;
              }
            }


            System.out.print("remove feature ");
            System.out.println(features.get(loc)+1);
             features.remove(loc);
              tempF.remove(loc);
              if(features.size() == 0){
                break;
              }
          }
            System.out.print("features choosen ");
          for(int featuresChoosen = 0; featuresChoosen < ansFeatures.size();featuresChoosen++  ){
            System.out.print(ansFeatures.get(featuresChoosen));
            System.out.print(" ");
          }
            System.out.print("\nAccuracy ");
            System.out.print(maxAccuracy);
          return maxAccuracy;
      }
      public  static void main (String args[]){
      List<Double> Labels = new ArrayList<Double>();
      List<String> lines =  new ArrayList<String>();
      int sizeF = 0;
      // Scanner scan = new Scanner(System.in);
      // String fileName = scan.nextLine();
      // String fileName = "CS170Smalltestdata__22.txt";
      String fileName = "CS170BIGtestdata__37.txt";
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
                mean = mean  + features[j][i];
            }
            mean = mean / Labels.size();
            // System.out.println("mean");
            // System.out.println(mean);
            double stDiv = 0.0;
            for (int j = 0; j < Labels.size(); j++){
                stDiv = stDiv + Math.pow(features[j][i] - mean,2);
            }
            stDiv = Math.sqrt(stDiv/ Labels.size());
            for (int j = 0; j < Labels.size(); j++){
                features[j][i] = (features[j][i] - mean)/stDiv;
            }
        }
        System.out.println(features[0][0]);
        System.out.println(features[1][1]);

        List<Integer> fa = new ArrayList<Integer>();
        fa.add(3);
        fa.add(7);
        // double wow  = NearestNeighbor(features, fa, Labels );
        // double wow = forward(features,sizeF,Labels);
        double wow = ThirdAlgorithm(features,sizeF,Labels);

        // double wow = Backward(features,sizeF,Labels);
        System.out.print(" the accuracy is ");
        System.out.println(wow);
    }
  }
