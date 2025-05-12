# SlideBar

[![JitPack](https://img.shields.io/jitpack/v/github/jenly1314/SlideBar?logo=jitpack)](https://jitpack.io/#jenly1314/SlideBar)
[![Download](https://img.shields.io/badge/download-APK-brightgreen?logo=github)](https://raw.githubusercontent.com/jenly1314/SlideBar/master/app/app-release.apk)
[![API](https://img.shields.io/badge/API-9%2B-brightgreen?logo=android)](https://developer.android.com/guide/topics/manifest/uses-sdk-element#ApiLevels)
[![License](https://img.shields.io/github/license/jenly1314/SlideBar?logo=open-source-initiative)](https://opensource.org/licenses/apache-2-0)


SlideBar for Android 一个很好用的联系人快速索引。

## 效果展示
![Image](GIF.gif)

> 你也可以直接下载 [演示App](https://raw.githubusercontent.com/jenly1314/SlideBar/master/app/app-release.apk) 体验效果

## 引入

### Gradle:

1. 在Project的 **build.gradle** 或 **setting.gradle** 中添加远程仓库

    ```gradle
    repositories {
        //...
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
    ```

2. 在Module的 **build.gradle** 中添加依赖项

    ```gradle
    implementation 'com.github.jenly1314:SlideBar:1.1.0'
    ```
    
## 使用

#### 布局示例
```xml
    <com.king.view.slidebar.SlideBar
        android:id="@+id/slideBar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="10dp" />
```

更多使用详情，请查看[app](app)中的源码使用示例或直接查看[API帮助文档](https://jitpack.io/com/github/jenly1314/SlideBar/latest/javadoc/)

#### 实现说明
具体实现详情请戳[传送门](http://blog.csdn.net/jenly121/article/details/48466641)

## 相关推荐
- [compose-component](https://github.com/jenly1314/compose-component) 一个Jetpack Compose的组件库；主要提供了一些小组件，便于快速使用

---

![footer](https://jenly1314.github.io/page/footer.svg)


