package filters;

public class AccomodationFilter extends BasicFilter {
	private Integer numberOfRooms;
	private Integer numberOfPeoplePerRoom;
	private Integer numberOfStars;
	private String transportType;
	private String transportName;

	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public Integer getNumberOfPeoplePerRoom() {
		return numberOfPeoplePerRoom;
	}

	public void setNumberOfPeoplePerRoom(Integer numberOfPeoplePerRoom) {
		this.numberOfPeoplePerRoom = numberOfPeoplePerRoom;
	}

	public Integer getNumberOfStars() {
		return numberOfStars;
	}

	public void setNumberOfStars(Integer numberOfStars) {
		this.numberOfStars = numberOfStars;
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
