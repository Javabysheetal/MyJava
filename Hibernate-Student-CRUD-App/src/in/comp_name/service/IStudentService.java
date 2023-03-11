package in.comp_name.service;

import in.comp_name.dto.Student;

public interface IStudentService {

	// operations to be implemented
	public String save(String sname, Integer sage, String saddress);

	public Student findById(Integer sid);

	public String updateById(Student student);

	public String deleteById(Integer sid);

}
