
import java.util.HashMap;

public class StepTracker {
	Converter converter = new Converter();

	int targetStepsDay = 10000;//Целевое количество шагов.

	public HashMap<String, Integer[]> mountTableStep = new HashMap<>();

	


	void getDayStep(String mount, Integer day, Integer step) {//Метод для внесения данных в таблицу HashMap
		// (Месяц, день, кол-во шагов)
		if (mountTableStep.containsKey(mount)) {//проверка на соответсвия существуещего месяца (ключа) в таблице
			Integer[] tmp = mountTableStep.get(mount);
			if (step<0){
				System.out.println("Количество шагов должно быть неотрицательным.");
			} else {
			tmp[day - 1] = step;//задаем по индексу=0(день) кол-во шагов
			mountTableStep.put(mount, tmp);}
		} else {// если месяц не найден, созадем новый массив (месяц) и вносим данные в массив Integer =(день, кол-во шагов)
			Integer[] fast = new Integer[30];// считается, что в месяце ровно 30 дней.
			if (step<0){
				System.out.println("Количество шагов должно быть неотрицательным.");
			} else {
				fast[day-1] = step;
				mountTableStep.put(mount, fast);
			}

		}
	}

	boolean isСheckFirstDay(String s) {//метод проверяет Если текущий день первый в месяце
		if (mountTableStep.containsKey(s)) {
			Integer[] value = mountTableStep.get(s);
			for (int i = 1; i < value.length; i++) {
				if (value[i] != null) {
					return true;
				}
			}

		}
		return false;
	}

	void printStaticMount (String p) {//Метод печатает статистику за определённый месяц;

		if (mountTableStep.containsKey(p)&&!(isСheckFirstDay(p))) {
			System.out.println("• Количество дней, за которые есть данные = 0;");
			System.out.println("\n • Количество пройденных шагов по дням = 0;"
					+"\n• Общее количество шагов за месяц = 0;"
					+"\n• Максимальное пройденное количество шагов в месяце = 0;"
					+"\n• Среднее количество шагов = 0;"
					+"\n• Пройденная дистанция (в км) = 0;"
					+"\n• Количество сожжённых килокалорий = 0;"
					+"\n• Лучшая серия: максимальное количество подряд идущих дней = 0;");
			//System.out.println("Месяц: " + p+"\nКоличество дней, за которые есть данные = 0;");
			//Количество пройденных шагов = 0;");
		}
		else if (mountTableStep.containsKey(p)&&
				isСheckFirstDay(p)) {// Печать за месяц Количества пройденных шагов по дням
			int day = 0;
			System.out.print("• Количество пройденных шагов по дням: ");
			Integer[] a = mountTableStep.get(p);
			for (Integer b : a) {
				if (b==null){
					b=0;
				}
				System.out.print(++day + " День: " + b);
				if (day < a.length) {
					System.out.print(", ");
				}
			}
			System.out.print(";");
		}
		else if (!(mountTableStep.containsKey(p))) {
			System.out.println("Такого месяца пока нет или ввод месяца неверный, возврат в меню выбора опций");


		}

		if (mountTableStep.containsKey(p)&&isСheckFirstDay(p)) {//Общее количество шагов за месяц
			int sumAllStep = 0;
			Integer[] a = mountTableStep.get(p);
			for (Integer b : a) {
				if (b==null){
					b=0;
				}
				sumAllStep+=b;
			}
			System.out.println("\n• Общее количество шагов за месяц: " +sumAllStep+";");
		}


		if (mountTableStep.containsKey(p)&&isСheckFirstDay(p)) {//Среднее количество шагов;
			int sumAllStep = 0;
			int averageSteps = 0;
			Integer[] a = mountTableStep.get(p);
			for (Integer b : a) {
				if (b==null){
					b=0;
				}
				if (b!=0){
					averageSteps++;
				}
				sumAllStep+=b;
			}
			averageSteps = sumAllStep/averageSteps;
			System.out.println("• Среднее количество шагов за месяц: " +averageSteps+";");
		}

		if (mountTableStep.containsKey(p) && isСheckFirstDay(p)) {//расчет пройденной дистанция (в км)

			int sumAllStep = 0;
			Integer[] a = mountTableStep.get(p);
			for (Integer b : a) {
				if (b == null) {
					b = 0;
				}
				sumAllStep += b;
			}
			double km = converter.convertInKm(sumAllStep);
			System.out.println("• Пройденная дистанция (в км): " + km + ";");

		}

		if (mountTableStep.containsKey(p) && isСheckFirstDay(p)) {//Подсчет Количество сожжённых килокалорий

			int sumAllStep = 0;
			Integer[] a = mountTableStep.get(p);
			for (Integer b : a) {
				if (b == null) {
					b = 0;
				}
				sumAllStep += b;
			}
			int fireCall = converter.convertKall(sumAllStep);
			System.out.println("• Количество сожжённых килокалорий: " + fireCall + ";");

		}

		if (mountTableStep.containsKey(p)&&isСheckFirstDay(p)) {//Лучшая серия: максимальное количество подряд идущих дней
			int day = 0;
			int max =0;
			Integer[] a = mountTableStep.get(p);
			System.out.print("• Лучшая серия: ");
			for (Integer b : a) {
				++day;
				if (b==null){
					b=0;
				}
				if (b>=targetStepsDay){//проверка с переменной целевого количества шагов.
					max = b;
					System.out.print(day + " День: " + b);
				}
				if (day < a.length&&b>=targetStepsDay) {
					System.out.print(", ");
				}

			}
			if (max==0){
				System.out.println("лучших результатов за этот месяц не найдено;");
			}

		}

	}

	int changeTargetSteps (int a){//метод по замене Целевого количество шагов.
		int change;
		if (a<0){
			System.out.println("Введённое значение не должно быть отрицательным.");
		}else {
			change = a;
			targetStepsDay = change;
		}
		return targetStepsDay;
	}

}
