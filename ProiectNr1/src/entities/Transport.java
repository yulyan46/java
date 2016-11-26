package entities;

public class Transport {
	private String transportType;
	private String transportName;

	public Transport(String transportType, String transportName) {
		super();
		this.transportType = transportType;
		this.transportName = transportName;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public String getTransportName() {
		return transportName;
	}

	public void setTransportName(String transportName) {
		this.transportName = transportName;
	}

}
