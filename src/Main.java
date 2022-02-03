
import java.util.Scanner;

public class Main {

	public static void printMenu() {//метод списка команд
		System.out.println("1 - Ввести количество шагов за определённый день;");
		System.out.println("2 - Напечатать статистику за определённый месяц;");
		System.out.println("3 - Изменить цель по количеству шагов в день;");
		System.out.println("0 - Выйти из приложения.");

	}

	public static void main(String[] args) {
		
		StepTracker stepTrackerUser = new StepTracker();
		Scanner scanner = new Scanner(System.in);//работа с консолью
		System.out.println("Прототип приложения «Счётчик калорий».");

		while (true) {// бесконечный цикл
			System.out.println("------------------------------------");
			System.out.println("Выберите нужную Вам опцию, из списка действий");//Выберите нужный вариант действия!
			System.out.println("------------------------------------");
			printMenu();// вызов метода меню
			int command = scanner.nextInt();

			if (command == 1) {
				System.out.println("Введите нужный Вам месяц:\n" +
						"\"январь\", \"февраль\", \"март\", \"апрель\", \"май\", \"июнь\"" +
						", \"июль\", \"август\", \"сентябрь\", \"октябрь\", \"ноябрь\", \"декабрь\".");
				String mountIn = scanner.next();//переменная ввода месяца через консоль
				System.out.println("Введите день:");
				Integer dayIn = scanner.nextInt();//переменная для ввода дня
				System.out.println("Введите количество пройденных шагов:");
				Integer stepDayIn = scanner.nextInt();//переменная для ввода дня
				System.out.println("Вы добавили "+ stepDayIn+" шагов в " +dayIn +"-й день, месяца - " +mountIn);
				stepTrackerUser.getDayStep(mountIn,dayIn,stepDayIn);
			} else if (command == 2) {
				System.out.println("Введите нужный Вам месяц:\n" +
						"\"январь\", \"февраль\", \"март\", \"апрель\", \"май\", \"июнь\"" +
						"\"июль\", \"август\", \"сентябрь\", \"октябрь\", \"ноябрь\", \"декабрь\".");
				String mountStatic = scanner.next();//переменная ввода месяца через консоль
				stepTrackerUser.printStaticMount(mountStatic);
				System.out.println();
			} else if (command == 3) {
				System.out.println("Введите новое значение целевого количества шагов:");
				int value = scanner.nextInt();
				int change=stepTrackerUser.changeTargetSteps(value);
				System.out.println("значение целевого количества шагов: " + change);


			} else if (command == 0) {
				System.out.println("Выход");
				break;
			} else {
				System.out.println("Извините, такой команды пока нет.");
			}
		}
	}
}
