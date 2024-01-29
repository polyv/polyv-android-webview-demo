# Q & A

## 更换web-native通信规制

`注意：该更换方式仅针对以源码方式集成该SDK，如不是使用源码集成SDK，请忽略。`

### 1. 说明

当前SDK内部是通过Jsbridge该库来实现web端与原生端的通信，Jsbridge中定义了web端与原生的通信规则，如果集成项目是没有制定指定的web-native通信规则，那么可以直接使用SDK的通信规则即可，无需其余改动。



如果集成项目中有定制指定的web-native通信规则，可以参考下面的方式进行修改。

无论是使用哪一种通信规则，关键在于原生端发送/接收web端消息，web端发送/接收原生端消息，所以当需要更换SDK内部的web-native通信规则仅关注上述的两点即可。



### 2. 更换native端通信规则

因为sdk内部是依赖于jsbridge该模块实现web-native通信，当需要更换通信规则时，可以选择不依赖该模块。


#### 2.1 更改PLVBaseWebView继承类

```java
public class PLVBaseWebView extends BridgeWebView {
    ....
}
```

可将PLVBaseWebView继承的BridgeWebView更换为集成项目中指定通信规则的webview。



#### 2.2 更改WebViewClient

WebViewClient是实现原生端接收web端消息的关键，也是定制修改webview的核心部份，所以在更换通信规则时也需要更换WebViewClient

```java
//替换自己需要用到的WebViewClient
webView.setWebViewClient(webviewClient);
```

可以通过setWebViewClient(new WebViewClient() 方法为webview设置指定WebViewclient。



#### 2.3 更改原生端发送/接收web端消息的方法

1. 更换发送消息方式

   ```java
   public void callMessage(String type, String message) {
       //当使用的新的web-native规则时，可以将下面的代码修改为webview使用新规则发送消息时的代码
       webview.callHandler(type, message, new CallBackFunction() {
           @Override
           public void onCallBack(String s) {
             ...
           }
       });
   }
   ```

   当前SDK内部时通过webview.callHandler()方法来实现消息的发送，当更换新的通信规则时，可以将这个webview.callHandler()方法替换为新规则中对应原生端发送消息的方法。



2. 更换接收web端消息

   ```java
   webview.registerHandler("xxx", new BridgeHandler() {
       @Override
       public void handler(String s, CallBackFunction callBackFunction) {
           ...    
       }
   });
   ```

   当前SDK内部是通过 webview.registerHandler()方法来监听web端发送的消息，当更换新的通信规则时，可以将这个webview.registerHandler()方法替换为新规则中对应原生端接收web消息的方法。



### 3. 更换web端通信规则

web-native通信规则是由web端和原生端两端定制的，所以当更换web-native通信规则不仅需要原生端更换，web端也需要进行更换。



#### 3.1 注意

当前需要通信的 web页面 与 更换通信规则后的原生端 所使用的通信规则是否是对应，如果是对应的情况下无需做其他更替。

如果需要通信的web页面与 更换通信规则后的原生端 所使用的不对应，那么就需要去更替web端的通信规则。

`如web端页面需要使用保利威的web页面（当前保利威web页面与sdk内部所使用的通信规则一致，当原生端切换通信规则，那么web端页面也需要更换对应通信规则）`



#### 3.2 更换web端发送/接收方法

1. 更改web端发送消息方式

   ```js
    window.bridge.callHandler(
                   'callAppEvent', message,
                   function(responseData) {
                      //发送消息
                       ....
                   }
               );
   ```

   当前SDK内部与Web端对应的通信规则是通过bridge.callHandler()方法来进行发送消息，当替换新的通信规则后，可以使用新的发送方式来取代这个bridge.callHandler()方法



2. 更改web端接收消息方式

   ```js
   // 监听来自xxx事件的消息
   bridge.registerHandler("xxx", function(data, responseCallback) {
                   document.getElementById("show").innerHTML = (data);
   });
   ```

   当前SDK内部与Web端对应的通信规则是通过bridge.registerHandler()方法来接收消息，当替换新的通信规则后，可以使用新的发送方式来取代这个bridge.registerHandler()方法


更多问题详见保利威帮助中心 [WebView常见问题](https://help.polyv.net/index.html#/vod/product/faq/webview_issue)  
