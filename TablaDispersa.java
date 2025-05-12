import java.lang.*;
import java.util.UUID;

public class TablaDispersa {
    private Tarea[] tareas;
    private int numElem;
    private double factorCarga;

    public TablaDispersa () {
        tareas = new Tarea[101];
        numElem = 0;
    }

    public boolean insertar (Tarea t) {
        if (numElem >= tareas.length) {
            return false;
        }

        int base = calcularPosicion(t.getCodigo());
        int posicion = base;
        int i = 0;

        while (tareas[posicion] != null && tareas[posicion].esAlta()) {
            i++;
            if (i >= tareas.length) return false;
            posicion = resolverColision(base, i);
        }

        tareas[posicion] = t;
        numElem++;

        return true;
    }

    public Tarea buscar (String codigo) {
        int base = calcularPosicion(codigo);
        int posicion = base;
        int i = 0;
        Tarea t;

        while (i < tareas.length) {
            posicion = resolverColision(base, i);
            t = tareas[posicion];

            if (t == null) return null;
            if (t.getCodigo().equals(codigo) && t.esAlta()) return t;

            i++;
        }
        return null;

    }

    public boolean eliminar (String codigo) {
        int base = calcularPosicion(codigo);
        int posicion = base;
        int i = 0;

        while (i < tareas.length) {
            posicion = resolverColision(base, i);

            if (tareas[posicion] == null) return false;
            if (tareas[posicion].getCodigo().equals(codigo) && tareas[posicion].esAlta()) {
                tareas[posicion].bajaAlta();
                return true;
            }

            i++;
        }
        return false;
    }

    public int calcularPosicion (String codigo) {
        double A = 0.6180339887; // Constante para el método de multiplicación
        double valor = obtenerValorNumerico(codigo);
        double producto = valor * A;
        double decimal = producto - Math.floor(producto);

        return (int)(decimal * 101);
    }

    private double obtenerValorNumerico (String codigo) {
        UUID id = UUID.fromString(codigo);
        return Math.abs(id.getMostSignificantBits() ^ id.getLeastSignificantBits());
    }

    public int resolverColision (int posicion, int i) {
        return (posicion + i * i) % 101;
    }

    public double calcularFactorCarga() {
        return (double) numElem / 101;
    }

    public void mostrarTabla () {
        System.out.println("La tabla contiene:");
        for (int i = 0; i < tareas.length; i++) {
            if (tareas[i] != null && tareas[i].esAlta()) {
                System.out.println("Indice = " + i + " - " + tareas[i]);
            }
        }
    }

}
