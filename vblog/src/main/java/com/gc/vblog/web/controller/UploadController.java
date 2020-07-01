package com.gc.vblog.web.controller;

import com.gc.vblog.commons.Result;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 上传文件
 * Create by gc on 2020/7/1
 * 铁甲依然在
 */
@RestController
public class UploadController {

    @RequestMapping("upload")
    public Result upload(MultipartFile file){
        try {
            if(file == null || file.getSize()<=0){
                return Result.fail("无效文件！");
            }
            //String basepath = request.getServletContext().getRealPath("/");  //该代码无法获取项目的运行目录

            //获取文件的后缀名,生成随机文件名
            int index = file.getOriginalFilename().lastIndexOf(".");
            if(index==-1){
                return Result.fail("文件格式不规范！");
            }
            String suffix = file.getOriginalFilename().substring(index);
            String filename = UUID.randomUUID()+suffix;
            /*
            ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath()
            在开发测试模式时，得到的地址为：{项目跟目录}/target/classes,
            在打包成jar正式发布时，得到的地址为：{发布jar包目录}/
            */
            //通过new File(父路径，子路径)拼接目录路径，upload是用于存储上传的文件的目录，没有的话下面会创建
            File basefile = new File(ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath(),"upload");
            if(!basefile.exists()) basefile.mkdirs();  //如果文件目录不存在，就创建
            String basepath = basefile.getAbsolutePath(); //获取路径的字符串

            //保存文件
            File savefile = new File(basepath+"/"+filename);
            file.transferTo(savefile);

            //响应客户端文件访问的url，文件已经保存在classes目录（也就是项目根目录下）的upload目录里，
            // 该目录客户端还无法访问，我们可以将该目录指定为静态资源目录，客户端就可以直接访问了
            // 在application.propertis中配置spring.resources.static-locations=classpath:/static/,classpath:/upload/
            // 由于静态资源目录的资源可以直接访问，所以只需要响应文件名即可
            return Result.success(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.fail();
    }
}
