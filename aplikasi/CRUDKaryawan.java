package aplikasi;

import komponen.Karyawan;
import java.util.ArrayList;
import java.util.List;

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

    public void tambahKaryawan(int urutan, String nama, String alamat, String jenisKelamin, String kategori,
            String noTelp) {
        karyawan2[urutan - 1] = new Karyawan(nama, alamat, jenisKelamin, kategori, noTelp);
    }

    public void hapuskaryawan(int urutan) {
        karyawan2[urutan - 1] = new Karyawan();
    }

    public void perbaruiKaryawan(int urutan, String nama, String alamat, String noTelp, String jenisKelamin,
            String kategori) {
        karyawan2[urutan - 1] = new Karyawan(nama, alamat, jenisKelamin, kategori, noTelp);
    }

    // Helper to split a string into chunks of width w
    private List<String> wrap(String s, int w) {
        List<String> parts = new ArrayList<>();
        if (s == null)
            s = "";
        int idx = 0;
        while (idx < s.length()) {
            int end = Math.min(idx + w, s.length());
            parts.add(s.substring(idx, end));
            idx = end;
        }
        if (parts.isEmpty())
            parts.add("");
        return parts;
    }

    private String repeat(char c, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append(c);
        return sb.toString();
    }

    private String padRight(String s, int w) {
        if (s == null)
            s = "";
        if (s.length() > w)
            return s.substring(0, w);
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < w)
            sb.append(' ');
        return sb.toString();
    }

    public void cetakKaryawan2() {
        // Determine dynamic widths based on content but cap them
        int capName = 20;
        int capAlamat = 20;
        int capTelp = 15;
        int capKategori = 15;

        int nameW = "Nama".length();
        int alamatW = "Alamat".length();
        int telpW = "Telp".length();
        int jkW = "Jenis Kelamin".length();
        int kategoriW = "Kategori".length();

        for (int i = 0; i < jumlahKaryawan; i++) {
            Karyawan k = karyawan2[i];
            if (k != null) {
                if (k.nama != null)
                    nameW = Math.min(capName, Math.max(nameW, k.nama.length()));
                String al = k.getAlamat();
                if (al != null)
                    alamatW = Math.min(capAlamat, Math.max(alamatW, al.length()));
                if (k.noTelp != null)
                    telpW = Math.min(capTelp, Math.max(telpW, k.noTelp.length()));
                if (k.kategori != null)
                    kategoriW = Math.min(capKategori, Math.max(kategoriW, k.kategori.length()));
            }
        }

        // fixed widths for number and jk
        int noW = 4;
        jkW = Math.max(jkW, 6);

        String sep = "+" + repeat('-', noW) + "+" + repeat('-', nameW + 2) + "+" + repeat('-', alamatW + 2) + "+"
                + repeat('-', telpW + 2) + "+" + repeat('-', jkW + 2) + "+" + repeat('-', kategoriW + 2) + "+";

        // header
        System.out.println(sep);
        String header = "| " + padRight("No", noW - 2) + " | " + padRight("Nama", nameW) + " | "
                + padRight("Alamat", alamatW)
                + " | " + padRight("Telp", telpW) + " | " + padRight("Jenis Kelamin", jkW) + " | "
                + padRight("Kategori", kategoriW)
                + " |";
        System.out.println(header);
        System.out.println(sep);

        for (int i = 0; i < jumlahKaryawan; i++) {
            Karyawan k = karyawan2[i];

            String name = "";
            String alamat = "";
            String telp = "";
            String jk = "";
            String kategori = "";

            if (k != null) {
                name = k.nama == null ? "" : k.nama;
                alamat = k.getAlamat() == null ? "" : k.getAlamat();
                telp = k.noTelp == null ? "" : k.noTelp;
                jk = (k.jenisKelamin == null ? "" : k.jenisKelamin);
                kategori = k.kategori == null ? "" : k.kategori;
            }

            List<String> linesName = wrap(name, nameW);
            List<String> linesAlamat = wrap(alamat, alamatW);
            List<String> linesTelp = wrap(telp, telpW);
            List<String> linesJk = wrap(jk, jkW);
            List<String> linesKategori = wrap(kategori, kategoriW);

            int maxLines = Math.max(Math.max(linesName.size(), linesAlamat.size()),
                    Math.max(linesTelp.size(), Math.max(linesJk.size(), linesKategori.size())));

            for (int ln = 0; ln < maxLines; ln++) {
                String noCell = (ln == 0) ? String.valueOf(i + 1) : "";
                String n = ln < linesName.size() ? linesName.get(ln) : "";
                String a = ln < linesAlamat.size() ? linesAlamat.get(ln) : "";
                String t = ln < linesTelp.size() ? linesTelp.get(ln) : "";
                String jks = ln < linesJk.size() ? linesJk.get(ln) : "";
                String kts = ln < linesKategori.size() ? linesKategori.get(ln) : "";

                String row = "| " + padRight(noCell, noW - 2) + " | " + padRight(n, nameW) + " | "
                        + padRight(a, alamatW)
                        + " | " + padRight(t, telpW) + " | " + padRight(jks, jkW) + " | " + padRight(kts, kategoriW)
                        + " |";
                System.out.println(row);
            }
        }

        System.out.println(sep);
    }
}
