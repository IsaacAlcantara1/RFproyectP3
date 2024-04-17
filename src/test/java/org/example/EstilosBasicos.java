package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EstilosBasicos {
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
    public void verificarColorFondo() throws InterruptedException {
        test = extent.createTest("Verificar Color de Fondo", "Validar que el color de fondo de un elemento sea el esperado");
        try {
            WebElement elemento = driver.findElement(By.xpath("//body"));
            String colorFondo = elemento.getCssValue("background-color");
            Assert.assertEquals(colorFondo, "rgba(255, 228, 196, 1)", "El color de fondo no es el esperado");

            test.pass("Se validó correctamente el color de fondo del elemento");
        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
            test.fail("La prueba falló: " + e.getMessage());
        }
    }

    @Test
    public void verificarTamañoFuente() throws InterruptedException {
        test = extent.createTest("Verificar Tamaño de Fuente", "Validar que el tamaño de fuente de un elemento sea el esperado");
        By formulario = By.xpath("//h2[normalize-space()='Formulario']");
        try {
            WebElement elemento = driver.findElement(formulario);
            String tamañoFuente = elemento.getCssValue("font-size");
            Assert.assertEquals(tamañoFuente, "32px", "El tamaño de la fuente no es el esperado");
            test.pass("Se validó correctamente el tamaño de la fuente del elemento");
        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
            test.fail("La prueba falló: " + e.getMessage());
        }
    }

    @Test
    public void verificarEspaciadoEntreElementos() throws InterruptedException {
        test = extent.createTest("Verificar margenes y relleno Entre Elementos", "Validar que el margen y relleno entre dos elementos sea el esperado");
        By nombre = By.xpath("//input[@id='nombre']");
        By puesto = By.xpath("//input[@id='puesto']");
        try {
            WebElement elemento = driver.findElement(nombre);
            WebElement elementos= driver.findElement(puesto);
            String elemento1 = elemento.getCssValue("margin");
            Assert.assertEquals(elemento1, "10px", "El margen entre elementos no es el esperado");
            String elemento2 = elementos.getCssValue("padding");
            Assert.assertEquals(elemento2, "10px", "El relleno entre elementos no es el esperado");
            test.pass("Se validó correctamente el espaciado entre elementos");
        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
            test.fail("La prueba falló: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
