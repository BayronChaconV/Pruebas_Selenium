package com.bayron.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Pagina del catalogo de productos (Products). */
public class InventoryPage extends BasePage {

    private final By titulo = By.className("title");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String obtenerTitulo() {
        return obtenerTexto(titulo);
    }
}