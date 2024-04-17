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

public class RedirigirVacaciones {
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
    public void pruebaRedireccionVacaciones() {
        By botonRedirigir = By.xpath("//button[@id='btnVacaciones']");

        test = extent.createTest("Redireccionar a Vacaciones/Ausencias", "Verificar que el botón Vacaciones/Ausencias redirige correctamente");


        WebElement vacacionesButton = driver.findElement(botonRedirigir);
        vacacionesButton.click();
        By titulo = By.xpath("//h1[text()='Gestión de Ausencias y Vacaciones']");
        WebElement titulo1 = driver.findElement(titulo);
        titulo1.isDisplayed();

        test.pass("El botón Vacaciones/Ausencias redirigió correctamente a la nueva página");
    }

    @AfterClass
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }


        extent.flush();
    }
}

