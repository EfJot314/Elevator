# Elevator System

Program do obsługi systemu wielu działających równolegle wind.

## Opis działania

Zasada działania opiera się na algorytmie, który można podzielić na dwa poziomy.

### Poziom pierwszy - interfejs systemu wind

System wind ma za zadanie odbierać żądania z przycisków na poszczególnych piętrach i rozdzielać je pomiędzy dostępne windy. Przydział żądań składa się z trzech iteracji. Pierwsza z nich opiera się na następujących zasadach:
1. Jeżeli winda jest w stanie ```IDLE```, czyli stoi bezczynnie, to zostaje jej przydzielone dane żądanie
2. Jeżeli winda porusza się zgodnie z kierunkiem żądania (na przykład, gdy kierunek windy to ```UP``` oraz kierunek podany w żądaniu to ```UP```) oraz piętro żądania znajduje się "po drodze" windy (czyli w tym przykładzie jeżeli piętro windy jest mniejsze od piętra żądania), to zostaje jej przydzielone żądanie 

Jeżeli po pierwszej iteracji żądanie nie zostanie przydzielone do żadnej z wind, to przechodzi ono do drugiej iteracji, w której każdej windzie poruszającej się w kierunku zgodnym z żądaniem, jest ono przydzielane. Jeżeli równeż po drugiej iteracji żądanie nie zostało wysłane do żadnej windy, to w trzeciej iteracji znajdywana jest winda, której piętro docelowe znajduje się najbliżej piętra żądania, a następnie jest jej ono przypisywane.

### Poziom drugi - interfejs pojedynczej windy

Pojedyncza winda ma dwa zadania:
1. Wykonywać żądania systemu
2. Przyjmować i wykonywać żądania wewnętrzne (podane przez panel przycisków wewnątrz kabiny)

Oba typy żądań są dodawane do listy, z której wybierany jest element minimalny za pomocą zaimplementowanego komparatora. Ten element minimalny jest ustawiany jako pierwsze żądanie do wykonania. Winda jedzie na wskazane piętro, otwiera drzwi i jeżeli lista żądań jest pusta oraz nie otrzyma kolejnego żądania, to przechodzi w tryb ```IDLE```. Informacja o spełnieniu żądania jest wysyłana do systemu, który wysyła wszystkim windom informacje, żeby usunęły je ze swoich list, dzięki czemu inne windy będą mogły wykonywać inne zadania. 

Wspomniany komparator (```RequestComparator```) jest klasą prywatną, która porównuje dwa żądania na podstawie wartości ich parametrów oraz aktualnego stanu windy. Porównanie opiera się na następujących zasadach:
1. Jeżeli winda jest w stanie ```IDLE```, wtedy lepszym żądaniem jest to, którego piętro znajduje się bliżej
2. Jeżeli winda porusza się w górę, to:
   1. Jeżeli jedno żądanie ma kierunek w górę, a drugie w dół, to lepsze jest to, które ma kierunek w górę
   2. Jeżeli oba mają kierunek w górę, wtedy lepsze jest to żądanie, którego piętro zostanie osiągnięte wcześniej przez windę (znajdujące się bliżej w górę)
3. Jeżeli winda porusza się w dół, to:
   1. Jeżeli jedno żądanie ma kierunek w górę, a drugie w dół, to lepsze jest to, które ma kierunek w dół
   2. Jeżeli oba mają kierunek w dół, wtedy lepsze jest to żądanie, którego piętro zostanie osiągnięte wcześniej przez windę (znajdujące się bliżej w dół)

## Struktura projektu

Projekt składa się z dwóch części:
1. Implementacji systemu wind (framework)
2. Aplikacji graficznej pozwalającej na prostą interakcję ze stworzonym systemem (wizualizacja)

### Framework

Pierwszą, a zarazem główną częścią projektu jest framework będący implementacją systemu umożliwiającego zarządzanie i obsługę windą.

#### Struktury danych

Stworzone rozwiązanie wykorzystuje następujące klasy będące strukturami danych:

##### Direction
Klasa ta jest enumeratorem, który może przyjmować trzy wartości:
1. ```UP``` - opisuje ruch windy w górę
2. ```DOWN``` - opisuje ruch windy w dół
3. ```IDLE``` - opisuje stan windy, w którym się nie porusza
   
##### Elevator State
Klasa stanu windy. Posiada ona cztery pola:
1. ```id``` - identyfikator windy (```int```)
2. ```currentFloor``` - aktualne piętro, na którym znajduje się kabina windy (```int```)
3. ```destinationFloor``` - piętro, do którego zmierza kabina windy (```int```)
4. ```direction``` - kierunek, w którym porusza się kabina windy (```Direction```)

##### Request
Klasa przedstawiająca rządanie do windy, składa się z następujących pól:
1. ```floor``` - piętro, na które winda ma się udać (```int```)
2. ```direction``` - wstępnie zadeklarowany kierunek, w którym winda ma się udać z żądanego piętra (```Direction```)

#### Komparatory

Ze względu na potrzebę porównywania żądań, stworzono klasę, która to robi.

##### RequestComparator
Klasa jest prywatna i znajduje się w pliku ```Elevator.java```. Ma ona na celu porównywanie dwóch różnych żądań typu ```Request``` na podstawie stanu windy oraz pięter i kierunków żądań.

#### Interfejsy
W celu umożliwienia ewentualnego dalszego rozwoju stworzonego rozwiązania, część komponentów została opisana interfejsami, w celu łatwiejszej integracji.

##### IElevator
Interfejs opisujący pojedynczą instancję windy.
```
public interface IElevator {
    int getId();
    void update();
    void addRequest(Request request);
    void goTo(int floor);
    void removeRequest(Request request);
    List<Request> getRequests();
    void setCurrentFloor(int currentFloor);
    void setDestinationFloor(int targetFloor);
    void setDirection(Direction direction);
    int getCurrentFloor();
    int getDestinationFloor();
    Direction getDirection();
    boolean isDoorOpen();
    boolean isEnabled();
    void setEnability(boolean enability);
    ElevatorState getElevatorState();
}
```

##### IElevatorSystem
Interfejs opisujący system zarządzający wieloma pracującymi równocześnie windami.
```
public interface IElevatorSystem {
    void addElevator(int id);
    int getNumberOfElevators();
    IElevator getElevator(int id);
    void removeElevator(int id);
    void disableElevator(int id);
    void enableElevator(int id);
    void pickup(int elevatorFloor, Direction direction);
    void update(int id, int currentFloor, int destinationFloor);
    void step();
    List<ElevatorState> status();
}
```

#### Klasa windy (```Elevator```)

Klasa ```Elevator.java``` implementuje interfejs ```IElevator```. Klasa jest odpowiedzialna za obsługę żądań zewnętrzych (```Request```) wysłanych przez system (```ElevatorSystem```) oraz za obsługę żądań wewnętrznych wysłanych z panelu przycisków w środku kabiny (metoda ```goTo(int floor)```). Winda samodzielnie wybiera żądanie, które zrealizuje jako pierwsze (korzystając z komparatora ```RequestComparator```). 

#### Klasa systemu wind (```ElevatorSystem```)
Klasa ```ElevatorSystem.java``` implementuje interfejs ```IElevatorSystem```. Klasa jest odpowiedzialna głównie za przyjmowanie żądań z paneli przycisków znajdujących się na poszczególnych piętrach, a następnie za rozdysponowywanie tych żądań (```Request```) do poszczególnych wind (```Elevator```). 


