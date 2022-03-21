package selfcheckout_software;
import java.util.ArrayList;

import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.Acceptor;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.ElectronicScaleObserver;

public class logic implements ElectronicScaleObserver{
	
	
	
	private ElectronicScale electronic;
	
	private ArrayList<Item> items = new ArrayList<>();
	
	double expectedWeight;
	
	public logic(ElectronicScale e) {
		this.electronic = e
;	}
	
	public void bagItem(Item i, ElectronicScale e) throws OverloadException, SimulationException {
		double maxWeight = e.getWeightLimit();
		double current = e.getCurrentWeight();
		if (e.getCurrentWeight()==0) {			//logic to make sure that the scale is empty before actually scanning items
			current = e.getCurrentWeight();
			e.add(i);
		} else {								//proceeds normally after the first scanned item	
			e.add(i);
			current = e.getCurrentWeight();
		}
		double itemWeight = i.getWeight();
		expectedWeight = current + itemWeight;
		double updatedWeight = e.getCurrentWeight();
		System.out.println(expectedWeight);
		if (updatedWeight>maxWeight) {
			throw new OverloadException();
		}
		if (updatedWeight!=expectedWeight) {
			throw new SimulationException("Please put the correct items in the bagging area");
		}
		}
	public double get_expected_weight() {
		return this.expectedWeight;
	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void weightChanged(ElectronicScale scale, double weightInGrams) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void overload(ElectronicScale scale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void outOfOverload(ElectronicScale scale) {
		// TODO Auto-generated method stub
		
	}

	

}
