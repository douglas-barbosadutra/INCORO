package it.contrader.servlets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.http.Part;

/*
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
*/
import it.contrader.dto.UsersDTO;

@MultipartConfig 

/**
 * Servlet implementation class ImageServlet
 */
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int MAX_MEMORY_SIZE = 0;
	private static final String DATA_DIRECTORY = null;
	private static final long MAX_REQUEST_SIZE = 0;
	private final static Logger LOGGER = Logger.getLogger(ImageServlet.class.getCanonicalName());
  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest x;
		
		final HttpSession session = request.getSession();
		final String scelta = request.getParameter("action");
		
		switch (scelta) {
		case "openInsert" : 
			response.sendRedirect("Image.jsp");
			break;
		case "process":
			Part filePart=request.getPart("corinne");
			InputStream fileContent = filePart.getInputStream(); 
			
			
					ByteArrayOutputStream buffer = new ByteArrayOutputStream();

					int nRead;
					

					while ((nRead = fileContent.read()) != -1) {
						
					  buffer.write( nRead);
					}

					byte[] content = buffer.toByteArray();
					response.setContentType("image/jpg");
					response.setContentLength(content.length);
					response.getOutputStream().write(content);
			
			response.sendRedirect("ImagesView.jsp");
			break;
		case "asd":
			// Create path components to save the file
		    final String path = new String ("c:\\webdata\\");
		    final Part fileParts = request.getPart("corinne");
		    final String fileName = getFileName(fileParts);

		    OutputStream out = null;
		    InputStream filecontent = null;
		    final PrintWriter writer = response.getWriter();

		    try {
		        out = new FileOutputStream(new File(path + File.separator
		                + fileName));
		        filecontent = fileParts.getInputStream();

		        int read = 0;
		        final byte[] bytes = new byte[1024];

		        while ((read = filecontent.read(bytes)) != -1) {
		            out.write(bytes, 0, read);
		        }
		        writer.println("New file " + fileName + " created at " + path);
		        LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", 
		                new Object[]{fileName, path});
		    } catch (FileNotFoundException fne) {
		        writer.println("You either did not specify a file to upload or are "
		                + "trying to upload a file to a protected or nonexistent "
		                + "location.");
		        writer.println("<br/> ERROR: " + fne.getMessage());

		        LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", 
		                new Object[]{fne.getMessage()});
		    } finally {
		        if (out != null) {
		            out.close();
		        }
		        if (filecontent != null) {
		            filecontent.close();
		        }
		        if (writer != null) {
		            writer.close();
		        }
		    }
		


		//MultipartFile file;
		 break; }
	}		



		
		
private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
        	String fn = content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        	int slashPos = fn.lastIndexOf( '\\' );
            if ( slashPos == -1 )
              slashPos = fn.lastIndexOf( '/' );
            return fn.substring( slashPos > 0 ? slashPos + 1 : 0 );
            //Paths.get(content).getFileName();
        }
    }
    return null;
}


}

 


