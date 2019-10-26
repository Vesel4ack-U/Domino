package ru.dyv.model;

// класс Игра
public class Game {
    
    public int gameId;        // идентификатор игры, записывается в результаты
    
    int score1 = 0;    // общий счёт команды 1 (игроки 1, 3)
    int score2 = 0;    // общий счёт команды 2 (игроки 2, 4)
    boolean fence1 = false;   // признак "забора" у команды 1
    boolean fence2 = false;   // признак "забора" у команды 2
    int balls = 0;     // яйца с предыдущего кона
    
    public void playRound() {

        Stones.dispensing();     // раздача камней, перед началом кона
        Stones.show();           // вывод камней по игрокам
        // создаём экземпляр кона, с инициализацией полей
        Round round = new Round();

        // делаем ходы пока не конец раунда
        do {
            round.nextMove();
        } while ( !round.endOfRound() );
        // запись результатов кона
        round.writingResults();

        
    };
    
    public boolean endOfGame() {
        return true;
    };

    public void writingResults() {
        
    };

};
