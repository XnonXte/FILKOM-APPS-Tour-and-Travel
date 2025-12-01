package applications;
import java.util.Scanner;
import components.Karyawan;

public class CRUDKaryawan {
    public int jumlahKaryawan;
    public Karyawan[] karyawan2;

    public CRUDKaryawan(int jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
    }
    
    public CRUDKaryawan() {
        
    }

    public void tambahKaryawan(int urutan, String nama, String alamat, boolean jenisKelamin, String kategori, String noTelp, int id){
       karyawan2[urutan - 1] = new Karyawan(nama, alamat, jenisKelamin, kategori, noTelp, id);

    }
    
    public void hapuskaryawan(int urutan){
        karyawan2[urutan - 1] = new Karyawan();
    }
    public void perbaruiKaryawan(int urutan, String nama,  String alamt, String noTelp, boolean jenisKelamin, String kategori) {

    }
    public void cetakKaryawan2(){
        for (int i = 0; i < jumlahKaryawan; i++) {
            karyawan2[i].cetakData();
        }
    }
}
