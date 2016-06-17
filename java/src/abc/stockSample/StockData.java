package abc.stockSample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class StockData {
	
	private ArrayList<StockRecord> records;
	
	public StockData(){
		records = new ArrayList<StockRecord>();
	}
	
	public StockRecord getRecordNumber(int i){
		return records.get(i);
	}
	
	public int getNumberOfRecords(){
		return records.size();
	}
	
	public void loadDataFromFile(String filepath){
		
		    //File file = new File("DJIA.csv");
		
		try {
		
			//File file = new File("C:\\Users\\YasserAlejandro\\mtp\\mtp-abm\\java\\DJIA.csv");
			
			Scanner scanner = new Scanner(new FileReader(filepath));
			String line;
			StockRecord record;
			scanner.nextLine();
			while (scanner.hasNextLine()){
				line = scanner.nextLine();
				String[] results = line.split(",");
				double opening = Double.parseDouble(results[1]);
				double high = Double.parseDouble(results[2]);
				double low = Double.parseDouble(results[3]);
				double closing = Double.parseDouble(results[4]);
				
				record = new StockRecord(results[0],low,high,opening,closing);
				records.add(record);
			}
			scanner.close();
		} catch (FileNotFoundException ex){
			System.out.println("File " + filepath + " does not exist");
		} catch (IOException ex){
			ex.printStackTrace();
		}
			
	}
	
	
	public StockRecord getLargestHigh(){
		StockRecord highest = records.get(0);
		for (StockRecord record:records){
			if (record.getHigh() > highest.getHigh()){
				highest = record;
			}
		}
		return highest;
	}
	
	
	public StockRecord getLowestLow(){
		StockRecord lowest = records.get(0);
		for (StockRecord record:records){
			if (record.getLow() < lowest.getLow()){
				lowest = record;
			}
		}
		return lowest;
	}	
	
	
	
	

}
