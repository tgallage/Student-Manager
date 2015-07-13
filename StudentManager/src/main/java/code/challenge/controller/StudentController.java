package code.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import code.challenge.dao.Student;
import code.challenge.response.StudentResponse;
import code.challenge.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "student";
	}

	@RequestMapping(value = "/studentDetails/{studentId}", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody StudentResponse getStudent(
			@PathVariable String studentId) {

		Student student = studentService.findStudent(Integer
				.parseInt(studentId));
		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setStatus(1);
		studentResponse.setMessage("success");
		studentResponse.setResult(student);
		return studentResponse;
	}

	@RequestMapping(value = "/listStudents", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody StudentResponse listStudents() {

		List<Student> students = studentService.listStudents();
		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setStatus(1);
		studentResponse.setMessage("success");
		studentResponse.setResult(students);
		return studentResponse;
	}

	@RequestMapping(value = "/addStudent", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" })
	public @ResponseBody StudentResponse addStudent(@RequestBody Student student) {

		studentService.addStudent(student);
		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setStatus(1);
		studentResponse.setMessage("success");

		return studentResponse;
	}

	@RequestMapping(value = "/editStudent", method = RequestMethod.POST, produces = {
			"application/xml", "application/json" })
	public @ResponseBody StudentResponse editStudent(
			@RequestBody Student student) {

		studentService.updateStudent(student);
		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setStatus(1);
		studentResponse.setMessage("success");

		return studentResponse;
	}

	@RequestMapping(value = "/deleteStudent/{studentId}", method = RequestMethod.GET, produces = {
			"application/xml", "application/json" })
	public @ResponseBody StudentResponse deleteStudent(
			@PathVariable String studentId) {

		studentService.deleteStudent(Integer.parseInt(studentId));
		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setStatus(1);
		studentResponse.setMessage("success");
		studentResponse.setMessage(studentId);

		return studentResponse;
	}

}