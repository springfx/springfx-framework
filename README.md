# springfx-framework

## Internationalization (i18n) and Localization (l10n)
Each bound application context holds an instance of the `LocalePropertyHolder` class.
```groovy
// Get the holder instance from the application context
LocalePropertyHolder holder = applicationContext.getBean(LocalePropertyHolder)

// Get the locale property from the holder
ObjectProperty<Locale> localeProperty = holder.localeProperty()

// Bind the locale property to a ComboBox
ComboBox localeSelect = new ComboBox()
localeSelect.items.addAll([Locale.ENGLISH, Locale.GERMAN])
localeProperty.bind(localeSelect.selectionModel.selectedItemProperty())
```


```groovy
def messageSource = ...
def localeProperty = ...

// Create a new observable message
ReadOnlyProperty<String> titleProperty = new MessageSourceProperty('foo.bar', messageSource, localeProperty)
```