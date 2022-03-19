package selfcheckout_test;

import selfcheckout_software.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;

import junit.extensions.TestSetup;
import junit.framework.Assert;

public class test_wish_to_checkout {

	private SCSLogic test_object;
	private AbstractDeviceObserverStub payment;
	private ElectronicScaleObserverStub w_observer;
	private ElectronicScale E;
	
	@Before
	public void TestSetup() {
		this.E = new ElectronicScale(100, 1);
		this.test_object = new SCSLogic(E);
		this.payment = new AbstractDeviceObserverStub();
		this.w_observer = new ElectronicScaleObserverStub();
		
	}
	
	@Test(expected = SimulationException.class)
	public void test_attachment() throws OverloadException, DisabledException {
		test_object.wish_checkout(payment);
		test_object.detach(payment);
		test_object.detach(payment);
	}
	
	@Test(expected = SimulationException.class)
	public void test_weight_changed() throws OverloadException, DisabledException {
		E.attach(w_observer);
		Item test_item1 = new Item(20) {};
		Item test_item2 = new Item(40) {};
		E.add(test_item1);
		E.enable();
		
		E.add(test_item2);
		
		test_object.wish_checkout(payment);
		
	}
	
	@Test
	public void test_normal_wish() throws OverloadException, DisabledException {
		test_object.endConfigurationPhase();
		E.attach(w_observer);
		E.endConfigurationPhase();
		E.enable();
		test_object.wish_checkout(payment);
		assertTrue("this should be true",test_object.isDisabled());
		
		
	}
	

}
