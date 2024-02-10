package assignment;

import java.io.IOException;

import ca.pfv.spmf.algorithms.frequentpatterns.MSApriori.AlgoMSApriori;

public class Exercise_5 {

	public static void main(String[] args) throws IOException {
		String input = "F:\\MDS\\Data Mining\\dataset\\retail2.txt";
		String output = ".//output.txt";  // the path for saving the frequent itemsets found
		
		double beta=0.5;
		double LS=0.05;
		AlgoMSApriori algo = new AlgoMSApriori();
		algo.runAlgorithm(input, output, beta, LS);
		algo.printStats();
		
		output = ".//output1_test.txt";  // the path for saving the frequent itemsets found
	
		Exercise_3 algor = new Exercise_3();
		double si = 0.05;
		Integer[][] must = {{32,38,39,41}};
		Integer[][] cannot = {{39,48},{39,41},{38,39},{41,48},{32,39}};
		algor.runAlgorithm(input, output, beta, LS,si,must,cannot);
		algor.printStats();
		
		output = ".//output2.txt";  // the path for saving the frequent itemsets found
		
		Exercise_4 algori = new Exercise_4();
		algori.runAlgorithm(input, output, beta, LS,si);
		algori.printStats();
	}

}
