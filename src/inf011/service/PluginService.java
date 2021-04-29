package inf011.service;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import inf011.interfaces.IDocument;

public class PluginService {
	
	private final String pluginPath = "inf011.plugins.";
	private final String dataPath = "data/Plugins.xml";
	private DocumentBuilder builder;
	private Document document;
	private Map<String, String> plugins;
	
	public PluginService() {
		try {
			this.plugins = new HashMap<String, String>();
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    this.builder = dbFactory.newDocumentBuilder();
		    this.document = builder.parse(dataPath);
		
		    NodeList nodeList = document.getElementsByTagName("plugin");
		     
		 	for (int itr = 0; itr < nodeList.getLength(); itr++)   
			{  
		 		Node node = nodeList.item(itr);   
				if (node.getNodeType() == Node.ELEMENT_NODE)   
				{  
					Element eElement = (Element) node; 
					String extension = eElement.getElementsByTagName("extension").item(0).getTextContent();
					String documentName = eElement.getElementsByTagName("pluginName").item(0).getTextContent();			
					this.plugins.put(extension, documentName);				
				}
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}	
	
	public IDocument getDocumentByExtension(String extension) {
		try {
			String documentName = this.plugins.get(extension);
		    if(documentName == null || documentName.isEmpty()) {  		    
				throw new Exception("Não existe plugin que suporte este arquivo");
		    }else {    		    	
		    	
		    	//URL u = (new File(this.pluginPath + documentName + ".jar")).toURL();
		    	//URL[] pluginsURLs = { new URL("src.plugins") };
		    	
				//URLClassLoader URLLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();//new URLClassLoader(pluginsURLs);
				Class<?> docClass = Class.forName(this.pluginPath + documentName); //URLLoader.loadClass("PDFDocument");
				IDocument doc = (IDocument) docClass.newInstance();
				//URLLoader.close();
				return doc;
				//URLClassLoader classLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
//				Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
//				method.setAccessible(true);
//				method.invoke(classLoader, url);
//								
//				Class sysclass = URLClassLoader.class;
//				
//		        Method method = sysclass.getDeclaredMethod("addURL", parameters);
//		        method.setAccessible(true);
//		        method.invoke(sysloader, new Object[]{u});
//		        sysloader.loadClass(u.getClass());
//		        Class.forName("myplugin.MyPlugin").newInstance();
//				
//				
//				
//		    	Class<?> documentClass = Class.forName(this.pluginPath + documentName);
//		    	
//		    	List<Method> methods = Arrays.asList(documentClass.getDeclaredMethods());
//		    	Method getInstanceMethod = methods.stream()
//		    			  .filter(method -> method.getName().equals("getInstance"))
//		    			  .findAny()
//		    			  .orElse(null);	    	
//				 
//		    	IDocument document =  (IDocument) getInstanceMethod.invoke(null);
//		    	return document;
		    }
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String[] getValidExtension() {
		List<String> str = new ArrayList<String>();
		for(Map.Entry<String,String> map : this.plugins.entrySet()){
		     str.add(map.getKey());
		}
		return str.toArray(new String[0]);
	}
}
