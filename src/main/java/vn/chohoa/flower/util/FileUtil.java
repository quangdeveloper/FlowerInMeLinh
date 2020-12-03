package vn.chohoa.flower.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.chohoa.flower.exception.GeneralException;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class FileUtil {

    private static final Long maxSize = 10240L;



    public static Integer getRadomImageLength() {
        Random rd = new Random();
        int high = 30;
        int low = 1;
        return rd.nextInt(high - low) + low;
    }

    public static String genarateName() {
        int length = getRadomImageLength();

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int year = cal.get(Calendar.YEAR);

        return year
                + RandomStringUtils.random(length, true, true)
                + RandomStringUtils.random(10, true, false)
                + RandomStringUtils.random(5, false, true);
    }

    public static String getExtentionFile(String url) {
        return "." + FilenameUtils.getExtension(url).toLowerCase();
    }

    public static String multipartfileToFile(MultipartFile file, String dirPath) throws IllegalStateException {
        try {


            if (file.isEmpty()) {
                throw new NullPointerException("File not empty");
            }
            Path dir = Paths.get(dirPath);
            if (!Files.isDirectory(dir)) {
                Files.createDirectories(dir);
            }

            String fileName = genarateName() + getExtentionFile(file.getOriginalFilename());
            Path filePath = Paths.get(dirPath, fileName);

            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
            File fileS = filePath.toFile();
            FileCopyUtils.copy(file.getBytes(), fileS);

            return filePath.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }


    }
}
