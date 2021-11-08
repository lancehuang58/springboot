package idv.lance.starter;

import idv.lance.starter.exception.EnvPropertyFileNotExistException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class VersionInfoFailureAnalyzer extends AbstractFailureAnalyzer<EnvPropertyFileNotExistException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, EnvPropertyFileNotExistException cause) {
        return new FailureAnalysis(getDescription(cause), getAction(cause), cause);
    }

    private String getDescription(EnvPropertyFileNotExistException ex) {
        return "can't find env.properties file in path 'src/main/resources'";
    }

    private String getAction(EnvPropertyFileNotExistException ex) {
        return "please create evn.properties file in path src/main/resources and add a property for version (info.version)";
    }
}
