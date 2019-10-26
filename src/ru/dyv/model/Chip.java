package ru.dyv.model;

// класс "Фишка"
// содержит описание структуры
public class Chip {
    
	public int digit1;    // поле 1
	public int digit2;    // поле 2
	public int is_double; // признак дубля
	public int place;     // положение фишки: 0 - не роздано, 1,2,3,4 - у игрока, 5 - на столе
	public int sum;       // сколько очков на камне
        
    // конструктор объекта
    public Chip (int d1, int d2) {
        digit1 = d1;
        digit2 = d2;
        if (d1 != d2) {
            is_double = 0;
        } else {
            is_double = 1;
        }    
        sum = d1 + d2;
        place = 0;       // статус при создании
    };
    
};
