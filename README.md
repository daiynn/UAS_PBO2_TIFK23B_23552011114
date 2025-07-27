# UAS_PBO2_TIFK23B_23552011114

# Profil  
*Nama:* Derian  
*NIM:* 23552011114  
*Studi Kasus:* Kasir Bank

# Kasir Bank  
Kasir sistem keuangan seperti setor dan tarik tunai.

1. **Encapsulation (Pembungkusan Data)**  
Contoh: *User.java*, *Rekening.java*, *Transaksi.java*  
- Gunakan `private` untuk semua field → data tidak bisa diakses langsung.  
- Ada *getter-setter* dan *JavaFX Property* → kontrol akses dan reaktif di UI.

```java
private StringProperty username;

public String getUsername() {
    return username.get();
}

public void setUsername(String username) {
    this.username.set(username);
}
```

2. **Inheritance (Pewarisan)**  
Diterapkan seperti ini:

```java
public class Rekening extends Akun {}
```

3. **Polymorphism (Polimorfisme)**  
Diterapkan di view atau operasi bank.  
Method `cetakTransaksi(Transaksi t)` bisa dipanggil baik dari subclass *Setoran* atau *Penarikan*:

```java
public void cetakTransaksi(Transaksi t) {
    System.out.println(t.getTipe() + ": " + t.getJumlah());
}
```

4. **Abstraction (Abstraksi)**  
Contoh: *UserOperations*, *TransaksiOperations*, *DatabaseConnection*  
Semua class ini menyembunyikan detail implementasi SQL.

```java
public boolean loginUser(String username, String password) {
    // implementasi tersembunyi
}
```

# Demo Projek
Youtube : https://youtu.be/2lQcWpt4fZ4
