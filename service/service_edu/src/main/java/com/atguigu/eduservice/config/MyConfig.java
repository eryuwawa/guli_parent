package com.atguigu.eduservice.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wj
 * @create 2021/5/22  18:38
 */

@Configuration
@MapperScan("com.atguigu.eduservice.mapper")
@ComponentScan(basePackages = {"com.atguigu"})
public class MyConfig {

    /**
     * 逻辑删除插件
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Component
    public class MyMetaObjectHandler implements MetaObjectHandler {
        @Override
        public void insertFill(MetaObject metaObject) {
            this.setFieldValByName("gmtCreate", new Date(), metaObject);
            this.setFieldValByName("gmtModified", new Date(), metaObject);
        }
        @Override
        public void updateFill(MetaObject metaObject) {
            this.setFieldValByName("gmtModified", new Date(), metaObject);
        }
    }
}
