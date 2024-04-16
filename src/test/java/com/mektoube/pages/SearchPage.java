package com.mektoube.pages;

public class SearchPage {
//
//    public void iScrollUpToText(String text) {
//        int count = 0;
//        System.out.println("dimision :" + driver.manage().window().getSize());
//        int y = driver.manage().window().getSize().getHeight() / 2;
//        int x = driver.manage().window().getSize().getWidth() / 2;
//
//        while (count == 0) {
//            ArrayList<MobileElement> list = new ArrayList<>(driver.findElements(By.xpath("//*[contains(@text,'" + text + "')]")));
//            if (list.size() == 1) {
//                count = 1;
//            } else {
//                new TouchAction<>(driver).press(PointOption.point(x, y))
//                        .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
//                        .moveTo(PointOption.point(x, y / 4))
//                        .release()
//                        .perform();
//                waitAboutSeconds(1);
//            }
//        }
//    }
//    public void clickButton(String btn) {
//        basePage.waitDisplayButtonXpathAndClick(btn);
//
//    }
//    public void iEnterOnField(String name) {
//        String x = TestDataService.properties.getProperty(name);
//        if (x == null) {
//            x = name;
//        }
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText"))).sendKeys(x);
//    }
//    public void iClickTickButton(String btnTick) {
//        String btn = TestDataService.properties.getProperty(btnTick);
//        waitDisplayButtonXpathAndClick(btn);
//        basePage.waitAboutSeconds(1);
//    }
//    public void clickProfileHaveNameAfterSearchPsedou(String username) {
//        // Tên user trong list discovery luôn viết hoa chữ cái đầu >> cần format lại để click đúng trong mọi case
//        String name = username.substring(0, 1).toUpperCase(Locale.ROOT) + username.substring(1, username.length()).toLowerCase(Locale.ROOT);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='" + name + "']/preceding-sibling::android.view.ViewGroup"))).click();
//    }
}
