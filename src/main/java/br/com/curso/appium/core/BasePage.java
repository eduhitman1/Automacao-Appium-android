package br.com.curso.appium.core;

import static br.com.curso.appium.core.DriverFactory.getDriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class BasePage {

//	private MobileElement findElement;

	public void escrever(By by, String texto) {
		getDriver().findElement(by).sendKeys(texto);
	}

	public String obterTexto(By by) {
		return getDriver().findElement(by).getText();
	}

	public void clicar(By by) {
		getDriver().findElement(by).click();
	}

	public void clicarPorTexto(String texto) {
		clicar(By.xpath("//*[@text='" + texto + "']"));
//		getDriver().findElement(By.xpath("//*[@text='"+texto+"']")).click();
	}

	public void selecionarCombo(By by, String valor) {
		getDriver().findElement(by).click();
		clicarPorTexto(valor);
//		getDriver().findElement(By.xpath("//android.widget.CheckedTextView[@text='"+valor+"']")).click();
	}

	public boolean isCheckMarcado(By by) {
		return getDriver().findElement(by).getAttribute("checked").equals("true");
	}

	// VERIFICAR NOVAMENTEO
//	List<MobileElement> elementos = getDriver().findElement(By.xpath("//*[@text='"+texto+"']"));
	public boolean existeElementoPorTexto(String texto) {
     List<MobileElement> elementos = (List<MobileElement>) getDriver()
				.findElements(By.xpath("//*[@text='" + texto + "']"));
		return elementos.size() > 0;
	}
	
	
	public void tap(int x, int y) {
		 new TouchAction(getDriver()).tap(x,y).perform();
	}
	
	public String obterMensagemAlert() {
		return obterTexto(By.id("android:id/message"));
	}


	public void scrollDown() {
		scroll(0.9, 0.1);
	}
	public void scrollUp() {
		scroll(0.1, 0.9);
	}
	public void scroll(double inicio, double fim ) {
		Dimension size = getDriver().manage().window().getSize();
		int x = size.width /2;
		int start_y = (int)(size.height* inicio);
		int end_y = (int)(size.height *fim);
		
		new TouchAction(getDriver())
		.press(x,start_y)
		.waitAction(Duration.ofMillis(500))
		.moveTo(x, end_y)
		.release()
		.perform();
	}
	
	
	
	
	public void swipeDireita() {
		swipe(0.9, 0.1);
	}
	public void swipeEsquerda() {
		swipe(0.1, 0.9);
	}
	public void swipe(double inicio, double fim) {
		Dimension size = getDriver().manage().window().getSize();
		
		int y=0;
		y = size.height /2;
		
		int start_x = (int)(size.width* inicio);
		
		int end_x=0;
		end_x = (int)(size.width *fim);
		
		new TouchAction(getDriver())
		.press(start_x, y)
		.waitAction(Duration.ofMillis(500))
		.moveTo(end_x, y)
		.release()
		.perform();
	}		
	
	
	
	public void swipeElementy(MobileElement element, double inicio, double fim) {
        int y = element.getLocation().y + (element.getSize().height/2);
		
		int start_x = (int)(element.getSize().width* inicio);
		int end_x = (int)(element.getSize().width *fim);
		
		new TouchAction(getDriver())
		.press(start_x, y)
		.waitAction(Duration.ofMillis(500))
		.moveTo(end_x, y)
		.release()
		.perform();
	}
	
}
