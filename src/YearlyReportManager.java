import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReportManager {
    FileProcessor processor = new FileProcessor();
    final public String TYPE = "y"; // to get the correct selection of files

    public void getYearlyReport() {
        /*
        Stats for yearly reports.
         */
        for (String year : processor.readFileContentsOrNull(TYPE).keySet()) {
            String[] content = processor.getProcessedFileContent(year);
            int totalExpense = 0;
            int totalProfit = 0;

            for (int val : getMonthlyExpenses(content)) {
                totalExpense += val;
            }
            for (int val : getMonthlyProfit(content)) {
                totalProfit += val;
            }
            System.out.println("Вывожу данные за " + year + " год.");
            for (int i = 0; i < getMonthlyMargin(content).size(); i++) {
                String monthName = Integer.toString(i + 1);
                if (i < 10) {
                    monthName = 0 + monthName;
                }
                System.out.println("Прибыль за " + processor.convertMonthToWords(monthName) + ": " + getMonthlyMargin(content).get(i));
            }
            System.out.println("Средний доход в году: " + totalProfit / getMonthlyProfit(content).size());
            System.out.println("Средние траты в году: " + totalExpense / getMonthlyExpenses(content).size());
        }
    }

    public HashMap<String, ArrayList<Integer>> getData (String[] content){
        /*
        Creates a HashMap with month name as Key and ArrayList of totalExpense and totalProfit.
        Used to validate yearly reports against monthly reports.
         */
        HashMap<String, ArrayList<Integer>> yearlyProfitAndExpense = new HashMap<>();
        for (int i = 0; i < getMonthlyExpenses(content).size(); i++) {
            String monthName = Integer.toString(i+1);
            if (i < 10){                // Need to add 0 to convert correctly.
                monthName = 0 + monthName;
            }
            monthName = processor.convertMonthToWords(monthName);
            if (yearlyProfitAndExpense.containsKey(monthName)) {
                yearlyProfitAndExpense.get(monthName).add(getMonthlyExpenses(content).get(i));
                yearlyProfitAndExpense.get(monthName).add(getMonthlyProfit(content).get(i));
            } else {
                ArrayList<Integer> values = new ArrayList<>();
                values.add(getMonthlyExpenses(content).get(i));
                values.add(getMonthlyProfit(content).get(i));
                yearlyProfitAndExpense.put(monthName, values);
            }
        }
        return yearlyProfitAndExpense;
    }
    public ArrayList<Integer> getMonthlyExpenses(String[] content) {
        ArrayList<Integer> monthlyExpense = new ArrayList<>();
        for (int i = 1; i < (content.length); i++) {
            if (content[i].contains(",")) {
                String[] splitVals = content[i].split(",");
                String month = processor.convertMonthToWords(splitVals[0]);
                int value = Integer.parseInt(splitVals[1]);
                boolean isExpense = Boolean.parseBoolean(splitVals[2]);
                if (isExpense) {
                    monthlyExpense.add(value);
                }
            }
        }
        return monthlyExpense;
    }

    public ArrayList<Integer> getMonthlyProfit(String[] content){
        ArrayList<Integer> monthlyProfit = new ArrayList<>();
        for (int i = 1; i < (content.length); i++) {
            if (content[i].contains(",")) {
                String[] splitVals = content[i].split(",");
                String month = processor.convertMonthToWords(splitVals[0]);
                int value = Integer.parseInt(splitVals[1]);
                boolean isExpense = Boolean.parseBoolean(splitVals[2]);
                if (!isExpense) {
                    monthlyProfit.add(value);
                }
            }
        }
        return monthlyProfit;
    }


    public ArrayList<Integer> getMonthlyMargin(String[] content){
        ArrayList<Integer> monthlyMargin = new ArrayList<>();
        for (int i = 0; i < getMonthlyExpenses(content).size(); i++) {
            int margin = getMonthlyProfit(content).get(i) - getMonthlyExpenses(content).get(i);
            monthlyMargin.add(margin);
        }
        return monthlyMargin;
    }
}