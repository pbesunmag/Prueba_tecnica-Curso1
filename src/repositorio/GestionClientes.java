package repositorio;

import modelo.Cliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class GestionClientes {
    // Almacenamiento limpio (sin static)
    private static Map<Integer, Cliente> mapaClientes = new HashMap<>();
    private static final DateTimeFormatter formateador = DateTimeFormatter.ofPattern("DD-MM-YYYY");

    public static void agregarCliente(Cliente nuevoCliente) {
        mapaClientes.put(nuevoCliente.getId(), nuevoCliente);
        System.out.println("modelo.Cliente agregado con éxito. ID asignado: " + nuevoCliente.getId());
    }

    public static void listarClientes() {
        if (mapaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.println("--- LISTADO DE CLIENTES ---");
            for (Cliente cliente : mapaClientes.values()) {
                System.out.println("ID: " + cliente.getId() + ", Nombre: " + cliente.getNombre() +
                        ", Apellido: " + cliente.getApellido());
            }
        }
    }

    public static void eliminarCliente(int idCliente) {
        if (mapaClientes.containsKey(idCliente)) {
            mapaClientes.remove(idCliente);
            System.out.println("modelo.Cliente " + idCliente + " eliminado con éxito.");
        } else {
            System.out.println("No se ha encontrado el cliente con ID " + idCliente + ".");
        }
    }

    public static void buscarPorCiudad(String ciudadBuscada) {
        System.out.println("\n--- Clientes encontrados en la ciudad: " + ciudadBuscada + " ---");
        boolean encontrado = false;
        for (Cliente c : mapaClientes.values()) {
            if (c.getCiudad().equalsIgnoreCase(ciudadBuscada)) {
                System.out.println("[ID: " + c.getId() + "] " + c.getNombre() + " " + c.getApellido());
                System.out.println("  • Email: " + c.getEmail());
                System.out.println("  • Teléfono: " + c.getTelefono());
                System.out.println("  • Nacimiento: " + c.getNacimiento().format(formateador));
                System.out.println("  --------------------------------------");
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron clientes que vivan en " + ciudadBuscada);
        }
    }

    public static void actualizarCliente(int idCliente, String nuevoNombre, String nuevoApellido,
                                  String nuevaCiudad, String nuevoTelefono, String nuevoEmail,
                                  String nuevaFechaNacimiento) {
        if (mapaClientes.containsKey(idCliente)) {
            Cliente c = mapaClientes.get(idCliente);
            c.setNombre(nuevoNombre);
            c.setApellido(nuevoApellido);
            c.setCiudad(nuevaCiudad);
            c.setTelefono(nuevoTelefono);
            c.setEmail(nuevoEmail);
            LocalDate fecha = LocalDate.parse(nuevaFechaNacimiento, formateador);
            c.setNacimiento(fecha);
            System.out.println("Información del cliente con ID " + idCliente + " actualizada con éxito");
        } else {
            System.out.println("Error: No se ha encontrado el cliente con ID " + idCliente + ".");
        }
    }

    public static Map<Integer, Cliente> getMapaClientes() {
        return mapaClientes;
    }
}