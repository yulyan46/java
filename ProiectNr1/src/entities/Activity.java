package entities;

public class Activity {
	private String activityType;
	private String activityName;

	public Activity(String activityType, String activityName) {
		super();
		this.activityType = activityType;
		this.activityName = activityName;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

}
