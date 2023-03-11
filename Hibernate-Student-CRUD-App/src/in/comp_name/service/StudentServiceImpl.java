package in.comp_name.service;

import in.comp_name.dao.IStudentDao;
import in.comp_name.daofactory.StudentDaoFactory;
import in.comp_name.dto.Student;

//service layer logic
public class StudentServiceImpl implements IStudentService {

	private IStudentDao stdDao;

	@Override
	public String save(String sname, Integer sage, String saddress) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.save(sname, sage, saddress);
	}

	@Override
	public Student findById(Integer sid) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.findById(sid);
	}

	@Override
	public String updateById(Student student) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.updateById(student);
	}

	@Override
	public String deleteById(Integer sid) {
		stdDao = StudentDaoFactory.getStudentDao();
		return stdDao.deleteById(sid);
	}

}
