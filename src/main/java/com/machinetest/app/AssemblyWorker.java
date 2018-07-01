package com.machinetest.app;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import com.machinetest.app.bean.AssemblyBean;

public class AssemblyWorker implements Callable<Boolean> {
	private AssemblyBean assemblyBean;
	private CountDownLatch latch;

	public AssemblyWorker(AssemblyBean assemblyBean, CountDownLatch latch) {
		super();
		this.assemblyBean = assemblyBean;
		this.latch = latch;
	}

	public Boolean call() throws Exception {
		System.out.println("Available machines : " + assemblyBean.getMachines());
		Boolean result = assembleMachine();
		System.out.println("Assembling part now..");
		System.out.println("Remaining machines : " + assemblyBean.getMachines());
		if (result.booleanValue() == false) {
			return false;
		}
		// Spend time in assembling machine
		// Assemble part here
		return result;
	}

	/**Do actual assembly
	 * 
	 * @return true if assembly can be done else rerun false
	 */
	private boolean assembleMachine() {
		synchronized (assemblyBean) {

			if (assemblyBean == null) {
				return false;
			}
			boolean result;
			if (assemblyBean.getMachines() > 0 && assemblyBean.getBolts() >= 2) {
				assemblyBean.setMachines(assemblyBean.getMachines() - 1);
				assemblyBean.setBolts(assemblyBean.getBolts() - 2);
				System.out.println("Assembly done..");
				result = true;
			} else {
				result = false;
			}

			latch.countDown();

			return result;

		}
	}
}
