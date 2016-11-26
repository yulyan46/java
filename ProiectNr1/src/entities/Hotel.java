package entities;

public class Hotel {
	private Integer numberOfRooms;
	private Integer numberOfPeoplePerRoom;
	private Integer numberOfStars;

	public Hotel(Integer numberOfRooms, Integer numberOfPeoplePerRoom,
			Integer numberOfStars) {
		super();
		this.numberOfRooms = numberOfRooms;
		this.numberOfPeoplePerRoom = numberOfPeoplePerRoom;
		this.numberOfStars = numberOfStars;
	}

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
}
