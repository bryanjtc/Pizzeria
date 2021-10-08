
//Por: Bryan Thomas 8-964-1554
//Por Valerie Hernandez 8-1039-1537
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Pizzeria {
    private double[] recaudado = new double[5];
    private int opcion, cantidad;
    private double venta, total;
    private ArrayList<String> factura = new ArrayList<>();

    public void fijarOpcion(int valorOpcion) {
        opcion = valorOpcion;
    }

    public void fijarCantidad(int valorCantidad) {
        cantidad = valorCantidad;
    }

    public int leerInt() {// Leer un entero
        Scanner input = new Scanner(System.in);
        int num = 0;
        try {
            do {
                num = input.nextInt();
                if (num < 1)
                    System.out.println("Ingreso un numero menor a 1, intente nuevamente");
            } while (num < 0);
        } catch (InputMismatchException ex) {
            System.out.println("No ingreso un numero, intente nuevamente");
            num = leerInt();
        }
        return num;
    }

    public void agregarPizzas(boolean isCombo) {// 1.1
        Scanner input = new Scanner(System.in);
        int counter = 0, valorOpcion = 0, valorCantidad = 0;
        char continuarFactura = 0;
        final String[][] TAMANOS = { { "Personal", "5.50" }, { "Familiar", "12.00" } };
        final String[] PIZZAS = { "Jamon", "Pepperoni", "Pollo", "Vegetariana" };
        System.out.println("PIZZAS:");
        do {
            System.out.println(
                    "Ingrese el numero del tipo de pizza que desea agregar:\r\n 1. Jamon\r\n 2. Pepperoni\r\n 3. Pollo\r\n 4. Vegetariana");
            valorOpcion = leerInt();
        } while (valorOpcion < 1 || valorOpcion > 4);
        fijarOpcion(valorOpcion);
        if (isCombo == false) {
            System.out.println("Ingrese la cantidad de pizzas de " + PIZZAS[(opcion - 1)]);
            valorCantidad = leerInt();
            fijarCantidad(valorCantidad);
            factura.add(cantidad + " Pizzas de " + PIZZAS[(opcion - 1)]);
            do {
                do {
                    System.out.println("Ingrese el numero del tamaño de la pizza " + (counter + 1)
                            + "\r\n 1. Personal\r\n 2. Familiar");
                    valorOpcion = leerInt();
                } while (valorOpcion < 1 || valorOpcion > 2);
                fijarOpcion(valorOpcion);
                factura.add(" 1 " + TAMANOS[(opcion - 1)][0] + " $ " + TAMANOS[(opcion - 1)][1]);
                total += Double.valueOf(TAMANOS[(opcion - 1)][1]);
                recaudado[0] += Double.valueOf(TAMANOS[(opcion - 1)][1]);
                System.out.println("Si desea agregar un ingrediente adicional, ingrese Y para si o N para no");
                continuarFactura = input.next().charAt(0);
                if (continuarFactura == 'Y' || continuarFactura == 'y')
                    recaudado[0] += agregarIngredientes();
                counter++;
            } while (counter < cantidad);
        } else {
            factura.add(" 1 Pizza de " + PIZZAS[(opcion - 1)] + " Personal");
            System.out.println("Si desea agregar un ingrediente adicional, ingrese Y para si o N para no");
            continuarFactura = input.next().charAt(0);
            if (continuarFactura == 'Y' || continuarFactura == 'y')
                recaudado[2] += agregarIngredientes();
        }
    }

    public double agregarIngredientes() {// 1.1.1
        int valorOpcion = 0;
        final String[][] INGREDIENTES = { { "Jamon", "Pepperoni", "Pollo", "Hongos" }, { "1.00" } };
        System.out.println("Ingredientes adicionales:");
        do {
            System.out.println(
                    "Ingrese el numero del ingrediente que desea agregar:\r\n 1. Jamon\r\n 2. Pepperoni\r\n 3. Pollo\r\n 4. Hongos");
            valorOpcion = leerInt();
        } while (valorOpcion < 1 || valorOpcion > 4);
        fijarOpcion(valorOpcion);
        factura.add("  +Ingrediente adicional: " + INGREDIENTES[0][(opcion - 1)] + " $" + INGREDIENTES[1][0]);
        total += Double.valueOf(INGREDIENTES[1][0]);
        return Double.valueOf(INGREDIENTES[1][0]);
    }

    public void agregarRefrescos(boolean isCombo) {// 1.2
        int valorOpcion = 0, valorCantidad = 0, counter = 0;
        final String[] REFRESCOS = { "Soda", "Te frio" };
        final String[][] TAMANOS = { { "Regular", "1.50" }, { "Grande", "2.25" } };
        boolean isSoda = false;
        do {
            System.out.println("Ingrese el numero del tipo de refresco que desea agregar:\r\n 1. Soda\r\n 2. Te frio");
            valorOpcion = leerInt();
        } while (valorOpcion < 1 || valorOpcion > 2);
        fijarOpcion(valorOpcion);
        if (opcion == 1)
            isSoda = true;
        if (isCombo == false) {
            System.out.println("Ingrese la cantidad de refrescos que desea agregar de " + REFRESCOS[(opcion - 1)]);
            valorCantidad = leerInt();
            fijarCantidad(valorCantidad);
            factura.add(cantidad + " Refrescos de " + REFRESCOS[(opcion - 1)]);
            do {
                if (isSoda == true) {
                    System.out.println("Para la soda " + (counter + 1));
                    agregarSabores();
                }
                System.out.println(
                        "Ingrese el numero del tamaño del refresco " + (counter + 1) + "\r\n 1. Regular\r\n 2. Grande");
                valorOpcion = leerInt();
                fijarOpcion(valorOpcion);
                factura.add("   " + TAMANOS[(opcion - 1)][0] + " $ " + TAMANOS[(opcion - 1)][1]);
                total += Double.valueOf(TAMANOS[(opcion - 1)][1]);
                recaudado[1] += Double.valueOf(TAMANOS[(opcion - 1)][1]);
                counter++;
            } while (counter < cantidad);
        } else {
            factura.add(" 1 Refresco de " + REFRESCOS[(opcion - 1)] + " Regular");
            if (opcion == 1)
                agregarSabores();
        }
    }

    public void agregarSabores() {// 1.2.1
        final String[] SABORES = { "Coca Cola", "Ginger Ale", "Kist Fresa" };
        int valorOpcion = 0;
        do {
            System.out.println(
                    "Ingrese el numero del sabor que desea agregar:\r\n 1. Coca Cola\r\n 2. Ginger Ale\r\n 3. Kist Fresa");
            valorOpcion = leerInt();
        } while (valorOpcion < 1 || valorOpcion > 3);
        fijarOpcion(valorOpcion);
        factura.add("  1 " + SABORES[(opcion - 1)]);
    }

    public void agregarCombos() {// 1.3
        Scanner input = new Scanner(System.in);
        int valorCantidad = 0, counter = 0;
        char continuarFactura = 0;
        final double COMBO = 6.75, AGRANDADO = 0.70;
        boolean isCombo = true;
        System.out.println("Ingrese la cantidad de combos que desea agregar");
        valorCantidad = leerInt();
        fijarCantidad(valorCantidad);
        factura.add(cantidad + " Combo $" + " x $" + COMBO);
        do {
            total += COMBO;
            recaudado[2] += COMBO;
            System.out.println("Ingrese los datos para el COMBO " + (counter + 1));
            agregarPizzas(isCombo);
            agregarRefrescos(isCombo);
            System.out.println("Si desea agrandar el refresco, ingrese Y para si o N para no");
            continuarFactura = input.next().charAt(0);
            if (continuarFactura == 'Y' || continuarFactura == 'y') {
                factura.add("   +Refresco agrandado $" + String.format("%.2f", AGRANDADO));
                total += AGRANDADO;
                recaudado[2] += AGRANDADO;
            }
            counter++;
        } while (counter < cantidad);
    }

    public void agregarAgua() {// 1.4
        int valorCantidad = 0;
        final double AGUA = 1.50;
        System.out.println("Ingrese la cantidad de agua embotellada que desea agregar");
        valorCantidad = leerInt();
        fijarCantidad(valorCantidad);
        factura.add(cantidad + " Agua embotellada x $" + String.format("%.2f", AGUA));
        total += cantidad * AGUA;
        recaudado[3] += (cantidad * AGUA);
    }

    public void agregarPostre() {// 1.5
        int valorCantidad = 0, valorOpcion = 0;
        final String[][] POSTRES = { { "Flan", "Cheesecake", "Tiramisu" }, { "3.50" } };
        do {
            System.out.println(
                    "Ingrese el tipo de postre que desea agregar:\r\n 1. Flan\r\n 2. Cheesecake\r\n 3. Tiramisu");
            valorOpcion = leerInt();
        } while (valorOpcion < 1 || valorOpcion > 3);
        fijarOpcion(valorOpcion);
        System.out.println("Ingrese la cantidad de postres que desea agregar de " + POSTRES[0][(opcion - 1)]);
        valorCantidad = leerInt();
        fijarCantidad(valorCantidad);
        factura.add(cantidad + " Postres de " + POSTRES[0][(opcion - 1)] + " x $" + POSTRES[1][0]);
        total += cantidad * Double.valueOf(POSTRES[1][0]);
        recaudado[4] += (cantidad * Double.valueOf(POSTRES[1][0]));

    }

    public void revisarDatos() {
        if (factura.isEmpty()) {
            System.out.println("Primero ingrese el pedido de un cliente\r\n");
            facturar();
        }
    }

    public double facturar() {// 1
        Scanner input = new Scanner(System.in);
        int counter = 0, valorOpcion = 0, cliente = 1;
        char continuarFactura = 0;
        boolean isCombo = false;
        do {
            System.out.println("Facturar cliente:\r\n" + "1. Agregar pizzas\r\n" + "2. Agregar refresco\r\n"
                    + "3. Agregar combo\r\n" + "4. Agregar agua embotellada\r\n" + "5. Agregar postre\r\n"
                    + "6. Imprimir factura\r\n" + "7. Salir y ver venta del dia\r\n" + "Ingrese una opcion: ");
            valorOpcion = leerInt();
            fijarOpcion(valorOpcion);
            switch (opcion) {
                case 1: // Agregar pizza
                    do {
                        agregarPizzas(isCombo);
                        System.out.println("Si desea agregar otro tipo de pizza, ingrese Y para si o N para no");
                        continuarFactura = input.next().charAt(0);
                    } while (continuarFactura == 'Y' || continuarFactura == 'y');
                    System.out.println("Ha agregado pizza\r\n");
                    break;
                case 2:// Agregar refresco
                    do {
                        agregarRefrescos(isCombo);
                        System.out.println("Si desea agregar otro tipo refresco, ingrese Y para si o N para no");
                        continuarFactura = input.next().charAt(0);
                    } while (continuarFactura == 'Y' || continuarFactura == 'y');
                    System.out.println("Ha agregado REFRESCOS\r\n");
                    break;
                case 3:// Agregar combo
                    agregarCombos();
                    System.out.println("Ha agregado combos\r\n");
                    break;
                case 4:// Agregar agua
                    agregarAgua();
                    System.out.println("Ha agregado botellas de agua\r\n");
                    break;
                case 5:// Agregar postre
                    do {
                        agregarPostre();
                        System.out.println("Si desea agregar otro tipo de postre, ingrese Y para si o N para no");
                        continuarFactura = input.next().charAt(0);
                    } while (continuarFactura == 'Y' || continuarFactura == 'y');
                    System.out.println("Ha agregado postres\r\n");
                    break;
                case 6:// Imprimir factura
                    revisarDatos();
                    System.out.println("Factura\r\n" + "Pizzeria SuperPizza\r\n" + "Cliente #" + cliente);
                    for (counter = 0; counter < factura.size(); counter++)
                        System.out.println(factura.get(counter));
                    System.out.println("Total a pagar: $" + String.format("%.2f", total));
                    System.out.println("Ha imprimido la factura\r\n");
                    venta += total;
                    total = 0;
                    factura.clear();
                    cliente++;
                    break;
                case 7:// Salir
                    System.out.println("Ha terminado la facturacion\r\n");
                    venta += total;
                    total = 0;
                    factura.clear();
                    break;
                default:
                    System.out.println("Ingreso una opcion que no existe, intente nuevamente\r\n");
                    break;
            }
        } while (opcion != 7);
        return Math.round(venta * 100.0) / 100.0;
    }

    public void revisarRecaudacion() {
        int categoria = 0;
        boolean isVacio = true;
        do {
            if (recaudado[categoria] != 0.0)
                isVacio = false;
            categoria++;
        } while (categoria < recaudado.length);
        if (isVacio == true) {
            System.out.println("Primero facture un cliente\r\n");
            facturar();
        }
    }

    public void consultarRecaudado() {// 2
        int numeroDeCategoria;
        String categorias[] = { "Pizza", "Refresco", "Combo", "Agua", "Postre" };
        revisarRecaudacion();
        for (numeroDeCategoria = 0; numeroDeCategoria < recaudado.length; numeroDeCategoria++)
            System.out.println("Recaudado " + String.format("%.2f", recaudado[numeroDeCategoria]) + "$ en "
                    + categorias[numeroDeCategoria]);
    }

    public void consultarAportes() {// 3
        int numeroDeCategoria;
        String categorias[] = { "Pizza", "Refresco", "Combo", "Agua", "Postre" };
        revisarRecaudacion();
        for (numeroDeCategoria = 0; numeroDeCategoria < recaudado.length; numeroDeCategoria++)
            System.out.println(categorias[numeroDeCategoria] + " aporto "
                    + String.format("%.2f", (recaudado[numeroDeCategoria] * 100) / venta) + "% a la venta total");
    }

    public void sistema() {
        int valorOpcion = 0;
        do {
            System.out
                    .println("MENU\r\n" + "1. Facturar pedido\r\n" + "2. Consultar recaudacion por tipo de producto\r\n"
                            + "3. Porcentaje de aporte a las ventas por tipo de producto\r\n" + "4. Salir\r\n"
                            + "Ingrese una opcion: ");
            valorOpcion = leerInt();
            fijarOpcion(valorOpcion);
            switch (opcion) {
                case 1:// Facturar y mostrar venta
                    venta = 0;
                    System.out.println("Monto vendido en el dia: $" + String.format("%.2f", facturar()) + "\r\n");
                    break;
                case 2:// Consultar recaudado x tipo de producto
                    revisarRecaudacion();
                    consultarRecaudado();
                    System.out.println("Ha consultado lo recaudado por tipo de producto\r\n");
                    break;
                case 3:// Consultar Aportes en % x tipo de producto
                    revisarRecaudacion();
                    consultarAportes();
                    System.out.println("Ha visto el porcentaje de aporte a las ventas por tipo de producto\r\n");
                    break;
                case 4:// Salir
                    System.out.println("Ha salido");
                    break;
                default:
                    System.out.println("Ingreso una opcion que no existe, intente nuevamente\r\n");
                    break;
            }
        } while (opcion != 4);
    }

    public static void main(String[] args) {
        Pizzeria pizzeria = new Pizzeria();
        pizzeria.sistema();
    }
}