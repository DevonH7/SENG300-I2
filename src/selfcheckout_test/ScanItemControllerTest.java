package selfcheckout_test;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.products.BarcodedProduct;

import junit.extensions.TestSetup;

import org.lsmr.selfcheckout.*;

import selfcheckout_software.ScanItemController;
import selfcheckout_software.*;
public class ScanItemControllerTest {
	private BigDecimal total;
	private double expectedWeight;
	private ArrayList<BarcodedProduct> productList;
	private 
	@Before
	public void Setup() {
		productList = new ArrayList<>();
		ArrayList<BarcodedItem> itemlist = new ArrayList<>();
	}
	
	/*
	 * Test case for adding a product which is null.
	 * A SimulationException should be thrown in this case.
	 */	
	@Test(expected = SimulationException.class)
	public void nullProduct() {

		ScanItemController scanner = new ScanItemController();
		Barcode barcode = null;
		BarcodedProduct[] productList = null;
		BarcodedItem[] itemList = null;

		scanner.addItem(barcode, productList, itemList);
	}

	/*
	 * Test case for adding an item which is null.
	 * A SimulationException should be thrown in this case.
	 */
	@Test(expected = SimulationException.class)
	public void nullItem() {

		ScanItemController scanner = new ScanItemController();
		Barcode barcode = null;
		BarcodedProduct[] productList = null;
		BarcodedItem[] itemList = null;

		scanner.addItem(barcode, productList, itemList);
	}

	/*
	 * Test case for ensuring price total is correct.
	 * total should equal 10.00 in this case.
	 */
	@Test
	public void correctTotal() {

		
		Numeral[] test_number = {Numeral.one,Numeral.one,Numeral.one,Numeral.one};
		//Create an item
		BigDecimal price = new BigDecimal("10.00");
		Barcode barcode = new Barcode(test_number);
		
		BarcodedProduct barcodedProduct = new BarcodedProduct(barcode, "N/A", price);
		productList.add(barcodedProduct);
		
		BarcodedItem item = new BarcodedItem(barcode, 10.00);

		ScanItemController scanner = new ScanItemController();

		scanner.addItem(barcode, productList, itemlist);

		total = scanner.getTotal();

		assertEquals(10.00, total);
	}

	/*
	 * Test case for ensuring weight total is correct.
	 * expectedWeight should equal 10.00 in this case.
	 */
	@Test
	public void correctWeight() {

		
		//Create an item
		Double price = 10.00 ;
		Barcode barcode = new Barcode(1111);
		BarcodedProduct[] productList = new BarcodedProduct[n];
		BarcodedProduct barcodedProduct = new BardcodedProduct(barcode, "N/A", price)
		productList.add(barcodedProduct)
		BarcodedItem[] itemlist = new BarcodedItem[n];
		BarcodedItem item = new BarcodedItem(barcode, 10.00);

		ScanItemController scanner = new ScanItemController();

		scanner.addItem(barcode, productList, itemlist);

		expectedWeight = getExpectedWeight()

		assertEquals(10.00, expectedWeight);
	}
}