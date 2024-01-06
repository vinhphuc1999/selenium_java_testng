package eclipseTips;

public class Topic_3_Getter_Setter {
	private String carName;
	private String carType;
	
	// Page Object Pattern
	
	// Constructor
		
	/**
	 * @return the carType
	 */
	public final String getCarType() {
		return carType;
	}

	/**
	 * @param carType the carType to set
	 */
	public final void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}
}
