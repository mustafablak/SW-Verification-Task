public class RegistrationSystem {
    public boolean validate(String fName, String lName, String email, String dob, String pass, String confirmPass) {
        // 1. Boş Alan Kontrolü
        if (fName == null || fName.isEmpty()) return false;
        if (lName == null || lName.isEmpty()) return false;
        if (email == null || email.isEmpty()) return false;
        if (dob == null || dob.isEmpty()) return false;
        if (pass == null || pass.isEmpty()) return false;

        // 2. Email Format Kontrolü (EP Tekniği)
        if (!email.contains("@") || !email.contains(".")) return false;

        // 3. Tarih Format Kontrolü (dd/mm/yyyy)
        if (!dob.matches("\\d{2}/\\d{2}/\\d{4}")) return false;

        // 4. Şifre Uzunluk Kontrolü (BVA Tekniği: En az 8 karakter)
        if (pass.length() < 8) return false;

        // 5. Şifre Eşleşme Kontrolü
        if (!pass.equals(confirmPass)) return false;

        return true;
    }
}