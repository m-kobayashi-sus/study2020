package bean;

public class DataBean {

	private String name;
	private String mail;
	private int id;
	private String date;
	private String startTime;
	private int breakTime;
	private String endTime;
	private String detail;
	private String diffTime;
	private int ID;


	public DataBean() {

	}

	public DataBean(String name, int id) {
		this.name = name;
		this.id = id;
	}
	public DataBean(String date) {
		this.date = date;
	}

	public DataBean(int id, String name, String mail) {
		this.id = id;
		this.name = name;
		this.mail = mail;
	}

	public DataBean(int ID ,String date, String startTime, String endTime, int breakTime, String diffTime, String detail) {
		this.ID = ID;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.breakTime = breakTime;
		this.diffTime = diffTime;
		this.detail = detail;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setBreakTime(int breakTime) {
		this.breakTime = breakTime;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setDiffTime(String diffTime) {
		this.diffTime = diffTime;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getMail() {
		return this.mail;
	}

	public String getDate() {
		return this.date;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public int getBreakTime() {
		return this.breakTime;
	}

	public String getDetail() {
		return this.detail;
	}

	public String getDiffTime() {
		return this.diffTime;
	}

	public int getID() {
		return this.ID;
	}
}