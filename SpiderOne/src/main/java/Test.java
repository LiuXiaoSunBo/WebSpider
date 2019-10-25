import com.baidu.aip.ocr.AipOcr;
import org.jboss.netty.channel.ExceptionEvent;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.server.SeleniumServer;

import java.awt.*;
import java.io.File;
import java.nio.channels.SelectableChannel;
import java.util.*;
import java.util.List;

public class Test {
    public static void main(String[] args) throws AWTException {
        System.setProperty("webdriver.chrome.driver", "D:\\谷歌下载\\chromedriver.exe");
        ChromeOptions option = new ChromeOptions();
        option.addArguments("disable-infobars");
        WebDriver webDriver = new ChromeDriver(option);
        webDriver.manage().window().maximize();
        webDriver.get("https://login.taobao.com/member/login.jhtml");
        webDriver.findElement(By.id("J_Quick2Static")).click();
        webDriver.findElement(By.className("alipay-login")).click();
        System.getProperties().setProperty("http.proxyHost","183.166.7.51");
       System.getProperties().setProperty("http.proxyPort","9999");
        while (true){
            if (webDriver.getCurrentUrl().contains("my_taobao")){
                break;
            }
        }
        new Threeds(webDriver).start();
    }

}
