package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    public static AndroidDriver getDriver() {
        return driver.get();
    }

    public static void initializeDriver() {
        try {
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setAutomationName("UiAutomator2")
                    .setDeviceName("emulator-5554") // adb devices'daki ismi buraya yazdık
                    .setAppPackage("com.akakce.akakce")
                    .setAppActivity("com.akakce.akakce.ui.splash.SplashActivity")
                    .setAutoGrantPermissions(true)
                    .setNoReset(false)
                    .setFullReset(false)
                    .setNewCommandTimeout(Duration.ofSeconds(60));

            // Appium 2.x için "/wd/hub" kaldırıldı. Eğer Appium 1.x kullanıyorsan sonuna ekleyebilirsin.
            // Appium 2.x varsayılan olarak ana dizinde çalışır
            URL url = new URL("http://127.0.0.1:4723");

            AndroidDriver androidDriver = new AndroidDriver(url, options);
            androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.set(androidDriver);
            System.out.println("Driver başarıyla başlatıldı ve Akakçe uygulaması açılıyor...");

        } catch (MalformedURLException e) {
            throw new RuntimeException("Appium Server URL'i hatalı!", e);
        } catch (Exception e) {
            throw new RuntimeException("Driver başlatılırken bir hata oluştu: " + e.getMessage(), e);
        }
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
            System.out.println("Driver kapatıldı.");
        }
    }
}