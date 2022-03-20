package selfcheckout_software;

import java.math.BigDecimal;

import org.lsmr.selfcheckout.*;
import org.lsmr.selfcheckout.devices.*;
import org.lsmr.selfcheckout.devices.observers.*;

// logic for the use case "customer wants to pay with coin" by implementing the CoinValidatorObserver
// and adding value of a coin to a local variable availableFunds if the proper event occurs (a valid coin is detected). 
public class PayWithCoin implements CoinValidatorObserver {
	
	private Coin c;
	private BigDecimal availableFunds = new BigDecimal(0);
	
	public PayWithCoin(Coin c) {
		this.c = c;
	}
	
	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
	}

	// validator determines the coin is valid, add value of coin to sum
	@Override
	public void validCoinDetected(CoinValidator validator, BigDecimal value) {
		availableFunds = availableFunds.add(value);
	}

	// validator determines the coin is not valid, do nothing
	@Override
	public void invalidCoinDetected(CoinValidator validator) {	
	}
	
	public BigDecimal getAvailableFunds() {
		return availableFunds;
	}

}
