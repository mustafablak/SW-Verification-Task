import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationSystemTest {
    RegistrationSystem system;

    @BeforeEach // SETUP METODU: Her testten önce sistemi sıfırlar
    void setUp() {
        system = new RegistrationSystem();
        System.out.println("Test hazırlığı yapıldı.");
    }

    // --- TEST SENARYOLARI (Toplam 15 Adet) ---

    @Test
    void TC01_ValidData() { // EP: Tamamen geçerli veri
        assertTrue(system.validate("Mustafa", "Ablak", "mrt@mail.com", "15/05/2000", "Sifre1234", "Sifre1234"));
    }

    @Test
    void TC02_EmptyFirstName() { // EP: Geçersiz (Boş isim)
        assertFalse(system.validate("", "Ablak", "mrt@mail.com", "15/05/2000", "Sifre1234", "Sifre1234"));
    }

    @Test
    void TC03_EmptyLastName() { // EP: Geçersiz (Boş soyisim)
        assertFalse(system.validate("Mustafa", "", "mrt@mail.com", "15/05/2000", "Sifre1234", "Sifre1234"));
    }

    @Test
    void TC04_InvalidEmailNoAt() { // EP: Geçersiz (E-postada @ yok)
        assertFalse(system.validate("Mustafa", "Ablak", "mrtmail.com", "15/05/2000", "Sifre1234", "Sifre1234"));
    }

    @Test
    void TC05_InvalidEmailNoDot() { // EP: Geçersiz (E-postada nokta yok)
        assertFalse(system.validate("Mustafa", "Ablak", "mrt@mailcom", "15/05/2000", "Sifre1234", "Sifre1234"));
    }

    @Test
    void TC06_PasswordTooShort() { // BVA: Sınır değer (7 karakter - Hatalı olmalı)
        assertFalse(system.validate("Mustafa", "Ablak", "mrt@mail.com", "15/05/2000", "1234567", "1234567"));
    }

    @Test
    void TC07_PasswordExactLimit() { // BVA: Sınır değer (Tam 8 karakter - Başarılı olmalı)
        assertTrue(system.validate("Mustafa", "Ablak", "mrt@mail.com", "15/05/2000", "12345678", "12345678"));
    }

    @Test
    void TC08_PasswordMismatch() { // EP: Şifreler uyuşmuyor
        assertFalse(system.validate("Mustafa", "Ablak", "mrt@mail.com", "15/05/2000", "Sifre1234", "Diger1234"));
    }

    @Test
    void TC09_InvalidDateFormat() { // EP: Tarih formatı yanlış (dd.mm.yyyy kullanılmış)
        assertFalse(system.validate("Mustafa", "Ablak", "mrt@mail.com", "15.05.2000", "Sifre1234", "Sifre1234"));
    }

    @Test
    void TC10_EmptyDate() { // EP: Doğum tarihi boş
        assertFalse(system.validate("Mustafa", "Ablak", "mrt@mail.com", "", "Sifre1234", "Sifre1234"));
    }

    @Test
    void TC11_EmptyEmail() { // EP: E-posta boş
        assertFalse(system.validate("Mustafa", "Ablak", "", "15/05/2000", "Sifre1234", "Sifre1234"));
    }

    @Test
    void TC12_EmptyPassword() { // EP: Şifre boş
        assertFalse(system.validate("Mustafa", "Ablak", "mrt@mail.com", "15/05/2000", "", ""));
    }

    @Test
    void TC13_LongPassword() { // EP: Çok uzun şifre (Geçerli olmalı)
        assertTrue(system.validate("Mustafa", "Ablak", "mrt@mail.com", "15/05/2000", "Sifre1234567890", "Sifre1234567890"));
    }

    @Test
    void TC14_NumericalFirstName() { // EP: İsimde sadece sayılar (Mantıksal kontrol)
        // Eğer sistemde isimde sayı yasaksa assertFalse yapılabilir, şu anki kodda sadece boşluk kontrolü var
        assertTrue(system.validate("12345", "Ablak", "mrt@mail.com", "15/05/2000", "Sifre1234", "Sifre1234"));
    }

    @Test
    void TC15_EmptyConfirmPassword() { // EP: Şifre onayı boş
        assertFalse(system.validate("Mustafa", "Ablak", "mrt@mail.com", "15/05/2000", "Sifre1234", ""));
    }

    @AfterEach // TEARDOWN METODU: Her testten sonra temizlik yapar
    void tearDown() {
        system = null;
        System.out.println("Test sonrası temizlik yapıldı.");
    }
    @Test
    void TC16_InvalidDateDigits() { // BVA: Tarih kısmında fazla rakam girilmesi (Örn: 155/05/2000)
        // Bizim kodumuz sadece 2 rakam (dd) bekliyor, 3 rakam girersek fail (false) vermeli.
        assertFalse(system.validate("Mustafa", "Ablak", "mrt@mail.com", "155/05/2000", "Sifre1234", "Sifre1234"));
    }
}
