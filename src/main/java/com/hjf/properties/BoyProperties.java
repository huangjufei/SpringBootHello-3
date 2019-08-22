package com.hjf.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
//读取配置文件的键值对,直接注入到类里面,prefix,是age的前缀,相当于boy.age
@ConfigurationProperties(prefix = "boy")
public class BoyProperties {
    private int age;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "BoyProperties{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
