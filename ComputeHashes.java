ange this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;
/**
 *
 * @author shayguo
 */
@WebServlet(urlPatterns = {"/ComputeHashes"})
public class ComputeHashes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        
        response.setContentType("text/html;charset=UTF-8");
        String search = request.getParameter("searchWord");
        String function = request.getParameter("function");
        
      if (search != null) {
        if(function.equals("MD5")) {
            MessageDigest m = null;
//          GET A MESSAGEDIGEST OBJECT OF MD5 AND GET A STRING OF ENCODED MD5 HASH VALUE
            try {
                m = MessageDigest.getInstance("MD5");
                m.update(search.getBytes());
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
            }
            BASE64Encoder MD5Encoder = new BASE64Encoder();
            String base64MD5 = MD5Encoder.encode(m.digest());
//          GET A HEXADECIMAL STRING OF ENCODED MD5 HASH VALUE
            String haxMD5 = null;
            try {
                m = MessageDigest.getInstance("MD5");
                m.update(search.getBytes());
                haxMD5 = getHexString(m.digest());
            } catch (Exception ex) {
                Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Search Word is " + search + "</h1>");
            out.println("<h1>Function is " + function + "</h1>");
            out.println("<h1>Base64: " + base64MD5 + "</h1>");
            out.println("<h1>Hexadecimal: " + haxMD5 + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
            
        } else if (function.equals("SHA-1")) {
            MessageDigest s = null;
        //  GET A MESSAGEDIGEST OBJECT OF SHA-1 AND GET A STRING OF ENCODED SHA-1 HASH VALUE
            try {
                s = MessageDigest.getInstance("SHA-1");
                s.update(search.getBytes());
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            BASE64Encoder SHA1Encoder = new BASE64Encoder();
            String base64SHA1 = SHA1Encoder.encode(s.digest());
            
            //  GET A HEXADECIMAL STRING OF ENCODED SHA-1 HASH VALUE
            String haxSHA1 = null;
            try {
                s = MessageDigest.getInstance("SHA-1");
                s.update(search.getBytes());
                haxSHA1 = getHexString(s.digest());
            } catch (Exception ex) {
                Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Search Word is " + search + "</h1>");
            out.println("<h1>Function is " + function + "</h1>");
            out.println("<h1>Base64: " + base64SHA1 + "</h1>");
            out.println("<h1>Hexadecimal: " + haxSHA1 + "</h1>");
            out.println("</body>");
            out.println("</html>");
            }
        }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public String getHexString(byte[] b) throws Exception {
            String result = "";
            for (int i=0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring( 1 );
            }
            return result;
    }

}

