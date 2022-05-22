import java.io.File;
import java.util.ArrayList;

public class ReportValidator {
    YearlyReportManager yearly = new YearlyReportManager();
    MonthlyReportManager monthly = new MonthlyReportManager();
    FileProcessor processor = new FileProcessor();

    public void getExpenseAndProfits() {
        for (String month : processor.readFileContentsOrNull(monthly.TYPE).keySet()) {
            String wordedMonthName = processor.convertMonthToWords(month.substring(month.length() - 2)); // get last 2 digit sof the file name and convert them
            String[] content = processor.getProcessedFileContent(month);

            System.out.println("Общие расходы за " + wordedMonthName + " - " + monthly.getExpense(content));
            System.out.println("Общие доходы за " + wordedMonthName + " - " + monthly.getProfit(content));
        }
    }


}