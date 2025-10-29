package model;

public class Empleado extends Persona {
    private String puesto;
    private double salario;

    public Empleado(String nombre, String email) {
        super(nombre, email);
    }

    public Empleado(String nombre, String email, String puesto, double salario) {
        super(nombre, email);
        this.puesto = puesto;
        this.salario = salario;
    }

    public String getPuesto() {
        return puesto;
    }
    public double getSalario() {
        return salario;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "puesto='" + puesto + '\'' +
                ", salario=" + salario +
                ", id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
