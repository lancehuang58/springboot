package idv.lance.starter;

import idv.lance.starter.intercepter.Converter;
import idv.lance.starter.intercepter.I18nConverterInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class I18nConverterAutoConfiguration {

  private static final Logger log = LoggerFactory.getLogger(I18nConverterAutoConfiguration.class);

  @Autowired(required = false)
  private Converter converter;

  @Bean
  @ConditionalOnBean(Converter.class)
  public I18nConverterInterceptor i18nConverterInterceptor() {
    log.info("init i18n converter.");
    return new I18nConverterInterceptor(converter);
  }
}
