package manning.chapterTwo;

public class HelloWorld {

	private static final String GREETING = "Hello ";
	private String name;
	private String customGreeting;
	public String execute(){
		setCustomGreeting(GREETING+getName());
		return "SUCCESS";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustomGreeting() {
		return customGreeting;
	}
	public void setCustomGreeting(String customGreeting) {
		this.customGreeting = customGreeting;
	}
	public static String getGREETING() {
		return GREETING;
	}
	
	
}
