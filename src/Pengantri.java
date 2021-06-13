public class Pengantri {
    private String nik;
    private String nama;
    private String ttl;
    private String jenis;
    private String alamat;
    private String agama;
    private String status;
    private String pekerjaan;
    private String kewarganegaraan;
    private String goldar;

    public Pengantri()
    {
        nik = "";
        nama = "";
        ttl = "";
        jenis = "";
        alamat = "";
        agama = "";
        status = "";
        pekerjaan = "";
        kewarganegaraan = "";
        goldar = "";
    }
    public Pengantri (String nik, String nama, String ttl, String jenis,
                      String alamat, String agama, String status, String pekerjaan,
                      String kewarganegaraan, String goldar)
    {
        this.nik = nik;
        this.nama = nama;
        this.ttl = ttl;
        this.jenis = jenis;
        this.alamat = alamat;
        this.agama = agama;
        this.status = status;
        this.pekerjaan = pekerjaan;
        this.kewarganegaraan = kewarganegaraan;
        this.goldar = goldar;
    }

    public String getNik()
    {
        return nik;
    }
    public String getNama()
    {
        return nama;
    }
    public String getTtl()
    {
        return  ttl;
    }
    public String getJenis()
    {
        return  jenis;
    }
    public String getAlamat()
    {
        return alamat;
    }
    public String getAgama()
    {
        return agama;
    }
    public String getStatus()
    {
        return status;
    }
    public String getPekerjaan()
    {
        return pekerjaan;
    }
    public String getKewarganegaraan()
    {
        return  kewarganegaraan;
    }
    public String getGoldar()
    {
        return goldar;
    }
    public void setnik(String nik)
    {
        this.nik = nik;
    }
    public void setnama(String nama)
    {
        this.nama = nama;
    }
    public void setttl(String ttl)
    {
        this.ttl = ttl;
    }
    public void setjenis (String jenis)
    {
        this.jenis = jenis;
    }
    public void setalamat (String alamat)
    {
        this.alamat = alamat;
    }
    public void setagama (String agama)
    {
        this.agama = agama;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    public void setpekerjaan(String pekerjaan)
    {
        this.pekerjaan = pekerjaan;
    }
    public void setKewarganegaraan(String kewarganegaraan)
    {
        this.kewarganegaraan = kewarganegaraan;
    }
    public void setgoldar(String goldar)
    {
        this.goldar = goldar;
    }
}

