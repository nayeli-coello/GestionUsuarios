/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import org.junit.jupiter.api.Test;
///**
// *
// * @author Johanna Moran
// */
//public class LoginServiceTest {
//    
//    @Test
//    public void testLoginSuccess() {
//        // Configura el objeto de servicio simulado
//        LoginService loginService = mock(LoginService.class);
//        when(loginService.authenticate("admin", "admin")).thenReturn(true);
//
//        // Realiza la prueba utilizando el objeto simulado
//        assertTrue(loginService.authenticate("admin", "admin"));
//
//        // Verifica que se llamó al método authenticate con los parámetros correctos
//        verify(loginService, times(1)).authenticate("admin", "admin");
//    }
//
//    @Test
//    public void testLoginFailure() {
//        // Configura el objeto de servicio simulado
//        LoginService loginService = mock(LoginService.class);
//        when(loginService.authenticate("admin", "wrongpassword")).thenReturn(false);
//
//        // Realiza la prueba utilizando el objeto simulado
//        assertFalse(loginService.authenticate("admin", "wrongpassword"));
//
//        // Verifica que se llamó al método authenticate con los parámetros correctos
//        verify(loginService, times(1)).authenticate("admin", "wrongpassword");
//    }
//}

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginServiceTest {

    @Test
    public void testLoginSuccess() {
        LoginService loginService = new LoginService();
        assertTrue(loginService.login("admin", "admin123"));
    }

    @Test
    public void testLoginFailure() {
        LoginService loginService = new LoginService();
        assertFalse(loginService.login("admin", "wrongpassword"));
    }

    @Test
    public void testLoginFailureInvalidUser() {
        LoginService loginService = new LoginService();
        assertFalse(loginService.login("invaliduser", "password"));
    }
}
