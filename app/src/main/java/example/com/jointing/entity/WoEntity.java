package example.com.jointing.entity;


public class WoEntity {
    private String no_seri;
    private String nama_jointer;
    private String koordinat_x;
    private String koordinat_y;
    private String foto_sekitar;
    private String foto_sesudah;
    private String foto_label;
    private String foto_eviden;
    private String merk;
    private String tipe;
    private String konektor;
    private String jenis_kabel;

    public WoEntity(String no_seri, String nama_jointer, String koordinat_x, String koordinat_y, String foto_sekitar, String foto_sesudah, String foto_label, String foto_eviden, String merk, String tipe, String konektor, String jenis_kabel) {
        this.no_seri = no_seri;
        this.nama_jointer = nama_jointer;
        this.koordinat_x = koordinat_x;
        this.koordinat_y = koordinat_y;
        this.foto_sekitar = foto_sekitar;
        this.foto_sesudah = foto_sesudah;
        this.foto_label = foto_label;
        this.foto_eviden = foto_eviden;
        this.merk = merk;
        this.tipe = tipe;
        this.konektor = konektor;
        this.jenis_kabel = jenis_kabel;
    }

    public String getNoSeri() {
        return no_seri;
    }

    public void setNoSeri(String no_seri) {
        this.no_seri = no_seri;
    }

    public String getNamaJointer() {
        return nama_jointer;
    }

    public void setNamaJointer(String nama_jointer) {
        this.nama_jointer = nama_jointer;
    }

    public String getKoordinatX() {
        return koordinat_x;
    }

    public void setKoordinatX(String koordinat_x) {
        this.koordinat_x = koordinat_x;
    }

    public String getKoordinatY() {
        return koordinat_y;
    }

    public void setKoordinatY(String koordinat_y) {
        this.koordinat_y = koordinat_y;
    }

    public String getFotoSekitar() {
        return foto_sekitar;
    }

    public void setFotoSekitar(String foto_sekitar) {
        this.foto_sekitar = foto_sekitar;
    }

    public String getFotoSesudah() {
        return foto_sesudah;
    }

    public void setFotoSesudah(String foto_sesudah) {
        this.foto_sesudah = foto_sesudah;
    }

    public String getFotoLabel() {
        return foto_label;
    }

    public void setFotoLabel(String foto_label) {
        this.foto_label = foto_label;
    }

    public String getFotoEviden() {
        return foto_eviden;
    }

    public void setFotoEviden(String foto_eviden) {
        this.foto_eviden = foto_eviden;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getKonektor() {
        return konektor;
    }

    public void setKonektor(String konektor) {
        this.konektor = konektor;
    }

    public String getJenisKabel() {
        return jenis_kabel;
    }

    public void setJenisKabel(String jenis_kabel) {
        this.jenis_kabel = jenis_kabel;
    }
}
