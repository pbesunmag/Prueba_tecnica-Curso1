import modelo.Cliente;
import repositorio.GestionClientes;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        GestionClientes gestor = new GestionClientes();

        Scanner teclado = new Scanner(System.in);
        // Creamos el formateador para usar el formato día-mes-año en todo el codigo
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int opcion;

        do {
            System.out.println("Bienvenido al sistema de gestión de clientes de Bastida's S.A.");
            System.out.println();
            System.out.println("\n----- MENÚ PRINCIPAL -----");
            System.out.print("Selecciona una opción: ");
            System.out.println();
            System.out.println("1. Agregar nuevo cliente");
            System.out.println("2. Listar todos los clientes");
            System.out.println("3. Actualizar información de un cliente");
            System.out.println("4. Eliminar un cliente");
            System.out.println("5. Buscar cliente por ciudad");
            System.out.println("6. Salir");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRAR NUEVO CLIENTE ---");
                    System.out.print("Indique el Nombre: ");
                    String nombre = teclado.nextLine();

                    System.out.print("Indique el Apellido: ");
                    String apellido = teclado.nextLine();

                    System.out.print("Indique la Ciudad: ");
                    String ciudad = teclado.nextLine();

                    System.out.print("Fecha de nacimiento (DD-MM-AAAA): ");
                    String fechaTexto = teclado.nextLine();
                    // Parseamos la fecha con el formateador
                    LocalDate nacimiento = LocalDate.parse(fechaTexto, formateador);

                    System.out.print("Teléfono móvil: ");
                    String telefono = teclado.nextLine();

                    System.out.print("Email: ");
                    String email = teclado.nextLine();

                    Cliente nuevo = new Cliente(nombre, apellido, ciudad, nacimiento, telefono, email);
                    GestionClientes.agregarCliente(nuevo);
                    break;

                case 2:
                    GestionClientes.listarClientes();
                    break;

                case 3:
                    System.out.println("\n--- ACTUALIZAR CLIENTE (ENTER PARA MANTENER) ---");
                    System.out.print("Introduce el ID del cliente a modificar: ");
                    int idModificar = teclado.nextInt();
                    teclado.nextLine();

                    if (GestionClientes.getMapaClientes().containsKey(idModificar)) {
                        Cliente actual = GestionClientes.getMapaClientes().get(idModificar);

                        System.out.print("Nuevo Nombre (" + actual.getNombre() + "): ");
                        String nuevoNombre = teclado.nextLine();
                        if (nuevoNombre.isEmpty()) nuevoNombre = actual.getNombre();

                        System.out.print("Nuevo Apellido (" + actual.getApellido() + "): ");
                        String nuevoApellido = teclado.nextLine();
                        if (nuevoApellido.isEmpty()) nuevoApellido = actual.getApellido();

                        System.out.print("Nueva Ciudad (" + actual.getCiudad() + "): ");
                        String nuevaCiudad = teclado.nextLine();
                        if (nuevaCiudad.isEmpty()) nuevaCiudad = actual.getCiudad();

                        System.out.print("Nuevo Teléfono (" + actual.getTelefono() + "): ");
                        String nuevoTelefono = teclado.nextLine();
                        if (nuevoTelefono.isEmpty()) nuevoTelefono = actual.getTelefono();

                        System.out.print("Nuevo Email (" + actual.getEmail() + "): ");
                        String nuevoEmail = teclado.nextLine();
                        if (nuevoEmail.isEmpty()) nuevoEmail = actual.getEmail();

                        System.out.print("Nueva Fecha (" + actual.getNacimiento().format(formateador) + "): ");
                        String nuevaFecha = teclado.nextLine();
                        if (nuevaFecha.isEmpty()) {
                            // Si le da a ENTER, pasamos su fecha actual formateada como texto
                            nuevaFecha = actual.getNacimiento().format(formateador);
                        }

                        // Enviamos los datos finales
                        gestor.actualizarCliente(idModificar, nuevoNombre, nuevoApellido, nuevaCiudad, nuevoTelefono, nuevoEmail, nuevaFecha);
                    } else {
                        System.out.println("Error: No se encontró ningún cliente con el ID " + idModificar);
                    }
                    break;

                case 4:
                    System.out.print("\nIntroduce el ID del cliente a eliminar: ");
                    int idEliminar = teclado.nextInt();
                    teclado.nextLine();
                    gestor.eliminarCliente(idEliminar);
                    break;

                case 5:
                    System.out.print("\nIntroduce la ciudad a buscar: ");
                    String ciudadBuscar = teclado.nextLine();
                    gestor.buscarPorCiudad(ciudadBuscar);
                    break;

                case 6:
                    System.out.println("Cerrando el sistema.");
                    break;
                default:
                    System.out.println("Opción incorrecta. Introduce un número del 1 al 6.");
            }

        } while (opcion != 6);
    }
}