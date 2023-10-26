package in.ineuron.service;

import in.ineuron.dao.IStudentDao;
import in.ineuron.dto.Student;
import in.ineuron.factory.StudentDaoFactory;

public class StudentSericeImpl implements IStudentService {

	@Override
	public String save(Student student) {
		IStudentDao studentDao = StudentDaoFactory.getStudentDao();
		 return studentDao.save(student);
	}

	@Override
	public Student findById(Integer sid) {
		IStudentDao studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.findById(sid);
	}

	@Override
	public String updateById(Student student) {
	 IStudentDao studentDao = StudentDaoFactory.getStudentDao();
	 return studentDao.updateById(student);
	}

	@Override
	public String deleteById(Integer sid) {

		 IStudentDao studentDao = StudentDaoFactory.getStudentDao();
		 return studentDao.deleteById(sid);

	}

}
