package com.bayron.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.bayron.pages.CartPage;
import com.bayron.pages.InventoryPage;
import com.bayron.pages.LoginPage;

/**
 * Casos de prueba automatizados del sitio https://www.saucedemo.com/
 * Cada caso compara el resultado obtenido con el resultado esperado
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SauceDemoTest extends BaseTest {

    private static final String USUARIO_VALIDO = "standard_user";
    private static final String USUARIO_BLOQUEADO = "locked_out_user";
    private static final String CLAVE = "secret_sauce";
    private static final String PRODUCTO_ID = "sauce-labs-backpack";
    private static final String PRODUCTO_NOMBRE = "Sauce Labs Backpack";

    @Test
    @Order(1)
    @DisplayName("CP-01 Inicio de sesión con datos validos")
    void cp01_loginValido() {
        new LoginPage(driver).abrir().iniciarSesion(USUARIO_VALIDO, CLAVE);
        InventoryPage catalogo = new InventoryPage(driver);

        assertEquals("Products", catalogo.obtenerTitulo());
        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test
    @Order(2)
    @DisplayName("CP-02 Inicio de sesion con contraseña incorrecta")
    void cp02_loginClaveIncorrecta() {
        LoginPage login = new LoginPage(driver).abrir();
        login.iniciarSesion(USUARIO_VALIDO, "clave_incorrecta");

        assertEquals("Epic sadface: Username and password do not match any user in this service",
                login.obtenerMensajeError());
    }

    @Test
    @Order(3)
    @DisplayName("CP-03 Inicio de sesion con usuario bloqueado")
    void cp03_loginUsuarioBloqueado() {
        LoginPage login = new LoginPage(driver).abrir();
        login.iniciarSesion(USUARIO_BLOQUEADO, CLAVE);

        assertEquals("Epic sadface: Sorry, this user has been locked out.",
                login.obtenerMensajeError());
    }

    @Test
    @Order(4)
    @DisplayName("CP-04 Agregar un producto al carrito")
    void cp04_agregarProductoAlCarrito() {
        new LoginPage(driver).abrir().iniciarSesion(USUARIO_VALIDO, CLAVE);
        InventoryPage catalogo = new InventoryPage(driver);
        catalogo.agregarAlCarrito(PRODUCTO_ID);

        assertEquals("1", catalogo.obtenerCantidadCarrito());

        catalogo.irAlCarrito();
        List<String> productos = new CartPage(driver).obtenerProductos();
        assertEquals(List.of(PRODUCTO_NOMBRE), productos);
    }
}