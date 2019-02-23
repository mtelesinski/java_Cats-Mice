# java_Cats-and-Mice
A cats and mice simulator

Symulator kotów i myszy

Mamy do dyspozycji kwadratową planszę o nieparzystym rozmiarze BOARD_SIZE (2 < BOARD_SIZE < 100 - wartość powinna być
konfigurowalna). Na środku planszy znajduje się ser. Równomiernie, przy krawędziach planszy rozmieszczone jest MAX_MICE
(1 < MAX_MICE < 100) myszy, oraz MAX_CATS (1 < MAX_CATS < 100) kotów - losowo w wolnych miejscach. Symulacja podzielona
jest na tury. W każdej turze każda mysz i każdy kot przemieszczają się na losowe, sąsiednie pole, przy czym myszy maja
do wyboru 8 pól (góra, dół, na boki i na ukos), a koty tylko 4 pola (góra, dół i na boki). Koty i myszy nie mogą
wychodzić poza planszę, ale mogą chodzić po polu sera, a na jednym polu może być kilka postaci na raz. Jeśli po danej
turze na danym polu znajduje się na raz kot(y) i mysz(y) to mysz(y) zostają złapane i znikają z planszy.

Grę wygrywają myszy, jeśli którakolwiek z nich dotrze do sera.
Grę wygrywają koty, jeśli wszystkie myszy zostaną złapane nim dotrą do sera.
Wyjątek: w przypadku, gdy w danej turze na polu sera pojawi się na raz kot i mysz (i jest to ostatnia z myszy
    na planszy) - ogłasza się remis.

Liczby BOARD_SIZE, MAX_MICE oraz MAX_CATS powinny być zdefiniowane jako stałe.
Myszy i koty powinny być ponumerowane.
Program powinien tekstowo wypisywać na konsolę informacje o każdym ruchu w formacie:

Mysz A: (x1,y1) -> (x2,y2)
Kot B: (x1',y1') -> (x2',y2')

gdzie A i B to odpowiednio numer danej myszy i kota, (x1,y1) to pozycja na której dana postać znajdowała się na początku
danej tury, a (x2,y2) to pozycja, na której znajduje się na koniec tury.

Dodatkowo na konsoli powinny pojawiać sie komunikaty specjalne, takie jak:

"Tura X - pozostało Y myszy" - na początku każdej nowej tury
"Kot B złapał Mysz A"
"Mysz A dotarła do sera"
"Koty wygrały"
"Myszy wygrały"
"Remis"

Dodatkowo po wygenerowaniu planszy i pozycji startowych wszystkich postaci, plansza powinna zostać wyrysowana na konsoli
(tekstowo). Rysowanie planszy powinno być następnie powtarzane co każde DRAW_BOARD_PERIOD tur
(DRAW_BOARD_PERIOD także powinno być konfigurowalną stałą).

Przykładowa plaszna przed rozpoczęciem symulacji, dla BOARD_SIZE = 9, MAX_MICE = 4, MAX_CATS = 4:

M.......M
.....K...
.........
.K.......
..K.S....
.........
.........
.....K...
M.......M

Uwagi implementacyjne:
- kod (nazwy klas, metody, pól, itp) piszemy w języku angielskim, komentarze mogą być po polsku
- należy uzyć mechanizmu dziedziczenia przy klasach kotów i myszy oraz wynikającego z niego polimorfizmu
- należy użyć mechanizmu interfejsów (dobrym pomysłem byłby np intefejs Printable pozwalający na wyrysowanie na ekran
   zarówno kotów, myszy, jak po prostu pola planszy)
- staramy się projektować aplikację możlwie obiektowo
- do przemyślenia, jakie jeszcze klasy będą potrzebne poza oczywistym Kotem i Myszą (Gra, Plansza, PolePlanszy, ... ?)
- minimalna ilość kodu w klasie Main - nie więcej niż kilka linii

Wersja rozszerzona (dla chętnych):
- wizualizacja przebiegu symulacji przy użyciu Java 2D API
