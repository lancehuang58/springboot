package idv.lance.controller;


import idv.lance.service.FileService;
import idv.lance.vo.FileRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("file")
    public String upload() {
        FileRequest fileRequest = new FileRequest();
        fileRequest.setDesc("d");
        fileRequest.setSize(154);
        fileRequest.setName("bingo");
        log.info("test");
        fileService.createFile(fileRequest);
        return "file";
    }
}
