package utils;

import org.apache.commons.io.FileDeleteStrategy;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileUtils {
    public String getLastFileModified(String directoryFilePath)
    {
        File dir = new File(directoryFilePath);
        File max = null;
        for (File file : dir.listFiles()) {
            if (file.isDirectory() && (max == null || max.lastModified() < file.lastModified())) {
                max = file;
            }
        }

        return String.valueOf(max);
    }

    public void cleanAllureReportFiles(){
        try {
//            String workingDir = System.getProperty("user.dir");
            String pathFolderallure = "allure-results";
            File fileAllure = new File(pathFolderallure);
            File[] listOfFilesAllure = fileAllure.listFiles();
            System.out.println("......................"+pathFolderallure);
            for(int i = 0; i < listOfFilesAllure.length; i++){
                if(listOfFilesAllure[i].isFile()){
                    new File(listOfFilesAllure[i].toString()).delete();
                }
            }
            System.out.println("================ DELETE ALLURE================");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cleanExtentReportFiles(){
        try {
//            String workingDir = System.getProperty("user.dir");
            String pathFolerExtent="ExtentReports/";
            File fileExtent = new File(pathFolerExtent);
            File[] listOfFilesExtent = fileExtent.listFiles();

                System.out.println("count extent: "+ listOfFilesExtent.length);
                if(listOfFilesExtent.length>2){
                    for(int i = 0; i <listOfFilesExtent.length-2 ; i++){
                        if(listOfFilesExtent[i].isDirectory()){
                            FileDeleteStrategy.FORCE.delete(new File(listOfFilesExtent[i].toString()));
                        }
                    }
                }


            System.out.println("================ DELETE EXTENT================");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
