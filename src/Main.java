import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run();
    }

    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean f = true;

        while (f) {
            System.out.println("-----------------------------------------------------------------------------------------------");
            System.out.println("Выберите кодировщик из списка ниже:");
            System.out.println("-----------------------------------------------------------------------------------------------");
            System.out.println("""
                    1. Шифр Blowfish
                    2. Шифр Цезаря
                    3. Шифр Вернама (XOR-шифр)
                    4. Шифр Плейфера
                    Введите 0 чтобы выйти из программы""");
            System.out.println("-----------------------------------------------------------------------------------------------");
            System.out.print("Выберите кодировщик: ");

            try {
                String actionString = scanner.nextLine();
                int n = Integer.parseInt(actionString);

                switch (n) {
                    case 0:
                        f = false;
                        break;
                    case 1:
                        BlowfishExample.Star_coder();
                        break;
                    case 2:
                        CaesarCipher.Start_coder();
                        break;
                    case 3:
                        VernamCipher.Start_coder();
                        break;
                    case 4:
                        PlayfairCipher.Start_coder();
                        break;
                    default:
                        System.out.println("Введите указанные номера заданий!");
                }
            } catch (NumberFormatException e) {
                // Обработка ситуации, когда введено не число
                System.out.println("Введите указанные номера заданий!");
            }
        }

        // Закрытие Scanner только после завершения работы программы
        scanner.close();
    }
}
