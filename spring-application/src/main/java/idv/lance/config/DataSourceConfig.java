package idv.lance.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("idv.lance.dao.mapper")
public class DataSourceConfig {
}
