package com.bayron.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Paginas del proceso de compra: datos del cliente, resumen y confirmacion */
public class CheckoutPage extends BasePage {

    private final By campoNombre = By.id("first-name");
    private final By campoApellido = By.id("last-name");
    private final By campoCodigoPostal = By.id("postal-code");
    private final By botonContinuar = By.id("continue");
    private final By botonFinalizar = By.id("finish");
    private final By mensajeFinal = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void llenarDatos(String nombre, String apellido, String codigoPostal) {
        escribir(campoNombre, nombre);
        escribir(campoApellido, apellido);
        escribir(campoCodigoPostal, codigoPostal);
    }

    public void continuar() {
        clic(botonContinuar);
    }

    public void finalizar() {
        clic(botonFinalizar);
    }

    public String obtenerMensajeFinal() {
        return obtenerTexto(mensajeFinal);
    }
}
