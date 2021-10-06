package proyecto1;

public class Camion extends Vehiculo{
    public Camion (String numeroPlaca) {
        super(numeroPlaca);
    }

    @Override
    public String toString() {
        return "Camion con " + super.toString();
    }
}