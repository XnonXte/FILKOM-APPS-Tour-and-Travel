package components;

public class Mobil {
    private String noPlat;
    public String merkMobil;
    public String warnaMobil;
    public int tahunKeluaranMobil;

    public Mobil() {
        this.noPlat = "";
        this.merkMobil = "";
        this.warnaMobil = "";
        this.tahunKeluaranMobil = 0;
    }

    public Mobil(String noPlat, String merkMobil, String warnaMobil, int tahunKeluaranMobil) {
        this.noPlat = noPlat;
        this.merkMobil = merkMobil;
        this.warnaMobil = warnaMobil;
        this.tahunKeluaranMobil = tahunKeluaranMobil;
    }

    public String getNoPlat() {
        return noPlat;
    }

    public void setnoPlat(String noPLat) {
        this.noPlat = noPLat;
    }

    public boolean isKosong() {
        return noPlat.equals("") && merkMobil.equals("") && warnaMobil.equals("") && tahunKeluaranMobil == 0;
    }

    public void cetakData(int urutan) {
        if (isKosong()) {
            System.out.printf("| %-3d | %-10s | %-12s | %-12s | %-6s |\n",
                urutan, "", "", "", "");
        } else {
            System.out.printf("| %-3d | %-10s | %-12s | %-12s | %-6d |\n",
                urutan, noPlat, merkMobil, warnaMobil, tahunKeluaranMobil);
        }
    }
}
