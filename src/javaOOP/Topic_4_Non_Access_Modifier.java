package javaOOP;

public class Topic_4_Non_Access_Modifier {
	private int studentID;
	private String studentName;
	private Float knowledgePoint;
	private Float praticePoint;
	
	/**
	 * @return the studentID
	 */
	private final int getStudentID() {
		return studentID;
	}

	/**
	 * @param studentID the studentID to set
	 */
	private final void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	/**
	 * @return the studentName
	 */
	private final String getStudentName() {
		return studentName;
	}

	/**
	 * @param studentName the studentName to set
	 */
	private final void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * @return the knowledgePoint
	 */
	private final Float getKnowledgePoint() {
		return knowledgePoint;
	}

	/**
	 * @param knowledgePoint the knowledgePoint to set
	 */
	private final void setKnowledgePoint(Float knowledgePoint) {
		this.knowledgePoint = knowledgePoint;
	}

	/**
	 * @return the praticePoint
	 */
	private final Float getPraticePoint() {
		return praticePoint;
	}

	/**
	 * @param praticePoint the praticePoint to set
	 */
	private final void setPraticePoint(Float praticePoint) {
		this.praticePoint = praticePoint;
	}

	private Float getAveragePoint() {
		return (this.knowledgePoint + this.praticePoint * 2) / 3;
	}

	private void showStudentInfor() {
		System.out.println(getStudentID());
		System.out.println(getStudentName());
		System.out.println(getKnowledgePoint());
		System.out.println(getPraticePoint());
		System.out.println(getAveragePoint());
	}

	public static void main(String[] args) {
		Topic_4_Non_Access_Modifier firstStudent = new Topic_4_Non_Access_Modifier();
		firstStudent.setStudentID(1751120097);
		firstStudent.setStudentName("Phung Vinh Phuc");
		firstStudent.setKnowledgePoint(7.9f);
		firstStudent.setPraticePoint(7.9f);
		firstStudent.showStudentInfor();
		Topic_4_Non_Access_Modifier secondStudent = new Topic_4_Non_Access_Modifier();
		secondStudent.setStudentID(1751120097);
		secondStudent.setStudentName("Phung Vinh Phuc 1");
		secondStudent.setKnowledgePoint(7.9f);
		secondStudent.setPraticePoint(7.9f);
		secondStudent.showStudentInfor();
	}
}