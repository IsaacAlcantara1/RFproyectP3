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

public class GuardarDatosLocalmente {
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
    public void guardarDatosLocalmente() {

        test = extent.createTest("Caso de prueba - Guardar datos localmente", "Guardar datos de empleado localmente en el navegador");

        try {
            By nombreInput = By.xpath("//input[@id='nombre']");
            By puestoInput = By.xpath("//input[@id='puesto']");
            By sueldoInput = By.xpath("//input[@id='sueldo']");
            By correoInput = By.xpath("//input[@id='correo']");
            By agregarBtn = By.xpath("//button[@id='btnAgregar']");

            WebElement nombre = driver.findElement(nombreInput);
            WebElement puesto = driver.findElement(puestoInput);
            WebElement sueldo = driver.findElement(sueldoInput);
            WebElement correo = driver.findElement(correoInput);


            nombre.sendKeys("Gabriel");
            puesto.sendKeys("Cyber");
            sueldo.sendKeys("50000");
            correo.sendKeys("Gabriel@ejemplo.com");

            WebElement agregar = driver.findElement(agregarBtn);
            agregar.click();

            test.pass("Se guardaron correctamente los datos del empleado localmente");
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
