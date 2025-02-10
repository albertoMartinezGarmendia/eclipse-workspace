
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;


public class Session {

    //Atributos-----------------------
    private User user;
    private boolean logged;
    static Scanner teclado = new Scanner(System.in);

    //Contructor vacio-----------------------
    public Session() {
        //guardas
        this.user = new User();
        this.logged = false;
    }

    //Getters---------------

    public User getUser() {
        return user;
    }

    public boolean isLogged() {
        return logged;
    }

    //Setters-----------------


    public void setUser(User user) {
        this.user = user;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    //Metodos
    public String login(String username, String password) {
        String[] fields = arrayInfo(username);
        if (fields == null || fields.length == 0) {
            return "El usuario no existe";
        } else {
            if (fields[0].equalsIgnoreCase(username) && fields[1].equals(password)) {
                this.logged = true;
                this.user.setName(fields[2]);
                this.user.setNif(fields[3]);
                this.user.setEmail(fields[4]);
                this.user.setBirthDate(fields[5]);
                this.user.setAddress(fields[6]);
                return "Has iniciado sesión";
            } else {
                return "Contraseña incorrecta";
            }
        }
    }


    public String signup(String username, String password, String name, String nif, String email, String birthDate, String address) {
        String[] fields = arrayInfo(username);
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(Config.USERS_FILE, true));
            if (!userExists(username)) {
                writer.write(username + "#" + password + "#" + name + "#" + nif + "#" + email + "#" + birthDate + "#" + address + "\n");
                writer.close();
                return "Usuario creado exitosamente\n";
            } else {
                return "El nombre de usuario ya está en uso\n";
            }
        } catch (IOException e) {
            return "Fichero no encontrado\n";
        }

    }

    public boolean userExists(String username) {

        String[] fields = arrayInfo(username);
        return fields != null && fields.length > 0 && fields[0].equalsIgnoreCase(username);
    }

    public String showUser(String username, String[] fields) {

        return "Nombre: " + this.user.getName() +
                "\nNif: " + this.user.getNif() +
                "\nEmail: " + this.user.getEmail() +
                "\nFecha nacimiento: " + this.user.getBirthDate() +
                "\nDirección: " + this.user.getAddress();
    }


    public String[] arrayInfo(String username) {
        File file = new File(Config.USERS_FILE);
        String[] fields = null;

        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                fields = line.split("#");
                if (fields[0].equalsIgnoreCase(username)) {
                    return fields;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Fichero no encontrado: ");
        }

        return null;
    }

    public void logout() {
        this.logged = false;
        this.user = null;
    }

    public String requestInfo(String data) {
        String info;
        while (true) {
            System.out.println(data);
            info = teclado.nextLine();

            if (!info.trim().isEmpty()) {
                break;
            }
        }
        return info;
    }
}