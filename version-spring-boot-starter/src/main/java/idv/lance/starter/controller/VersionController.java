package idv.lance.starter.controller;

import idv.lance.starter.entity.DmsAppVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    @Autowired
    DmsAppVersion info;

    @GetMapping("/version")
    public String versionInfo() {
        return info.getVersion();
    }
}
