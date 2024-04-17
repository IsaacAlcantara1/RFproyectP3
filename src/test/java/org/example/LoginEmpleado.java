package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginEmpleado {
    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setUp() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");


        driver = new ChromeDriver();


        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\alcan\\IdeaProjects\\MyTest\\reporte\\reporte.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        driver.get("http://127.0.0.1:5500/Login.html");
        Thread.sleep(1000);
    }

    @Test
    public void pruebaCamposVacios() {
        By errorMessage = By.xpath("//p[text()='Por favor complete todos los campos']");
        By name = By.xpath("//input[@id='username']");
        By password = By.xpath("//input[@id='password']");
        By loginButton = By.xpath("//button[@id='btnLogin']");

        test = extent.createTest("Caso de prueba 1", "Verificar que los campos de nombre de usuario y contraseña no estén vacíos");

        try {
            Thread.sleep(1000);
            WebElement usuarioInput = driver.findElement(name);
            WebElement contraseñaInput = driver.findElement(password);
            WebElement loginBtn = driver.findElement(loginButton);
            usuarioInput.sendKeys("");
            contraseñaInput.sendKeys("");
            loginBtn.click();
            Thread.sleep(1000);
            WebElement mensajeError = driver.findElement(errorMessage);
            assert(mensajeError.getText().contains("Por favor complete todos los campos"));
            test.pass("Se verificó que los campos de nombre de usuario y contraseña no estén vacíos");
        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
        } finally {
            extent.flush();
        }
    }

    @Test
    public void pruebaInicioSesionExitoso() {
        By name = By.xpath("//input[@id='username']");
        By password = By.xpath("//input[@id='password']");
        By loginButton = By.xpath("//button[@id='btnLogin']");

        test = extent.createTest("Caso de prueba 2", "Inicio de sesión exitoso");

        try {
            WebElement usuarioInput = driver.findElement(name);
            WebElement contraseñaInput = driver.findElement(password);
            WebElement loginBtn = driver.findElement(loginButton);
            usuarioInput.sendKeys("admin");
            Thread.sleep(1000);
            contraseñaInput.sendKeys("admin");
            loginBtn.click();
            test.pass("Se realizó el inicio de sesión exitosamente");
        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
        } finally {
            extent.flush();
        }
    }

    @AfterClass
    public void tearDown() {
        // Cerrar el navegador
        if (driver != null) {
            driver.quit();
        }
    }
}




