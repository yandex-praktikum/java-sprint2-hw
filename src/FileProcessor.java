import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class FileProcessor {
    private final String PATH = "/home/yukine/java/java-sprint2-hw-1/resources/"; // require pointer to the actual file
    public HashMap<String, String> fileContent = new HashMap<>();

    public HashMap<String, String> readFileContentsOrNull(String type) {
        /*
        Reads files from a folder (PATH constant)
         */
        String content = "";
        File directory = new File(PATH);
        File[] fileList = directory.listFiles(); //get all files in PATH
        for (File file : fileList) {
            if (file.getName().contains(type)) { // yearly or monthly only
                String fileName = file.getName();
                try {
                    fileContent.put(processFileMame(fileName), Files.readString(Path.of(file.getPath())));  //add each file to hashMap with file name as Key and Content as Value
                } catch (IOException e) {
                    System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
                    return null;
                }
            }
        }
        return fileContent;
    }

    public String processFileMame(String fileName){
        /*
        Trims the file name removing unnecessary notations
         */
        String[] tempName = fileName.split("\\.");
        return tempName[1];
    }

    public String convertMonthToWords(String month){
        /*
        Converts numerical values for months into words.
        */
        String wordMonth = "";
        switch (month) {
            case "01":
                wordMonth = "Январь";
                break;
            case "02":
                wordMonth = "Февраль";
                break;
            case "03":
                wordMonth = "Март";
                break;
            case "04":
                wordMonth = "Апрель";
                break;
            case "05":
                wordMonth = "Май";
                break;
            case "06":
                wordMonth = "Июнь";
                break;
            case "07":
                wordMonth = "Июль";
                break;
            case "08":
                wordMonth = "Август";
                break;
            case "09":
                wordMonth = "Сентябрь";
                break;
            case "10":
                wordMonth = "Октябрь";
                break;
            case "11":
                wordMonth = "Ноябрь";
                break;
            case "12":
                wordMonth = "Декабрь";
                break;
            default:
                System.out.println("Неверный месяц");
                break;
        }
        return wordMonth;
    }

    public String[] getProcessedFileContent(String fileName){
        /*
        Reads file content and splits it into lines.
         */
        String content = fileContent.get(fileName);
        return content.split(System.lineSeparator());
    }
}
