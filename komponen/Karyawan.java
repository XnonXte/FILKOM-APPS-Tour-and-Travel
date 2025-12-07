package komponen;

public class Karyawan {
    public String nama;
    private String alamat;
    public String noTelp;
    public String jenisKelamin;
    public String kategori;

    public Karyawan() {
        this.nama = "";
        this.alamat = "";
        this.jenisKelamin = "";
        this.kategori = "";
        this.noTelp = "";
    }

    public Karyawan(String nama, String alamat, String jenisKelamin, String kategori, String noTelp) {
        this.nama = nama;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin == null ? "" : jenisKelamin;
        this.kategori = kategori;
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void cetakData(int urutan) {
        System.out.printf(
                "| %-2d | %-12s | %-12s | %-10s | %-14s | %-9s |\n",
                urutan,
                nama,
                alamat,
                noTelp,
                (jenisKelamin == null || jenisKelamin.equals("") ? "" : jenisKelamin),
                kategori);
    }
}
