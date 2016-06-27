package configuration;

import java.util.Objects;

public class Tuple<T,K> {

	private T val1;
	
	private K val2;
	
	public Tuple(T val1, K val2){
		this.val1=val1;
		this.val2=val2;
	}
	
	public T getName() {
		return val1;
	}
	
	public K getPosition() {
		return val2;
	}
	
	public void setName(T val1) {
		this.val1 = val1;
	}
	
	public void setPosition(K val2) {
		this.val2 = val2;
	}
	
	
	public void exp(){
		Tuple<String,String> first = new Tuple("Ale","Tonto");
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Tuple<?, ?>)) {
            return false;
        }
        Tuple<?, ?> p = (Tuple<?, ?>) obj;
        return val1.equals(p.val1) && val2.equals(p.val2);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(val1,val2);
	}
	
}
