package components;

public class Karyawan {
    public String nama;
    private String alamat;
    public String noTelp;
    public boolean jenisKelamin;
    public String kategori;

    public Karyawan(){
        
    }

    public Karyawan(String nama, String alamat, boolean jenisKelamin, String kategori, String noTelp, int id) {
        this.nama = nama;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
        this.kategori = kategori;
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public void cetakData() {
        System.out.printf("Nama: %s\n", nama);
        System.out.printf("Alamat: %s\n", alamat);
        System.out.printf("No Telp: %s\n", noTelp);
        System.out.printf("Jenis Kelamin: %s\n", jenisKelamin ? "Laki-laki" : "Perempuan");
        System.out.printf("Kategori: %s\n", kategori);
    }
}
