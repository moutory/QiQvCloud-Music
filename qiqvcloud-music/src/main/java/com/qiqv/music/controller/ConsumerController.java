package com.qiqv.music.controller;

import com.qiqv.music.pojo.Consumer;
import com.qiqv.music.pojo.vo.ConsumerVO;
import com.qiqv.music.service.ConsumerService;
import com.qiqv.music.utils.MD5Utils;
import com.qiqv.music.utils.QiqvJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("consumer")
public class ConsumerController extends BasicController{

    @Autowired
    private ConsumerService consumerService;


    @RequestMapping(method = RequestMethod.GET,path = "/getAllConsumer")
    public QiqvJSONResult getAllConsumer(){
        List<Consumer> allConsumer = consumerService.getAllConsumer();
        if(null == allConsumer){
            return QiqvJSONResult.errorMsg("查询失败");
        }
        return QiqvJSONResult.ok(allConsumer);
    }


    @PostMapping("/addConsumer")
    public QiqvJSONResult addConsumer(@RequestBody Consumer consumer){
        if(null ==consumer || StringUtils.isBlank(consumer.getUsername()) || StringUtils.isBlank(consumer.getPassword())){
            return QiqvJSONResult.errorMsg("参数缺少，添加失败");
        }
        try{
            if(StringUtils.isBlank(consumer.getAvator())){
                consumer.setAvator("/img/avatorImages/user.jpg");
            }
            boolean addSuccess = consumerService.addConsumer(consumer);
            if(addSuccess){
                return QiqvJSONResult.ok();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return QiqvJSONResult.errorMsg("添加失败");
    }


    /**
     *  更新用户头像
     * @param id,file
     * @return
     */
    @PostMapping("/updateConsumerPic")
    public QiqvJSONResult updateConsumerPic(@RequestParam("id") Integer id,@RequestParam("file") MultipartFile file){
        if(id==null){
            return QiqvJSONResult.errorMsg("更新失败,缺少用户id");
        }
        if(file==null || StringUtils.isBlank(file.getOriginalFilename()) || file.isEmpty()){
            return QiqvJSONResult.errorMsg("更新失败,文件格式错误");
        }
        String newPicPath = this.saveNewConsumerPic(file);
        if(!StringUtils.isBlank(newPicPath)){
            Consumer consumer = new Consumer();
            consumer.setAvator(newPicPath);
            consumer.setId(id);
            consumerService.updateConsumer(consumer);
            return QiqvJSONResult.ok();
        }
        return QiqvJSONResult.errorMsg("添加失败");
    }

    /**
     * 更新用户基本信息
     * @param consumer
     * @return
     */
    @PostMapping("/updateConsumer")
    public QiqvJSONResult updateConsumer(@RequestBody Consumer consumer){
        if(consumer==null || consumer.getId()==null){
            return QiqvJSONResult.errorMsg("更新失败,缺少用户id");
        }
        boolean updateSuccess = consumerService.updateConsumer(consumer);
        if(updateSuccess){
            return QiqvJSONResult.ok();
        }
        return QiqvJSONResult.errorMsg("更新失败");
    }

    /**
     * 删除用户
     * @param consumer
     * @return
     */
    @PostMapping("/delConsumer")
    public QiqvJSONResult delConsumer(@RequestBody Consumer consumer){
        if(consumer==null || consumer.getId()==null){
            return QiqvJSONResult.errorMsg("删除失败,缺少用户id");
        }
        boolean delSuccess = consumerService.deleteConsumerById(consumer.getId());
        if(delSuccess){
            return QiqvJSONResult.ok();
        }
        return QiqvJSONResult.errorMsg("删除失败");
    }

    private String saveNewConsumerPic(MultipartFile file) {
        String newFileName = System.currentTimeMillis() +file.getOriginalFilename();
        String picSavePath = System.getProperty("user.dir") + System.getProperty("file.separator") +"img"+ System.getProperty("file.separator")
                + "avatorImages" + System.getProperty("file.separator");
        File saveDir = new File(picSavePath);
        if(!saveDir.exists()){
            saveDir.mkdirs();
        }
        File picFile = new File(picSavePath +newFileName);
        try {
            file.transferTo(picFile);
            String relevantPicPath = "/img/avatorImages/" + newFileName;
            return relevantPicPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // =====================================================================================
    /**
     * 用户登录
     * @param consumer
     * @return
     */
    @PostMapping("/login")
    public QiqvJSONResult login(@RequestBody Consumer consumer) throws NoSuchAlgorithmException {
        if(consumer==null || StringUtils.isAnyBlank(consumer.getPassword(),consumer.getUsername())){
            return QiqvJSONResult.errorMsg("请输入用户名或密码");
        }
        Consumer result = consumerService.consumerLogin(consumer.getUsername(),
                MD5Utils.getEnCryptionStrByMD5(consumer.getPassword()));
        // 如果通过校验，则给用户新建一个token
        if(result != null){
            String userToken = UUID.randomUUID().toString();
            // token有效期为半小时
            redisOperator.setValue(USER_TOKEN_SESSION + ":" + result.getId(),userToken,60*30);
            ConsumerVO consumerVO = new ConsumerVO();
            BeanUtils.copyProperties(result,consumerVO);
            consumerVO.setPassword("");
            consumerVO.setUserToken(userToken);
            return QiqvJSONResult.ok(consumerVO);
        }
        return QiqvJSONResult.errorMsg("账号或密码错误，请重新输入");
    }

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    @RequestMapping(path = "/queryUserById",method = RequestMethod.GET)
    public QiqvJSONResult queryUserById(Integer userId) {
        if( userId==null ){
            return QiqvJSONResult.errorMsg("缺少用户Id");
        }
        Consumer result = consumerService.queryConsumerById(userId);
        if(result != null){
            return QiqvJSONResult.ok(result);
        }
        return QiqvJSONResult.errorMsg("查询失败");
    }




}
