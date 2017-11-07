package longtq2.core.common.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

public class UploadUtil {
    public final int maxMemorySize = 1024 * 1024 * 3; //3MBl
    public final int maxRequestSize = 1024 * 1024 * 50; //50MB
    public void writeOrUpdateFile(HttpServletRequest request) throws FileUploadException ,Exception{
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if(!isMultipart){
            System.out.println("have not enctype multipart/form-data");
        }
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Set factory constraints
        factory.setSizeThreshold(maxMemorySize);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(maxRequestSize);

        try {
            // Parse the request
            List<FileItem> items = upload.parseRequest(request);
            for(FileItem item: items){
                if(!item.isFormField()){
                    String filename = item.getName();
                    File uploadedFile = new File("D:\\" + filename);
                    boolean isExit = uploadedFile.exists();
                    if(isExit){
                        uploadedFile.delete();
                        item.write(uploadedFile);
                    }else {
                        item.write(uploadedFile);
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
