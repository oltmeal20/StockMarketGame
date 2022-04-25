package stockProjectBeta;

public class OwnedStock extends Stock{

	private int ownedStock_quantity;
	private double ownedStock_TotalValue;

	public void setQuantity(int q) {
		this.ownedStock_quantity = q;
	}
	public int getQuantity() {
		return ownedStock_quantity;
	}
	
	public void setOwnedTotalValue(double total) {
		this.ownedStock_TotalValue = total;
	}
	public double getOwnedValue() {
		return ownedStock_TotalValue;
	}
	
}
