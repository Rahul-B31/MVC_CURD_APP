package in.ineuron.factory;

import in.ineuron.service.IStudentService;
import in.ineuron.service.StudentSericeImpl;

public class StudentServiceFactory {

	private static IStudentService studentService = null;
	private StudentServiceFactory()
	{

	}

public static IStudentService getIStudentService()
	{
		if(studentService==null)
		{
			studentService = new StudentSericeImpl();
		}
		return studentService;
	}



}
