package dunabapp;

/**
 * Modelo simple de estudiante para compartir datos entre pantallas.
 */
public class Estudiante {
    private String nombre;
    private String carrera;
    private String semestre;
    private int dunabsActuales;
    private int dunabsMeta; // por ejemplo 80 para graduarse

    private String rutaFoto;

    public Estudiante(String nombre, String carrera, String semestre, int dunabsActuales, int dunabsMeta) {
        this.nombre = nombre;
        this.carrera = carrera;
        this.semestre = semestre;
        this.dunabsActuales = dunabsActuales;
        this.dunabsMeta = dunabsMeta;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }

    public int getDunabsActuales() { return dunabsActuales; }
    public void setDunabsActuales(int dunabsActuales) { this.dunabsActuales = dunabsActuales; }

    public int getDunabsMeta() { return dunabsMeta; }
    public void setDunabsMeta(int dunabsMeta) { this.dunabsMeta = dunabsMeta; }

    public int getDunabsFaltantes() { return Math.max(0, dunabsMeta - dunabsActuales); }

    public String getRutaFoto() { return rutaFoto; }
    public void setRutaFoto(String rutaFoto) { this.rutaFoto = rutaFoto; }
}
