public class IbanValidator {

    public static boolean validateIban(String iban) {
        if (iban == null) {
            return false;
        }

        String normalized = iban.replaceAll("\\s+", "").toUpperCase();

        if (normalized.length() < 15 || normalized.length() > 34) {
            return false;
        }

        if (!normalized.matches("^[A-Z]{2}[0-9]{2}[A-Z0-9]+$")) {
            return false;
        }

        String rearranged = normalized.substring(4) + normalized.substring(0, 4);
        StringBuilder numericIban = new StringBuilder();

        for (char ch : rearranged.toCharArray()) {
            if (Character.isLetter(ch)) {
                numericIban.append(ch - 'A' + 10);
            } else {
                numericIban.append(ch);
            }
        }

        return mod97(numericIban.toString()) == 1;
    }

    private static int mod97(String numericIban) {
        int remainder = 0;

        for (int i = 0; i < numericIban.length(); i++) {
            char ch = numericIban.charAt(i);
            remainder = (remainder * 10 + (ch - '0')) % 97;
        }

        return remainder;
    }

    public static void main(String[] args) {
        String[] testIbans = {
                "DE89370400440532013000",
                "GB82WEST12345698765432",
                "FR1420041010050500013M02606",
                "INVALID1234567890",
                "DE89370400440532013001"
        };

        boolean[] expectedResults = {
                true,
                true,
                true,
                false,
                false
        };

        for (int i = 0; i < testIbans.length; i++) {
            boolean result = validateIban(testIbans[i]);
            String status = result == expectedResults[i] ? "✓" : "✗";
            System.out.println(status + " " + testIbans[i] + ": " + result);
        }
    }
}