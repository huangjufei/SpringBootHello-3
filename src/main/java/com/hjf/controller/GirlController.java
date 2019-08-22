package com.hjf.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hjf.domain.Girl;
import com.hjf.service.GirlService;
import com.hjf.utils.Result;
import com.hjf.utils.ResultUtil;



/**
 * RESTful API 设计
 * |请求类型|请求路径|功能
 * |GET|/girls|获取女生列表|
 * |POST|/girls|添加一个女生|
 * |GET|/girls/id|通过 id 查询一个女生|
 * |PUT|/girls/id|通过 id 更新一个女生|
 * |DELETE|girls/id|通过 id 删除一个女生|
 * |GET|girls/age/{age}|通过 age 获取女生列表|
 * |POST|girls/two|模拟测试事物处理|
 * |GET|girls/getAge/{id}|通过 id 查询女生年龄,模拟实现统一异常处理|
 *
 */
@RestController
public class GirlController {

    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlService girlService;

    //结合pom.xml文件实现热部署,热部署可以我们添加方法都不需要重启
    @GetMapping("/hotDeploy")  
    public String hotDeploy(HttpServletRequest request) {
    	request.setAttribute("say","vvv"); 	
    	return "he";//返回he.html 这个页面不能使用@RestController
    }
    
    //可以在项目运行中,复制方法,或修改参数个数等,不需要再次重启服务
    @GetMapping("/hotDeploy2")  
    public String hotDeploy2(HttpServletRequest request) {
    	request.setAttribute("say","fffasd"); 	
    	return "he";//返回he.html 这个页面不能使用@RestController
    }
    
    //获取女生列表
    @GetMapping("/girls")
    public Result<List<Girl>> getGirlList() {
        List<Girl> girls = girlService.getGirlList();
        logger.info(girls.toString());
        return ResultUtil.success(girls);
    }


    //添加女生,用 @Valid 过滤
    @PostMapping("/girls")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(girlService.addGirl(girl));
    }

    //通过 id 查找女生
    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id) {
        return girlService.findById(id);
    }

    //更新女生 Content-Type = application/x-www-form-urlencoded 且 age >= 18
    @PutMapping(value = "/girls/{id}")
    public Result<Girl> girlUpdate(@PathVariable("id") Integer id, @Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        girl.setId(id);
        return ResultUtil.success(girlService.addGirl(girl));
    }

    //通过 id 删除女生
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id) {
        girlService.deleteGirl(id);
    }

    //通过年龄查询女生
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age) {
        return girlService.getGirlByAge(age);
    }


    //事务处理
    @PostMapping(value = "/girls/two")
    public void girlTwo() {
        girlService.insertTwo();
        logger.info("如果抛出异常先去统一异常类");
    }

    //通过 id 查询女生年龄,最多知识点
    @GetMapping(value = "/girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {
        girlService.getAge(id);
    }

}
