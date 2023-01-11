# SC_Lib

##Применение стилей к тексту

###Пример применения стиля
####Java
```java
TextView textView = findViewById(R.id.textView);
Spanned spanText = StyleableText.builder(textView.getText()).bold().build();
textView.setText(spanText);
```

####Kotlin
```kotlin
val textView = findViewById<TextView>(R.id.textView)
textView.applyStyle {
    textView.applyStyle {
        bold()
    }
}
```
Список методов [StyleableText](/app/src/main/java/com/samplecode/lib/styles/StyleableText.kt):
- bold (жирный текст)
- lineThrough (зачеркнутый текст)
- italic (наклонный текст)
- underline (подчеркнутый текст)
- addStyle (добавляет [кастомный стиль](https://developer.android.com/guide/topics/resources/string-resource#StylingWithHTML))

