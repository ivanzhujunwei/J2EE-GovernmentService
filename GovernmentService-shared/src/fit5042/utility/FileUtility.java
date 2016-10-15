/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5042.utility;

import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Ivan Zhu <ivanzhujunwei@gmail.com>
 */
public class FileUtility
{

    public final static String THUMBNAIL_DIRECTORY = "/Users/zjw/GitHub/J2EE-GovernmentService/GovernmentService-war/web/resources/images/serviceThumbnail/";
    // reference: www.youtube.com/watch?v=8KzoSfkMMXM
    /***
     * Get file name from servlet http 
     * @param part
     * @return file name
     */
    public static String getFileName(Part part)
    {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
    
    public static String getThumbnailPath(){
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("images");
        path = path.substring(0,path.indexOf("\\build"));
        path = path + "\\web\\resources\\images\\serviceThumbnail\\";
        System.out.println("pathhhhh"+path);
        return path;
    }

}
