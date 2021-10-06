package proyecto1;

public class Vehiculo {
    private final String numeroPlaca;

    public Vehiculo(String numeroPlaca)
    {
        this.numeroPlaca = numeroPlaca;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public String setNumeroPlaca() {
        return numeroPlaca;
    }

    @Override
    public String toString() {
        return "Placa No. " + numeroPlaca;
    }
}
