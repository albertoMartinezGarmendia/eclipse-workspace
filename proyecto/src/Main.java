

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int option = -1;


        System.out.println(Config.WELCOME);
        //pedir la opcion que se usará en el menú sin haber iniciado sesión
        while (true) {
            //mostrar menú sin iniciar sesión
            option = showUnloggedMenu(teclado);
            teclado.nextLine();
            Session session = new Session();
            logout:
            switch (option) {
                //Iniciar sesión-------------------------------------
                case 1:
                    startSession(teclado, session);
                    break;
                //Registrarse-----------------------------
                case 2:
                    register(teclado, session);
                    break;
                //Acabar el programa-------------------------
                case 0:
                    System.out.println(Config.GOODBYE);
                    System.exit(0);
                default:
                    System.out.println("Introduzca un opción válida");


            }
        }
    }

    public static int showUnloggedMenu(Scanner teclado) {
        System.out.println(Config.UNLOGGED_MENU);
        return teclado.nextInt();
    }

    //Metodo para registrar usuarios
    public static void register(Scanner teclado, Session session) {
        String nif, address, username, birthDate, password, name, email;

        System.out.println("Introduzca los siguientes datos");
        while (true) {

            username = session.requestInfo("Nombre de usuario: ");
            password = session.requestInfo("contraseña: ");
            name = session.requestInfo("Nombre: ");
            nif = session.requestInfo("nif: ");
            email = session.requestInfo("email: ");
            birthDate = session.requestInfo("Fecha nacimiento(dd/mm/aaaa): ");
            address = session.requestInfo("Dirección: ");
            break;
        }
        System.out.println(session.signup(username, password, name, nif, email, birthDate, address));

    }

    //Metodo para login y post-login
    public static void startSession(Scanner teclado, Session session) {
        String username, password;

        int option;
        System.out.println("Introduzca los siguientes datos");
        while (true) {

            username = session.requestInfo("Nombre de usuario: ");
            password = session.requestInfo("contraseña: ");
            break;
        }
        String loginResult = session.login(username, password);
        if (loginResult != null && loginResult.equals("Has iniciado sesión")) {
            System.out.println(loginResult);
        }
        if (session.isLogged()) {
            //si iniciamos sesión elegimos la próxima opción --------------------
            while (true) {
                System.out.println(Config.LOGGED_MENU);
                option = teclado.nextInt();
                //del 1-4 aún no está implementado---------------------
                switch (option) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        System.out.println("Proximamente");
                        break;
                    //información usuario iniciado sesión-----------------------------------
                    case 5:
                        System.out.println(session.showUser(username, session.arrayInfo(username)));
                        break;
                    //cerrar sesión-----------------------
                    case 6:
                        session.logout();
                        return;
                    //Acabar programa-------------------------
                    case 0:
                        System.out.println(Config.GOODBYE);
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            }
            //error si fallamos usuario o contraseña
        } else {
            System.out.println("Error: Usuario o contraseña incorrecta");
        }
    }
}