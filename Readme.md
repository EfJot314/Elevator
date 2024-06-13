# Elevator System

Program do obsługi systemu wielu działających równolegle wind.

## Opis działania



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


