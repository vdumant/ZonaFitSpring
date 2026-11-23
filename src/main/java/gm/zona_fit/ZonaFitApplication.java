package gm.zona_fit;

import gm.zona_fit.model.Customer;
import gm.zona_fit.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

    @Autowired
    private ICustomerService customerService;

    String nl = System.lineSeparator();

    private static final Logger logger =
            LoggerFactory.getLogger(ZonaFitApplication.class);

    public static void main(String[] args) {
        logger.info("Iniciando la aplicacion");
        // Levantar la fabrica de spring
        SpringApplication.run(ZonaFitApplication.class, args);
        logger.info("Aplicacion finalizada!");
    }

    @Override
    public void run(String... args) throws Exception {
        zonaFitApp();
    }

    private void zonaFitApp() {
        var salir = false;
        var consola = new Scanner(System.in);
        while (!salir) {
            var opcion = mostraMenu(consola);
            salir = ejecutarOpciones(consola, opcion);
            logger.info(nl);
        }
    }


    private int mostraMenu(Scanner consola) {
        logger.info("""
                      \n*** Aplicacion Zona Fit (GYM) ***
                1. Listar Customers
                2. Buscar Customer
                3. Agregar Customer
                4. Modificar Customer
                5. Eliminar Customer
                6. Salir
                Elige una opcion:\s""");
        return Integer.parseInt(consola.nextLine());
    }

    private boolean ejecutarOpciones(Scanner consola, int opcion) {
        var salir = false;
        switch (opcion) {
            case 1 -> {
                logger.info(nl + "--- Listado de Customers ---" + nl);
                List<Customer> customers = customerService.listCustomers();
                customers.forEach(customer -> logger.info(customer.toString() + nl));
            }
            case 2 -> {
                logger.info(nl + "--- Buscar Customer por Id ---" + nl);
                logger.info("Id Customer a buscar: ");
                var idCustomer = Integer.parseInt(consola.nextLine());
                Customer customer = customerService.findCustomerById(idCustomer);
                if (customer != null)
                    logger.info("Customer encontrado: " + customer + nl);
                else
                    logger.info("Customer NO encontrado: " + customer + nl);
            }
            case 3 -> {
                logger.info("--- Agregar Customer ---" + nl);
                logger.info("Nombre: ");
                var nombre = consola.nextLine();
                logger.info("Apellido: ");
                var apellido = consola.nextLine();
                logger.info("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                var customer = new Customer();
                customer.setFirstname(nombre);
                customer.setLastname(apellido);
                customer.setMembership(membresia);
                customerService.saveCustomer(customer);
                logger.info("Customer agregado: " + customer + nl);
            }
            case 4 -> {
                logger.info("--- Modificar Customer ---" + nl);
                logger.info("Id Customer: ");
                var idCustomer = Integer.parseInt(consola.nextLine());
                Customer customer = customerService.findCustomerById(idCustomer);
                if (customer != null) {
                    logger.info("Nombre: ");
                    var nombre = consola.nextLine();
                    logger.info("Apellido: ");
                    var apellido = consola.nextLine();
                    logger.info("Membresia: ");
                    var membresia = Integer.parseInt(consola.nextLine());
                    customer.setFirstname(nombre);
                    customer.setLastname(apellido);
                    customer.setMembership(membresia);
                    customerService.saveCustomer(customer);
                    logger.info("Customer modificado: " + customer + nl);
                }
            }
            case 5 -> {
                logger.info("--- Eliminar Customer ---" + nl);
                logger.info("Id Customer: ");
                var idCustomer = Integer.parseInt(consola.nextLine());
                var customer = customerService.findCustomerById(idCustomer);
                if(customer != null){
                    customerService.deleteCustomer(customer);
                    logger.info("Customer eliminado: " + customer + nl);
                }
                else
                    logger.info("Customer No encontrado: " + customer + nl);
            }
            case 6 -> {
                logger.info("Hasta pronto!" + nl + nl);
                salir = true;
            }
            default -> logger.info("Opcion NO reconocida: " + opcion + nl);
        }
        return salir;
    }
}
