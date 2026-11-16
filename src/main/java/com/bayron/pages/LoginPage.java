package com.bayron.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Pagina de inicio de sesion: https://www.saucedemo.com/ */
public class LoginPage extends BasePage {

    public static final String URL = "https://www.saucedemo.com/";

    private final By campoUsuario = By.id("user-name");
    private final By campoClave = By.id("password");
    private final By botonLogin = By.id("login-button");
    private final By mensajeError = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage abrir() {
        driver.get(URL);
        return this;
    }

    public void iniciarSesion(String usuario, String clave) {
        escribir(campoUsuario, usuario);
        escribir(campoClave, clave);
        clic(botonLogin);
    }

    public String obtenerMensajeError() {
        return obtenerTexto(mensajeError);
    }
}