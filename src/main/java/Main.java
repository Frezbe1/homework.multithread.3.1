import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static AtomicInteger count3 = new AtomicInteger(0);
    public static AtomicInteger count4 = new AtomicInteger(0);
    public static AtomicInteger count5 = new AtomicInteger(0);

    public static void main(String[] args) {

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        new Thread(() -> {
            for (String text : texts) {
                if (isPalindrome(text)) {
                    if (text.length() == 3) {
                        count3.getAndIncrement();
                    }
                    if (text.length() == 4) {
                        count4.getAndIncrement();
                    }
                    if (text.length() == 5) {
                        count5.getAndIncrement();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (String text : texts) {
                if (isEqual(text)) {
                    if (text.length() == 3) {
                        count3.getAndIncrement();
                    }
                    if (text.length() == 4) {
                        count4.getAndIncrement();
                    }
                    if (text.length() == 5) {
                        count5.getAndIncrement();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            for (String text : texts) {
                if (isAscending(text)) {
                    if (text.length() == 3) {
                        count3.getAndIncrement();
                    }
                    if (text.length() == 4) {
                        count4.getAndIncrement();
                    }
                    if (text.length() == 5) {
                        count5.getAndIncrement();
                    }
                }
            }
        }).start();

        System.out.println("Красивых слов с длиной 3: " + count3);
        System.out.println("Красивых слов с длиной 4: " + count4);
        System.out.println("Красивых слов с длиной 5: " + count5);
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static boolean isPalindrome(String text) {
        if (!isEqual(text)) {
            return text.equals(new StringBuilder(text).reverse().toString());
        }
        return false;
    }

    public static boolean isAscending(String text) {
        if (!isEqual(text)) {
            if (text == null || text.length() == 0) {
                return false;
            }
            for (int i = 1; i < text.length(); i++) {
                if (text.charAt(i) < text.charAt(i - 1))
                    return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isEqual(String text) {
        if (text == null || text.length() == 0) {
            return false;
        }
        for (int i = 1; i < text.length(); i++) {
            if (text.charAt(i) == text.charAt(i - 1))
                return false;
        }
        return true;
    }
}
