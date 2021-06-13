public class Pegawai {
    private String nama;
    private String role;
    private String username;
    private String password;

    public Pegawai()
    {
        nama = "";
        role = "";
        username = "";
        password = "";
    }
    public Pegawai (String nama, String role, String username, String password)
    {
        this.nama = nama;
        this.role = role;
        this.username = username;
        this.password = password;
    }
    public String getnama()
    {
        return nama;
    }
    public String getRole()
    {
        return  role;
    }
    public String getUsername()
    {
        return username;
    }
    public String getPassword()
    {
        return password;
    }

    public void setNama(String nama)
    {
        this.nama = nama;
    }
    public void setrole(String role)
    {
        this.role = role;
    }
    public void setUsername (String username)
    {
        this.username = username;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
}
