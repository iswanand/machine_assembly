package com.machinetest.app;

public class AssemblyMain {
	public static void main(String[] args) {
		MachineAssembler assembler = new MachineAssembler();
		int seconds = 0;
		try {
			seconds = assembler.getTimeToAssemble(12, 24, 60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Seconds : " + seconds);
	}
}
