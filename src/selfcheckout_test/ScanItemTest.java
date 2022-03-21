package selfcheckout_test;

import org.junit.Test;
import org.lsmr.selfcheckout.devices.SimulationException;

public class ScanItemTest {

	@Test(expected = SimulationException.class)
	public void noProduct() {
		
	}
	
	@Test(expected = SimulationException.class)
	public void noItem() {
		
	}
	
	@Test()
	public void correctTotal() {
		
	}
	
	@Test()
	public void correctWeight() {
		
	}
}
