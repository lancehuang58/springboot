package com.delta.dms.i18n.tool;

import com.delta.dms.i18n.service.I18nService;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class I18nUtil {
  private I18nUtil() {}

  public static String mapping(String key) {
    I18nService service = ContextSupport.getApplicationContext().getBean(I18nService.class);
    Map<String, String> keyValueMapping = service.findByKeys(Sets.newHashSet(key));
    return keyValueMapping.get(key);
  }

  public static String mappingWithLocale(String key, Locale locale) {
    I18nService service = ContextSupport.getApplicationContext().getBean(I18nService.class);
    Map<String, String> keyValueMapping = service.findByKeys(Sets.newHashSet(key), locale);
    return keyValueMapping.get(key);
  }

  public static String mappingWithLocale(String key, Locale locale, String... params) {
    I18nService service = ContextSupport.getApplicationContext().getBean(I18nService.class);
    Map<String, String> keyValueMapping = service.findByKeys(Sets.newHashSet(key), locale);
    String value = keyValueMapping.get(key);
    return MessageFormat.format(value, params);
  }

  @Component
  private static class ContextSupport implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
      return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      ContextSupport.applicationContext = applicationContext;
    }
  }
}
