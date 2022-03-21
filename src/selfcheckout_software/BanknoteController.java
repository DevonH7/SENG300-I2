package selfcheckout_software;
import java.math.BigDecimal;
import java.util.Currency;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.BanknoteValidator;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.BanknoteStorageUnitObserver;
import org.lsmr.selfcheckout.devices.observers.BanknoteValidatorObserver;

public class BanknoteController implements BanknoteValidatorObserver, BanknoteStorageUnitObserver{

	private int availableFunds;
	private int validBanknotes = 0;
	private int invalidBanknotes = 0;
	private boolean isFull = false;
	
	public BanknoteController(SelfCheckoutStation s) {
		s.banknoteValidator.attach(this);
		s.banknoteStorage.attach(this);
		availableFunds = 0;
	}

	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {}

	@Override
	public void validBanknoteDetected(BanknoteValidator validator, Currency currency, int value) {
		if(!isFull) {
			availableFunds+=value;
			validBanknotes++;
		}
		
	}

	@Override
	public void invalidBanknoteDetected(BanknoteValidator validator) {
		invalidBanknotes++;
	}

	@Override
	public void banknotesFull(BanknoteStorageUnit unit) {
		isFull = true;
		
	}

	@Override
	public void banknoteAdded(BanknoteStorageUnit unit) {}

	@Override
	public void banknotesLoaded(BanknoteStorageUnit unit) {}

	@Override
	public void banknotesUnloaded(BanknoteStorageUnit unit) {
		isFull = false;
	}
	
	public boolean hasSufficientFunds(float price) {
		return availableFunds>price;
	}
	
	public int getCurrentFunds() {
		return availableFunds;
	}
	
	public int getValidBanknotes() {
		return validBanknotes;
	}
	
	public int getInvalidBanknotes() {
		return invalidBanknotes;
	}
	
	
}
