# Podział Koszyka

Ta biblioteka umożliwia podział produktów znajdujących się w koszyku klienta na grupy dostaw na podstawie wcześniej zdefiniowanych sposobów dostawy przechowywanych w pliku konfiguracyjnym w formacie JSON.

## Dane Wejściowe

Aby użyć biblioteki, należy przekazać listę produktów znajdujących się w koszyku klienta oraz przekazać plik konfiguracyjnego, który zawiera możliwe sposoby dostawy wszystkich oferowanych w sklepie produktów.

- Przykładowa lista produktów (basket.json):
  ```json
  [
    "Steak (300g)",
    "Carrots (1kg)",
    "Soda (24x330ml)",
    "AA Battery (4 Pcs.)",
    "Espresso Machine",
    "Garden Chair"
  ]
  ```
- Przykładowa zawartość pliku konfiguracyjnego (config.json):
  ```json
  {
    "Carrots (1kg)": ["Express Delivery", "Click&Collect"],
    "Cold Beer (330ml)": ["Express Delivery"],
    "Steak (300g)": ["Express Delivery", "Click&Collect"],
    "AA Battery (4 Pcs.)": ["Express Delivery", "Courier"],
    "Espresso Machine": ["Courier", "Click&Collect"],
    "Garden Chair": ["Courier"]
  }
  ```
  Kluczem w mapie jest nazwa produktu, a wartością - lista z możliwymi sposobami dostawy danego produktu.

## Użycie

- Przykład użycia w javie:

  ```java
  // Utwórz instancję biblioteki
  DeliverySplitter splitter = new DeliverySplitter("ścieżka/do/config.json");

  // Podziel produkty na grupy dostaw
  Map<String, List<String>> deliveryGroups = splitter.split(products);
  ```

- Przykład użycia pliku .jar:
  ```powershell
  java -jar PodzialKoszyka.jar config.json basket.json
  ```
