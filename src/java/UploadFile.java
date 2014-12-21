/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
//import org.apache.tomcat.util.http.fileupload.FileItem;

public class UploadFile extends HttpServlet {

    String SaveFile = "E:/upload/";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            boolean ismultipart =ServletFileUpload.isMultipartContent(request);
            if(!ismultipart){
                
            }
            else{
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                
                try{
                    items = upload.parseRequest(request);
                    
                }catch(Exception e){
                    
                }
                Iterator itr = items.iterator();
                while(itr.hasNext()){
                    FileItem item = (FileItem) itr.next();
                    
                    if(item.isFormField()){
                        
                    }
                    else{
                        String itemname = item.getName();
                        if((itemname == null) || itemname.equals("")){
                            continue;
                        }
                        String filename = FilenameUtils.getName(itemname);
                        File f = checkExist(filename);
                        item.write(f);
                        
                    }
                }
                
            }
          
        }catch(Exception e){
            
        }
        finally{
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private File checkExist(String filename) {
        File f = new File(SaveFile+"/"+filename);
        
        if(f.exists()){
            StringBuffer sb = new StringBuffer(filename);
            sb.insert(sb.lastIndexOf("."),"-"+new Date().getTime());
            f = new File(SaveFile+"/"+sb.toString());
        }
        return f;   
        
    } 


}
