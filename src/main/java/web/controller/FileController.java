package web.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

    // [0] service 연결
    @Autowired
    private FileService fileService;

    // [1] 파일 업로드 ==============================================================
    // POST
    // Headers : {Content-Type : multipart/form-data }
    // Body : form

    @PostMapping("/upload")
    public String fileUpload(MultipartFile multipartFile){
        System.out.println("FileController.fileUpload");
        System.out.println("multipartFile = " + multipartFile);

        String result = fileService.fileUpload(multipartFile);
        return result;
    } // func end
    // TEST 결과 : 8fd01fae-5364-4c67-8f90-ebea9faedeb3_tj-logo.jpg : 정상적으로 저장됨



    // [2] 파일 다운로드 ==============================================================
    // GET
    // http://localhost:8080/file/download?fileName=8fd01fae-5364-4c67-8f90-ebea9faedeb3_tj-logo.jpg
    @GetMapping("/download")
    public void fileDownload(@RequestParam String fileName , HttpServletResponse response){
        System.out.println("FileController.fileDownload");
        System.out.println("fileName = " + fileName + ", response = " + response);

        fileService.fileDownload(fileName, response);
    }

    // [3] 파일 삭제 ==============================================================
    // http://localhost:8080/file/delete?fileName=8fd01fae-5364-4c67-8f90-ebea9faedeb3_tj-logo.jpg
    @GetMapping("/delete")
    public boolean fileDelete(@RequestParam String fileName){
        System.out.println("fileName = " + fileName);
        System.out.println("FileController.fileDelete");
        
        boolean result = fileService.fileDelete(fileName);
        return result;
    } // func end

} // class end
