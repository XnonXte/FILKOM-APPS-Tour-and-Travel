import java.util.Scanner;
import applications.*;

public class App {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Masukan jumlah karyawan: ");
        int jumlahKaryawan = scan.nextInt();
        System.out.print("Masukan jumlah mobil: ");
        int jumlahMobil = scan.nextInt();
        scan.nextLine();
        CRUDKaryawan crudKaryawan = new CRUDKaryawan(jumlahKaryawan);
        CRUDMobil crudMobil = new CRUDMobil(jumlahMobil);
        boolean[] filledKaryawan = new boolean[jumlahKaryawan];
        boolean[] filledMobil = new boolean[jumlahMobil];

        int pilihMenu;

        do {
            System.out.println("\n==== MENU UTAMA ====");
            System.out.println("1. Menu Karyawan");
            System.out.println("2. Menu Mobil");
            System.out.println("3. Exit");
            System.out.print("Pilih: ");
            pilihMenu = scan.nextInt();
            scan.nextLine();

            switch (pilihMenu) {

                case 1:
                    menuKaryawan(crudKaryawan, filledKaryawan);
                    break;

                case 2:
                    menuMobil(crudMobil, filledMobil);
                    break;

            }

        } while (pilihMenu != 3);

        System.out.println("Program selesai.");
    }

    public static void menuKaryawan(CRUDKaryawan crud, boolean[] filled) {
        int pilih, urutan;

        do {
            System.out.println("\n== MENU KARYAWAN ==");
            System.out.println("1. CREATE");
            System.out.println("2. READ");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. BACK");
            System.out.print("Pilih: ");
            pilih = scan.nextInt();
            scan.nextLine();

            switch (pilih) {

                case 1:
                    System.out.print("Masukkan urutan (1-5): ");
                    urutan = scan.nextInt();
                    scan.nextLine();

                    if (filled[urutan - 1]) {
                        System.out.println("❌ Slot sudah terisi!");
                        break;
                    }

                    System.out.print("Nama: ");
                    String nama = scan.nextLine();

                    System.out.print("Alamat: ");
                    String alamat = scan.nextLine();

                    System.out.print("No Telp: ");
                    String telp = scan.nextLine();

                    System.out.print("Jenis Kelamin (L/P): ");
                    char jk = scan.nextLine().charAt(0);

                    boolean gender = (jk == 'L' || jk == 'l');

                    System.out.print("Kategori: ");
                    String kategori = scan.nextLine();

                    crud.tambahKaryawan(urutan, nama, alamat, gender, kategori, telp);
                    filled[urutan - 1] = true;
                    break;

                case 2:
                    crud.cetakKaryawan2();
                    break;

                case 3:
                    System.out.print("Urutan: ");
                    urutan = scan.nextInt();
                    scan.nextLine();

                    if (!filled[urutan - 1]) {
                        System.out.println("❌ Belum ada data untuk update.");
                        break;
                    }

                    System.out.print("Nama Baru: ");
                    nama = scan.nextLine();

                    System.out.print("Alamat Baru: ");
                    alamat = scan.nextLine();

                    System.out.print("No Telp Baru: ");
                    telp = scan.nextLine();

                    System.out.print("Jenis Kelamin Baru (L/P): ");
                    jk = scan.nextLine().charAt(0);
                    gender = (jk == 'L' || jk == 'l');

                    System.out.print("Kategori Baru: ");
                    kategori = scan.nextLine();

                    crud.perbaruiKaryawan(urutan, nama, alamat, telp, gender, kategori);
                    break;

                case 4:
                    System.out.print("Urutan: ");
                    urutan = scan.nextInt();
                    scan.nextLine();

                    crud.hapuskaryawan(urutan);
                    filled[urutan - 1] = false;

                    System.out.println("✔ Data karyawan dihapus.");
                    break;
            }

        } while (pilih != 5);
    }

    public static void menuMobil(CRUDMobil crud, boolean[] filled) {
        int pilih, urutan;

        do {
            System.out.println("\n== MENU MOBIL ==");
            System.out.println("1. CREATE");
            System.out.println("2. READ");
            System.out.println("3. UPDATE");
            System.out.println("4. DELETE");
            System.out.println("5. BACK");
            System.out.print("Pilih: ");
            pilih = scan.nextInt();
            scan.nextLine();

            switch (pilih) {

                case 1:
                    System.out.print("Masukkan urutan (1-5): ");
                    urutan = scan.nextInt();
                    scan.nextLine();

                    if (filled[urutan - 1]) {
                        System.out.println("❌ Slot sudah terisi!");
                        break;
                    }

                    System.out.print("No Plat: ");
                    String plat = scan.nextLine();

                    System.out.print("Merk Mobil: ");
                    String merk = scan.nextLine();

                    System.out.print("Warna Mobil: ");
                    String warna = scan.nextLine();

                    System.out.print("Tahun Keluaran: ");
                    int tahun = scan.nextInt();
                    scan.nextLine();

                    crud.tambahMobil(urutan, plat, merk, warna, tahun);
                    filled[urutan - 1] = true;
                    break;

                case 2:
                    crud.cetakMobil2();
                    break;

                case 3:
                    System.out.print("Urutan: ");
                    urutan = scan.nextInt();
                    scan.nextLine();

                    if (!filled[urutan - 1]) {
                        System.out.println("❌ Belum ada data untuk update.");
                        break;
                    }

                    System.out.print("No Plat Baru: ");
                    plat = scan.nextLine();

                    System.out.print("Merk Mobil Baru: ");
                    merk = scan.nextLine();

                    System.out.print("Warna Mobil Baru: ");
                    warna = scan.nextLine();

                    System.out.print("Tahun Keluaran Baru: ");
                    tahun = scan.nextInt();
                    scan.nextLine();

                    crud.perbaruiMobil(urutan, plat, merk, warna, tahun);
                    break;

                case 4:
                    System.out.print("Urutan: ");
                    urutan = scan.nextInt();
                    scan.nextLine();

                    crud.hapusMobil(urutan);
                    filled[urutan - 1] = false;

                    System.out.println("✔ Data mobil dihapus.");
                    break;
            }

        } while (pilih != 5);
    }
}
