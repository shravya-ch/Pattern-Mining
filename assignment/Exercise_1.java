package assignment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import ca.pfv.spmf.algorithms.frequentpatterns.apriori.AlgoApriori;
import ca.pfv.spmf.algorithms.frequentpatterns.eclat.AlgoEclat;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;
import ca.pfv.spmf.patterns.itemset_array_integers_with_count.Itemsets;

//program prints the time taken in ms for each file by 3 algorithms 
public class Exercise_1 {

	public static void main(String[] args) throws IOException {
		
		String output1 = null ;
		//each string contains path of retail data set files
		String[] input_files = {"F:\\MDS\\Data Mining\\dataset\\retail1\\retail1.txt","F:\\MDS\\Data Mining\\dataset\\retail2.txt"};
		TransactionDatabase database = new TransactionDatabase();
		//minimum support values 
		double[] minsup = {0.005,0.01,0.02,0.03,0.05,0.07};
		
		long startTime;
		long endTime;
		double duration ;
		//array to save time taken by Apriori for each file
		double[] ap = new double[minsup.length];
		//array to save time taken by FP growth for each file
		double[] fpg = new double[minsup.length];
		//array to save time taken by Eclat for each file
		double[] ec = new double[minsup.length];
		//for each input file 
		for (int j =0 ; j <input_files.length;j++) {
			String input = input_files[j];
			try {
				database.loadFile(input);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//for each minimum support value 
			for (int i =0;i<minsup.length;i++ ) {
				
			AlgoApriori algo = new AlgoApriori();
			startTime = System.nanoTime();
			algo.runAlgorithm(minsup[i],input, output1);
			endTime = System.nanoTime();
			duration = (endTime - startTime)/1000000.0;
			//append time for apriori
			ap[i] = duration;
			
			AlgoFPGrowth fpgrowth = new AlgoFPGrowth();
			startTime = System.nanoTime();
			Itemsets patterns1 = fpgrowth.runAlgorithm(input, null, minsup[i]);
			endTime = System.nanoTime();
			duration = (endTime - startTime)/1000000.0;
			//append time for fpgrowth
			fpg[i]= duration;
			
			AlgoEclat eclat = new AlgoEclat();
			startTime = System.nanoTime();
			eclat.runAlgorithm(null, database, minsup[i], true);
			endTime = System.nanoTime();
			duration = (endTime - startTime)/1000000.0;
			//append time for eclat
			ec[i]= duration;
			
			}
		String[] ret = {"ret1","ret2"};
		//Printing the time taken for each algorithm as an array for each data set
		System.out.printf("ap_%s = %s" ,ret[j],Arrays.toString(ap));
		System.out.printf("fpg_%s = %s" ,ret[j],Arrays.toString(fpg));
		System.out.printf("eclat_%s = %s" ,ret[j],Arrays.toString(ec));
		}
		
	}
}
