package com.hjf.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hjf.domain.Girl;

import java.util.List;


//类名 id 类型
public interface GirlRespository extends JpaRepository<Girl, Integer> {

    //通过年龄来查询 方法名不能乱写
    public List<Girl> findByAge(Integer age);

}
