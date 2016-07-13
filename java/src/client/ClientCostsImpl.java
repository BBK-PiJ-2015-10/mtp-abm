package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

import bpa.BpaCostsMaker;
import period.PeriodMaker;
import bpa.BpaCostCalculator;

public class ClientCostsImpl implements ClientCosts {
	
	private BpaCostCalculator bpaCostCalculator;
	
	public ClientCostsImpl(BpaCostCalculator bpaCostCalculator){
		this.bpaCostCalculator=bpaCostCalculator;
	}
	
	@Override
	public boolean calculateCosts() {
		// TODO Auto-generated method stub
		return false;
	}

}
