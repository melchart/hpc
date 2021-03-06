package at.fhtw.hpc.exercise2;

import at.fhtw.hpc.util.ExecutionStatisticHelper;
import at.fhtw.hpc.util.TimeLogger;

import java.util.Random;

/**
 * Exercise 2
 */
public class ScanComparer {


	public static ExecutionStatisticHelper executionStatisticHelper = new ExecutionStatisticHelper();

	public static void main(String args[]) {
		// fill array with random numbers
		Random random = new Random();

		int size = (int) Math.pow(2, 24);
		System.out.println("Perform scan with array size: " + size);

		int[] input = new int[size];
		for (int i = 0; i < input.length; i++) {
			//input[i] = (int) random.nextInt(9999);
			input[i] = 1;
		}

		int serialOutput[] = SeriellScan.performScan(input);

		int temp[] = WorkEfficientParallelScan.performScan(input);

		ScanComparer.executionStatisticHelper.clear();
		TimeLogger logger = new TimeLogger();
		logger.start();
		int parallelOutput[] = WorkEfficientParallelScan.performScan(input);
		logger.end("parallel scan");
		ScanComparer.executionStatisticHelper.printSummary();

		// compare results
		for (int i = 0; i < serialOutput.length; i++) {
			if (parallelOutput[i] != serialOutput[i]) {
				System.out.println("Scan was wrong at index: " + i + ". (" + serialOutput[i] + " vs. " + parallelOutput[i] + ")");
				return;
			}
		}
		System.out.println("Scan was successful!");
	}
}
