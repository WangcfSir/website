package com.website.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

import static com.website.core.util.ToolUtil.isEmpty;

/**
 * website项目配置
 *
 * @author yanqb
 * @Date 2018-11-17 22:31
 */
@Component
@ConfigurationProperties(prefix = WebsiteProperties.PREFIX)
public class WebsiteProperties {

    static final String PREFIX = "website";

    private Boolean kaptchaOpen = false;

    private Boolean swaggerOpen = false;

    private String fileUploadPath;

    private Boolean haveCreatePath = false;     // 这个属性作用：相当于第一次需要在磁盘中创建目录；该项目写死创建image和doc

    private Boolean springSessionOpen = false;

    private Integer sessionInvalidateTime = 5 * 60;  //session 失效时间（默认为30分钟 单位：秒）

    private Integer sessionValidationInterval = 5 * 60;  //session 验证失效时间（默认为15分钟 单位：秒）

    public String getFileUploadPath() {
        //判断目录存不存在,不存在得加上
        if (!haveCreatePath && !isEmpty(fileUploadPath)) {
            File files = new File(fileUploadPath);
            files.mkdirs();
            new File(fileUploadPath + "/doc").mkdirs();
            new File(fileUploadPath + "/image").mkdirs();
            new File(fileUploadPath + "/imageArticle").mkdirs();
            haveCreatePath = true;
        }
        return fileUploadPath;
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    public Boolean getKaptchaOpen() {
        return kaptchaOpen;
    }

    public void setKaptchaOpen(Boolean kaptchaOpen) {
        this.kaptchaOpen = kaptchaOpen;
    }

    public Boolean getSwaggerOpen() {
        return swaggerOpen;
    }

    public void setSwaggerOpen(Boolean swaggerOpen) {
        this.swaggerOpen = swaggerOpen;
    }

    public Boolean getSpringSessionOpen() {
        return springSessionOpen;
    }

    public void setSpringSessionOpen(Boolean springSessionOpen) {
        this.springSessionOpen = springSessionOpen;
    }

    public Integer getSessionInvalidateTime() {
        return sessionInvalidateTime;
    }

    public void setSessionInvalidateTime(Integer sessionInvalidateTime) {
        this.sessionInvalidateTime = sessionInvalidateTime;
    }

    public Integer getSessionValidationInterval() {
        return sessionValidationInterval;
    }

    public void setSessionValidationInterval(Integer sessionValidationInterval) {
        this.sessionValidationInterval = sessionValidationInterval;
    }
}
