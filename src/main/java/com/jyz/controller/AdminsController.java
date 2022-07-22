package com.jyz.controller;


import com.jyz.comm.lang.Result;
import com.jyz.entity.Admins;
import com.jyz.entity.Teachers;
import com.jyz.service.AdminsSrevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/admins")
public class AdminsController {

    @Autowired
    AdminsSrevice adminsSrevice;


    @GetMapping("/getadmin")
    public Result getAdmin(@RequestParam String userid){
        Admins admins = adminsSrevice.getById(userid);
        return Result.succ(admins);
    }

    @PostMapping("/updataadminpassword")
    public Result updataTeacherPassword(@RequestBody Map<String, String> map) {
        Admins admins = new Admins();
        admins.setAdminid(map.get("userid"));
        admins.setPassword(map.get("password"));
        adminsSrevice.updateById(admins);
        return Result.succ("");
    }
}
