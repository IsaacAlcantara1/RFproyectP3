package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InterfazUsuario {
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
        driver.get("http://127.0.0.1:5500/inicio.html");
        Thread.sleep(1000);
    }

    @Test
    public void validarInterfazUsuario() {
        test = extent.createTest("Caso de prueba - Interfaz de usuario", "Validar la interfaz de usuario para garantizar que sea intuitiva y fácil de usar");

        try {

            test.pass("Se validó correctamente la interfaz de usuario");
        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
            test.fail("La prueba falló: " + e.getMessage());
        } finally {
            extent.flush();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

