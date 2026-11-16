package com.bayron.tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/*
 * Prepara y cierra el navegador antes y despues de cada caso de prueba
 * Al final de cada caso guarda una captura de pantalla en target/capturas.
 */
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    void abrirNavegador() {
        ChromeOptions opciones = new ChromeOptions();

        Map<String, Object> preferencias = new HashMap<>();
        preferencias.put("credentials_enable_service", false);
        preferencias.put("profile.password_manager_enabled", false);
        preferencias.put("profile.password_manager_leak_detection", false);
        opciones.setExperimentalOption("prefs", preferencias);
        opciones.addArguments("--start-maximized", "--disable-features=PasswordLeakDetection");

        driver = new ChromeDriver(opciones);
    }

    @AfterEach
    void cerrarNavegador(TestInfo info) {
        try {
            File captura = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String nombre = info.getTestMethod().map(Method::getName).orElse("prueba");
            Path destino = Paths.get("target", "capturas", nombre + ".png");
            Files.createDirectories(destino.getParent());
            Files.copy(captura.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("No se pudo guardar la captura: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
