package cn.ibbidream.controller;

import cn.ibbidream.utils.FastDFSClient;
import cn.ibbidream.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @author: ShiJia
 * @version: 1.0
 * @Company: http://www.ibbidream.cn
 * @date: 2017年12月23日
 */
@Controller
public class PictureController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @RequestMapping(value = "/pic/upload",produces = MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8")
    @ResponseBody
    public String fileUpload(MultipartFile uploadFile){
        try {
            //1、取文件的扩展名
            String originalFilename  = uploadFile.getOriginalFilename();
            String extName = originalFilename .substring(originalFilename .lastIndexOf(".") + 1);

            //2、把图片上传到图片服务器。使用封装的工具类实现。需要取文件的内容和扩展名。
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");

            //3、图片服务器返回图片的url
            String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);

            //4、将图片的url补充完整，返回一个完整的url。
            String url = IMAGE_SERVER_URL + path;

            //5、把返回结果封装到一个Map对象中返回。
            Map result = new HashMap();
            result.put("error",0);
            result.put("url",url);
            String json = JsonUtils.objectToJson(result);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            Map result = new HashMap();
            result.put("error",1);
            result.put("message","图片上传失败");
            String json = JsonUtils.objectToJson(result);
            return json;
        }
    }
}
