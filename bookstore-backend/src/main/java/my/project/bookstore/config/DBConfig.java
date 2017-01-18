package my.project.bookstore.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

/**
 * Created by zaychick-pavel on 1/17/17.
 */

//@Configuration
public class DBConfig {
//	@Bean
//	@ConfigurationProperties(prefix="spring.db_sqlite")
	public DataSource dataSource() {
//		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//		dataSourceBuilder.driverClassName("org.sqlite.JDBC");
//		dataSourceBuilder.url("jdbc:sqlite:bookstore.db");
//		return dataSourceBuilder.build();
		return DataSourceBuilder.create().build();
	}
}
