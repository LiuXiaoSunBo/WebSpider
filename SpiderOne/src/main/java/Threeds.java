import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Threeds extends Thread {

    WebDriver webDriver = null;

    public Threeds( WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    @Override
    public void run() {
        try {
            taobaogo(webDriver);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void rodomsleep() throws InterruptedException {
        Thread.sleep((int) (3000 + Math.random() * (5000 - 3000 + 1)));
    }
    public static void   ispay(WebDriver webDriver) throws InterruptedException {
        if (webDriver.getCurrentUrl().contains("buy_now")||webDriver.getCurrentUrl().contains("confirm_order")) {
            webDriver.findElement(By.id("submitOrderPC_1")).findElement(By.xpath("./div/a")).click();
            Thread.sleep(50000);
        }
    }

    public static void taobaogo(WebDriver webDriver) throws InterruptedException {
        rodomsleep();
        try {
            String url ="https://detail.tmall.com/item.htm?id=580860384053&spm=a21bz.7725273.1998564503.44.35d63db8wGJVnm&umpChannel=qianggou&u_channel=qianggou";
            if (url != "") {
                ((ChromeDriver)webDriver).executeScript("window.open('"+url+"')", "");
                List<String> list = new ArrayList<String>(webDriver.getWindowHandles());
                webDriver.switchTo().window(list.get(list.size()-1));
            }
            WebElement j_linkBuy = null;
            rodomsleep();
            while (true){
                try {
                    j_linkBuy = webDriver.findElement(By.id("J_LinkBuy"));

                } catch (Exception e) {
                    try {
                        j_linkBuy = webDriver.findElement(By.className("J_LinkBuy"));

                    }catch (Exception e1){
                        System.out.println("正在抢购.....");
                    }
                }
                try {
                    j_linkBuy.click();
                }catch (Exception e){
                    System.out.println("正在抢购");
                    continue;
                }

                if (j_linkBuy!=null){
                    break;
                }
            }

            if (webDriver.getCurrentUrl().contains("buy_now")||webDriver.getCurrentUrl().contains("confirm_order")) {
                webDriver.findElement(By.id("submitOrderPC_1")).findElement(By.xpath("./div/a")).click();
                rodomsleep();
//                if (webDriver.getCurrentUrl().contains("lightPayCashier")) {
//                    WebElement sixDigitPassword = webDriver.findElement(By.id("payPassword_rsainput"));
//                    System.out.println(sixDigitPassword.getTagName());
//                    sixDigitPassword.sendKeys("123456");
//                    Thread.sleep(150000);
//                }
            } else {
                rodomsleep();
                WebElement element = null;
                try {
                    element = webDriver.findElement(By.cssSelector(".J_TSaleProp"));
                } catch (Exception e) {
                    System.out.println("没有尺码");
                }
                if (element != null) {

                    WebElement element1 = element.findElement(By.linkText("白色"));
                    if (!element1.getText().equals("")) {
                        element1.click();

                    }

                }
                WebElement element1 = null;
                try {
                    element1 = webDriver.findElement(By.cssSelector(".tb-img"));
                } catch (Exception e) {
                    System.out.println("没有图片");
                }

                if (element1 != null) {
                    if (!element1.getText().equals("")) {
                        element1.findElement(By.linkText("1725黑色加绒（平底）")).click();
                    }
                }
                webDriver.findElement(By.className("ensureText")).click();
                rodomsleep();
                ispay(webDriver);
            }
            rodomsleep();

        } catch (Exception e) {
            System.out.println("错误了");
            System.out.println(e);
            webDriver.quit();
        }
    }
}
