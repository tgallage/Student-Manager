package code.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import code.challenge.dao.Student;
import code.challenge.util.Constant;

@Repository
public class StudentService {

	@Autowired
	private MongoTemplate mongoTemplate;

	

	public void addStudent(Student student) {
		if (!mongoTemplate.collectionExists(Student.class)) {
			mongoTemplate.createCollection(Student.class);
		}

		mongoTemplate.insert(student, Constant.STUDENT_COLLECTION);
	}

	public List<Student> listStudents() {
		return mongoTemplate.findAll(Student.class, Constant.STUDENT_COLLECTION);
	}

	public Student findStudent(Integer studentNumber) {
		DBCollection collection = mongoTemplate.getCollection(Constant.STUDENT_COLLECTION);
		DBObject query = new BasicDBObject();
		query.put(Constant.STUDENT_NUMBER, studentNumber);
		DBCursor studentCursor = collection.find(query);
		Student student = null;
		while (studentCursor.hasNext()) {
			DBObject obj = studentCursor.next();
			student = mongoTemplate.getConverter().read(Student.class, obj);
			break;
		}

		return student;
	}

	public void updateStudent(Student student) {
		DBCollection collection = mongoTemplate.getCollection(Constant.STUDENT_COLLECTION);
		DBObject query = new BasicDBObject();
		query.put(Constant.STUDENT_NUMBER, student.getStudentNumber());

		BasicDBObject newDocument = new BasicDBObject();
		newDocument.append(
				"$set",
				new BasicDBObject()
						.append(Constant.PREFFERD_NAME, student.getPreferredName())
						.append(Constant.GENDER, student.getGender())
						.append(Constant.BIRTH_DATE, student.getBirthDate())
						.append(Constant.DISABILITY, student.getDisability())
						.append(Constant.FIRST_NAME, student.getFirstName())
						.append(Constant.LAST_NAME, student.getLastName())
						.append(Constant.EMAIL, student.getEmail()));

		collection.update(query, newDocument);
	}

	public void deleteStudent(Integer studentNumber) {
		DBCollection collection = mongoTemplate.getCollection(Constant.STUDENT_COLLECTION);
		DBObject query = new BasicDBObject();
		query.put(Constant.STUDENT_NUMBER, studentNumber);
		collection.remove(query);
	}

}
