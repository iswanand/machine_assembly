package com.machinetest.app.bean;

public class AssemblyBean {
	private int machines;
	private int bolts;	
	
	public AssemblyBean(int machines, int bolts) {
		super();
		this.machines = machines;
		this.bolts = bolts;
	}
	
	public synchronized int getMachines() {
		return machines;
	}

	public synchronized void setMachines(int machines) {
		this.machines = machines;
	}

	public synchronized int getBolts() {
		return bolts;
	}

	public synchronized void setBolts(int bolts) {
		this.bolts = bolts;
	}
}
