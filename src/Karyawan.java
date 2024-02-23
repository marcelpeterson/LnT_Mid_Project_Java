
public class Karyawan {
    String kode;
    String nama;
    String kelamin;
    String jabatan;
    Long gaji;
    
    boolean hasReceivedBonus = false;
    
    public Karyawan(String kode, String nama, String kelamin, String jabatan, Long gaji) {
        this.kode = kode;
        this.nama = nama;
        this.kelamin = kelamin;
        this.jabatan = jabatan;
        this.gaji = gaji;
    }
}
