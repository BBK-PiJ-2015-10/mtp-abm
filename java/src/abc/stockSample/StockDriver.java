package abc.stockSample;

public class StockDriver {

	public static void main(String[] args) {
		
		
		StockData stockData = new StockData();
		stockData.loadDataFromFile("DJIA.csv");
		
		System.out.println(stockData.getNumberOfRecords());
		
		System.out.println(stockData.getLargestHigh());
		
		System.out.println(stockData.getLowestLow());

	}

}
