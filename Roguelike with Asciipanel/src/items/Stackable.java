package items;

public interface Stackable {
	
	public void incrementCapacity(int i);
	public void decrementCapacity(int i);
	public boolean isEmpty();
	public int getCapacity();
	
	
}
