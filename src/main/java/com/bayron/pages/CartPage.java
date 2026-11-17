package com.bayron.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/** Pagina del carrito de compras */
public class CartPage extends BasePage {

    private final By nombresProductos = By.className("inventory_item_name");
    private final By botonCheckout = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> obtenerProductos() {
        esperarVisible(nombresProductos);
        return driver.findElements(nombresProductos).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void irACheckout() {
        clic(botonCheckout);
    }
}