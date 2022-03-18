package org.lsmr.selfcheckout;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.Acceptor;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;

public class SCSLogic extends AbstractDevice<AbstractDeviceObserver> implements Acceptor<AbstractDeviceObserver>{
	
	private ElectronicScale electronic;
	
	
	
	
	public SCSLogic(ElectronicScale e) {
		this.electronic = e;
	}
	
	
	
	
	public void wish_checkout(AbstractDeviceObserver payment_method) throws OverloadException, DisabledException  {
		
		try {
			this.attach(payment_method);
			while(true) {
				if (!electronic.isDisabled()) {  // keep checking the weight of bagging area, check if the weight has changed
					
					this.accept(payment_method);
					
					if (this.isDisabled()) {
						break;
					}
					else { //this should never been run
						throw new SimulationException("can't receive payment method");
					}
					
				}
				else {
					throw new SimulationException("the weight has changed");
				}
				
			}
			//....might need to call the matched payment method
			
		} catch (OverloadException e) {
			throw e;
		}catch (DisabledException e) {
		    throw e;
		}
		
		
		
		
		
	}










	@Override
	public void accept(AbstractDeviceObserver thing) throws OverloadException, DisabledException {
			thing.enabled(this);
	}



	@Override
	public boolean hasSpace() { //not used yet
		// TODO Auto-generated method stub
		return false;
	}
	
}
