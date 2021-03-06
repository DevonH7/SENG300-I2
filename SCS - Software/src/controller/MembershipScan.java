package controller;

import java.util.ArrayList;
import java.util.List;

import org.lsmr.selfcheckout.*;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.observers.AbstractDeviceObserver;
import org.lsmr.selfcheckout.devices.observers.CardReaderObserver;
public class MembershipScan implements CardReaderObserver{

	private List <Card> Memberships = new ArrayList<Card>();
	
	private SelfCheckoutStation station;	
	
	private String membershipcard;
	
	@Override
	public void enabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractDevice<? extends AbstractDeviceObserver> device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cardInserted(CardReader reader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cardRemoved(CardReader reader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cardTapped(CardReader reader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cardSwiped(CardReader reader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cardDataRead(CardReader reader, CardData data) {
		// TODO Auto-generated method stub
		
	}
	
	public MembershipScan(SelfCheckoutStation station) {
		this.station = station;
		station.cardReader.attach(this);
	}
	
	public boolean ScanMembershipCard(Card membercard) {
		return true;
	}
	
	public boolean CheckMembershipCard(Card membercard) {
		return true;
	}
	
	
}
