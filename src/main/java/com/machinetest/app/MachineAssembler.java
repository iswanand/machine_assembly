package com.machinetest.app;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.machinetest.app.bean.AssemblyBean;

public class MachineAssembler {
	private ExecutorService executorService = null;
	private static final int NUMBER_OF_WORKERS = 3;
	private CountDownLatch latch;

	public MachineAssembler() {
		executorService = Executors.newFixedThreadPool(NUMBER_OF_WORKERS);
		latch = new CountDownLatch(NUMBER_OF_WORKERS);
	}

	/**
	 * Return number of seconds to assemble the product
	 * 
	 * @param machines
	 * @param bolts
	 * @param secondsToAssemble
	 * @return
	 * @throws InterruptedException
	 */
	public int getTimeToAssemble(int machines, int bolts, int secondsToAssemble) throws InterruptedException {
		AssemblyBean assemblyBean = new AssemblyBean(machines, bolts);
		int count = 0;
		List<AssemblyWorker> workerList = null;
		List<Future<Boolean>> results = null;
		boolean stopProcessing = false;
		

		while (true) {
			//Submit tasks to all workers
			boolean isAssembled = false;
			//Reinitialize the latch
			latch = new CountDownLatch(NUMBER_OF_WORKERS);
		    workerList = new ArrayList<AssemblyWorker>();
		    results = new ArrayList<Future<Boolean>>();
			 
			for (int i = 0; i < NUMBER_OF_WORKERS; i++) {
				AssemblyWorker worker = new AssemblyWorker(assemblyBean, latch);
				workerList.add(worker);	
			}
			
			for(AssemblyWorker work: workerList) {
				Future<Boolean> res = executorService.submit(work);
				results.add(res);
			}
						
			if(results!=null) {				
				for(Future<Boolean> f : results) {
					try {
						if(f.get().booleanValue() == true) {
							//Part assembling done
							System.out.println("Processing done...");
							isAssembled = true;
						} else {
							stopProcessing = true;
							break;
						}
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}	
			
			
			//Increment the count as the atleast one processing is done
			if(isAssembled) {
				count++;
				System.out.println("Count : " + count);
			}
			
			if(stopProcessing) {
				break;
			}
			
			//Wait for their execution and check the execution results
			latch.await();

		}
		
		executorService.shutdown();
		executorService.awaitTermination(10, TimeUnit.SECONDS);
		
		return count * secondsToAssemble;
	}

}
