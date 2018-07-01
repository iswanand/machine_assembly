import static org.junit.Assert.*;

import org.junit.Test;

import com.machinetest.app.MachineAssembler;

public class AssemblyTest {
	public static final String EXCEPTION_MESSAGE = "Falied, no exception should be thrown";
	
	@Test
	public void testAssemblyWithEqualParts() {
		MachineAssembler assembler = new MachineAssembler();
		int seconds = 0;
		try {
			seconds = assembler.getTimeToAssemble(3, 6, 60);
			assertEquals(seconds, 60);
			
			assembler = new MachineAssembler();
			seconds = assembler.getTimeToAssemble(6, 12, 60);
			assertEquals(seconds, 120);
			
			assembler = new MachineAssembler();
			seconds = assembler.getTimeToAssemble(9, 18, 60);
			assertEquals(seconds, 180);
			
		} catch (InterruptedException e) {			
			fail(EXCEPTION_MESSAGE);
		}		
	}


	@Test
	public void testAssemblyWithLessMachines() {
		MachineAssembler assembler = new MachineAssembler();
		int seconds = 0;
		try {
			
			assembler = new MachineAssembler();
			seconds = assembler.getTimeToAssemble(4, 12, 60);
			assertEquals(seconds, 120);
			
			assembler = new MachineAssembler();
			seconds = assembler.getTimeToAssemble(8, 18, 60);
			assertEquals(seconds, 180);
			
		} catch (InterruptedException e) {			
			fail(EXCEPTION_MESSAGE);
		}
	}
	
	@Test
	public void testAssemblyWithMoreMachinesLessBolts() {
		MachineAssembler assembler = new MachineAssembler();
		int seconds = 0;
		try {
			//Only 5 machines can be assembled
			assembler = new MachineAssembler();
			seconds = assembler.getTimeToAssemble(5, 12, 60);
			assertEquals(seconds, 120);
			
			//Only 9 machines can be assembled
			assembler = new MachineAssembler();
			seconds = assembler.getTimeToAssemble(10, 18, 60);
			assertEquals(seconds, 180);
			
		} catch (InterruptedException e) {			
			fail(EXCEPTION_MESSAGE);
		}
	}

	@Test
	public void testAssemblyWithNoBolts() {
		MachineAssembler assembler = new MachineAssembler();
		int seconds = 0;
		try {
			//No bolts are provided hence 0 machine would be assembled
			seconds = assembler.getTimeToAssemble(1, 0, 60);
			assertEquals(seconds, 0);			
			
		} catch (InterruptedException e) {			
			fail(EXCEPTION_MESSAGE);
		}
	}
	
	@Test
	public void testAssemblyWithNoMachines() {
		MachineAssembler assembler = new MachineAssembler();
		int seconds = 0;
		try {
			//No machines are provided hence 0 machine would be assembled
			seconds = assembler.getTimeToAssemble(0, 12, 60);
			assertEquals(seconds, 0);			
			
		} catch (InterruptedException e) {			
			fail(EXCEPTION_MESSAGE);
		}
	}
}
