import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReportManager {
    private final String PATH = "/home/yukine/java/java-sprint2-hw-1/resources/"; // require pointer to the actual file
    HashMap<String, String> fileContent = new HashMap<>();

    public HashMap<String, String> readFileContentsOrNull(){
        File directory = new File(PATH);
        File[] fileList = directory.listFiles(); //get all files in PATH
        for (File file : fileList) {
            if (!file.getName().contains("m")) { // exclude monthly
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


    public void getYearlyReport() {
        for (String year : fileContent.keySet()) {
            String[] content = getProcessedFileContent(year);
            int totalProfit = 0;
            int totalExpense = 0;
            ArrayList<Integer> monthlyExpense = new ArrayList<>();
            ArrayList<Integer> monthlyProfit = new ArrayList<>();
            ArrayList<Integer> monthlyMarign = new ArrayList<>();

            for (int i = 1; i < content.length; i++) {                                                    // parsing lines from the file
                String[] splitVals = content[i].split(",");
                String month = convertMonthToWords(splitVals[0]);
                int value = Integer.parseInt(splitVals[1]);
                boolean isExpense = Boolean.parseBoolean(splitVals[2]);
                if (isExpense) {
                    monthlyExpense.add(value);
                } else  {
                    monthlyProfit.add(value);
                }
            }

            for (int i = 0; i < monthlyExpense.size(); i++) {
                int margin = monthlyProfit.get(i) - monthlyExpense.get(i);
                monthlyMarign.add(margin);
            }
            for (int val : monthlyExpense){
                totalExpense+= val;
            }
            for (int val : monthlyProfit){
                totalProfit+= val;
            }


            System.out.println("Вывожу данные за " + year + " год.");
            for (int i = 0; i < monthlyMarign.size(); i++) {
                String monthName = Integer.toString(i+1);
                if (i < 10){
                    monthName = 0 + monthName;
                }
                System.out.println("Прибыль за " + convertMonthToWords(monthName) + ": " + monthlyMarign.get(i));
            }
            System.out.println("Средняя прибыль в году: " + totalProfit / monthlyProfit.size());
            System.out.println("Средние траты в году: " + totalExpense / monthlyExpense.size());
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
