package com.website.system.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.website.common.controller.BaseController;
import com.website.common.warpper.ImageArticle;
import com.website.common.warpper.ResultMap;
import com.website.core.util.DateUtil;
import com.website.system.utils.AopTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 验证码生成
 *
 * @author fengshuonan
 * @date 2017-05-05 23:10
 */
@Controller
@RequestMapping("/kaptcha")
public class KaptchaController extends BaseController {

    @Autowired
    Producer producer;

    /**
     * 生成验证码
     */
    @AopTime
    @RequestMapping("")
    public void kaptcha(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        response.setDateHeader("Expires", 0);

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = producer.createText();

        // store the text in the session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = producer.createImage(capText);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // write the data out
        try {
            ImageIO.write(bi, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            try {
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 上传图片(网站新闻) layUI2.0上传
     */
    @RequestMapping(path = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public ResultMap fileUpload(@RequestParam("file") MultipartFile file) {
        String pictureName = DateUtil.getAllTime();
        try {
            String fileSavePath = getFileUploadPath() + "/image/" + pictureName + ".jpg";
            file.transferTo(new File(fileSavePath));
            return getResultMap(SUCCESS_CODE,pictureName);
        } catch (Exception e) {
            return getResultMap(ERROR_CODE,"图片上传失败，请稍后重试，或联系管理员");
        }
    }

    /**
     * 上传图片(文章)
     */
    @RequestMapping(path = "/imageArticleUpload", method = RequestMethod.POST)
    @ResponseBody
    public ResultMap imageArticleUpload(@RequestParam("file") MultipartFile file) {
        String pictureName = DateUtil.getAllTime();
        try {
            String fileSavePath = getFileUploadPath() + "/imageArticle/" + pictureName + ".jpg";
            file.transferTo(new File(fileSavePath));

            ResultMap resultMap = new ResultMap<>(SUCCESS_CODE, SUCCESS_MSG, null, new ImageArticle("/files/imageArticle/" + pictureName + ".jpg",""));
            return resultMap;
        } catch (Exception e) {
            return getResultMap(ERROR_CODE,"图片上传失败，请稍后重试，或联系管理员");
        }
    }

}
