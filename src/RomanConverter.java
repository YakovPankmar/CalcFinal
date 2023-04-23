public class RomanConverter {
    static int  romanInArabic(String romanChars) {

        int result = 0;
        char[] charString = romanChars.toCharArray();
        int stringWidth = charString.length;

        for (int i = 0; i < stringWidth; i++) {
            if (i == (stringWidth - 1)) {
                result += charValue(charString[i]);
                break;
            }
            if (charValue(charString[i]) >= charValue(charString[i+1]) ) {
                result += charValue(charString[i]);
            } else {
                result -= charValue(charString[i]);
            }
        }

        return result;
    }

    static int charValue(char input) {
        switch (input) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            default:
                throw new IllegalArgumentException("Неправильно задано число ");
        }
    }
    static String arabToRoman(int arabChars) {
        String result = "";
        char[] romanValue = {'C', 'L', 'X', 'V', 'I'};
        int[] arabValue = {100,50,10,5,1 };

        for(int i = 0; i < arabValue.length; i++) {
            while(arabChars >= arabValue[i]) {
                result += romanValue[i];
                arabChars -= arabValue[i];
            }
        }
        return result;
    }

}
