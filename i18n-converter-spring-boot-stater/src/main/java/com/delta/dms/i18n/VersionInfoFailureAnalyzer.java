package com.delta.dms.i18n;

import com.delta.dms.i18n.exception.ConfigFileNotExistException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class VersionInfoFailureAnalyzer extends AbstractFailureAnalyzer<ConfigFileNotExistException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, ConfigFileNotExistException cause) {
        return new FailureAnalysis(getDescription(cause), getAction(cause), cause);
    }

    private String getDescription(ConfigFileNotExistException ex) {
        return "can't find i18n-config.properties file in path 'src/main/resources'";
    }

    private String getAction(ConfigFileNotExistException ex) {
        return "please create i18n-config.properties file in path src/main/resources and add a property for version (info.version)";
    }
}
