package entities;

public class BasicPackage {
	private Destination destination;
	private PackageDates packageDates;
	private Double price;

	public BasicPackage(Destination destination, PackageDates packageDates,
			Double price) {
		super();
		this.destination = destination;
		this.packageDates = packageDates;
		this.price = price;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public PackageDates getPackageDates() {
		return packageDates;
	}

	public void setPackageDates(PackageDates packageDates) {
		this.packageDates = packageDates;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
