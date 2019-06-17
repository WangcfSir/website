package com.website.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.website.common.controller.BaseController;
import com.website.common.exception.BizExceptionEnum;
import com.website.common.warpper.ResultMap;
import com.website.system.model.User;
import com.website.system.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 系统管理员控制器
 *
 * @author yanqb
 * @since 2018-11-18 下午1:08:17
 */
@Controller
@RequestMapping("manage/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 跳转到 后台-->管理员列表
     */
    @RequestMapping("")
    public String userList() {
        if(!isPower()){
            return "/no_power";
        }
        return "manage/user";
    }

    /**
     * 加载数据 后台-->管理员列表
     */
    @ResponseBody
    @RequestMapping("/userTable")
    public ResultMap userTable(@RequestParam Integer page, @RequestParam Integer limit,@RequestParam(required =false) String content) {
        ResultMap<List<User>> resultMap = new ResultMap<>(SUCCESS_CODE, SUCCESS_MSG, null, null);
        Page<User> pageInfo = new Page<>(page,limit);
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper =  wrapper.eq("level",MANAGEMENT_LEVEL);
        if(content != null && !content.equals("")){
            wrapper =  wrapper.eq("level", MANAGEMENT_LEVEL)
                    .and().like("account", content).or().like("name", content).or().like("phone", content);
        }
        List<User> userList = userService.selectPage(pageInfo, wrapper.orderBy("create_time", false)).getRecords();
        resultMap.setCount(userService.selectCount(wrapper));
        resultMap.setData(userList);
        return resultMap;
    }

    /**
     * 跳转到 后台-->管理员管理--> 添加
     */
    @RequestMapping("/toUserAdd")
    public String toUserAdd() {
        if(!isPower()){
            return "/no_power";
        }
        return "manage/userAdd";
    }

    /**
     * 提交 管理员添加
     */
    @ResponseBody
    @RequestMapping(value = "/userAdd",method = RequestMethod.POST)
    public String userAdd(@ModelAttribute User user) {
        if(!isPower()){ return "/no_power"; }
        try {
            String userIds = userService.getByAccount(user.getAccount(),user.getPhone());
            if (userIds != null) {
                return BizExceptionEnum.USER_ALREADY_REG.getMessage();
            }
            user.setState(SHOW_STATE);
            user.setLevel(MANAGEMENT_LEVEL);
            user.setCreateTime(new Date());
            userService.insert(user);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }

    /**
     * 修改状态（后台-->管理员管理-->列表操作）
     */
    @ResponseBody
    @RequestMapping(value = "/updateUserState",method = RequestMethod.POST)
    public String updateUserState(@Param("id") Integer id, @Param("stateValue") Integer stateValue) {
        try{
            if(!isPower()){ return "/no_power"; }
            userService.updateStateById(id, stateValue);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }

    /**
     * 删除用户（后台-->管理员管理-->列表操作）
     */
    @ResponseBody
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public String deleteUser(@Param("id") Integer id) {
        try{
            if(!isPower()){ return "/no_power"; }
            userService.deleteById(id);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }

    /**
     * 跳转到 后台-->个人中心
     */
    @RequestMapping("/userCenter")
    public String userCenter(Model model) {
        model.addAttribute("currentUser",getCurrentUser());
        return "manage/userCenter";
    }

    /**
     * 跳转到 后台-->个人中心--> 修改资料
     */
    @RequestMapping("/toUserUpdate")
    public String toUserUpdate(Model model) {
        model.addAttribute("currentUser",getCurrentUser());
        return "manage/userUpdate";
    }

    /**
     * 跳转到 后台-->个人中心--> 保存修改资料
     */
    @ResponseBody
    @RequestMapping(value = "/userUpdate",method = RequestMethod.POST)
    public String userUpdate(@ModelAttribute User user) {
        try {
            // 结果只可能有0个、1个、2个 若是查到俩个肯定不行，0个也不行，查询到一个且是自己的行
            // 判断账号和手机是否重复(查询结果只要不是自己的id就行不通，只查到一个结果且相等的时候 才成立) 相当于查询多个结果的
            String userIds = userService.getByAccount(user.getAccount(),user.getPhone());
            if (userIds != null && !userIds.equals(user.getId().toString())) {
                return BizExceptionEnum.USER_ALREADY_REG.getMessage();
            }

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            user.setBirthday(format.parse(user.getVersion()));
            user.setVersion(null);
            userService.updateById(user);
            getSession().setAttribute("currentUser",userService.selectById(user.getId()));
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }

    /**
     * 跳转到 后台-->个人中心--> 修改密码
     */
    @RequestMapping("/toPasswordUpdate")
    public String toPasswordUpdate(Model model) {
        model.addAttribute("currentUser",getCurrentUser());
        return "manage/userPassword";
    }

    /**
     * 修改当前用户的密码
     */
    @RequestMapping(value="/updatePassword",method = RequestMethod.POST)
    @ResponseBody
    public String updatePassword(@RequestParam Integer id, @RequestParam String newPassword) {
        try {
            userService.changePwd(id,newPassword);
            return SUCCESS_MSG;
        }catch (Exception e){
            return ERROR_MSG;
        }
    }


//    /**
//     * 修改当前用户的密码
//     */
//    @RequestMapping("/changePwd")
//    @ResponseBody
//    public Object changePwd(@RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String rePwd) {
//        if (!newPwd.equals(rePwd)) {
//            throw new BussinessException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
//        }
//        Integer userId = ShiroKit.getUser().getId();
//        User user = userMapper.selectById(userId);
//        String oldMd5 = ShiroKit.md5(oldPwd, user.getSalt());
//        if (user.getPassword().equals(oldMd5)) {
//            String newMd5 = ShiroKit.md5(newPwd, user.getSalt());
//            user.setPassword(newMd5);
//            user.updateById();
//            return SUCCESS_TIP;
//        } else {
//            throw new BussinessException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
//        }
//    }

}
