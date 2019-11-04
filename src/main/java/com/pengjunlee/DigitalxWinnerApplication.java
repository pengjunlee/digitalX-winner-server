package com.pengjunlee;

import com.pengjunlee.listener.EliminateCommentListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = {"com.pengjunlee.service.mapper"}) //扫描DAO
public class DigitalxWinnerApplication extends SpringBootServletInitializer {

    // 重写configure方法，否则在部署到tomcat时，接口将访问不到
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DigitalxWinnerApplication.class);
    }

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(DigitalxWinnerApplication.class);
        // 添加一个初始化监听器，对映射地址配置进行加载
        application.addListeners(new EliminateCommentListener());
        application.run(args);


    }

}
