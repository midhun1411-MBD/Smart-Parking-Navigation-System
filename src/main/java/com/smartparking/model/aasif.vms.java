package com.vms;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class VisitorServlet extends HttpServlet {

    // File path to save visitor records
    private static final String FILE_PATH = "C:\\visitor_records\\visitors_data.txt";
    // 🔹 Change this path to any folder on your system where you want data saved

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form inputs
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
        String purpose = request.getParameter("purpose");
        String time = request.getParameter("time");

        // Set response type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Input validation
        if (name == null || name.isEmpty() ||
            contact == null || contact.isEmpty() ||
            purpose == null || purpose.isEmpty() ||
            time == null || time.isEmpty()) {

            out.println("<html><body style='text-align:center; font-family:Segoe UI;'>");
            out.println("<h2 style='color:red;'>⚠ All fields are required!</h2>");
            out.println("<a href='index.html'>← Go Back</a>");
            out.println("</body></html>");
            return;
        }

        // Save visitor details into file
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // create directory if not exists

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(name + "," + contact + "," + purpose + "," + time);
                writer.newLine();
            }

            // Display success message
            out.println("<html><body style='font-family:Segoe UI; background:#e3f2fd; text-align:center;'>");
            out.println("<div style='background:white; display:inline-block; padding:30px; margin-top:80px; border-radius:10px; box-shadow:0 4px 12px rgba(0,0,0,0.2);'>");
            out.println("<h2 style='color:#1565c0;'>✅ Visitor Registered Successfully!</h2>");
            out.println("<p><b>Name:</b> " + name + "</p>");
            out.println("<p><b>Contact:</b> " + contact + "</p>");
            out.println("<p><b>Purpose:</b> " + purpose + "</p>");
            out.println("<p><b>Entry Time:</b> " + time + "</p>");
            out.println("<a href='index.html' style='color:#1565c0; font-weight:bold;'>← Back to Home</a>");
            out.println("</div></body></html>");

        } catch (IOException e) {
            out.println("<html><body style='font-family:Segoe UI; text-align:center;'>");
            out.println("<h3 style='color:red;'>❌ Error saving visitor data: " + e.getMessage() + "</h3>");
            out.println("</body></html>");
        }
    }
}