package com.bayron.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/** Pagina del catalogo de productos (Products). */
public class InventoryPage extends BasePage {

    private final By titulo = By.className("title");
    private final By contadorCarrito = By.className("shopping_cart_badge");
    private final By enlaceCarrito = By.className("shopping_cart_link");
    private final By listaOrden = By.className("product_sort_container");
    private final By precios = By.className("inventory_item_price");

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

    /** Ordena los productos. */
    public void ordenarPor(String valor) {
        new Select(esperarVisible(listaOrden)).selectByValue(valor);
    }

    /** Devuelve los precios en el orden en que aparecen en pantalla */
    public List<Double> obtenerPrecios() {
        esperarVisible(precios);
        return driver.findElements(precios).stream()
                .map(p -> Double.parseDouble(p.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public void irAlCarrito() {
        clic(enlaceCarrito);
    }
}