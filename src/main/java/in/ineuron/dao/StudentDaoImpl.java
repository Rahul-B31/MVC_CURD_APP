package in.ineuron.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.ineuron.dto.Student;
import in.ineuron.util.HibernateUtil;


public class StudentDaoImpl implements IStudentDao{
    private Session session = null;
    private Transaction transaction = null;
    private  String status = null;
    boolean flag = false;


	@Override
	public String save(Student student) {
		try {
			// Getting the session object
			session = HibernateUtil.getSession();

			if(session!=null)
				transaction = session.beginTransaction();

			if(transaction!=null)
			{
				session.save(student);
				flag = true;
			}


		} catch (HibernateException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(flag)
			{
				transaction.commit();
				status = "success";
			}
			else {
				transaction.rollback();
				status = "fail";
			}
		}

	return status;
	}

	@Override
	public Student findById(Integer sid) {
		Student student = new Student();
		try {
		   session = HibernateUtil.getSession();
		   student = session.get(Student.class, sid);
		   
		   if(student==null)
			   return student;
		   flag = true;

		} catch (HibernateException e) {
			e.printStackTrace();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(flag)
			{
				return student;
			}

		}
		return null;
//
//		PreparedStatement psmt = null;
//		ResultSet resultSet = null;
//		Connection connection = null;
//		Student student = null;
//		String selectSqlQuery = "select sname,sage,saddr from student1 where sid=?";
//
//		try {
//			connection = JdbcUtil.getJdbcConnection();
//			if(connection!=null)
//				psmt = connection.prepareStatement(selectSqlQuery);
//			if(psmt!=null)
//			{
//				psmt.setInt(1,sid);
//				resultSet =  psmt.executeQuery();
//			}
//			if(resultSet!=null)
//			{
//				if(resultSet.next())
//				{
//				    student = new Student();
//					student.setSid(sid);
//					student.setSname(resultSet.getString(1));
//					student.setSage(resultSet.getInt(2));
//					student.setSaddr(resultSet.getString(3));
//		 		}
//			}
//
//
//		} catch (SQLException | IOException e) {
//			e.printStackTrace();
//		}
//		return student;

	}

	@Override
	public String updateById(Student student) {
          String status = null;

		try {
			session = HibernateUtil.getSession();

			if(session!=null)
			transaction = session.beginTransaction();

			if(transaction!=null)
			{
				 session.merge(student);
				 flag=true;
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(flag)
			{
				transaction.commit();
				status = "success";

			}
			else {
				transaction.rollback();
				status = "fail";
			}
		}








//		Connection connection = null;
//        String upadtequery= "update student1 set sname=?,sage=?,saddr=? where sid=?";
//		PreparedStatement psmt=null;
//		String status =null;
//		try{
//			// Getting the logical connection
//			connection = JdbcUtil.getJdbcConnection();
//			if(connection!=null)
//				psmt = connection.prepareStatement(upadtequery);
//			if(psmt!=null)
//			{
//				psmt.setString(1,student.getSname());
//				psmt.setInt(2, student.getSage());
//				psmt.setString(3, student.getSaddr());
//				psmt.setInt(4,student.getSid());
//			}
//			if(psmt!=null)
//			{
//				 int affected =  psmt.executeUpdate();
//
//				 if(affected==1)
//					 status = "success";
//				 else
//					 status = "fali";
//
//
//			}
//
//		} catch (SQLException | IOException e) {
//
//			 e.printStackTrace();
//		}
  return status;


	}

	@Override
	public String deleteById(Integer sid) {
      Student student = null;
		try {

			session = HibernateUtil.getSession();

			if(session!=null)
				transaction = session.beginTransaction();
			    student =  session.get(Student.class,sid);
	
			if (transaction!=null) {

				session.delete(student);
				flag = true;
			}

		} catch (HibernateException e) {
			e.printStackTrace();
			flag = false;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (flag) {
				transaction.commit();
				status = "success";
			}
			else {
				transaction.rollback();
				status = "fail";
			}
		}



//		Connection connection = null;
//        String deleteQuery= "delete from student1 where sid=?";
//		PreparedStatement psmt=null;
//		String status =null;
//		try{
//			// Getting the logical connection
//			connection = JdbcUtil.getJdbcConnection();
//			if(connection!=null)
//				psmt = connection.prepareStatement(deleteQuery);
//			if(psmt!=null)
//			{
//			  psmt.setInt(1,sid);
//			}
//			if(psmt!=null)
//			{
//				 int affected =  psmt.executeUpdate();
//
//				 if(affected==1)
//					 status = "success";
//				 else
//					 status = "fali";
//
//
//			}
//
//		} catch (SQLException | IOException e) {
//
//			 e.printStackTrace();
//		}
	return status;

	}

}
