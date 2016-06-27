package configuration;

public class Tuple<T,K> {

	private T name;
	
	private K position;
	
	public Tuple(T name, K position){
		this.name=name;
		this.position=position;
	}
	
	public T getName() {
		return name;
	}
	
	public K getPosition() {
		return position;
	}
	
	public void setName(T name) {
		this.name = name;
	}
	
	public void setPosition(K position) {
		this.position = position;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
}
