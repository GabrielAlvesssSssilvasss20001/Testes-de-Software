package br.com.ifpb.testesdesoftware.bookflix.system;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DetalharLivroSystemTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testDetalharLivro() {
        driver.get("http://localhost:8080/livros/1");

        String titulo = driver.findElement(By.cssSelector(".titulo")).getText();
        assertEquals("Livro 1", titulo);

        String autor = driver.findElement(By.cssSelector(".autor")).getText();
        assertEquals("Autor 1", autor);

        String anoPublicacao = driver.findElement(By.cssSelector(".ano-publicacao")).getText();
        assertEquals("2022", anoPublicacao);
    }
}
