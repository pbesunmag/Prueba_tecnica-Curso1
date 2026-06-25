package repositorio;

import modelo.Cliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class GestionClientes {
    // Almacenamiento limpio (sin static)
    private static Map<Integer, Cliente> mapaClientes = new HashMap<>();
    private DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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
            System.out.println("modelo.Cliente " + idCliente + " eliminado con éxito.");
        } else {
            System.out.println("No se ha encontrado el cliente con ID " + idCliente + ".");
        }
    }

    public void buscarPorCiudad(String ciudadBuscada) {
        System.out.println("\n--- Clientes encontrados en la ciudad: " + ciudadBuscada + " ---");
        boolean encontrado = false;
        for (Cliente c : mapaClientes.values()) {
            if (c.getCiudad().equalsIgnoreCase(ciudadBuscada)) {
                System.out.println("[ID: " + c.getId() + "] " + c.getNombre() + " " + c.getApellido());
                System.out.println("  • Email: " + c.getEmail());
                System.out.println("  • Teléfono: " + c.getTelefono());
                System.out.println("  • Nacimiento: " + c.getNacimiento().format(formateador));
                System.out.println("  • Ciudad: " + c.getCiudad());
                System.out.println("  • Profesión: " + c.getProfesion());
                System.out.println("  --------------------------------------");
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron clientes que vivan en " + ciudadBuscada);
        }
    }

    public void actualizarCliente(int idCliente, String nuevoNombre, String nuevoApellido,
                                         String nuevaCiudad, String nuevoTelefono, String nuevoEmail,
                                         String nuevaFechaNacimiento, String nuevaProfesion) {
        if (mapaClientes.containsKey(idCliente)) {
            Cliente c = mapaClientes.get(idCliente);
            c.setNombre(nuevoNombre);
            c.setApellido(nuevoApellido);
            c.setCiudad(nuevaCiudad);
            c.setTelefono(nuevoTelefono);
            c.setEmail(nuevoEmail);
            LocalDate fecha = LocalDate.parse(nuevaFechaNacimiento, formateador);
            c.setNacimiento(fecha);
            c.setProfesion(nuevaProfesion);
            System.out.println("Información del cliente con ID " + idCliente + " actualizada con éxito");
        } else {
            System.out.println("Error: No se ha encontrado el cliente con ID " + idCliente + ".");
        }
    }

    public static Map<Integer, Cliente> getMapaClientes() {
        return mapaClientes;
    }

    public void buscarPorAgno(int agnoBuscar) {
        System.out.println("\n--- Clientes encontrados nacidos en el año: " + agnoBuscar + " ---");
        boolean encontrado = false;

        for (Cliente c : mapaClientes.values()) {
            // Extraemos directamente el año del LocalDate usando .getYear()
            if (c.getNacimiento().getYear() == agnoBuscar) {
                System.out.println("[ID: " + c.getId() + "] " + c.getNombre() + " " + c.getApellido());
                System.out.println("  • Email: " + c.getEmail());
                System.out.println("  • Teléfono: " + c.getTelefono());
                System.out.println("  • Nacimiento: " + c.getNacimiento().format(formateador));
                System.out.println("  • Ciudad: " + c.getCiudad());
                System.out.println("  • Profesión: " + c.getProfesion());
                System.out.println("----------------------------------------");
                encontrado = true;
            }
        }

        if (!encontrado) {
            // Corregido el mensaje para que no diga "vivan en"
            System.out.println("No se encontraron clientes nacidos en el año " + agnoBuscar);
        }
    }
    public void mostrarEstadisticasCiudades() {
        System.out.println("\n--- ESTADÍSTICAS DE CLIENTES POR CIUDAD ---");

        if (mapaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados en el sistema.");
            return;
        }

        // Mapa temporal para almacenar: "Ciudad" -> Cantidad de clientes
        Map<String, Integer> contadorCiudades = new HashMap<>();

        for (Cliente c : mapaClientes.values()) {
            String ciudad = c.getCiudad();
            // Si la ciudad ya existe, le sumamos 1; si no, la iniciamos en 1
            contadorCiudades.put(ciudad, contadorCiudades.getOrDefault(ciudad, 0) + 1);
        }

        for (Map.Entry<String, Integer> entrada : contadorCiudades.entrySet()) {
            System.out.println("  • " + entrada.getKey() + ": " + entrada.getValue() + " cliente(s)");
        }
        System.out.println("-------------------------------------------");
    }
}