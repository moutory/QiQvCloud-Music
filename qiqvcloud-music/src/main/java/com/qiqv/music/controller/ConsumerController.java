package com.qiqv.music.controller;

import com.qiqv.music.pojo.Consumer;
import com.qiqv.music.pojo.vo.ConsumerVO;
import com.qiqv.music.service.ConsumerService;
import com.qiqv.music.utils.EmailValidator;
import com.qiqv.music.utils.MD5Utils;
import com.qiqv.music.utils.QiqvJSONResult;
import com.qiqv.music.utils.VerifyCodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("consumer")
public class ConsumerController extends BasicController{

    @Autowired
    private ConsumerService consumerService;
    @Autowired
    private EmailValidator emailValidator;


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
        String msg = updateDataCheck(consumer);
        if(msg == null){
            boolean updateSuccess = consumerService.updateConsumer(consumer);
            if(updateSuccess){
                return QiqvJSONResult.ok();
            }
            return QiqvJSONResult.errorMsg("更新失败");
        }
        return QiqvJSONResult.errorMsg(msg);
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
    public QiqvJSONResult login(@RequestBody ConsumerVO consumer) throws NoSuchAlgorithmException {
        String errorMsg = null;
        errorMsg = consumerDataCheck(consumer);
        if(null == errorMsg){
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
        }
        return QiqvJSONResult.errorMsg(errorMsg);
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

    private String updateDataCheck(Consumer consumer){
        if(consumer==null || consumer.getId()==null){
            return "更新失败,缺少用户id";
        }
        if(StringUtils.isNotBlank(consumer.getEmail())){
            if(emailValidator.isLegal(consumer.getEmail())){
                return "邮箱格式不正确，请重新输入";
            }
        }
        return null;
    }

    /**
     * 用户登录/注册校验码生成
     * 生成验证码后，将本次生成验证码操作存入redis中，有效期为3分钟
     * 键值规则为  USER_VERIFYCODE_SESSION + UUID : 4位数字验证码
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(path = "/getVerifyCodePic",method = RequestMethod.GET)
    public QiqvJSONResult getVerifyCodePic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> result = new HashMap<>();
        VerifyCodeUtils code = new VerifyCodeUtils();
        // 生成验证码图片
        BufferedImage image = code.getImage();
        // 获取验证码四位数字
        String text = code.getText();
        // 验证码-键值对存入分别存入redis
        String verifyCode_key = USER_VERIFYCODE_SESSION+UUID.randomUUID().toString();
        redisOperator.setValue(verifyCode_key,text,60*3);
        //进行base64编码
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try{
            ImageIO.write(image, "png", bos);
            String string = Base64Utils.encodeToString(bos.toByteArray());
            result.put("key", verifyCode_key);
            result.put("image", string);
            return QiqvJSONResult.ok(result);
        }catch (IOException e){
            System.out.println(e);
        }finally {
            bos.close();
        }
        return QiqvJSONResult.errorMsg("生成验证码失败");
    }

    /**
     * 验证码校验
     * 将用户写入的验证码和保存到redis的验证码比对
     * @param verifyCode
     * @return
     */
    private String verifyCodeCheck(String verifyCodeKey,String verifyCode){
        if(StringUtils.isBlank(verifyCode) || StringUtils.isBlank(verifyCodeKey)){
            return "验证码不能为空";
        }
        String value = redisOperator.getValue(verifyCodeKey);
        // 验证码已过期
        if(null == value){
            return "验证码已过期，请刷新后重试";
            //说明是用户乱填或者有缓存
        }else if(!verifyCode.equalsIgnoreCase(value)){
            return "无效的验证码，请刷新后重试";
        }
        return null;
    }

    private String consumerDataCheck(ConsumerVO consumer){
        String result = null;
        if(consumer==null || StringUtils.isAnyBlank(consumer.getPassword(),consumer.getUsername())){
            return "请输入用户名或密码";
        }
        result = verifyCodeCheck(consumer.getVerifyKey(),consumer.getVerifyCode());
        return result;
    }


}
