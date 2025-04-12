package controller;

import dao.HostelStudentDAO;
import model.HostelStudent;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.*;

@WebServlet("/hostel_students")
public class HostelStudentServlet extends HttpServlet {

    private HostelStudentDAO dao;
    private Gson gson;

    @Override
    public void init() {
        dao = new HostelStudentDAO();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");

        try {
            List<HostelStudent> students = dao.getAllStudents();
            Map<String, Object> response = new HashMap<>();
            response.put("students", students);
            res.getWriter().write(gson.toJson(response));
        } catch (Exception e) {
            res.setStatus(500);
            res.getWriter().write("{\"error\": \"Something went wrong: " + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");

        String action = req.getParameter("action");
        Map<String, String> response = new HashMap<>();

        try {
            switch (action) {
                case "register":
                   
                    HostelStudent newStudent = new HostelStudent(
                        req.getParameter("firstName"),
                        req.getParameter("lastName"),
                        req.getParameter("email"),
                        req.getParameter("mobileNumber"),
                        req.getParameter("course"),
                        req.getParameter("roomNumber"),
                        Double.parseDouble(req.getParameter("feesPaid"))
                    );

                   
                    boolean isSaved = dao.save(newStudent);
                    response.put("message", isSaved ? "Student registered successfully." : "Registration failed.");
                    break;

                case "update":
                    int updateId = Integer.parseInt(req.getParameter("id"));
                    HostelStudent existingStudent = dao.getStudent(updateId);

                    if (existingStudent != null) {
                        // Update details
                        existingStudent.setFirstName(req.getParameter("firstName"));
                        existingStudent.setLastName(req.getParameter("lastName"));
                        existingStudent.setEmail(req.getParameter("email"));
                        existingStudent.setMobileNumber(req.getParameter("mobileNumber"));
                        existingStudent.setCourse(req.getParameter("course"));
                        existingStudent.setRoomNumber(req.getParameter("roomNumber"));
                        existingStudent.setFeesPaid(Double.parseDouble(req.getParameter("feesPaid")));

                        boolean isUpdated = dao.updateStudent(existingStudent);
                        response.put("message", isUpdated ? "Student updated successfully." : "Update failed.");
                    } else {
                        response.put("error", "Student not found.");
                    }
                    break;

                case "delete":
                    int deleteId = Integer.parseInt(req.getParameter("id"));
                    boolean isDeleted = dao.deleteStudent(deleteId);
                    response.put("message", isDeleted ? "Student deleted successfully." : "Deletion failed.");
                    break;

                default:
                    response.put("error", "Unknown action.");
            }
        } catch (Exception e) {
            res.setStatus(500);
            response.put("error", "Server error: " + e.getMessage());
        }

        res.getWriter().write(gson.toJson(response));
    }
}