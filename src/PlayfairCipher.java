import java.util.Scanner;

//Шифр Плейфера
//Работает!
public class PlayfairCipher {

    private static final String ENGLISH_ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
    private static final String RUSSIAN_ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    private static final int MATRIX_SIZE = 5;

    private static char[][] generateKeyMatrix(String key, String alphabet) {
        char[][] matrix = new char[MATRIX_SIZE][MATRIX_SIZE];
        String keyWithoutDuplicates = removeDuplicates(key.toUpperCase() + alphabet);

        int k = 0;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                matrix[i][j] = keyWithoutDuplicates.charAt(k++);
            }
        }
        return matrix;
    }

    private static String removeDuplicates(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (result.indexOf(String.valueOf(c)) == -1) {
                result.append(c);
            }
        }
        return result.toString();
    }

    private static String prepareText(String text, String alphabet) {
        text = text.toUpperCase().replaceAll("[^" + alphabet + "]", "");
        StringBuilder preparedText = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char firstChar = text.charAt(i);
            char secondChar = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X';

            preparedText.append(firstChar);

            if (firstChar == secondChar) {
                preparedText.append('X');
            }

            preparedText.append(secondChar);
        }

        return preparedText.toString();
    }

    private static String playfairEncrypt(String plaintext, char[][] keyMatrix) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            char firstChar = plaintext.charAt(i);
            char secondChar = (i + 1 < plaintext.length()) ? plaintext.charAt(i + 1) : 'X';

            int[] firstCharPosition = findCharPosition(keyMatrix, firstChar);
            int[] secondCharPosition = findCharPosition(keyMatrix, secondChar);

            if (firstCharPosition != null && secondCharPosition != null) {
                if (firstCharPosition[0] == secondCharPosition[0]) {
                    ciphertext.append(keyMatrix[firstCharPosition[0]][(firstCharPosition[1] + 1) % MATRIX_SIZE]);
                    ciphertext.append(keyMatrix[secondCharPosition[0]][(secondCharPosition[1] + 1) % MATRIX_SIZE]);
                } else if (firstCharPosition[1] == secondCharPosition[1]) {
                    ciphertext.append(keyMatrix[(firstCharPosition[0] + 1) % MATRIX_SIZE][firstCharPosition[1]]);
                    ciphertext.append(keyMatrix[(secondCharPosition[0] + 1) % MATRIX_SIZE][secondCharPosition[1]]);
                } else {
                    ciphertext.append(keyMatrix[firstCharPosition[0]][secondCharPosition[1]]);
                    ciphertext.append(keyMatrix[secondCharPosition[0]][firstCharPosition[1]]);
                }
            }
        }

        return ciphertext.toString();
    }

    private static String playfairDecrypt(String ciphertext, char[][] keyMatrix) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char firstChar = ciphertext.charAt(i);
            char secondChar = (i + 1 < ciphertext.length()) ? ciphertext.charAt(i + 1) : 'X';

            int[] firstCharPosition = findCharPosition(keyMatrix, firstChar);
            int[] secondCharPosition = findCharPosition(keyMatrix, secondChar);

            if (firstCharPosition != null && secondCharPosition != null) {
                if (firstCharPosition[0] == secondCharPosition[0]) {
                    plaintext.append(keyMatrix[firstCharPosition[0]][(firstCharPosition[1] - 1 + MATRIX_SIZE) % MATRIX_SIZE]);
                    plaintext.append(keyMatrix[secondCharPosition[0]][(secondCharPosition[1] - 1 + MATRIX_SIZE) % MATRIX_SIZE]);
                } else if (firstCharPosition[1] == secondCharPosition[1]) {
                    plaintext.append(keyMatrix[(firstCharPosition[0] - 1 + MATRIX_SIZE) % MATRIX_SIZE][firstCharPosition[1]]);
                    plaintext.append(keyMatrix[(secondCharPosition[0] - 1 + MATRIX_SIZE) % MATRIX_SIZE][secondCharPosition[1]]);
                } else {
                    plaintext.append(keyMatrix[firstCharPosition[0]][secondCharPosition[1]]);
                    plaintext.append(keyMatrix[secondCharPosition[0]][firstCharPosition[1]]);
                }
            }
        }

        return plaintext.toString();
    }

    private static int[] findCharPosition(char[][] keyMatrix, char target) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (keyMatrix[i][j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void Start_coder() {
        Scanner scanner = new Scanner(System.in);
        String selectedAlphabet;

        while (true) {
            System.out.print("Выберите алфавит (1 - Английский, 2 - Русский): ");

            if (scanner.hasNextInt()) {
                int alphabetChoice = scanner.nextInt();

                if (alphabetChoice == 1) {
                    selectedAlphabet = ENGLISH_ALPHABET;
                    break;
                } else if (alphabetChoice == 2) {
                    selectedAlphabet = RUSSIAN_ALPHABET;
                    break;
                } else {
                    System.out.println("Неверный выбор алфавита. Пожалуйста, выберите 1 или 2.");
                    // Очистка буфера перед новым вводом
                    scanner.nextLine();
                }
            } else {
                System.out.println("Неверный ввод. Пожалуйста, введите целое число.");
                // Очистка буфера перед новым вводом
                scanner.nextLine();
            }
        }

        System.out.print("Введите ключ: ");
        String key = scanner.next().toUpperCase();

        char[][] keyMatrix = generateKeyMatrix(key, selectedAlphabet);

        System.out.print("Выберите действие (1 - Зашифровать, 2 - Дешифровать): ");
        int action = scanner.nextInt();

        scanner.nextLine();  // Поглотить символ новой строки

        System.out.print("Введите текст: ");
        String text = scanner.nextLine().toUpperCase();

        String resultText = "";
        if (action == 1) {
            String preparedText = prepareText(text, selectedAlphabet);
            resultText = playfairEncrypt(preparedText, keyMatrix);
            System.out.println("Зашифрованный текст: " + resultText);
        } else if (action == 2) {
            resultText = playfairDecrypt(text, keyMatrix);
            System.out.println("Дешифрованный текст: " + resultText);
        } else {
            System.out.println("Неверное действие. Выберите 1 для шифрования или 2 для дешифрования.");
        }


    }
}
