package br.com.iago.tasks.functionaltests;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste com selenium");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
			
			driver.findElement(By.id("saveButton")).click();
			
			String actualMessage = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Sucess!", actualMessage);
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarComDataPassada() {
		WebDriver driver = acessarAplicacao();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste com selenium");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			
			driver.findElement(By.id("saveButton")).click();
			
			String actualMessage = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", actualMessage);
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemTask() {
		WebDriver driver = acessarAplicacao();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
			
			driver.findElement(By.id("saveButton")).click();
			
			String actualMessage = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", actualMessage);
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste com selenium");
			
			driver.findElement(By.id("saveButton")).click();
			
			String actualMessage = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", actualMessage);
		} finally {
			driver.quit();
		}
	}
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://192.168.10.13:8080/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
}
