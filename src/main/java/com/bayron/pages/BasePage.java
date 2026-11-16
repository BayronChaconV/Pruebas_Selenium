package com.bayron.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Clase base del patron Page Object Model
 */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Espera a que el elemento se vea en pantalla y lo devuelve
     */
    protected WebElement esperarVisible(By localizador) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(localizador));
    }

    /**
     * Espera a que el elemento se pueda presionar y le da clic
     */
    protected void clic(By localizador) {
        wait.until(ExpectedConditions.elementToBeClickable(localizador)).click();
    }

    /**
     * Borra el campo y escribe el texto indicado
     */
    protected void escribir(By localizador, String texto) {
        WebElement campo = esperarVisible(localizador);
        campo.clear();
        campo.sendKeys(texto);
    }

    /**
     * Devuelve el texto visible de un elemento
     */
    protected String obtenerTexto(By localizador) {
        return esperarVisible(localizador).getText();
    }
}
