package com.jyz.controller;


import com.jyz.comm.lang.Result;
import com.jyz.entity.Teachers;
import com.jyz.service.TeachersSrevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jocky
 * @since 2022-04-08
 */
@RestController
@RequestMapping("/teachers")
public class TeachersController {


    @Autowired
    TeachersSrevice teachersSrevice;

    //    老师获取自己的个人信息
    @GetMapping("/getteacher")
    public Result getTeacher(@RequestParam String userid) {
        Teachers teachers = teachersSrevice.getById(userid);
        return Result.succ(teachers);
    }

    //    更新老师信息
    @PostMapping("/updatateacher")
    public Result updataTeacher(@RequestBody Teachers teachers) {
        teachersSrevice.updateById(teachers);
        return Result.succ("修改成功", "");
    }

    //    更改老师用户密码
    @PostMapping("/updatateacherpassword")
    public Result updataTeacherPassword(@RequestBody Map<String, String> map) {
        Teachers teachers = new Teachers();
        teachers.setTeacherid(map.get("userid"));
        teachers.setPassword(map.get("password"));
        teachersSrevice.updateById(teachers);
        return Result.succ("");
    }

    //    获取全部教师列表
    @GetMapping("/getallteachers")
    public Result getAllTeachers() {
        List<Teachers> teachersList = teachersSrevice.list();
        return Result.succ(teachersList);
    }

    //    通过id查找教师
    @GetMapping("/getteacherbyid")
    public Result getMyStudentsById(@RequestParam("teacherid") String teacherid) {
        Teachers teachers = teachersSrevice.getById(teacherid);
        return Result.succ(teachers);
    }

    //    通过id删除教师账户
    @GetMapping("/deleteteacherbyid")
    public Result deleteTeacherById(@RequestParam("teacherid") String teacherid) {
        boolean result = teachersSrevice.removeById(teacherid);
        if (result) {
            return Result.succ("删除成功", "");
        }
        return Result.fail("删除失败");
    }

    //    添加教师账户
    @PostMapping("/addteacher")
    public Result addTeacher(@RequestBody Teachers teachers) {

        if (teachersSrevice.getById(teachers) == null) {
            if (teachers.getPassword()==null||teachers.getPassword().equals("")) {
                String password = "tc"+teachers.getTeacherid();
                teachers.setPassword(password);
            }
            teachersSrevice.save(teachers);
            return Result.succ("添加成功", "");
        }
        return Result.fail("已有此用户");
    }


}
