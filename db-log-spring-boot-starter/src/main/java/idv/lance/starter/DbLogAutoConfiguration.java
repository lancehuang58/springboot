package idv.lance.starter;

import idv.lance.starter.interceptor.ConfidentialInterceptor;
import idv.lance.starter.interceptor.SqlStatementInterceptor;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class DbLogAutoConfiguration {
  private final Logger logger = LoggerFactory.getLogger(DbLogAutoConfiguration.class);

  @Bean
  @ConditionalOnProperty(name = "mybatis.interceptor.active", havingValue = "true")
  public SqlStatementInterceptor sqlStatementInterceptor() throws IOException {
    logger.info("init SqlStatementInterceptor");
    return new SqlStatementInterceptor();
  }

  @Bean
  @ConditionalOnProperty(name = "mybatis.interceptor.active", havingValue = "true")
  public ConfidentialInterceptor confidentialInterceptor() {
    logger.info("init ConfidentialInterceptor");
    return new ConfidentialInterceptor();
  }
}
