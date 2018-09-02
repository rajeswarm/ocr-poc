package org.botteam.ocr.tessract.wrapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

public class TesseractExecutor {

	TesseractConfig config;

	public TesseractExecutor(TesseractConfig config) {
		this.config = config;
	}

	public String parseImage(String imagePath) throws IOException, InterruptedException {
		String parsedText = "";

		String tesseractExeFullPath = config.getTessearctInstallPath() + getTesseractExecutableName();

		// 1. create a temp file for output
		File outputFile = File.createTempFile("bot-ocr-out", ".tmp");

		// 2. execute the tesseract executable
		ProcessBuilder processBuilder = new ProcessBuilder(tesseractExeFullPath, imagePath,
				outputFile.getAbsolutePath(), "-l", config.getLanguage());

		Process process = processBuilder.start();
		process.waitFor(config.getTimeoutInSeconds(), TimeUnit.SECONDS);

		// 3. Read the content of output as text
		outputFile = new File(outputFile.getAbsolutePath() + ".txt"); //this is required as tesseract appends .txt at the end of output file
		parsedText = FileUtils.readFileToString(outputFile, Charset.defaultCharset());

		// 4. delete temp output file
		FileUtils.deleteQuietly(outputFile);

		return parsedText;
	}

	private String getTesseractExecutableName() {
		return config.getOSName().toLowerCase().startsWith("windows") ? "tesseract.exe" : "tesseract";
	}
}
