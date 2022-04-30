package idv.lance.service.imp;

import idv.lance.service.FileService;
import idv.lance.vo.FileRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FileServiceImpl implements FileService {


    @Override
    public void createFile(FileRequest request) {

        log.info("test file {}", request);
    }
}
