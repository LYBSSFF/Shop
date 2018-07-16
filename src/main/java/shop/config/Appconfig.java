package shop.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("shop")  //扫描整个项目
@MapperScan("shop.mapper")  //扫描Mapper
@PropertySource("classPath:jdbc.properties") 
public class Appconfig extends WebMvcConfigurerAdapter{
	@Bean
	public DataSource dataSource(Environment environment) {
		DriverManagerDataSource ds=new DriverManagerDataSource(
				environment.getProperty("jdbc.url"),
				environment.getProperty("jdbc.username"),
				environment.getProperty("jdbc.password"));
				ds.setUsername(environment.getProperty("jdbc.driverClassName"));
		return ds;	
	}
	
	@Bean // 定义Mybatis的会话工厂
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
		SqlSessionFactoryBean sf = new SqlSessionFactoryBean();
		sf.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		sf.setDataSource(dataSource);
		return sf;
	}
}
