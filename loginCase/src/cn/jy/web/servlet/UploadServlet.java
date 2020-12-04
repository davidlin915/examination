package cn.jy.web.servlet;

import cn.jy.domain.User;
import cn.jy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;

import java.security.SecureRandom;

@MultipartConfig
@WebServlet("/uploadServlet")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //receive file and save on disk
        Part part = request.getPart("file");
        InputStream is = part.getInputStream();
        String filePath = "D:\\temp\\";
        String fileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + ".jpg";
        File file1 = new File(filePath + fileName);
        FileOutputStream os = new FileOutputStream(file1);
        byte[] bb = new byte[1024];
        int ch;
        while ((ch = is.read(bb)) > -1) {
            os.write(bb, 0, ch);
        }
        os.close();
        is.close();

        //upload to Aliyun.com cloud.
        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "LTAI4GKL3aAmpNwwfs7KCRYe";
        String accessKeySecret = "C3TzGhEmf4O8aqZwnj538ZHK5mS4mi";
        String bucketName = "examination6";

        File file = new File(filePath + fileName);

        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("image/jpg");

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        ossClient.putObject(bucketName, fileName, file, meta);

        ossClient.shutdown();

        //get file URL uploaded and save to database
        String uploadUrl = "https://" + bucketName + "." + endpoint + "/" + fileName;

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        String newUrl;
        if (user.getUrl_address() != null) {
            newUrl = user.getUrl_address() + "," + uploadUrl;
        } else {
            newUrl = uploadUrl;
        }

        user.setUrl_address(newUrl);
        UserServiceImpl userService = new UserServiceImpl();
        userService.updateUser(user);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
