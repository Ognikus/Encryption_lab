import java.util.Scanner;

//Шифр Вернама (XOR-шифр)
//Работает!
public class VernamCipher {

    // Метод для шифрования и дешифрования текста XOR-шифром
    private static String vernamCipher(String text, String key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char textChar = text.charAt(i);
            char keyChar = key.charAt(i % key.length());

            // Применение XOR к символам текста и ключа
            char processedChar = (char) (textChar ^ keyChar);
            result.append(processedChar);
        }

        return result.toString();
    }

    public static void Start_coder() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите текст: ");
        String originalText = scanner.nextLine();

        System.out.print("Введите ключ: ");
        String key = scanner.nextLine();

        System.out.print("Выберите действие (1 - Зашифровать, 2 - Дешифровать): ");
        int action = scanner.nextInt();

        String resultText = "";
        if (action == 1) {
            // Шифрование
            resultText = vernamCipher(originalText, key);
            System.out.println("Зашифрованный текст: " + resultText);
        } else if (action == 2) {
            // Дешифрование
            resultText = vernamCipher(originalText, key);
            System.out.println("Дешифрованный текст: " + resultText);
        } else {
            System.out.println("Неверное действие. Выберите 1 для шифрования или 2 для дешифрования.");
        }


    }
}
