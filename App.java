import java.util.Scanner;
import java.security.MessageDigest;
import aplikasi.CRUDKaryawan;
import aplikasi.CRUDMobil;

public class App {
    static class Booking {
        public int id;
        public String type;
        public String customer;
        public String route;
        public int mobilIndex;
        public String note;

        public Booking(int id, String type, String customer, String route, int mobilIndex, String note) {
            this.id = id;
            this.type = type;
            this.customer = customer;
            this.route = route;
            this.mobilIndex = mobilIndex;
            this.note = note;
        }
    }

    private static void adminMenu(Scanner sc, CRUDKaryawan crudK, CRUDMobil crudM, String[] routes, Booking[] bookings,
            boolean[] mobilBooked) {
        while (true) {
            System.out.println("--- Menu Admin ---");
            System.out.println("1. Manajemen Karyawan");
            System.out.println("2. Manajemen Mobil");
            System.out.println("3. Modul Pemesanan Travel & Penyewaan Mobil");
            System.out.println("0. Logout");
            int pilih = readIntInRange(sc, "Pilih: ", 0, 3);
            if (pilih == 0)
                break;
            switch (pilih) {
                case 1:
                    karyawanMenu(sc, crudK);
                    break;
                case 2:
                    mobilMenu(sc, crudM);
                    break;
                case 3:
                    travelMenu(sc, crudM, routes, bookings, mobilBooked);
                    break;
            }
        }
    }

    private static void userMenu(Scanner sc, CRUDMobil crudM, String[] routes, Booking[] bookings,
            boolean[] mobilBooked) {
        while (true) {
            System.out.println("--- Menu User ---");
            System.out.println("1. Modul Pemesanan Travel & Penyewaan Mobil");
            System.out.println("0. Logout");
            int pilih = readIntInRange(sc, "Pilih: ", 0, 1);
            if (pilih == 0)
                break;
            if (pilih == 1) {
                travelMenu(sc, crudM, routes, bookings, mobilBooked);
            }
        }
    }

    private static void mobilMenu(Scanner sc, CRUDMobil crudM) {
        while (true) {
            System.out.println("--- Manajemen Mobil ---");
            System.out.println("1. Lihat daftar mobil");
            System.out.println("2. Tambah mobil");
            System.out.println("3. Perbarui mobil");
            System.out.println("4. Hapus mobil");
            System.out.println("0. Kembali");
            int p = readIntInRange(sc, "Pilih: ", 0, 4);
            if (p == 0)
                break;
            switch (p) {
                case 1:
                    crudM.cetakMobil2();
                    break;
                case 2: {
                    int pos = readIntInRange(sc, "Posisi (1-" + crudM.jumlahMobil + "): ", 1, crudM.jumlahMobil);
                    System.out.print("No Plat: ");
                    String noPlat = sc.nextLine();
                    System.out.print("Merk Mobil: ");
                    String merk = sc.nextLine();
                    System.out.print("Warna Mobil: ");
                    String warna = sc.nextLine();
                    int tahun = readInt(sc, "Tahun Keluaran: ");
                    crudM.tambahMobil(pos, noPlat, merk, warna, tahun);
                    System.out.println("Mobil ditambahkan.");
                    break;
                }
                case 3: {
                    int pos = readIntInRange(sc, "Urutan mobil untuk diperbarui (1-" + crudM.jumlahMobil + "): ", 1,
                            crudM.jumlahMobil);
                    System.out.print("No Plat: ");
                    String noPlat = sc.nextLine();
                    System.out.print("Merk Mobil: ");
                    String merk = sc.nextLine();
                    System.out.print("Warna Mobil: ");
                    String warna = sc.nextLine();
                    int tahun = readInt(sc, "Tahun Keluaran: ");
                    crudM.perbaruiMobil(pos, noPlat, merk, warna, tahun);
                    System.out.println("Data mobil diperbarui.");
                    break;
                }
                case 4: {
                    int pos = readIntInRange(sc, "Urutan mobil untuk dihapus (1-" + crudM.jumlahMobil + "): ", 1,
                            crudM.jumlahMobil);
                    crudM.hapusMobil(pos);
                    System.out.println("Mobil dihapus.");
                    break;
                }
            }
        }
    }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Input tidak valid. Masukkan angka yang benar.");
            }
        }
    }

    private static int readIntInRange(Scanner sc, String prompt, int min, int max) {
        while (true) {
            int v = readInt(sc, prompt);
            if (v < min || v > max) {
                System.out.println("Masukan di luar rentang. Masukkan antara " + min + " sampai " + max + ".");
                continue;
            }
            return v;
        }
    }

    private static String sha256(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(s.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes)
                sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean verifyAdminPassword(Scanner sc) {
        final String expectedHash = sha256("password");
        int attempts = 3;
        for (int i = 0; i < attempts; i++) {
            System.out.print("Masukkan password admin: ");
            String input = sc.nextLine();
            String h = sha256(input);
            if (h.equals(expectedHash)) {
                System.out.println("Autentikasi berhasil.");
                return true;
            } else {
                int left = attempts - i - 1;
                System.out.println("Password salah." + (left > 0 ? (" Sisa percobaan: " + left) : ""));
            }
        }
        System.out.println("Gagal autentikasi. Akses admin ditolak.");
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CRUDKaryawan crudK = new CRUDKaryawan(10);
        CRUDMobil crudM = new CRUDMobil(10);

        Booking[] bookings = new Booking[50];
        boolean[] mobilBooked = new boolean[crudM.jumlahMobil];

        String[] routes = {
                "Surabaya - Malang",
                "Madura - Malang",
                "Banyuwangi - Malang",
                "Situbondo - Malang",
                "Tulungagung - Malang"
        };

        while (true) {
            System.out.println("=== Pilih Role ===");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("0. Keluar");
            int role = readIntInRange(sc, "Pilih role: ", 0, 2);
            if (role == 0) {
                System.out.println("Terima kasih. Sampai jumpa.");
                break;
            }

            if (role == 1) { 
                if (verifyAdminPassword(sc)) {
                    adminMenu(sc, crudK, crudM, routes, bookings, mobilBooked);
                } else {
                    System.out.println("Kembali ke pemilihan role.");
                }
            } else { 
                userMenu(sc, crudM, routes, bookings, mobilBooked);
            }
        }

        sc.close();
    }

    private static void karyawanMenu(Scanner sc, CRUDKaryawan crudK) {
        while (true) {
            System.out.println("--- Modul Karyawan ---");
            System.out.println("1. Lihat semua karyawan");
            System.out.println("2. Tambah karyawan");
            System.out.println("3. Perbarui karyawan");
            System.out.println("4. Hapus karyawan");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            String p = sc.nextLine();
            if (p.equals("0"))
                break;

            switch (p) {
                case "1":
                    crudK.cetakKaryawan2();
                    break;
                case "2":
                    int pos = readIntInRange(sc, "Posisi (1-" + crudK.jumlahKaryawan + "): ", 1, crudK.jumlahKaryawan);
                    System.out.print("Nama: ");
                    String nama = sc.nextLine();
                    System.out.print("Alamat: ");
                    String alamat = sc.nextLine();
                    System.out.print("No Telp: ");
                    String telp = sc.nextLine();
                    String jk = "";
                    while (true) {
                        System.out.print("Jenis Kelamin (L/P): ");
                        String jkInput = sc.nextLine().trim();
                        if (!jkInput.equals("")) {
                            String up = jkInput.toUpperCase();
                            if (up.startsWith("L"))
                                jk = "Laki-Laki";
                            else if (up.startsWith("P"))
                                jk = "Perempuan";
                            else
                                jk = jkInput;
                            break;
                        }
                        System.out.println("Jenis kelamin tidak boleh kosong. Silakan masukkan.");
                    }
                    System.out.print("Kategori: ");
                    String kat = sc.nextLine();
                    crudK.tambahKaryawan(pos, nama, alamat, jk, kat, telp);
                    System.out.println("Karyawan ditambahkan.");
                    break;
                case "3":
                    int ur = readIntInRange(sc, "Urutan karyawan untuk diperbarui (1-" + crudK.jumlahKaryawan + "): ",
                            1, crudK.jumlahKaryawan);
                    System.out.print("Nama: ");
                    String n2 = sc.nextLine();
                    System.out.print("Alamat: ");
                    String a2 = sc.nextLine();
                    System.out.print("No Telp: ");
                    String t2 = sc.nextLine();
                    String jk2 = "";
                    while (true) {
                        System.out.print("Jenis Kelamin (L/P): ");
                        String jkInput2 = sc.nextLine().trim();
                        if (!jkInput2.equals("")) {
                            String up2 = jkInput2.toUpperCase();
                            if (up2.startsWith("L"))
                                jk2 = "Laki-Laki";
                            else if (up2.startsWith("P"))
                                jk2 = "Perempuan";
                            else
                                jk2 = jkInput2;
                            break;
                        }
                        System.out.println("Jenis kelamin tidak boleh kosong. Silakan masukkan.");
                    }
                    System.out.print("Kategori: ");
                    String k2 = sc.nextLine();
                    crudK.perbaruiKaryawan(ur, n2, a2, t2, jk2, k2);
                    System.out.println("Data diperbarui.");
                    break;
                case "4":
                    int hd = readIntInRange(sc, "Urutan karyawan untuk dihapus (1-" + crudK.jumlahKaryawan + "): ", 1,
                            crudK.jumlahKaryawan);
                    crudK.hapuskaryawan(hd);
                    System.out.println("Karyawan dihapus.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void travelMenu(Scanner sc, CRUDMobil crudM, String[] routes, Booking[] bookings,
            boolean[] mobilBooked) {
        int bookingIdCounter = 0;
        while (true) {
            System.out.println("--- Modul Travel & Sewa Mobil ---");
            System.out.println("1. Pemesanan Travel");
            System.out.println("2. Penyewaan Mobil");
            System.out.println("3. Lihat daftar mobil");
            System.out.println("4. Lihat booking");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            String p = sc.nextLine();
            if (p.equals("0"))
                break;

            switch (p) {
                case "1":
                    System.out.println("Pilih rute:");
                    for (int i = 0; i < routes.length; i++) {
                        System.out.println((i + 1) + ". " + routes[i]);
                    }
                    int r = readIntInRange(sc, "Rute (nomor): ", 1, routes.length);
                    System.out.print("Nama pemesan: ");
                    String cust = sc.nextLine();

                    int assign = -1;
                    for (int i = 0; i < crudM.jumlahMobil; i++) {
                        if (!mobilBooked[i] && !crudM.mobil2[i].isKosong()) {
                            assign = i + 1;
                            mobilBooked[i] = true;
                            break;
                        }
                    }

                    if (assign == -1) {
                        System.out.println("Tidak ada mobil tersedia saat ini untuk travel.");
                    } else {
                        Booking b = new Booking(++bookingIdCounter, "TRAVEL", cust, routes[r - 1], assign,
                                "Dipesan untuk rute");
                        for (int i = 0; i < bookings.length; i++) {
                            if (bookings[i] == null) {
                                bookings[i] = b;
                                break;
                            }
                        }
                        System.out.println("Booking travel berhasil. Mobil ditugaskan: " + assign);
                    }
                    break;
                case "2":
                    System.out.println("Daftar mobil:");
                    crudM.cetakMobil2();
                    int m = readIntInRange(sc, "Pilih mobil (nomor) untuk disewa: ", 1, crudM.jumlahMobil);
                    if (m < 1 || m > crudM.jumlahMobil || crudM.mobil2[m - 1].isKosong()) {
                        System.out.println("Pilihan tidak valid atau mobil kosong.");
                        break;
                    }
                    if (mobilBooked[m - 1]) {
                        System.out.println("Mobil sudah dibooking/sewa.");
                        break;
                    }
                    System.out.print("Nama penyewa: ");
                    String renter = sc.nextLine();
                    mobilBooked[m - 1] = true;
                    Booking br = new Booking(++bookingIdCounter, "RENTAL", renter, "", m, "Sewa mobil");
                    for (int i = 0; i < bookings.length; i++) {
                        if (bookings[i] == null) {
                            bookings[i] = br;
                            break;
                        }
                    }
                    System.out.println("Mobil nomor " + m + " berhasil disewa oleh " + renter);
                    break;
                case "3":
                    crudM.cetakMobil2();
                    break;
                case "4":
                    System.out.println("-- Daftar Booking --");
                    System.out.println("ID | Type   | Customer        | Route               | Mobil");
                    for (int i = 0; i < bookings.length; i++) {
                        if (bookings[i] != null) {
                            Booking bx = bookings[i];
                            System.out.printf("%2d | %-6s | %-15s | %-19s | %s\n",
                                    bx.id, bx.type, bx.customer, (bx.route == null ? "" : bx.route),
                                    (bx.mobilIndex == 0 ? "-" : bx.mobilIndex));
                        }
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak sah.");
            }
        }
    }
}