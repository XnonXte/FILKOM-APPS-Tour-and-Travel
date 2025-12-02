package applications;

import components.Karyawan;

public class CRUDKaryawan {
    public int jumlahKaryawan;
    public Karyawan[] karyawan2;

    public CRUDKaryawan(int jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
        karyawan2 = new Karyawan[jumlahKaryawan];
        for (int i = 0; i < jumlahKaryawan; i++) {
            karyawan2[i] = new Karyawan();
        }
    }

    public void tambahKaryawan(int urutan, String nama, String alamat, boolean jenisKelamin, String kategori,
            String noTelp) {
        karyawan2[urutan - 1] = new Karyawan(nama, alamat, jenisKelamin, kategori, noTelp);
    }

    public void hapuskaryawan(int urutan) {
        karyawan2[urutan - 1] = new Karyawan();
    }

    public void perbaruiKaryawan(int urutan, String nama, String alamat, String noTelp, boolean jenisKelamin,
            String kategori) {
        karyawan2[urutan - 1] = new Karyawan(nama, alamat, jenisKelamin, kategori, noTelp);
    }

    public void cetakKaryawan2() {
        System.out.println("+----+--------------+--------------+------------+----------------+-----------+");
        System.out.println("| No | Nama         | Alamat       | Telp       | Jenis Kelamin | Kategori  |");
        System.out.println("+----+--------------+--------------+------------+----------------+-----------+");

        for (int i = 0; i < jumlahKaryawan; i++) {
            if (karyawan2[i] != null && karyawan2[i].nama != null) {
                karyawan2[i].cetakData(i + 1);
            } else {
                System.out.printf("| %-2d | %-12s | %-12s | %-10s | %-14s | %-9s |\n",
                        (i + 1), "", "", "", "", "");
            }
        }

        System.out.println("+----+--------------+--------------+------------+----------------+-----------+");
    }
}
