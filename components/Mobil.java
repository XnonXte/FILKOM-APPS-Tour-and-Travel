package components;

public class Mobil {
    private String noPlat;
    public String merkMobil;
    public String warnaMobil;
    public int tahunKeluaranMobil;

    public Mobil(){}

    public Mobil(String noPlat, String merkMobil, String warnaMobil, int tahunKeluaranMobil){
        this.noPlat = noPlat;
        this.merkMobil = merkMobil;
        this.warnaMobil = warnaMobil;
        this.tahunKeluaranMobil = tahunKeluaranMobil;
    }
    
    public String getNoPlat() {
        return noPlat;
    }


    public void setnoPlat(String noPLat){
        this.noPlat = noPLat;
    }
    

    public void cetakData(){
        System.out.println("No Plat: " + noPlat);
        System.out.println("Merk Mobil: " + merkMobil);
        System.out.println("Warna Mobil: " + warnaMobil);
        System.out.println("Tahun Keluaran Mobil: " + tahunKeluaranMobil);
    }
}
