package cn.com.goolife.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import cn.com.goolife.controller.PageController;
import cn.com.goolife.dao.Dao;
import cn.com.goolife.db.controller.DBController;

@Configuration
@ComponentScan(basePackageClasses= {PageController.class,DBController.class,Dao.class})
public interface ContextConfiguration {

}
