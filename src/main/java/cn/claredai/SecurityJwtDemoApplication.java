package cn.claredai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.claredai.mapper")
public class SecurityJwtDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityJwtDemoApplication.class, args);
        System.out.println("* * * * * * * * * * * * * * *\n" +
                "*        系统启动成功        *\n" +
                "* * * * * * * * * * * * * * *");
    }

}
