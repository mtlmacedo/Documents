package inf011.interfaces;
import java.io.File;

import javax.swing.JFrame;

public interface IDocument {
//	open() e getEditor(). O getEditor() deve retornar um JFrame contendo a visualização do documento.
	
	public void  open(File file);
	public JFrame getEditor();
}
