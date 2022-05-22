import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReportManager {
    FileProcessor processor = new FileProcessor();
    final public String TYPE = "y";

    public void getYearlyReport() {
        for (String year : processor.readFileContentsOrNull(TYPE).keySet()) {
            String[] content = processor.getProcessedFileContent(year);
            int totalProfit = 0;
            int totalExpense = 0;
            ArrayList<Integer> monthlyExpense = new ArrayList<>();
            ArrayList<Integer> monthlyProfit = new ArrayList<>();
            ArrayList<Integer> monthlyMarign = new ArrayList<>();

            for (int i = 1; i < content.length; i++) {                                                    // parsing lines from the file
                String[] splitVals = content[i].split(",");
                String month = processor.convertMonthToWords(splitVals[0]);
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
                System.out.println("Прибыль за " + processor.convertMonthToWords(monthName) + ": " + monthlyMarign.get(i));
            }
            System.out.println("Средняя прибыль в году: " + totalProfit / monthlyProfit.size());
            System.out.println("Средние траты в году: " + totalExpense / monthlyExpense.size());
        }
    }

}