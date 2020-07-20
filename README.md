# selenide-web-ios-android-tests

e2e-tests for [react-native-web-ios-android-app](https://github.com/autotests-cloud/react-native-web-ios-android-app) \
**stack:** Java / Gradle / JUnit5 / Selenide / Selenide-appium / Rest-assured / Allure / Allure EE / Selenoid

`gradle web`\
`gradle web -Dweb_mobile_device="iPhone X"`\
`gradle ios`\
`gradle android`

**gradle clean web -Dremote_driver_url=https://user1:1234@selenoid.autotests.cloud:4444/wd/hub/ -Dvideo_storage_url=https://selenoid.autotests.cloud/video/**