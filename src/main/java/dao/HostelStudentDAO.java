package dao;

import model.HostelStudent;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HostelStudentDAO {

    // Save a new hostel student
    public boolean save(HostelStudent student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(student); // save student
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<HostelStudent> getAllStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<HostelStudent> query = session.createQuery("FROM HostelStudent", HostelStudent.class);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching hostel students: " + e.getMessage());
        }
    }

    public HostelStudent getStudent(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(HostelStudent.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching hostel student: " + e.getMessage());
        }
    }

    public boolean updateStudent(HostelStudent student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(student); 
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error updating hostel student: "+e.getMessage());
        }
    }

    public boolean deleteStudent(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            HostelStudent student = session.get(HostelStudent.class, id);
            if (student != null) {
                session.remove(student);
                session.getTransaction().commit();
                return true;
            } else {
                session.getTransaction().rollback(); 
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error deleting hostel student: " + e.getMessage());
        }
    }
}