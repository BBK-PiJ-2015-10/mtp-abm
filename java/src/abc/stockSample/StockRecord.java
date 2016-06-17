package abc.stockSample;

public class StockRecord {

	private double low, high, opening, closing;
	
	private String date;
	
	public StockRecord(String date, double low, double high, double opening, double closing) {
		this.low = low;
		this.high = high;
		this.opening = opening;
		this.closing = closing;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getOpening() {
		return opening;
	}

	public void setOpening(double opening) {
		this.opening = opening;
	}

	public double getClosing() {
		return closing;
	}

	public void setClosing(double closing) {
		this.closing = closing;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString(){
		return " high: " + high + " low: " +low + " opening: " + opening  + " closing: " +closing;
	}
	
	
}
