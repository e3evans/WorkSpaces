package testing;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.microsoft.OfficeParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * Servlet implementation class TikaTest
 */
public class TikaTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TikaTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		InputStream input = WordParserTest.class.getResourceAsStream("/test-documents/testWORD.doc");
		PrintWriter out = response.getWriter();
//		InputStream is = new BufferedInputStream(new FileInputStream("C:/xxTestResumes/WHResign.doc"));
//		out.println(is.available());
//		
//		ContentHandler handler = new BodyContentHandler();
//		Metadata metadata = new Metadata();
		
		
		
		File dir = new File("C:/xxTestResumes");
		
		if (dir.isDirectory()){
			File [] allFiles = dir.listFiles();
			
			for (int i=0;i<allFiles.length;i++){
				if (allFiles[i].isFile()){
					InputStream is = new BufferedInputStream(new FileInputStream(allFiles[i]));
					String file_name = "c:/xxTestResumes/txt/"+allFiles[i].getName()+".txt";
	                FileWriter file = new FileWriter(file_name);
	                BufferedWriter fileout = new BufferedWriter (file);
					try{
						WordExtractor we = new WordExtractor(is);
						out.println(allFiles[i].getName());
						out.println("<hr>");
		                fileout.write(we.getText());
		                fileout.close();
					}catch(Exception e){
						e.printStackTrace();
					}finally{
						is.close();
						fileout.close();
					}
					
				}
			}
			
		}
		
		out.println(dir.isDirectory());
		
		
		
		
//			OfficeParser op = new OfficeParser();
//			op.parse(is, handler, metadata);
//			out.println(op.toString());
	

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
