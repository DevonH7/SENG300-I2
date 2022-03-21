package selfcheckout_test;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.Numeral;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.products.BarcodedProduct;

import selfcheckout_software.BanknoteController;

public class BanknoteTest {
	
	private HashMap<Barcode, BarcodedProduct> products = new HashMap<Barcode, BarcodedProduct>();
	private BanknoteController pay;
	private SelfCheckoutStation s;
	
	@Before
	public void setup() {
		products.put(new Barcode(new Numeral[] {Numeral.one,Numeral.one}), 
				new BarcodedProduct(new Barcode(new Numeral[] {Numeral.one,Numeral.one}), "Bread", new BigDecimal(4.99)));
		products.put(new Barcode(new Numeral[] {Numeral.two,Numeral.two}), 
				new BarcodedProduct(new Barcode(new Numeral[] {Numeral.two,Numeral.two}), "Milk", new BigDecimal(2.50)));
		products.put(new Barcode(new Numeral[] {Numeral.three,Numeral.three}), 
				new BarcodedProduct(new Barcode(new Numeral[] {Numeral.three,Numeral.three}), "Oreos", new BigDecimal(10.99)));
		products.put(new Barcode(new Numeral[] {Numeral.four,Numeral.four}), 
				new BarcodedProduct(new Barcode(new Numeral[] {Numeral.four,Numeral.four}), "Orange", new BigDecimal(0.99)));
	    
	    Currency currency = Currency.getInstance("CAD");
	    int[] banknoteDenoms = new int[] {5,10,20,50};
	    BigDecimal[] coinDenominations = new BigDecimal[]{new BigDecimal("0.05"), new BigDecimal("0.10"), 
	    		new BigDecimal("0.25"), new BigDecimal("0.50"), new BigDecimal("1.00"), new BigDecimal("2.00")};
	    
	    
	    s = new SelfCheckoutStation(currency,banknoteDenoms,coinDenominations,Integer.MAX_VALUE,1);
	    pay = new BanknoteController(s);
	}
	
	@Test
	public void testBanknoteControllerRegular() throws DisabledException, OverloadException {
		Barcode itemCode = new Barcode(new Numeral[] {Numeral.one,Numeral.one});
		s.banknoteInput.accept(new Banknote(Currency.getInstance("CAD"),5));
		s.banknoteInput.accept(new Banknote(Currency.getInstance("CAD"),10));
		assertEquals(pay.getCurrentFunds(),15);
	}
	
	@Test
	public void testBanknoteControllerFull() throws DisabledException, OverloadException {
		Barcode itemCode = new Barcode(new Numeral[] {Numeral.one,Numeral.one});
		for(int i = 0;i<75;i++)
			s.banknoteInput.accept(new Banknote(Currency.getInstance("CAD"),5));
		assertEquals(pay.getCurrentFunds(),5*s.BANKNOTE_STORAGE_CAPACITY);
	}
	
	@Test
	public void testBanknoteControllerInvalidBanknote() throws DisabledException, OverloadException {
		s.banknoteInput.accept(new Banknote(Currency.getInstance("USD"),5));
		assertEquals(0,pay.getCurrentFunds());
		assertEquals(1,pay.getInvalidBanknotes());
	}
	
	
	
	
}
