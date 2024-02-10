package assignment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPClose;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPGrowth;
import ca.pfv.spmf.algorithms.frequentpatterns.fpgrowth.AlgoFPMax;
import ca.pfv.spmf.input.transaction_database_list_integers.TransactionDatabase;

//program saves the item sets for given min sup values for given data retail data sets 
public class Exercise_2 {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String[] input_files = {"F:\\MDS\\Data Mining\\dataset\\retail1\\retail1.txt","F:\\MDS\\Data Mining\\dataset\\retail2.txt"};
		String output1 = null ;
		TransactionDatabase database = new TransactionDatabase();
		//minimum support values 
		double[] minsup = {0.005,0.01,0.02,0.03,0.05,0.07};
		//string to save output file in format : algotith+min sup+dataset
		String[] ret = {"ret1","ret2"};
		
		//for each data set
		for (int j =0 ; j <input_files.length;j++) {
			String input = input_files[j];
			try {
				database.loadFile(input);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//for each algorithm save the frequent items sets 
			//and print time taken for each algorithm
			for (int i =0;i<minsup.length;i++ ) {
				output1 = "fpg"+minsup[i]+ret[j]+".txt";
				AlgoFPGrowth fpgrowth = new AlgoFPGrowth();
				fpgrowth.runAlgorithm(input,output1, minsup[i]);
				fpgrowth.printStats();
				
				output1 = "fpclose"+minsup[i]+ret[j]+".txt";
				AlgoFPClose fpclose = new AlgoFPClose();
				fpclose.runAlgorithm(input,output1, minsup[i]);
				fpclose.printStats();
	
				output1 ="fpmax"+minsup[i]+ret[j]+".txt";
				AlgoFPMax algo = new AlgoFPMax();
				algo.runAlgorithm(input,output1, minsup[i]); 
				algo.printStats();
			}
		}
	}
}
