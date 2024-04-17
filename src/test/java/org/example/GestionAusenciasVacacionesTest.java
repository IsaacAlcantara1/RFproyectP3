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
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class GestionAusenciasVacacionesTest {
    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    private  WebDriverWait wait;
    @BeforeClass
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\alcan\\IdeaProjects\\MyTest\\reporte\\reporte.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        driver.get("http://127.0.0.1:5500/gestion.html");
        Thread.sleep(2000);
    }

    @Test
    public void agregarAusenciaExitosa() {
        test = extent.createTest("Agregar Ausencia Exitosa", "Verificar que se puede agregar una nueva ausencia correctamente");

        try {
            Thread.sleep(3000);
            By nombre = By.xpath("//input[@id='nombre']");
            By fecha = By.xpath("//input[@id='fecha']");
            By agregar = By.xpath("//button[text()='Agregar']");

            WebElement nombreInput = driver.findElement(nombre);
            WebElement fechaInput = driver.findElement(fecha);
            WebElement submitBtn = driver.findElement(agregar);


            nombreInput.sendKeys("Empleado1");
            Thread.sleep(1000);
            fechaInput.sendKeys("02-07-2024");
            Thread.sleep(1000);
            submitBtn.click();

            By vacacion = By.xpath("//li[text()='Empleado1: Ausencia el 02/07/2024']");
            WebElement vacacionlist = driver.findElement(vacacion);
            Thread.sleep(1000);
            vacacionlist.isDisplayed();

            test.pass("Ausencia agregada correctamente");
        } catch (Exception e) {
            test.fail("Error al agregar la ausencia: " + e.getMessage());
        }
    }

    @Test
    public void agregarAusenciaCamposVacios() {
        test = extent.createTest("Agregar Ausencia con Campos Vacíos", "Verificar que no se puede agregar una nueva ausencia si faltan campos");

        try {

            By agregar = By.xpath("//button[text()='Agregar']");
            WebElement submitBtn = driver.findElement(agregar);

            submitBtn.click();
            By camp = By.xpath("//span[text()='Todos los campos son obligatorios']");
            WebElement campoobligatorio = driver.findElement(camp);
            campoobligatorio.isDisplayed();
            Thread.sleep(1000);

            test.pass("Se detectó correctamente que faltan campos");
        } catch (Exception e) {
            test.fail("Error inesperado: " + e.getMessage());
        }
    }

    @Test
    public void eliminarAusencia() {
        test = extent.createTest("Eliminar Ausencia", "Verificar que se puede eliminar una ausencia de la lista");

        try {
            agregarAusenciaExitosa();


            WebElement eliminarBtn = driver.findElement(By.xpath("//button[contains(text(),'Eliminar')]"));
            eliminarBtn.click();
            Thread.sleep(2000);

            test.pass("Ausencia eliminada correctamente");
        } catch (Exception e) {
            test.fail("Error al eliminar la ausencia: " + e.getMessage());
        }
    }

    @Test
    public void verificarFormatoFecha() {
        test = extent.createTest("Verificar Formato de Fecha", "Verificar que la fecha de la primera ausencia en la lista tiene el formato correcto");

        try {
            agregarAusenciaExitosa();
            By fecha= By.xpath("//li[text()='Empleado1: Ausencia el 1/7/2024']");

            WebElement primeraAusencia = driver.findElement(fecha);
            String fechaTexto = primeraAusencia.getText().split(": ")[1].split(" el ")[1];
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                sdf.parse(fechaTexto);
                test.pass("El formato de fecha es correcto");
            } catch (ParseException e) {
                test.fail("El formato de fecha es incorrecto");
            }
        } catch (Exception e) {
            test.fail("Error al verificar el formato de fecha: " + e.getMessage());
        }
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}

