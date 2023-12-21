import java.util.Scanner;

//Работает!
//Шифр Цезаря
public class CaesarCipher {

    private static final int RUSSIAN_ALPHABET_SIZE = 33;

    // Метод для шифрования текста шифром Цезаря
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'А' : 'а';
                result.append((char) ((character - base + shift) % RUSSIAN_ALPHABET_SIZE + base));
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }

    // Метод для дешифрования текста, зашифрованного шифром Цезаря
    public static String decrypt(String text, int shift) {
        return encrypt(text, RUSSIAN_ALPHABET_SIZE - shift); // Для дешифровки используем обратный сдвиг
    }

    public static void Start_coder() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите текст: ");
        String originalText = scanner.nextLine();

        System.out.print("Выберите действие (1 - Зашифровать, 2 - Дешифровать): ");
        int action = scanner.nextInt();

        int shift;
        while (true) {
            System.out.print("Введите сдвиг: ");
            if (scanner.hasNextInt()) {
                shift = scanner.nextInt();
                break; // Выход из цикла, если введено целое число
            } else {
                System.out.println("Некорректный ввод. Введите целое число для сдвига.");
                scanner.next(); // Очистка буфера сканера перед повторным вводом
            }
        }

        // Обработка выбранного действия
        String resultText = "";
        if (action == 1) {
            resultText = encrypt(originalText, shift);
            System.out.println("Зашифрованный текст: " + resultText);
        } else if (action == 2) {
            resultText = decrypt(originalText, shift);
            System.out.println("Дешифрованный текст: " + resultText);
        } else {
            System.out.println("Неверное действие. Выберите 1 для шифрования или 2 для дешифрования.");
        }
    }
}
