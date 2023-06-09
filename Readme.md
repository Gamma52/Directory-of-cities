# Проект по загрузке справочника городов из .csv файла в Postgres и дальнейшая работа с ним

## Модель "City" используется для выгрузки данных из.csv файла и работы с базой Postgres со следующими полями:

- Id;
- Name;
- Region;
- District;
- Population;
- Foundation;

Пример возвращаемого значения функции toString(): 
- City{name='Александровск-Сахалинский', region='Сахалинская область', district='Дальневосточный', population=10613, foundation='1869'}


## Класс City_DAO - берет на себя работу по взаимодействию с программой и базой данных Postgres с помощью JDBC

Класс реализует функции:

get_all_city() - возвращает список всех городов в формате List<City>;
save(City city) - сохраняет объект City в базу;
get_big_city() - возвращает из базы самой большой город;


## В классе Main реализованы следующие функции:

Функция parse_from_file(String path_file) - принимает адрес .csv файла с данными о городах, возвращает List<City>; 

Функция sortCityNameByLamda(List<City> cities) - сортирует список городов в лексикографическом порядке;

Функция sortCityDistrictAndName(List<City> cities) - сортирует список городов по округу и наименованию;

Функция quantityCities(List<City> cities) - выводит количество городов в каждом округе;

Пример полученного результата:
- Вологодская область - 15
- Татарстан - 22
- Хабаровский край – 7
- … 