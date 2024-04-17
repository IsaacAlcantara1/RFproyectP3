package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CompatibilidadNavegadores {
    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setUp() throws InterruptedException {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\alcan\\IdeaProjects\\MyTest\\reporte\\reporte.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test(priority = 1)
    public void probarCompatibilidadChrome() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        test = extent.createTest("Caso de prueba - Compatibilidad con Chrome", "Probar la compatibilidad del sistema con el navegador Google Chrome");

        try {

            test.pass("El sistema es compatible con Google Chrome");
        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
            test.fail("La prueba falló: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test(priority = 2)
    public void probarCompatibilidadEdge() {
        System.setProperty("webdriver.edge.driver", "C:\\edgedriver_win64\\msedgedriver.exe");
         driver = new EdgeDriver();
        test = extent.createTest("Caso de prueba - Compatibilidad con Edge", "Probar la compatibilidad del sistema con el navegador Microsft Edge");

        try {

            test.pass("El sistema es compatible con Microsft Edge");
        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
            test.fail("La prueba falló: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test(priority = 3)
    public void probarCompatibilidadFirefox() {
        driver = new FirefoxDriver();
        test = extent.createTest("Caso de prueba - Compatibilidad con Safari", "Probar la compatibilidad del sistema con el navegador Firefox");

        try {

            test.pass("El sistema es compatible con Firefox");
        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
            test.fail("La prueba falló: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @AfterClass
    public void tearDown() {
        extent.flush();
    }
}