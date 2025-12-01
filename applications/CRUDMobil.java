package applications;
import components.Mobil;

public class CRUDMobil {
    public int jumlahMobil;
    public Mobil[] mobil2;

    public CRUDMobil(int jumlahMobil) {
        this.jumlahMobil = jumlahMobil;
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
        for (int i = 0; i < jumlahMobil; i++) {
            mobil2[i].cetakData();
        }
    }
}
