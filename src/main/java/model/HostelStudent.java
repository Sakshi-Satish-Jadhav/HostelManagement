package model;

import jakarta.persistence.*;

@Entity
@Table(name = "hostel_students")
public class HostelStudent {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="student_id")
    private int studentId;

    @Column(name="first_name",nullable=false)
    private String firstName;

    @Column(name="last_name",nullable=false)
    private String lastName;

    @Column(name="email",nullable=false,unique=true)
    private String email;

    @Column(name="mobile_number",nullable=false,unique = true)
    private String mobileNumber;

    @Column(name="course",nullable=false)
    private String course;

    @Column(name="room_number",nullable=false,unique=true)
    private String roomNumber;

    @Column(name="fees_paid",nullable=false)
    private double feesPaid;

    // Default constructor
    public HostelStudent() {}

    // Parameterized constructor
    public HostelStudent(String firstName, String lastName, String email, String mobileNumber, String course, String roomNumber, double feesPaid) {
        this.firstName =firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.course = course;
        this.roomNumber = roomNumber;
        this.feesPaid = feesPaid;
    }

    // Getters and Setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public double getFeesPaid() { return feesPaid; }
    public void setFeesPaid(double feesPaid) { this.feesPaid = feesPaid; }
}
