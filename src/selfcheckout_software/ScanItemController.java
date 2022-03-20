package selfcheckout_software;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.BarcodeScannerObserver;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.Product;

public class ScanItemController implements BarcodeScannerObserver,  {
	
	private ArrayList<Product> product = new ArrayList<Product>();
	private BigDecimal total;
	
	public ScanItemController() {
		
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
	public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
		// TODO Auto-generated method stub
		
	}
	
	public void addItem(BarcodedProduct Item) {
		product.add(Item);
		total += Item.getPrice();
		
	}
	
}
