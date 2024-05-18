package com.citi.erick.service;

import com.citi.erick.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class StudentService {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 类名也会存进去
     * @param student
     * @return
     */
    public Student insertOne(Student student) {
        student.setCreateTime(new Date());
        return mongoTemplate.insert(student);
    }

    public List<Student> queryAll() {
        List<Student> all = mongoTemplate.findAll(Student.class);
        return all;
    }

    public List<Student> queryByStatus(boolean status) {
        Query query = new Query(Criteria.where("status").is(status));
        List<Student> students = mongoTemplate.find(query, Student.class);
        return students;
    }

    public void updateStatus(String id, boolean status) {
        Query query = new Query(Criteria.where("_id").is(id));
        UpdateDefinition update = new Update().set("status", status).set("updateTime", new Date());

        mongoTemplate.updateMulti(query, update, Student.class);
    }

}
