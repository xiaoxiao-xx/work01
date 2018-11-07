package com.wh.controller;import com.wh.util.FileUtils;
import com.wh.util.PoiUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
public class PIOController {
	@Resource
	private PoiUtil poi;
	@RequestMapping(value="/gouploadimg", method = RequestMethod.POST)
    public String goUploadImg() {
        //跳转到 templates 目录下的 uploadimg.html
        return "uploadimg";
    }
    //处理文件上传
    @RequestMapping(value="/importExcel", method = RequestMethod.POST)
    @ResponseBody
     public  String uploadImg(@RequestParam("file") MultipartFile file,
            HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        /*System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
        System.out.println("+++++++++++++++"+filePath);
        try {
            FileUtils.uploadFile(file.getBytes(), filePath, fileName);
          //  Business business = null;
			if(poi.isExistfileName(filePath+fileName)>0){
            	System.out.println("ssss");
            	poi.delFileName(filePath+fileName);
            	poi.loadExcel(filePath+fileName);  
     	        poi.init();  
     	        poi.show(filePath+fileName);  
     	       FileUtils.deleteDir(filePath);
           }else{
            	System.out.println("aaaa");
	        poi.loadExcel(filePath+fileName);  
	        poi.init();  
	        poi.show(filePath+fileName);  
	        FileUtils.deleteDir(filePath);
	    
           }
        } catch (Exception e) {
            // TODO: handle exception
        	e.printStackTrace();
        }
        //返回json
        return "successful";
    }
  
	}
	

