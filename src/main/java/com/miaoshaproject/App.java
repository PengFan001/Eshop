package com.miaoshaproject;

import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
//扫描整个com.miaoshaproject目录下的文件
@SpringBootApplication(scanBasePackages = {"com.miaoshaproject"})
@RestController
@MapperScan("com.miaoshaproject.dao")           //扫描dao的路径
public class App
{
    @Autowired
    private UserDOMapper userDOMapper; //此处可以理解为通过@Autowired 生成UserDOMapper这个bean 消除set方法
                                       //Spring 会将这些传递过来的值或者引用自动分配给UserDOMapper中对应的那些属性

    @RequestMapping("/home")
    public String home(){

        UserDO userDO = userDOMapper.selectByPrimaryKey(1);
        if(userDO != null){
            return "hello world";
        }
        else {
            return "该用户不存在";
        }
    }


    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class, args);
    }
}
