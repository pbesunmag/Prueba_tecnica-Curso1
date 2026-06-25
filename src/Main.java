import modelo.Cliente;
import repositorio.GestionClientes;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        // Instanciamos el objeto gestor correctamente
        GestionClientes gestor = new GestionClientes();

        Scanner teclado = new Scanner(System.in);
        // Creamos el formateador para usar el formato día-mes-año en todo el código
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int opcion;

        do {
            System.out.println("\nBienvenido al sistema de gestión de clientes de Bastida's S.A.");
            System.out.println("----- MENÚ PRINCIPAL -----");
            System.out.println("1. Agregar nuevo cliente");
            System.out.println("2. Listar todos los clientes");
            System.out.println("3. Actualizar información de un cliente");
            System.out.println("4. Eliminar un cliente");
            System.out.println("5. Buscar cliente por ciudad");
            System.out.println("6. Buscar cliente por año");
            System.out.println("7. Salir");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("❌ Error: Debes introducir un número entero del 1 al 7.");
                teclado.nextLine();
                opcion = 0; // Asignamos un valor neutro para que el menú vuelva a empezar sin romperse
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- REGISTRAR NUEVO CLIENTE ---");
                    System.out.print("Indique el Nombre: ");
                    String nombre = teclado.nextLine();

                    System.out.print("Indique el Apellido: ");
                    String apellido = teclado.nextLine();

                    System.out.print("Indique la Ciudad: ");
                    String ciudad = teclado.nextLine();

                    LocalDate fechaNacimiento = null;
                    boolean fechaValida = false;

                    // Bucle robusto con try-catch para la fecha
                    while (!fechaValida) {
                        try {
                            System.out.print("Fecha de nacimiento (DD-MM-AAAA): ");
                            String fechaTexto = teclado.nextLine();
                            fechaNacimiento = LocalDate.parse(fechaTexto, formateador);
                            fechaValida = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("❌ Error: El formato de fecha no es válido. Debe ser DD-MM-AAAA (Ejemplo: 29-08-1995).");
                        }
                    }

                    System.out.print("Teléfono móvil: ");
                    String telefono = teclado.nextLine();

                    System.out.print("Email: ");
                    String email = teclado.nextLine();

                    System.out.print("Profesión: ");
                    String profesion = teclado.nextLine();

                    Cliente nuevo = new Cliente (nombre, apellido, ciudad, fechaNacimiento, telefono, email, profesion);
                    gestor.agregarCliente(nuevo);
                    break;

                case 2:
                    gestor.listarClientes();
                    break;

                case 3:
                    System.out.println("\n--- ACTUALIZAR CLIENTE (ENTER PARA MANTENER) ---");
                    System.out.print("Introduce el ID del cliente a modificar: ");
                    int idModificar = teclado.nextInt();
                    teclado.nextLine();


                    if (gestor.getMapaClientes().containsKey(idModificar)) {
                        Cliente actual = gestor.getMapaClientes().get(idModificar);

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

                        String nuevaFecha = "";
                        boolean nuevaFechaValida = false;

                        while (!nuevaFechaValida) {
                            try {
                                System.out.print("Nueva Fecha de nacimiento (" + actual.getNacimiento().format(formateador) + "): ");
                                nuevaFecha = teclado.nextLine();

                                if (nuevaFecha.isEmpty()) {
                                    nuevaFecha = actual.getNacimiento().format(formateador);
                                } else {
                                    LocalDate.parse(nuevaFecha, formateador); // Validamos que sea correcta
                                }
                                nuevaFechaValida = true;
                            } catch (DateTimeParseException e) {
                                System.out.println("❌ Error: El formato de fecha no es válido. Debe ser DD-MM-AAAA.");
                            }
                        }

                        System.out.print("Nueva Profesión (" + actual.getProfesion() + "): ");
                        String nuevaProfesion = teclado.nextLine();
                        if (nuevaProfesion.isEmpty()) nuevaProfesion = actual.getProfesion();

                        gestor.actualizarCliente(idModificar, nuevoNombre, nuevoApellido, nuevaCiudad, nuevoTelefono, nuevoEmail, nuevaFecha, nuevaProfesion);
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
                    gestor.mostrarEstadisticasCiudades();
                    break;

                case 6:
                    System.out.print("\nIntroduce año a buscar: ");
                    int agnoBuscar = teclado.nextInt();
                    teclado.nextLine();
                    gestor.buscarPorAgno(agnoBuscar);
                    break;

                case 7:
                    System.out.println("Cerrando el sistema.");
                    break;

                default:
                    System.out.println("Opción incorrecta. Selecciona un número del 1 al 7.");
            }

        } while (opcion != 7);
    }
}