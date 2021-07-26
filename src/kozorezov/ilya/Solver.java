package kozorezov.ilya;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Solver {

    int[] order;
    Map map;

    public Solver(){
        order = sort(inputReader.getDistances());
        map = inputReader.getMap();

        map.printMap();
    }




    // Gets the list from the input and creates a list in which order to solve each color in
    public static int[] sort(double[] unsorted) {
        int n = 0;
        for (double v : unsorted) {
            if (v != -1) n++;
        }
        int[] sorted = new int[n];
        int list = 0;
        double[] sortedList = new double[n];
        for (double v : unsorted) {
            if (v != -1) sortedList[list++] = v;
        }
        for (int i = 0; i < sortedList.length; i++) {
            for(int j = 0; j < sortedList.length -i -1; j++){
                if(sortedList[j] > sortedList[j+1]){
                    double temp = sortedList[j];
                    sortedList[j] = sortedList[j+1];
                    sortedList[j+1] = temp;
                }
            }
        }
        LinkedList<Integer> used = new LinkedList<>();
        for(int i = 0;i < sortedList.length;i++){
            int j = 0;
            while((unsorted[j] != sortedList[i]) || used.contains(j)) j++;
            sorted[i] = j;
            used.add(j);

        }
        return sorted;
    }
}
