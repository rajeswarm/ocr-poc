package org.botteam.ocr.tessract.wrapper;

import java.io.IOException;

public class TesseractWrapperApp {

	public static void main(String[] args) throws IOException, InterruptedException {

		String inputImagePath = "/Users/rajeswarm/Documents/BOT Team/IMG_1898.TIFF";
		String tesseractInstallDir = "/usr/local/Cellar/tesseract/3.05.01/bin/";

		TesseractConfig config = new TesseractConfig(tesseractInstallDir);
		TesseractExecutor executor=new TesseractExecutor(config);
		
		String parsedText = executor.parseImage(inputImagePath);
		
		System.out.println("Parsed Text:");
		System.out.println(parsedText);

	}
}
