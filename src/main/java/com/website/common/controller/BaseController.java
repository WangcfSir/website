package com.website.common.controller;

import com.website.common.warpper.BaseControllerWarpper;
import com.website.common.warpper.ResultMap;
import com.website.config.properties.WebsiteProperties;
import com.website.core.support.HttpKit;
import com.website.core.util.FileUtil;
import com.website.core.util.SpringContextHolder;
import com.website.system.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * SpringBoot方式启动类
 *
 * @author yanqb
 * @Date 2018-11-17 12:06
 */
public class BaseController {

    protected static final Integer PICTURE_TYPE_TOP = 1;
    protected static final Integer PICTURE_TYPE_DOWN = 2;
    protected static final Integer SHOW_STATE = 1;
    protected static final Integer MANAGEMENT_LEVEL = 2;

    protected static Integer SUCCESS_CODE = 0;
    protected static String SUCCESS_MSG = "处理成功";
    protected static String ERROR_MSG = "处理失败，请稍后重试或联系管理员";
    protected static Integer ERROR_CODE = 1;

    protected static String NO_POWER = "您的等级不够，或者无权操作";

    // 主要用于地址栏显示，相应页面跳转也会默认 （_href="main"）= （_href="manage/main"）
    protected static String REDIRECT = "redirect:";

    // 检测权限是否可操作
    protected static boolean isPower(){
        return getCurrentUser().getLevel() == 1;
    }

    /**
     * 获取layUI统一返回对象ResultMap
     */
    protected static ResultMap getResultMap(Integer code, String content) {
        if(code != 0){
            return new ResultMap<>(ERROR_CODE, ERROR_MSG, null, content);
        }
        return new ResultMap<>(SUCCESS_CODE, SUCCESS_MSG, null, content);
    }

    /**
     * 删除文件
     */
    protected static String deleteFile(String fileUrl) {
        File file = new File(getFileUploadPath() + fileUrl.replace("files/",""));
        file.delete();
        return null;
    }

    /**
     * 获取磁盘路径
     */
    protected static String getFileUploadPath() {
        return SpringContextHolder.getBean(WebsiteProperties.class).getFileUploadPath();
    }

    protected static HttpSession getSession() {
        return HttpKit.getRequest().getSession();
    }

    /**
     * 从session中获取当前登录对象（也可判断当前用户是否登录）
     */
    protected static User getCurrentUser() {
        HttpSession session = getSession();
        if(session != null && session.getAttribute("currentUser") != null){
            return (User)session.getAttribute("currentUser");
        }
        return null;
    }

    /**
     * 从session中获取移除当前登录对象（也可判断当前用户是否登录）
     */
    protected static void removeUser() {
        HttpSession session = getSession();
        if(session != null){
            getSession().setAttribute("currentUser",null);
        }
    }


    protected static HttpServletRequest getHttpServletRequest() {
        return HttpKit.getRequest();
    }

    protected HttpServletResponse getHttpServletResponse() {
        return HttpKit.getResponse();
    }

    protected HttpSession getSession(Boolean flag) {
        return HttpKit.getRequest().getSession(flag);
    }

    protected String getPara(String name) {
        return HttpKit.getRequest().getParameter(name);
    }

    protected void setAttr(String name, Object value) {
        HttpKit.getRequest().setAttribute(name, value);
    }

    protected Integer getSystemInvokCount() {
        return (Integer) this.getHttpServletRequest().getServletContext().getAttribute("systemCount");
    }


    /**
     * 包装一个list，让list增加额外属性
     */
    protected Object warpObject(BaseControllerWarpper warpper) {
        return warpper.warp();
    }

    /**
     * 返回前台文件流
     *
     * @author yanqb
     * @date 2017年2月28日 下午2:53:19
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, String filePath) {
        byte[] bytes = FileUtil.toByteArray(filePath);
        return renderFile(fileName, bytes);
    }

    /**
     * 返回前台文件流
     *
     * @author yanqb
     * @date 2017年2月28日 下午2:53:19
     */
    protected ResponseEntity<byte[]> renderFile(String fileName, byte[] fileBytes) {
        String dfileName = null;
        try {
            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED);
    }
}
