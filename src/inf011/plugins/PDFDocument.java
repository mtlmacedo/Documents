package inf011.plugins;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import inf011.interfaces.IDocument;
import inf011.ui.DocumentEditor;

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
			String pageText = "";
			if(this.document != null) {
				PDFTextStripper reader;
				reader = new PDFTextStripper();
				reader.setStartPage(page);
				reader.setEndPage(page);
				pageText = reader.getText(this.document);
			}
			return new DocumentEditor(pageText);
		} catch (IOException e) {
			e.printStackTrace();			
			return null;
		}
	}
}
