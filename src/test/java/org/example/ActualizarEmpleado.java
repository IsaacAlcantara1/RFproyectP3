package org.example;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class ActualizarEmpleado {

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

    @Test(priority = 1)
    public void pruebaVisualizarListaEmpleados() {
        test = extent.createTest("Caso de prueba 1", "Visualizar lista de empleados");

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

            nombre.sendKeys("Juan Pérez");
            puesto.sendKeys("Desarrollador");
            sueldo.sendKeys("50000");
            correo.sendKeys("juan.perez@example.com");

            WebElement agregar = driver.findElement(agregarBtn);
            agregar.click();


            WebElement empleadoAgregado = driver.findElement(By.xpath("//p[contains(text(),'Juan Pérez')]"));
            assert empleadoAgregado.isDisplayed();

            assert empleadoAgregado.isDisplayed();

            test.pass("Se visualizó correctamente la lista de empleados");
        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
        } finally {
            extent.flush();
        }
    }

    @Test(priority = 2)
    public void pruebaEditarEmpleado() {
        By editarButton = By.xpath("//button[@class='btn-editar']");
        test = extent.createTest("Caso de prueba 2", "Editar empleado de la lista");

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

            nombre.sendKeys("Juan Pérez");
            puesto.sendKeys("Desarrollador");
            sueldo.sendKeys("50000");
            correo.sendKeys("juan.perez@example.com");

            WebElement agregar = driver.findElement(agregarBtn);
            agregar.click();

            WebElement editarBtn = driver.findElement(editarButton);
            editarBtn.click();
            Thread.sleep(1000);
            assert !nombre.getAttribute("value").isEmpty();
            assert !puesto.getAttribute("value").isEmpty();
            assert !sueldo.getAttribute("value").isEmpty();
            assert !correo.getAttribute("value").isEmpty();

            test.pass("Se verificó que los campos editables estén prellenados con la información actual del empleado seleccionado");
        } catch (Exception e) {
            System.out.println("Error en la prueba: " + e.getMessage());
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

