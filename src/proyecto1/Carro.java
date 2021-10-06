package proyecto1;

public class Carro extends Vehiculo {

    public int contadorC;

    public Carro(String numeroPlaca) {
        super(numeroPlaca);
    }

    @Override
    public String toString() {
        return "Carro con " + super.toString();
    }

}

