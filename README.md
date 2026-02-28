# UC14: Temperature Measurement with Selective Arithmetic Support

## Description

UC14 extends the Quantity Measurement Application to support **temperature measurements** alongside existing categories such as **length, weight, and volume**.

Unlike length, weight, and volume (which support arithmetic operations like addition, subtraction, and division), **temperature is fundamentally different**:

- Temperature values support:
  - Equality comparison  
  - Unit conversion (Celsius ↔ Fahrenheit ↔ Kelvin)

- Temperature values do **NOT** support:
  - Addition  
  - Subtraction  
  - Division  

Operations such as `100°C + 50°C` or `100°C ÷ 50°C` are physically meaningless in the context of absolute temperature values. UC14 enforces these constraints while keeping the existing architecture backward compatible.

---

## Objectives

- Add a new measurement category: **Temperature**
- Support temperature units:
  - Celsius (CELSIUS) – base unit  
  - Fahrenheit (FAHRENHEIT)  
  - Kelvin (KELVIN)  
- Enable:
  - Equality comparison between temperature quantities  
  - Conversion between temperature units  
- Restrict:
  - Arithmetic operations (add, subtract, divide) for temperature quantities  
- Ensure:
  - No breaking changes to UC1–UC13  
  - Cross-category operations remain disallowed  
  - Existing length, weight, and volume behavior remains unchanged  

---

## Preconditions

- UC1–UC13 are fully implemented and tested.
- `Quantity<U extends IMeasurable>` supports:
  - Equality comparison  
  - Conversion  
  - Arithmetic operations for non-temperature categories  
- `IMeasurable` interface remains unchanged.
- LengthUnit, WeightUnit, and VolumeUnit continue to work without modification.

---


A new `TemperatureUnit` enum is introduced with the following constants:

- `CELSIUS` (base unit)  
- `FAHRENHEIT`  
- `KELVIN`  

Temperature conversions are implemented using **formulas** (non-linear conversion):

- Celsius → Fahrenheit
- Fahrenheit → Celsius
- Kelvin → Celsius


---

### 2. Equality Comparison

Temperature quantities can be compared across units by converting both values to the base unit (Celsius) and comparing with epsilon tolerance.

Examples:

- `0°C == 32°F`  
- `100°C == 212°F`  
- `0°C == 273.15K`  
- `-40°C == -40°F`  

---

### 3. Unit Conversion

Temperature quantities can be converted between all supported units.

Examples:

- `100°C → 212°F`  
- `32°F → 0°C`  
- `0°C → 273.15K`  
- `-40°C → -40°F`  

---

### 4. Unsupported Arithmetic Operations

Arithmetic operations on temperature quantities are explicitly **disallowed**:

- `add()` → throws `UnsupportedOperationException`  
- `subtract()` → throws `UnsupportedOperationException`  
- `divide()` → throws `UnsupportedOperationException`  

This prevents logically invalid operations on absolute temperature values.

---

### 5. Cross-Category Safety

Temperature quantities cannot be compared or combined with other categories:

- Temperature vs Length → not equal  
- Temperature vs Weight → not equal  
- Temperature vs Volume → not equal  

Generic type safety and runtime checks ensure category isolation.

---

## Example Usage

### Equality

```java
new Quantity<>(0.0, TemperatureUnit.CELSIUS)
  .equals(new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT)); // true

new Quantity<>(-40.0, TemperatureUnit.CELSIUS)
  .equals(new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT)); // true
```


  
