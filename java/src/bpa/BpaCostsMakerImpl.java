package bpa;

import period.PeriodMaker;

public class BpaCostsMakerImpl implements BpaCostsMaker {

	private PeriodMaker periodMaker;
	
	public BpaCostsMakerImpl(PeriodMaker periodMaker){
		this.periodMaker=periodMaker;
	}
	
	public PeriodMaker getPeriodMaker(){
		return this.periodMaker;
	}
	
	@Override
	public void createbpaCosts() {

		
	}
	
	

}
