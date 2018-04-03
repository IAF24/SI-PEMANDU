package id.tiregdev.si_pemandu.utils;

public class itemObject_anak {

    private String namaAnak;
    private String id_anak;

    public itemObject_anak(String namaAnak, String id_anak) {
        this.namaAnak = namaAnak;
        this.id_anak = id_anak;
    }

    public itemObject_anak() {
    }

    public String getNamaAnak() {
        return namaAnak;
    }

    public void setNamaAnak(String namaAnak) {
        this.namaAnak = namaAnak;
    }

    public String getId_anak() {
        return id_anak;
    }

    public void setId_anak(String id_anak) {
        this.id_anak = id_anak;
    }
}
