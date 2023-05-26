package idv.lance.starter;

import idv.lance.starter.intercepter.I18nConverterInterceptor;
import idv.lance.starter.service.I18nService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
@ComponentScan
public class I18nConverterAutoConfiguration {

  private final I18nService i18nService;

  @Bean
  public I18nConverterInterceptor i18nConverterInterceptor() {
    log.debug("init i18n converter.");
    return new I18nConverterInterceptor(i18nService);
  }
}
