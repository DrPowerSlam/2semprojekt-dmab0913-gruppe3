package modellayer;

public class Settings {
	
	private String title = "Ligustica ";
	private int width = 850;
	private int height = 525;
	private Breeder breeder;
	private static Settings instance = null;

	private Settings() {
		title = "Ligustica ";
		width = 850;
		height = 525;
	}
	
	public static Settings getInstance() {
		if(instance == null) {
	         instance = new Settings();
	    }
		return instance;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the breeder
	 */
	public Breeder getBreeder() {
		return breeder;
	}

	/**
	 * @param breeder the breeder to set
	 */
	public void setBreeder(Breeder breeder) {
		this.breeder = breeder;
	}
	
	

}
