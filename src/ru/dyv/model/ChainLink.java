package ru.dyv.model;

// класс "Звено цепочки ходов"
public class ChainLink {

    int player;       // номер игрока
    int stone = 50;   // выставленный камень, 50 = пусто
    char side = 'R';  // сторона установки: 'R' - справа, 'L' - слева, 'P' - пропуск хода
    int attack = 50;  // какую масть выставил, 50 = пусто
    int keep = 50;    // какую масть обошёл, 50 = пусто
    int pass1 = 50;   // в случае P, какую масть 1 пропустил, 50 = пусто
    int pass2 = 50;   // в случае P, какую масть 2 пропустил, 50 = пусто
    int edge1 = 50;   // край L после этого хода
    int edge2 = 50;   // край R после этого хода
        
    // конструктор объекта
    public ChainLink (int in_player, int in_stone, char in_side, int in_attack, int in_keep, int in_pass1, int in_pass2, int in_edge1, int in_edge2) {
        player = in_player;
        stone = in_stone;
        side = in_side;
        attack = in_attack;
        keep = in_keep;
        pass1 = in_pass1;
        pass2 = in_pass2;
        edge1 = in_edge1;
        edge2 = in_edge2;
    };
    
};
