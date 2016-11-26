package entities;

public class ActivitiesPackage extends BasicPackage {
	private Activity activity;

	public ActivitiesPackage(Destination destination,
			PackageDates packageDates, Double price, Activity activity) {
		super(destination, packageDates, price);
		this.activity = activity;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
}
