import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

//Алгоритм Blowfish
//Работает!
public class BlowfishExample {

    private static final String ALGORITHM = "Blowfish";

    // Метод для шифрования и дешифрования текста
    private static byte[] processText(byte[] data, byte[] keyBytes, int mode) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);

        if (mode == Cipher.ENCRYPT_MODE) {
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        } else if (mode == Cipher.DECRYPT_MODE) {
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
        }

        return cipher.doFinal(data);
    }

    public static void Star_coder() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите текст для шифрования: ");
        String originalText = scanner.nextLine();

        System.out.print("Введите ключ (от 1 до 56 байт): ");
        byte[] keyBytes = scanner.nextLine().getBytes();

        try {
            // Выбор действия
            System.out.println("Выберите действие (1 - Зашифровать, 2 - Дешифровать): ");
            int action = scanner.nextInt();
            scanner.nextLine(); // Поглотить символ новой строки

            byte[] resultBytes;
            if (action == 1) {
                // Шифрование
                resultBytes = processText(originalText.getBytes(), keyBytes, Cipher.ENCRYPT_MODE);
                System.out.println("Зашифрованный текст: " + Base64.getEncoder().encodeToString(resultBytes));
            } else if (action == 2) {
                // Дешифрование
                byte[] decodedBytes = Base64.getDecoder().decode(originalText);
                resultBytes = processText(decodedBytes, keyBytes, Cipher.DECRYPT_MODE);
                System.out.println("Дешифрованный текст: " + new String(resultBytes));
            } else {
                System.out.println("Неверное действие. Выберите 1 для шифрования или 2 для дешифрования.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
