package web.service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@Service
public class FileService {

    // [0] 업로드 경로 ==============================================================
    // 방법 1. 프로젝트 내 src     : 개발자가 코드를 작성하는 폴더
    // 방법 2. 프로젝트 내 build   : 서버 실행 시, 컴파일된 코드가 저장되는 코드

    // [0.1] 현재 프로젝트의 최상위 디렉토리 경로 찾기
    private String baseDir = System.getProperty("user.dir");

    // [0.2] 방법2의 build 폴더, 즉 실행된 서버내의 업로드 경로 지정 / 개발환경에 따라 달라짐
    private String uploadPath = baseDir + "/build/resources/main/static/upload/";

    // [1] 파일 업로드 ==============================================================
    public String fileUpload(MultipartFile multipartFile) {
        // [1.1] 업로드한 파일명이 중복일 때, (서로 다른파일이지만 파일명만 중복)
        // 방법 1. 파일명 앞에 PK 번호 기재
        // 방법 2. 파일명 앞에 날짜·시간 기재
        // 방법 3. UUID 라이브러리 사용 >> 고유성 보장

        // [1.1.1] UUID 생성
        String uuid = UUID.randomUUID().toString();

        // [1.1.2] 업로드된 파일명과 UUID 합치기
        // .getOriginFilename(); : 업로드된 파일명 반환
        // .replaceAll( "As-is", "To-be" ) : as-is 를 to-be로 변환
        // 업로드된 파일명의 언더바(_)가 존재할 경우, 하이픈(-)으로 변경 필요
        // [ UUID_파일명 ] 의 형태로 저장이 되므로, 파일명의 언더바가 유지될 경우, UUID 구분이 불가해짐
        String fileName = uuid + "_" + multipartFile.getOriginalFilename().replaceAll("_", "-");

        // [1.2] 업로드 경로와 파일명 결합
        String uploadFilePath = uploadPath + fileName;

        // [1.3] If, 업로드 경로에 upload 폴더가 존재하지 않으면 해당 폴더를 생성
        File pathFile = new File(uploadPath);
        // File 클래스 : 파일·폴더의 처리 관련 함수 제공

        if (!pathFile.exists()) {       // .exists() : 존재여부를 T·F로 반환 + 앞에 !(not)
            pathFile.mkdir();           // .mkdir()  : 지정 경로에 폴더를 생성
        }

        // [1.4] 업로드 할 파일의 경로에 대한 File 클래스 생성
        File uploadFile = new File(uploadFilePath);

        // [1.5] 업로드 하기 (파일·바이트로 이동)
        // /transferTo( file객체 ) : 지정한 file 객체의 경로로 업로드 파일을 이동
        try {
            multipartFile.transferTo(uploadFile);           // 일반 예외 발생
        } catch (IOException e) {
            System.out.println(e);
            return null;                                    // 업로드 실패시 null 반환
        }

        // [1.6] 업로드 성공시 파일 이름 반환
        return fileName;

    } // func end

    // [2] 파일 다운로드 ==============================================================
    // String fileName : 다운로드할 파일명
    // HttpServletResponse response : 다운로드를 요청한 사용자의 응답 객체
    public void fileDownload(String fileName, HttpServletResponse response) {

        // [2.1] 다운로드할 파일명과 업로드 경로를 조합
        String downloadPath = uploadPath + fileName;

        // [2.2] If, 지정한 경로에 파일이 없으면 return
        File file = new File( downloadPath );
        if( !file.exists() ){
            return;
        }

        // [2.3] 업로드할 파일으 Java(Byte)로 읽어오기
        try{
            // [2.3.1] 파일 입력스트림 객체 생성
            FileInputStream fin = new FileInputStream( downloadPath );

            // [2.3.2] 파일 용량 만큼의 byte 배열 선언
            long fileSize = file.length();
            byte[] bytes = new byte[ (int)fileSize ];

            // [2.3.3] 파일 입력스트림 객체를 읽어 배열에 저장
            fin.read(bytes);

            // [2.3.4] 안전하게 스트림 닫기
            fin.close();
            // 필수는 아니지만 대용량에서는 안전한 스트림 닫기를 권장

            // [2.4] 다운로드 형식 지정 : 브라우저 마다 상이 ☆★☆★☆★☆★☆★☆★☆★
            // [2.4.1] 파일명에서 UUID 제거
            // .split("기준문자") : 기준문자를 기준으로 문자열을 나눔
            String lodFilename = fileName.split("_")[1]; // 기준문자로 나눈후 [index]의 문자열을 취함

            // [2.4.2] 응답형식 지정, 다운로드 형식·파일에 대한 Header 지정
            // URL은 한글을 지원하지 않음
            // URLEncoder.encode( 파일명 , "UTF-8" );
            response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(lodFilename, "UTF-8") );

            // [2.5] 파일은 사용자에게 응답·반환 해주기
            // [2.5.1] 파일 출력 스트림 객체 생성
            ServletOutputStream fout = response.getOutputStream();

            // [2.5.2] 바이트배열을 사용자에게 출력·쓰기
            fout.write(bytes);

            // [2.5.3] 안전하게 스트림 닫기
            fout.close(); // 필수는 아니지만 권장
        } catch (Exception e) {
            System.out.println(e);
        }
    } // func end

    // [3] 파일 삭제 ==============================================================
    public boolean fileDelete( String fileName ){

        // [3.1] 삭제할 파일명과 업로드 경로 조합
        String filePath = uploadPath + fileName;
        // [3.2] If, 경로에 파일이 존재하면 파일 삭제 후 return true
        File file = new File(filePath);
        if( file.exists() ){
            file.delete();
            return true;
        }
        return false;
    } // func end

} // class end
