package com.bayron.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Pagina del catalogo de productos (Products). */
public class InventoryPage extends BasePage {

    private final By titulo = By.className("title");
    private final By contadorCarrito = By.className("shopping_cart_badge");
    private final By enlaceCarrito = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String obtenerTitulo() {
        return obtenerTexto(titulo);
    }

    /** Agrega un producto usando su identificador. */
    public void agregarAlCarrito(String idProducto) {
        clic(By.id("add-to-cart-" + idProducto));
    }

    public String obtenerCantidadCarrito() {
        return obtenerTexto(contadorCarrito);
    }

    public void irAlCarrito() {
        clic(enlaceCarrito);
    }
}