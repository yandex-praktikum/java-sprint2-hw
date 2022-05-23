import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReportManager {
    FileProcessor processor = new FileProcessor();
    final public String TYPE = "m"; // to get the correct selection of files

    public void getMonthlyReports() {
        /*
        Provides stats for monthly reports.
         */
        for (String month : processor.readFileContentsOrNull(TYPE).keySet()) {
            String wordedMonthName = processor.convertMonthToWords(month.substring(month.length() - 2)); // get last 2 digit sof the file name and convert them
            String[] content = processor.getProcessedFileContent(month);

            String mostProfitableItem = getMostProfitableItem(content).get(0);
            int biggestProfit = Integer.parseInt(getMostProfitableItem(content).get(1));
            String biggestExpenseItem = getMaxExpense(content).get(0);
            int biggestExpenseValue = Integer.parseInt(getMaxExpense(content).get(1));

            System.out.println("Вывожу данные за " + wordedMonthName + ".");
            System.out.println("Наибоее прибыльный товар: " + mostProfitableItem + ". Общий доход: " + biggestProfit);
            System.out.println("Наибольшая трата: " + biggestExpenseItem + ". Общая сумма трат: " + biggestExpenseValue);
        }
    }

    public HashMap<String, ArrayList<Integer>> getData() {
        /*
        Creates a HashMap with month name as Key and ArrayList of totalExpense and totalProfit.
        Used to validate monthly reports against yearly reports.
         */
        HashMap<String, ArrayList<Integer>> monthlyProfitAndExpense = new HashMap<>();
        for (String month : processor.readFileContentsOrNull(TYPE).keySet()) {
            String wordedMonthName = processor.convertMonthToWords(month.substring(month.length() - 2));
            String[] content = processor.getProcessedFileContent(month);
            ArrayList<Integer> values = new ArrayList<>();
            monthlyProfitAndExpense.put(wordedMonthName, values);

            int totalExpense = getExpense(content);
            int totalProfit = getProfit(content);
            monthlyProfitAndExpense.get(wordedMonthName).add(totalExpense);
            monthlyProfitAndExpense.get(wordedMonthName).add(totalProfit);
        }

        return monthlyProfitAndExpense;
    }

    public int getExpense(String[] content) {
        int totalExpense = 0;
        ArrayList<Integer> totalMonthlyExpense = new ArrayList<>();

        for (int i = 1; i < content.length; i++) {
            String[] splitVals = content[i].split(",");
            String item = splitVals[0];
            boolean isExpense = Boolean.parseBoolean(splitVals[1]);
            int qty = Integer.parseInt(splitVals[2]);
            int value = Integer.parseInt(splitVals[3]);
            int expense = 0;
            if (isExpense) {
                expense += (value * qty);
            }
            totalMonthlyExpense.add(expense);
        }

        for (Integer val : totalMonthlyExpense) {
            totalExpense += val;
        }
        return totalExpense;
    }
    public int getProfit(String[] content) {
        int totalProfit = 0;
        ArrayList<Integer> totalMonthlyProfit = new ArrayList<>();

        for (int i = 1; i < content.length; i++) {
            String[] splitVals = content[i].split(",");
            String item = splitVals[0];
            boolean isExpense = Boolean.parseBoolean(splitVals[1]);
            int qty = Integer.parseInt(splitVals[2]);
            int value = Integer.parseInt(splitVals[3]);
            int profit = 0;
            if (!isExpense) {
                profit += (value * qty);
            }

            totalMonthlyProfit.add(profit);
        }

        for (Integer val : totalMonthlyProfit) {
            totalProfit += val;
        }
        return totalProfit;
    }
    public ArrayList<String> getMaxExpense(String[] content){
        int maxExpense = 0;
        String biggestExpense = "";
        ArrayList<String> maxExpenseList = new ArrayList<>();
        for (int i = 1; i < content.length; i++){
            String[] splitVals = content[i].split(",");
            String item = splitVals[0];
            boolean isExpense = Boolean.parseBoolean(splitVals[1]);
            int qty = Integer.parseInt(splitVals[2]);
            int value = Integer.parseInt(splitVals[3]);
            if(isExpense) {
                int total = qty * value;
                if (total > maxExpense) {
                    maxExpense = total;
                    biggestExpense = item;
                }
            }
        }
        maxExpenseList.add(biggestExpense);
        maxExpenseList.add(Integer.toString(maxExpense));

        return maxExpenseList;
    }
    public ArrayList<String> getMostProfitableItem(String[] content){
        int maxProfit = 0;
        String mostProfitableItem = "";
        ArrayList<String> MostProfitableList = new ArrayList<>();
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
            }
        }
        MostProfitableList.add(mostProfitableItem);
        MostProfitableList.add(Integer.toString(maxProfit));

        return MostProfitableList;
    }


}

