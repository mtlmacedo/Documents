package plugins;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.tools.PDFBox;

import interfaces.IDocument;
import ui.DocumentEditor;

public class PDFDocument implements IDocument {

	private PDDocument document;
	@Override
	public void open(File file) {		
		try {
			this.document = Loader.loadPDF(file);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public JFrame getEditor() {
		try {
			int page = 1;
			
			PDFTextStripper reader;
			reader = new PDFTextStripper();
			reader.setStartPage(page);
			reader.setEndPage(page);
			String pageText = reader.getText(this.document);
			
			return new DocumentEditor(pageText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}

}
