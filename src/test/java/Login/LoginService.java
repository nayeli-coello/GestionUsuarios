/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

/**
 *
 * @author Johanna Moran
 */
//class LoginService {
//
//    boolean authenticate(String admin, String admin0) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//}

public class LoginService {

    public boolean login(String username, String password) {
        // Diccionario con usuarios y contraseñas (en un entorno real, esto debería almacenarse de manera segura)
        java.util.Map<String, String> users = new java.util.HashMap<>();
        users.put("admin", "admin123");

        // Verificar si el usuario y la contraseña coinciden
        return users.containsKey(username) && users.get(username).equals(password);
    }
}