package ru.dyv.domino.controller;

import java.util.ArrayList;

import ru.dyv.domino.model.ChainLink;
import ru.dyv.domino.model.Chip;

// класс "Кон"
public class Round {

    public int roundId;  // идентификатор кона, записывается в результаты
    
    int attacker;  // кто ходит
    int stacker;   // кто заходчик
    int edge1;     // текущий край L
    int edge2;     // текущий край R
    
    // цепочка ходов
    ArrayList<ChainLink> moveChains;

    // внутренний класс "Возможный ход"
    class PossibleMove {
        int priority;  // приоритет хода, 0 - самый малый
        int stone;     // индекс камня в массиве
        char side;     // сторона установки камня: 'R' - справа, 'L' - слева
        int digit1;    // поле 1
        int digit2;    // поле 2
        int is_double; // признак дубля
        int sum;       // сколько очков на камне

        // конструктор
        public PossibleMove(int p, int st, char sd, int d1, int d2, int dd, int s) {
            priority = p;
            stone = st;
            side = sd;
            digit1 = d1;
            digit2 = d2;
            is_double = dd;
            sum = s;
        };
    }
        
    // конструктор класса
    public Round() {
        // создаём новую цепочку ходов
        moveChains = new ArrayList<ChainLink>(); 
        
        // в начале кона ходит игрок с камнем 1/1
        attacker = Domino.stones[7].place; 
        stacker = attacker;    // он же заходчик
        edge1 = 1; // края
        edge2 = 1;
        Domino.stones[7].place = 5; // выкладываем камень на стол
        // записываем ход в цепочку
        moveChains.add( new ChainLink(attacker, 7, 'R', 50, 50, 50, 50, edge1, edge2) );
        // кто ходит следующий
        if (++attacker == 5) attacker = 1;
    };
    
    // очередной ход очередного игрока
    public void nextMove() {
        // коллекция возможных ходов
        ArrayList<PossibleMove> possibleMoves = new ArrayList<>();    
        
        // наполняем коллекцию возможных ходов из текущих камней игрока
        // если совпадает с текущими краями, записываем в возможные ходы
        // возможность установки одного камня и справа и слева записывается отдельными ходами
        // в первую цифру возможного хода записывается "жопка" камня
        Chip x;
        for (int i = 0; i < 28; i++) {
            x = Domino.stones[i];
            if (x.place == attacker) {
                if (x.digit1 == edge2)   // правый край
                    possibleMoves.add( new PossibleMove(0, i, 'R', x.digit1, x.digit2, x.is_double, x.sum) );
                else if (x.digit2 == edge2 && x.is_double == 0)  // не дублируем запись дублей
                    possibleMoves.add( new PossibleMove(0, i, 'R', x.digit2, x.digit1, x.is_double, x.sum) );
                
                if (x.digit1 == edge1)   // левый край
                    possibleMoves.add( new PossibleMove(0, i, 'L', x.digit1, x.digit2, x.is_double, x.sum) );
                else if (x.digit2 == edge1 && x.is_double == 0)  // не дублируем запись дублей
                    possibleMoves.add( new PossibleMove(0, i, 'L', x.digit2, x.digit1, x.is_double, x.sum) );
            }
        }
        
        // смотрим, сколько есть вариантов ходов
        switch ( possibleMoves.size() ) {
            case 0: // если ходов нет, фиксируем пропуск
                moveChains.add( new ChainLink(attacker, 50, 'P', 50, 50, edge1, edge2, edge1, edge2) );
                if (stacker == attacker) {   // если текущий игрок был заходчиком - ищем следующего
                    //// нужен код
                }
                break;
            
            case 1: // если ход только один - сразу ходим
                Domino.stones[ possibleMoves.get(0).stone ].place = 5; // выкладываем камень на стол
                // записываем ход в цепочку
                if (possibleMoves.get(0).side == 'R') {  // если ход справа
                    edge2 = possibleMoves.get(0).digit2; // новый край R (в digit1 записана "жопка")
                    moveChains.add( new ChainLink(attacker, possibleMoves.get(0).stone, 'R', edge2, edge1, 50, 50, edge1, edge2) );
                } else {  // если ход слева
                    edge1 = possibleMoves.get(0).digit2; // новый край L (в digit1 записана "жопка")
                    moveChains.add( new ChainLink(attacker, possibleMoves.get(0).stone, 'L', edge1, edge2, 50, 50, edge1, edge2) );
                }
                break;
            
            default: // если ходов несколько - выбираем лучший
                int bestMove; // лучший ход
                bestMove = choiceOfBestMove(possibleMoves);
                
                Domino.stones[ possibleMoves.get(bestMove).stone ].place = 5; // выкладываем камень на стол
                // записываем ход в цепочку
                if (possibleMoves.get(bestMove).side == 'R') {  // если ход справа
                    edge2 = possibleMoves.get(bestMove).digit2; // новый край R (в digit1 записана "жопка")
                    moveChains.add( new ChainLink(attacker, possibleMoves.get(bestMove).stone, 'R', edge2, edge1, 50, 50, edge1, edge2) );
                } else {  // если ход слева
                    edge1 = possibleMoves.get(bestMove).digit2; // новый край L (в digit1 записана "жопка")
                    moveChains.add( new ChainLink(attacker, possibleMoves.get(bestMove).stone, 'L', edge1, edge2, 50, 50, edge1, edge2) );
                }
                break;
        }
        
        // кто ходит следующий
        if (++attacker == 5) attacker = 1; 
    };

    // выбор лучшего хода из цепочки возможных
    private int choiceOfBestMove(ArrayList<PossibleMove> pm) {
        int bm = 0; // лучший ход
        
        // правило 1, приоритет 1: игра на сброс
        
        
        return bm;
    };
    
    // проверка на конец кона: закончились камни или рыба
    public boolean endOfRound() {
        int[] stonesHavePlayer = {0,0,0,0}; // количество камней у игроков
        int[] stonesHaveTable = {0,0,0,0,0,0,0}; // камней каждой масти на столе
        
        // подсчитываем количество оставшихся камней у игроков и на столе
        for(Chip x : Domino.stones) {
            if (x.place != 5) {
                stonesHavePlayer[x.place-1]++;
            } else {
                stonesHaveTable[x.digit1]++;
                stonesHaveTable[x.digit2]++;
            }
        }

        // если у кого-то не осталось камней - конец кона
        for (int i = 0; i < 4; i++) {
            if (stonesHavePlayer[i] == 0) {
                System.out.println("Выиграл игрок №: " + ++i);
                showMoveChain(moveChains); // выводим цепочку ходов
                return true;
            }
        }
        
        // если на столе на краях одна масть
        // и камней этой масти на столе уже 7 шт - рыба, конец кона
        if (edge1 == edge2 && stonesHaveTable[edge1] == 8) { // 6 цифр +2 на дубле
            // если последний ход был НЕ дубль
            if (Domino.stones[moveChains.get(moveChains.size() - 1).stone].is_double != 1) {
                System.out.println("Рыбу поставил игрок №: " + moveChains.get(moveChains.size() - 1).player);
            // иначе рыбу поставил предпоследний игрок, который не пропустил ход
            } else {
                for (int i = 2; i < 6; i++) {
                    if (moveChains.get(moveChains.size() - i).side == 'P')
                        continue;
                    System.out.println("Рыбу c отходом поставил игрок №: " + moveChains.get(moveChains.size() - i).player);
                    break; // выходим из цикла
                }
            }
            showMoveChain(moveChains); // выводим цепочку ходов
            return true;
        }
        
        // если ходы ещё есть и не рыба - играем дальше
        return false;
        
    };

    public void writingResults() {
        
    };
    
    // вывод цепочки ходов
    public void showMoveChain (ArrayList<ChainLink> in_chain) {
        int i = 1;
        for (ChainLink x : in_chain) {
            if (x.stone != 50) {
                System.out.println(i++ + " ход: " +
                                   x.player + " " + 
                                   Domino.stones[x.stone].digit1 + "/" + 
                                   Domino.stones[x.stone].digit2 + " " +
                                   x.side + 
                                   " края: " + x.edge1 + "/" + x.edge2);
            } else {
                System.out.println(i++ + " ход: " +
                                   x.player +
                                   " ПАС " + 
                                   x.pass1 + "/" + x.pass2);
            }
        }
    };
    
};
