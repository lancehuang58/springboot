package idv.lance.starter;

import idv.lance.starter.entity.DmsAppVersion;
import idv.lance.starter.exception.EnvPropertyFileNotExistException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan
public class VersionInfoConfiguration {

    public static final String INFO_VERSION = "info.version";
    public static final String ENV_PROPERTIES = "/env.properties";

    @Bean
    @ConditionalOnResource(resources = {"classpath:env.properties"})
    public DmsAppVersion versionInfo() throws IOException {
        DmsAppVersion info = new DmsAppVersion();
        Properties p = new Properties();
        p.load(getClass().getResourceAsStream(ENV_PROPERTIES));
        info.setVersion(p.getProperty(INFO_VERSION));
        return info;
    }

    @Bean
    @ConditionalOnMissingBean
    public DmsAppVersion versionNotProvided() throws EnvPropertyFileNotExistException {
        throw new EnvPropertyFileNotExistException();
    }
}
