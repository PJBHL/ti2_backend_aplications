package dog;

import static spark.Spark.staticFiles;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import spark.Request;

public class UploadFile {
	
	public static String saveArq(Request req, String uploaded_file)throws IOException, ServletException{
	    
			File uploadDir = new File("upload");
	        uploadDir.mkdir();

        	Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");    
		        
		    req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
		
		    try (InputStream input = req.raw().getPart(uploaded_file).getInputStream()) { // getPart needs to use same "name" as input field in form
		        Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
		    }
		
		    logInfo(req, tempFile, uploaded_file);
		    return "" + tempFile.getFileName();

	}//end saveArq
	
	
   private static void logInfo(Request req, Path tempFile, String uploaded_file) throws IOException, ServletException {
        System.out.println("Uploaded file '" + getFileName(req.raw().getPart(uploaded_file)) + "' saved as '" + tempFile.toAbsolutePath() + "'");
    }
    
    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }//end getFile
}//end class
