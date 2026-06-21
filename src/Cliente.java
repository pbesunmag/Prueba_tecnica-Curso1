import java.time.LocalDate;

public class Cliente {
    private int id;

    private String nombre;
    private String apellido;
    private String ciudad;
    private LocalDate nacimiento;
    private String telefono;
    private String email;

    public Cliente(String nombre, String apellido, String ciudad, LocalDate nacimiento,
                   String telefono, String email) {

        this.id = contadorID;
        contadorID++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.nacimiento = nacimiento;
        this.telefono = telefono;
        this.email = email;
    }

    private static int contadorID = 1;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static int getContadorID() {
        return contadorID;
    }
}





