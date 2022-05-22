import java.io.IOException;
import java.util.Scanner;
public class Main {

    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        MonthlyReportManager monthly = new MonthlyReportManager();

        while (true) {
            printMenu();
            int input = reader.nextInt();
            //int input = 1; // hardcoded for testing
            switch (input) {
                case 1:
                    monthly.readFileContentsOrNull();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    monthly.getMonthlyReports();
                    break;
                case 5:
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

