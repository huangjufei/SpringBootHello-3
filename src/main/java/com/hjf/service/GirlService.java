package com.hjf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjf.domain.Girl;
import com.hjf.enums.ResultEnum;
import com.hjf.exception.GirlException;
import com.hjf.respository.GirlRespository;


@Service
public class GirlService {

    @Autowired
    private GirlRespository girlRespository;

    /**
     * 添加女生
     */
    public Girl addGirl(Girl girl) {
        return girlRespository.save(girl);
    }

    /**
     * 通过 id 删除女生
     */
    public boolean deleteGirl(Integer id) {
        Girl girl = findById(id);
        if (girl.getName() != null) {
           // girlRespository.delete(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 通过 id 查找女生
     */
    public Girl findById(Integer id) {
        return girlRespository.getOne(id);
    }

    /**
     * 获取所有女生列表
     */
    public List<Girl> getGirlList() {
        return girlRespository.findAll();
    }

    /**
     * 通过年龄女生列表
     */
    public List<Girl> getGirlByAge(Integer age) {
        return girlRespository.findByAge(age);
    }

    // 同时成功或者失败
    @Transactional
    public void insertTwo() {
        Girl girl1 = new Girl();
        girl1.setName("A");
        girl1.setAge(21);
        girlRespository.save(girl1);
        if(false){
            //通过抛异常的方式是可以回滚的
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }
        Girl girl2 = new Girl();
        girl2.setName("sdf");
        girl2.setAge(12);
        girlRespository.save(girl2);
    }

    /**
     * 好处逻辑才service层处理,返回结果controller层可以不用管;由统一的异常处理类来完成
     */
    public void getAge(Integer id) throws Exception {
       Girl girl = girlRespository.getOne(id);
        Integer age = girl.getAge();
        if (age < 10) {
            // 知识点:抛 throw new GirlException(ResultEnum.PRIMARY_SCHOOL);出自定义的异常,参数是自定义的枚举类(里面放的是key-value)
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        } else if (age < 16) {
            //初中
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        } else {
            //大学
            throw new GirlException(ResultEnum.HIGHT_SCHOOL);
        }
        // 其他复杂内容
    }

}
