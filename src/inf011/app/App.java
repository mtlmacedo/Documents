package inf011.app;
import inf011.ui.MainFrame;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;

import inf011.interfaces.IDocument;

public class App {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new MainFrame();
					frame.setVisible(true);
//					IDocument document = new PDFDocument();
//					document.open(new File("C:/Users/mathe/Desktop/Estagio/EstagioDocs/TERMO ADITIVO - Matheus Lucas da Silva Macedo.pdf"));
//					JFrame frame = document.getEditor();
//					frame.setVisible(true);
//					DocumentEditor frame = new DocumentEditor();
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
