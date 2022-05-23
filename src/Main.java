import java.util.Scanner;
public class Main {

    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        FileProcessor processor = new FileProcessor();
        MonthlyReportManager monthly = new MonthlyReportManager();
        YearlyReportManager yearly = new YearlyReportManager();
        ReportValidator validator = new ReportValidator();

        while (true) {
            printMenu();
            int input = reader.nextInt();
            //int input = 3; // hardcoded for testing

            switch (input) {
                case 1:
                    processor.readFileContentsOrNull(monthly.TYPE);
                    break;
                case 2:
                    processor.readFileContentsOrNull(yearly.TYPE);
                    break;
                case 3:
                    validator.compareReports();
                    break;
                case 4:
                    monthly.getMonthlyReports();
                    break;
                case 5:
                    yearly.getYearlyReport();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Такой команды еще нет");
                    break;
            }
        }
    }
    private static void printMenu(){
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
}

