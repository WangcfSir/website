package com.website.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.website.common.controller.BaseController;
import com.website.common.warpper.ResultMap;
import com.website.system.model.Download;
import com.website.system.service.DownloadService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 下载中心 前端控制器
 *
 * @author yanqb
 * @since 2018-12-04
 */
@Controller
@RequestMapping("manage/download")
public class DownloadController extends BaseController {
    
    @Resource
    private DownloadService downloadService;

    /**
     * 跳转到 后台-->下载中心管理
     */
    @RequestMapping("")
    public String download() {
        return "manage/downloadList";
    }
    
    /**
     * 加载数据 后台-->下载中心管理
     */
    @ApiOperation("下载中心JSON数据")
    @ResponseBody
    @RequestMapping("/downloadTable")
    public ResultMap downloadTable(@RequestParam Integer page, @RequestParam Integer limit, @RequestParam(required =false) String content) {
        ResultMap<List<Download>> resultMap = new ResultMap<>(SUCCESS_CODE, SUCCESS_MSG, null, null);
        Page<Download> pageInfo = new Page<>(page,limit);
        Wrapper<Download> wrapper = new EntityWrapper<>();
        wrapper = content == null ? wrapper : wrapper.like("file_name", content);
        List<Download> downloadList = downloadService.selectPage(pageInfo, wrapper.orderBy("upload_time", false)).getRecords();
        resultMap.setCount(downloadService.selectCount(wrapper));
        resultMap.setData(downloadList);
        return resultMap;
    }

    /**
     * 提交 下载中心---> 文件添加
     */
    @ResponseBody
    @RequestMapping(path = "/downloadAdd", method = RequestMethod.POST)
    public ResultMap downloadAdd(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();

            // 获取所有文件，判断此文件有没有保存过
            List<Download> fileList = downloadService.selectList(new EntityWrapper<>());
            for(Download download:fileList){
                if(fileName.equals(download.getFileName())){
                    return getResultMap(SUCCESS_CODE,"该文件已经上传到服务器，无需重新上传");
                }
            }
            String fileSavePath = getFileUploadPath() + "doc/" + fileName;
            file.transferTo(new File(fileSavePath));
            Download download = new Download();
            download.setFileName(fileName);
            download.setFileUrl("files/doc/" + fileName);
            download.setUploadTime(new Date());
            download.setUploadUser(Objects.requireNonNull(getCurrentUser()).getName());
            downloadService.insert(download);
            return getResultMap(SUCCESS_CODE, SUCCESS_MSG);
        }catch (Exception e){
            return getResultMap(ERROR_CODE, ERROR_MSG);
        }
    }

    /**
     * 跳转到 下载中心---> 修改
     */
    @RequestMapping("/toDownloadUpdate")
    public String toDownloadUpdate() {
        return "manage/downloadUpdate";
    }

    /**
     * 提交 下载中心--->文件排序
     */
    @ResponseBody
    @RequestMapping(value = "/downloadUpdate",method = RequestMethod.POST)
    public String downloadUpdate(@ModelAttribute Download download)  {
        try {
//            download.setDownloadId(download.getDownloadUrl());
//            download.setDownloadUrl("/image/" + download.getDownloadUrl() + ".jpg");
//            download.setModifyTime(new Date());
//            downloadService.updateById(download);   // 更新
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }

    /**
     * 修改状态（下载中心--->列表操作）
     */
    @ResponseBody
    @RequestMapping(value = "/updateDownloadState",method = RequestMethod.POST)
    public String updateDownloadState(@Param("id") Integer id, @Param("stateValue") Integer stateValue) {
        try{
            downloadService.updateStateById(id, stateValue);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }

    /**
     * 删除（下载中心--->列表操作）
     */
    @ResponseBody
    @RequestMapping(value = "/deleteDownload",method = RequestMethod.POST)
    public String deleteDownload(@Param("id") Integer id) {
        try{
            String downloadUrl = downloadService.selectById(id).getFileUrl();
            deleteFile(downloadUrl);
            downloadService.deleteById(id);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }
}
