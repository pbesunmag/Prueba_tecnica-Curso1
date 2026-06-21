import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class GestionClientes {
    private static Map<Integer, Cliente> mapaClientes = new HashMap<>();

    public static void agregarCliente(Cliente nuevoCliente) {
        mapaClientes.put(nuevoCliente.getId(), nuevoCliente);
        System.out.println("Cliente agregado con éxito. ID asignado: " + nuevoCliente.getId());
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
            System.out.println("Cliente " + idCliente + " eliminado con éxito.");
        } else {
            System.out.println("No se ha encontrado el cliente con ID " + idCliente + ".");
        }
    }

    public static void buscarPorCiudad(String ciudadBuscada) {
        System.out.println("\n--- Clientes encontrados en la ciudad: " + ciudadBuscada + " ---");
        boolean encontrado = false;

        // Necesitamos este formateador para que la fecha de nacimiento se vea bonita al imprimirla (DD-MM-AAAA)
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (Cliente c : mapaClientes.values()) {
            if (c.getCiudad().equalsIgnoreCase(ciudadBuscada)) { // He puesto 'equalsIgnoreCase' para que le dé igual 'manresa' que 'Manresa'

                // Imprimimos la ficha completa del cliente bien ordenada
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
            LocalDate fecha = LocalDate.parse(nuevaFechaNacimiento);
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

