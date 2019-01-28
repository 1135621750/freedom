package com.freedom.core.config;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * mapper,bean配置类
 * @author Bai
 *
 */
@Configuration
public class MybatisConfigurer {

   @Bean
   public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
      SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
      factory.setDataSource(dataSource);
      factory.setTypeAliasesPackage("com.freedom.*.model");
      // 添加XML目录
      ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
      factory.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));
      factory.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
      return factory.getObject();
   }

   @Bean
   public MapperScannerConfigurer mapperScannerConfigurer() {
      MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
      mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
      mapperScannerConfigurer.setBasePackage("com.freedom.*.mapper");
      return mapperScannerConfigurer;
   }
}

