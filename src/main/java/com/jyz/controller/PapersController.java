package com.jyz.controller;


import com.jyz.comm.lang.Result;
import com.jyz.entity.Papers;
import com.jyz.service.PapersSrevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jocky
 * @since 2022-04-12
 */
@RestController
@RequestMapping("/papers")
public class PapersController {

    @Autowired
    PapersSrevice papersSrevice;

//  上传论文并将信息存储到数据库
    @PostMapping("/uploadpaper")
    public Result upload(@RequestParam MultipartFile file, String studentid, String teacherid) {

        Instant nowdata = Instant.now();

        String filename = nowdata.toEpochMilli()+file.getOriginalFilename();

//        本机
        String url = "C:\\论文存放\\"+studentid;

//        服务器
//          String url = "/home/论文存放/"+studentid;

        if (studentid == null){
           return Result.fail("请登录之后再操作");
        }else {
            if (teacherid==null){
                return Result.fail("请等待老师选择");
            }else {
                Papers papers = new Papers();
                papers.setStudentid(studentid);
                papers.setFilename(file.getOriginalFilename());
                papers.setFileurl(url+"\\"+filename);
//                papers.setFileurl(url+"/"+filename);
                papers.setTeacherid(teacherid);
                papers.setDate(Timestamp.from(nowdata).toLocalDateTime());
                boolean b = papersSrevice.save(papers);

                if (b){
                    try{
                        File filedir = new File(url);
                        if (!filedir.exists()){
                            filedir.mkdirs();
                        }
                        File file1 = new File(filedir,filename);
                        file.transferTo(file1);
                        return Result.succ("");
                    }catch (IOException e){
                        papersSrevice.removeById(papers.getPaperid());
                        return Result.fail("上传错误");
                    }

                }else {
                    return Result.fail("错误");
                }
            }
        }

    }

//    通过论文id下载论文
    @GetMapping("/downloadpaper")
    public void download(@RequestParam Integer paperid, HttpServletResponse response){
        Papers papers = papersSrevice.getById(paperid);
        String paperurl = papers.getFileurl();
        File file = new File(papers.getFileurl());
        if(!file.exists()){
            System.out.println("下载文件不存在");
        }

        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int)file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + papers.getFilename() );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            OutputStream os = response.getOutputStream();){

            byte[] buffer = new byte[1024];

            int len = 0;
            while ((len = bis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


//    通过学生id获取其全部论文信息
    @GetMapping("/getmypapers")
    public Result getMyPapers(@RequestParam String studentid){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("studentid",studentid);
        List<Papers> papersList = papersSrevice.listByMap(columnMap);
        if (papersList == null){
            return Result.succ("");
        }else {
            return Result.succ(papersList);
        }

    }

    @PostMapping("/updatapaper")
    public Result updataPaper(@RequestBody Papers papers){
            papersSrevice.updateById(papers);
            return Result.succ("成功");
    }

//    通过论文id删除论文（数据库数据以及文件）
    @PostMapping("/deletepaper")
    public Result deletePaper(@RequestBody Papers papers){
        File file = new File(papers.getFileurl());
        file.delete();
        papersSrevice.removeById(papers);
        return Result.succ("");
    }

}