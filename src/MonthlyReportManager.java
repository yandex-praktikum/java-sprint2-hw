import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class MonthlyReportManager {
    private final String PATH = "/home/yukine/java/java-sprint2-hw-1/resources/"; // require pointer to the actual file
    HashMap<String, String> fileContent = new HashMap<>();

    public HashMap<String, String> readFileContentsOrNull(){
        String content = "";
        File directory = new File(PATH);
        File[] fileList = directory.listFiles(); //get all files in PATH
        for (File file : fileList) {

            if (!file.getName().contains("y")) { // exclude yearly
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

    public void getMonthlyReports(){
        for (String month : fileContent.keySet()){
            String wordedMonthName = convertMonthToWords(month.substring(month.length() - 2)); // get last 2 digit sof the file name and convert them
            String[] content = getProcessedFileContent(month);
            int maxProfit = 0;
            int maxExpense = 0;
            String mostProfitableItem = "";
            String biggestExpense = "";

            for (int i = 1; i < content.length; i++){                                                    // parsing lines from the file
                String[] splitVals = content[i].split(",");
                String item = splitVals[0];
                boolean isExpense = Boolean.parseBoolean(splitVals[1]);
                int qty = Integer.parseInt(splitVals[2]);
                int value = Integer.parseInt(splitVals[3]);
                if(!isExpense) {
                    int total = qty * value;
                    if (total > maxProfit) {
                        maxProfit = total;
                        mostProfitableItem = item;
                    }
                } else {
                    int total = qty * value;
                        if (total > maxExpense) {
                            maxExpense = total;
                            biggestExpense = item;
                        }

                    }
                }
            System.out.println("Вывожу данные за " + wordedMonthName + ".");
            System.out.println("Наибоее прибыльный товар: " + mostProfitableItem + ". Общий доход: " + maxProfit);
            System.out.println("Наибольшая трата: " + biggestExpense +". Общая сумма трат: " + maxExpense);
        }
    }


    private String processFileMame(String fileName){
        /*
        Trims the file name removing unnecessary notations
         */
        String[] tempName = fileName.split("\\.");
        String processedName = tempName[1];
        return processedName;
    }

    private String convertMonthToWords(String month){
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

    private String[] getProcessedFileContent(String fileName){
        String content = fileContent.get(fileName);
        String[] lines = content.split(System.lineSeparator());
        return lines;
    }
}

