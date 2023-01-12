# SC_Lib
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fdanilerofei%2FSC_Lib.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fdanilerofei%2FSC_Lib?ref=badge_shield)


## Применение стилей к тексту

### Пример применения стиля
#### Java
```java
TextView textView = findViewById(R.id.textView);
Spanned spanText = StyleableText.builder(textView.getText()).bold().build();
textView.setText(spanText);
```

#### Kotlin
```kotlin
val textView = findViewById<TextView>(R.id.textView)
textView.applyStyle {
    bold()
}
```
Список методов [StyleableText](/app/src/main/java/com/samplecode/lib/styles/StyleableText.kt):
- bold (жирный текст)
- lineThrough (зачеркнутый текст)
- italic (наклонный текст)
- underline (подчеркнутый текст)
- addStyle (добавляет [кастомный стиль](https://developer.android.com/guide/topics/resources/string-resource#StylingWithHTML))



## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fdanilerofei%2FSC_Lib.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fdanilerofei%2FSC_Lib?ref=badge_large)