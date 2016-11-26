package entities;

public class BookingPackage extends BasicPackage {
	private Hotel hotel;
	private Transport transport;

	public BookingPackage(Destination destination, PackageDates packageDates,
			Hotel hotel, Transport transport, Double price) {
		super(destination, packageDates, price);
		this.hotel = hotel;
		this.transport = transport;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

}
