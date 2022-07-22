package com.jyz.controller;


import com.jyz.comm.lang.Result;
import com.jyz.entity.Students;
import com.jyz.entity.Teachers;
import com.jyz.service.StudentsSrevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jocky
 * @since 2022-04-08
 */
@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    StudentsSrevice studentsSrevice;

//    学生用户获取自己的个人信息
    @GetMapping("/getstudent")
    public Result getStudent(@RequestParam String userid){
        Students students = studentsSrevice.getById(userid);
        return Result.succ(students);
    }

//    获取全部学生列表
    @GetMapping("/getallstudents")
    public Result getAllStudents(){
        List<Students> studentsList = studentsSrevice.list();
        return Result.succ(studentsList);
    }

    //    通过学生id查找学生
    @GetMapping("/getstudentbyid")
    public Result getMyStudentsById(@RequestParam("studentid") String studentid){
        Students students = studentsSrevice.getById(studentid);
        return Result.succ(students);
    }


    //通过 老师id 获取 学生 列表
    @GetMapping("/getstudentsbyteacherid")
    public Result getMyStudentsByTeacherId(@RequestParam("userid") String teacherid){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("teacherid",teacherid);
        List<Students> studentsList = studentsSrevice.listByMap(columnMap);
        return Result.succ(studentsList);
    }

//    老师绑定学生
    @PostMapping("/bindstudent")
    public Result BindStudent(@RequestBody Students students){
        studentsSrevice.updateById(students);
        return  Result.succ("添加成功","");
    }

//    老师解绑学生
    @PostMapping("/unbindstudent")
    public Result unBindStudent(@RequestBody Students students){
        students.setTeacherid("");
        studentsSrevice.updateById(students);
        return  Result.succ("删除成功","");
    }

//    学生修改密码
    @PostMapping("/updatastudentpassword")
    public Result updataStudent(@RequestBody Map<String,String> map){
        Students students =  new Students();
        students.setStudentid(map.get("userid"));
        students.setPassword(map.get("password"));
        studentsSrevice.updateById(students);
        return Result.succ("");
    }

    @PostMapping("/updatastudent")
    public Result updataTeacher(@RequestBody Students students) {
        studentsSrevice.updateById(students);
        return Result.succ("修改成功", "");
    }

//    通过id删除学生
    @GetMapping("/deletestudentbyid")
    public Result deleteStudentById(@RequestParam("studentid") String studentid){
        boolean result = studentsSrevice.removeById(studentid);
        if (result){
            return Result.succ("删除成功","");
        }
        return Result.fail("删除失败");
    }


    //    添加学生账户
    @PostMapping("/addstudent")
    public Result addStudent(@RequestBody Students students){

        if (studentsSrevice.getById(students) == null){
            if (students.getPassword()==null||students.getPassword().equals("")){
                String password = "stu"+students.getStudentid();
                students.setPassword(password);
            }
            studentsSrevice.save(students);
            return Result.succ("添加成功","");
        }
        return Result.fail("已有此用户");
    }






}
