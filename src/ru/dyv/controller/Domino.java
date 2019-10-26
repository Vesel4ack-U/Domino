package ru.dyv.controller;

import ru.dyv.model.Chip;
import ru.dyv.model.Stones;

// главный класс проекта
public class Domino {
    
    // массив камней
    public static Chip[] stones = new Chip[28];
    
    public static void main(String[] args) {
        // инициализация камней
        Stones.init();
        
        // запускаем процесс игры
        Domino domino = new Domino();
        domino.processing();
    };
    
    public void processing() {
        // создаём экземпляр игры
        Game game = new Game();

        // играем несколько конов, пока не конец игры
        do {
            game.playRound();
        } while ( !game.endOfGame() );
        // запись результатов игры
        game.writingResults();   
    };
    
};
