package com.jyz.controller;

import com.jyz.comm.lang.Result;
import com.jyz.entity.Admins;
import com.jyz.entity.Students;
import com.jyz.entity.Teachers;
import com.jyz.service.AdminsSrevice;
import com.jyz.service.StudentsSrevice;
import com.jyz.service.TeachersSrevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestController
public class LoginController {
    @Autowired
    AdminsSrevice adminsSrevice;
    @Autowired
    TeachersSrevice teachersSrevice;
    @Autowired
    StudentsSrevice studentsSrevice;


    @PostMapping("/login")
    public Result Login(@RequestBody Map<String, String> map, HttpServletResponse response) {
        String userid = map.get("userid");
        String password = map.get("password");
        String role = map.get("role");
        if (role.equals("管理员")) {
            Admins admins = adminsSrevice.getById(userid);
            if (admins == null) {
                return Result.fail("用户名或密码错误");
            } else if (admins.getPassword().equals(password)) {
                return Result.succ("");
            }else{
                return Result.fail("用户名或密码错误");
            }
        } else if (role.equals("教师")) {
            Teachers teachers = teachersSrevice.getById(userid);
            if (teachers == null) {
                return Result.fail("用户名或密码错误");
            }
            if (teachers.getPassword().equals(password)) {
                return Result.succ("");
            }else{
                return Result.fail("用户名或密码错误");
            }
        } else {
            Students students = studentsSrevice.getById(userid);
            if (students == null) {
                return Result.fail("用户名或密码错误");
            }
            if (students.getPassword().equals(password)) {
                return Result.succ("");
            }else{
                return Result.fail("用户名或密码错误");
            }
        }

    }

}
