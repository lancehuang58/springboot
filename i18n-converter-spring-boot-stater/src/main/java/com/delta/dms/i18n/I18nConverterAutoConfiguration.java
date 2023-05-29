package com.delta.dms.i18n;

import com.delta.dms.i18n.config.I18nMappingConfig;
import com.delta.dms.i18n.exception.ConfigFileNotExistException;
import com.delta.dms.i18n.intercepter.I18nConverterInterceptor;
import com.delta.dms.i18n.intercepter.I18nMappingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.util.Properties;

@Slf4j
@Configuration
@ComponentScan
public class I18nConverterAutoConfiguration {

  private static final String TABLE_NAME = "table";
  public static final String SCHEMA = "schema";
  private final I18nMappingHandler i18nMappingHandler;
  public static final String CONFIG_PROPERTIES = "/i18n-mapping-config.properties";

  @Autowired
  public I18nConverterAutoConfiguration(@Lazy I18nMappingHandler i18nMappingHandler) {
    this.i18nMappingHandler = i18nMappingHandler;
  }

  @Bean
  public I18nConverterInterceptor i18nConverterInterceptor() {
    return new I18nConverterInterceptor(i18nMappingHandler);
  }

  @Bean
  @ConditionalOnResource(resources = {"classpath:i18n-mapping-config.properties"})
  public I18nMappingConfig i18nMappingConfig() throws IOException {
    I18nMappingConfig info = new I18nMappingConfig();
    Properties p = new Properties();
    p.load(getClass().getResourceAsStream(CONFIG_PROPERTIES));
    info.setTableName(p.getProperty(TABLE_NAME));
    info.setSchema(p.getProperty(SCHEMA));
    return info;
  }

  @Bean
  @ConditionalOnMissingBean
  public I18nMappingConfig missingConfigFile() throws ConfigFileNotExistException {
    throw new ConfigFileNotExistException();
  }
}
