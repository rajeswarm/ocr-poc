package org.botteam.ocr.tessract.wrapper;

public class TesseractConfig {

	private String tessearctInstallPath;
	private String language = "eng";
	private String OSName;
	private int timeoutInSeconds = 60;

	public String getOSName() {
		return OSName;
	}

	public TesseractConfig(String tessearctInstallPath) {
		this.tessearctInstallPath = tessearctInstallPath;
		this.OSName = System.getProperty("os.name");
	}

	public String getTessearctInstallPath() {
		return tessearctInstallPath;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getTimeoutInSeconds() {
		return timeoutInSeconds;
	}

	public void setTimeoutInSeconds(int timeoutInSeconds) {
		this.timeoutInSeconds = timeoutInSeconds;
	}

}
