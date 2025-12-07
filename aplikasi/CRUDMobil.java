package aplikasi;

import komponen.Mobil;

public class CRUDMobil {
    public int jumlahMobil;
    public Mobil[] mobil2;

    public CRUDMobil(int jumlahMobil) {
        this.jumlahMobil = jumlahMobil;
        mobil2 = new Mobil[jumlahMobil];

        for (int i = 0; i < jumlahMobil; i++) {
            mobil2[i] = new Mobil();
        }
    }

    public void tambahMobil(int urutan, String noPlat, String merkMobil, String warnaMobil, int tahunKeluaranMobil) {
        mobil2[urutan - 1] = new Mobil(noPlat, merkMobil, warnaMobil, tahunKeluaranMobil);
    }

    public void hapusMobil(int urutan) {
        mobil2[urutan - 1] = new Mobil();
    }

    public void perbaruiMobil(int urutan, String noPlat, String merkMobil, String warnaMobil, int tahunKeluaranMobil) {
        mobil2[urutan - 1] = new Mobil(noPlat, merkMobil, warnaMobil, tahunKeluaranMobil);
    }

    public void cetakMobil2() {
        System.out.println("+-----+------------+--------------+--------------+--------+");
        System.out.println("| No  | No Plat    | Merk Mobil   | Warna Mobil  | Tahun  |");
        System.out.println("+-----+------------+--------------+--------------+--------+");

        for (int i = 0; i < jumlahMobil; i++) {
            mobil2[i].cetakData(i + 1);
        }

        System.out.println("+-----+------------+--------------+--------------+--------+");
    }
}
