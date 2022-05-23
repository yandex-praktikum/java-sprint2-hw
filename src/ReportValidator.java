import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportValidator {
    YearlyReportManager yearly = new YearlyReportManager();
    MonthlyReportManager monthly = new MonthlyReportManager();
    FileProcessor processor = new FileProcessor();


    private HashMap<String, ArrayList<Integer>> collectYearlyData() {
        HashMap<String, ArrayList<Integer>> yearlyReportData = new HashMap<>();
        for (String year : processor.readFileContentsOrNull(yearly.TYPE).keySet()) {
            String[] content = processor.getProcessedFileContent(year);
            yearlyReportData = yearly.getData(content);

        }
        return yearlyReportData;
    }

    private HashMap<String, ArrayList<Integer>> collectMonthlyData() {
        HashMap<String, ArrayList<Integer>> monthlyReportData;
        monthlyReportData = monthly.getData();
        return monthlyReportData;
    }

    public void compareReports() {
        HashMap<String, ArrayList<Integer>> yearlyReportData = collectYearlyData();
        HashMap<String, ArrayList<Integer>> monthlyReportData = collectMonthlyData();

        for(String keyYear : yearlyReportData.keySet()){
            for (String keyMonth : monthlyReportData.keySet()) {
                if (keyYear.equals(keyMonth)) {
                    if (yearlyReportData.get(keyYear).equals(monthlyReportData.get(keyMonth))) {
                        System.out.println("Проверяю данные за " + keyMonth.toLowerCase() + ". Данные валидны.");
                    } else {
                        System.out.println("Проверяю данные за " + keyMonth.toLowerCase() + ". Данные не валидны.");
                        System.out.println("Отчет за месяц содержит: \n" +
                                "- суммарный расход " + monthlyReportData.get(keyMonth).get(0) + "; \n" +
                                "- суммарный доход " + monthlyReportData.get(keyMonth).get(1) + ". \n" +
                                "Отчет за год содержит: \n" +
                                "- суммарный расход " + yearlyReportData.get(keyYear).get(0) + "; \n" +
                                "- суммарный доход " + yearlyReportData.get(keyYear).get(1) + ". \n");
                    }
                }
            }
        }
    }
}