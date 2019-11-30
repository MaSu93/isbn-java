// Validierung einer ISBN
class isbn
{
  public static void checkISBN(String isbn) {
    System.out.println(String.format("Die eingegebene ISBN %s wird überprüft...", isbn));

    // Entferne die Bindestriche
    String _isbn = isbn.replace("-", "");

    // Prüfe, ob ISBN-13 oder ISBN-10
    Boolean isPrefixed = _isbn.substring(0, 3).equals("978") || _isbn.substring(0, 3).equals("979");
    Integer isbnLength = _isbn.length();
    Boolean isISBN13 = isPrefixed && isbnLength == 13;
    Boolean isISBN10 = isbnLength == 10 && !isPrefixed;
    
    // Abbruch, falls weder ISBN-13 noch ISBN-10
    if (!(isbnLength == 10 || isISBN13)) System.out.println("Die ISBN ist ungültig!");

    // Abbruch, falls Eingabe nicht aus Ziffern besteht
    if (!_isbn.matches("[0-9]+")) System.out.println("Die ISBN darf nur aus Ziffern bestehen!");

    // Validierungsvariable
    Boolean isValid = false;

    // Berechnung der Prüfziffer für ISBN-10
    if (isISBN10) {
      Integer[] digits = new Integer[isbnLength];
      for (Integer i = 0; i < isbnLength; i++) {
        Integer indexValue = Character.getNumericValue(_isbn.charAt(i));
        digits[i] = indexValue;
      }

      Integer calcPruefziffer = (1*digits[0] + 2*digits[1] + 3*digits[2] + 4*digits[3] + 5*digits[4] + 6*digits[5] + 7*digits[6] + 8*digits[7] + 9*digits[8]) % 11;
      Boolean checkPruefziffer = calcPruefziffer == digits[9];
      isValid = checkPruefziffer;
    }

    // Berechnung der Prüfziffer für ISBN-13
    if (isISBN13) {
      Integer[] digits = new Integer[isbnLength];
      for (Integer i = 0; i < isbnLength; i++) {
        Integer indexValue = Character.getNumericValue(_isbn.charAt(i));
        digits[i] = indexValue;
      }

      Integer calcPruefziffer = (10 - ((digits[0] + digits[2] + digits[4] + digits[6] + digits[8] + digits[10] + 3*(digits[1] + digits[3] + digits[5] + digits[7] + digits[9] + digits[11])) % 10 )) % 10;
      Boolean checkPruefziffer = calcPruefziffer == digits[12];
      isValid = checkPruefziffer;
    }

    // Überprüfe Gültigkeit von ISBN-10 oder ISBN-13
    System.out.println(
      isValid
        ? String.format("Die eingegebene ISBN %s ist gültig!", isbn)
        : String.format("Die eingegebene ISBN %s ist ungültig!", isbn)
    );
  }
    
  public static void main (String[] args) throws java.lang.Exception
  {
    System.out.println("Starte ISBN Prüftool");
    System.out.println("Bitte geben Sie eine ISBN ein.");
    String isbn = System.console().readLine();
    if (isbn.equals("")) isbn = "978-3-86680-192-9";
    checkISBN(isbn);
  }
}
