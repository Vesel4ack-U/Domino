package ru.dyv.domino.model;

import ru.dyv.domino.controller.Domino;

// класс "Камни"
// содержит абстрактные методы
public class Stones {

    // вывод камней по игрокам
    public static void show () {
        int k;

        for (int i = 0; i < 4; i++) {
            System.out.print(i+1 + ": ");
            k = 0; // количество дублей
            for(Chip x : Domino.stones)
                if (x.place == (i+1)) { 
                    System.out.print(x.digit1 + "/" + x.digit2 + "  ");
                    if (x.is_double == 1)
                        k++;
                }
            System.out.println("дублей: " + k);
        }
    };
    
    // раздача камней, перед началом кона
    public static void dispensing () {
        int random;
        
        do {
            // возвращаем все камни в состояние "не роздано"
            for(Chip s : Domino.stones)
                s.place = 0;

            // раздаём
            for (int x = 1; x < 8; x++) {  // берёт по 7 камней
                for (int y = 1; y < 5; y++) {  // каждый из 4-ёх игоков
                    // поиск невыданного камня
                    do {
                        random = (int)(Math.random() * 28);
                    } while ( Domino.stones[random].place != 0 );
                    // найденный невыданный камень записываем текущему игроку
                    Domino.stones[random].place = y;

                    //z = Domino.stones[random];
                    //System.out.println(z.digit1 + "/" + z.digit2 + " " + " " + z.place);
                }
            }
        } while ( badDispensing() ); // повторяем, пока раздача не пройдёт проверки на корректность
    };

    
    // проверка раздачи на корректность
    public static boolean badDispensing () {

        // проверка #1 - наличие 5 дублей
        int[] d = new int[4];  // массив результатов по 4-ём игрокам

        for(int i = 0; i < 4; i++) {  // обнуляем массив результатов
            d[i] = 0;
        }
        for(Chip s : Domino.stones)   // заполняем массив результатов
            d[s.place-1] = d[s.place-1] + s.is_double; // 1 = дубль

        for(int i : d) {              // проверяем результаты
            //System.out.println(i);
            if (i >= 5) {
                //System.out.println("---");
                return true;  // проверка не пройдена
            }
        }

        // проверка #2 - наличие 6 камней одной масти
        int[][] dd = new int[4][7];  // массив результатов по 4-ём игрокам
        
        for(int i = 0; i < 4; i++) {  // обнуляем массив результатов
            for(int j = 0; j < 7; j++) {
                dd[i][j] = 0;
            }
        }
        for(Chip s : Domino.stones) { // заполняем массив результатов
            dd[s.place-1][s.digit1] = dd[s.place-1][s.digit1] + 1; // первая цифра в камне
            if (s.is_double != 1) {   // если дубль - вторую цифру пропускаем
                dd[s.place-1][s.digit2] = dd[s.place-1][s.digit2] + 1;
            }
        }
        for(int i = 0; i < 4; i++) {  // проверяем результаты
            for(int j = 0; j < 7; j++) {
                if ( dd[i][j] >= 6 ) {
                    //System.out.println(i+1 + " " + j);
                    return true;  // проверка не пройдена
                }
            }
        }
        
        return false; // если все проверки пройдены успешно
    };
   
    // инициализация камней
    public static void init () {
        Domino.stones[0]  = new Chip(0,0);
        Domino.stones[1]  = new Chip(0,1);
        Domino.stones[2]  = new Chip(0,2);
        Domino.stones[3]  = new Chip(0,3);
        Domino.stones[4]  = new Chip(0,4);
        Domino.stones[5]  = new Chip(0,5);
        Domino.stones[6]  = new Chip(0,6);
        Domino.stones[7]  = new Chip(1,1);
        Domino.stones[8]  = new Chip(1,2);
        Domino.stones[9]  = new Chip(1,3);
        Domino.stones[10] = new Chip(1,4);
        Domino.stones[11] = new Chip(1,5);
        Domino.stones[12] = new Chip(1,6);
        Domino.stones[13] = new Chip(2,2);
        Domino.stones[14] = new Chip(2,3);
        Domino.stones[15] = new Chip(2,4);
        Domino.stones[16] = new Chip(2,5);
        Domino.stones[17] = new Chip(2,6);
        Domino.stones[18] = new Chip(3,3);
        Domino.stones[19] = new Chip(3,4);
        Domino.stones[20] = new Chip(3,5);
        Domino.stones[21] = new Chip(3,6);
        Domino.stones[22] = new Chip(4,4);
        Domino.stones[23] = new Chip(4,5);
        Domino.stones[24] = new Chip(4,6);
        Domino.stones[25] = new Chip(5,5);
        Domino.stones[26] = new Chip(5,6);
        Domino.stones[27] = new Chip(6,6);
    };
   
};
