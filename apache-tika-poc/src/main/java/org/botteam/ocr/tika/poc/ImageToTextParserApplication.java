package org.botteam.ocr.tika.poc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.jpeg.JpegParser;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.parser.ocr.TesseractOCRParser;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class ImageToTextParserApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String parsedText = null;
		TikaConfig config = TikaConfig.getDefaultConfig();

		AutoDetectParser parser = new AutoDetectParser(config);
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		ParseContext pcontext = new ParseContext();

		PDFParserConfig pdfConfig = new PDFParserConfig();
		pdfConfig.setExtractInlineImages(true);

		// pcontext.set(Parser.class, parser);
		// pcontext.set(PDFParserConfig.class, pdfConfig);

		TesseractOCRConfig tesserConfig = new TesseractOCRConfig();
		tesserConfig.setLanguage("eng");
		tesserConfig.setTesseractPath("/usr/local/Cellar/tesseract/3.05.01/bin/");
		tesserConfig.setTimeout(60);
		pcontext.set(TesseractOCRConfig.class, tesserConfig);

		File file = new File("/Users/rajeswarm/Documents/BOT Team/IMG_1898.TIFF");

		TesseractOCRParser ocrParser = new TesseractOCRParser();
		boolean hasTesser = ocrParser.hasTesseract(tesserConfig);

		try (InputStream stream = new FileInputStream(file)) {
			try {
				ocrParser.parse(stream, handler, metadata, pcontext);
				parsedText = handler.toString();
			} catch (SAXException | TikaException e) { // TODO Auto-generated catch block e.printStackTrace(); }

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			/*
			 * try (InputStream stream = new FileInputStream(file)) { // Jpeg Parse
			 * JpegParser JpegParser = new JpegParser(); JpegParser.parse(stream, handler,
			 * metadata, pcontext); System.out.println("Contents of the document:" +
			 * handler.toString()); System.out.println("Metadata of the document:");
			 * String[] metadataNames = metadata.names();
			 * 
			 * for (String name : metadataNames) { System.out.println(name + ": " +
			 * metadata.get(name)); } } catch (IOException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); } catch (SAXException e) { // TODO Auto-generated
			 * catch block e.printStackTrace(); } catch (TikaException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }
			 */

			System.out.println(parsedText);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
