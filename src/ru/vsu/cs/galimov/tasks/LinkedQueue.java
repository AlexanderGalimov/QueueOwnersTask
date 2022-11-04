package ru.vsu.cs.galimov.tasks;

public class LinkedQueue {
    public OwnerInQueue first;
    public OwnerInQueue last;
    public int length = 1;

    public LinkedQueue(OwnerInQueue first) {
        this.first = first;
        last = first;
    }

    public LinkedQueue() {
    }

    public void removeOwner() {
        if(length == 1){
            last = null;
        }
        first = first.nextOwner;
        length--;
    }

    public void addOwner(int S, int T, int N) {
        if(length == 0){
            first = last = new OwnerInQueue(S,T,N);
        }
        else {
            last.nextOwner = new OwnerInQueue(S,T,N);
            last = last.nextOwner;
        }
        length++;
    }


    public OwnerInQueue getOwner(){
        if(checkBounds()){
            throw new IllegalArgumentException();
        }
        return first;
    }

    public boolean checkBounds() {
        return length == 0;
    }

    public int getSize() {
        return length;
    }

    public int[] countTimeOfDeal() {
        int[] timeOfLeave = new int[getSize()];
        int prevDelta = 0;
        int currDelta = 0;
        int t;// время ухода текущего покупателя
        int currS;// S + T
        int prevCurrS = 0;
        int count = 0;// счетчик для перемещения индексов массива
        int prevN = 0;

        while(length != 0){
            currS = first.S + first.T;

            if(count + 1 == 1){
                t = currS + first.N;
            }
            else{
                if(currS >= timeOfLeave[count - 1]){
                    t = currS + first.N;
                    prevDelta = 0;
                }
                else {
                    currDelta = prevN - (currS - prevCurrS);

                    if(currS >= prevCurrS){
                        prevDelta = 0;
                        t = timeOfLeave[count - 1] + first.N;
                    }
                    else{
                        // нужно чтобы понять сколько уже пробивает товары предыдущий покупатель
                        t = currS + currDelta + first.N + prevDelta;
                        prevDelta = currDelta;
                    }
                }
            }

            timeOfLeave[count] = t;

            prevCurrS = currS;
            prevN = first.N;

            removeOwner();

            count++;
        }
        return timeOfLeave;
    }

    public void sort(){

        OwnerInQueue newFirst = null;
        OwnerInQueue node;
        OwnerInQueue current;

        // 10 3 8 10

        while (first != null){
            node = first;
            first = first.nextOwner;
            // идем по нодам слева направо, перекидвая first
            if(newFirst == null || node.T + node.S < newFirst.S + newFirst.T){
                node.nextOwner = newFirst;
                newFirst = node;
            }
            else {
                current = newFirst;
                // идем пока не найдем меньше статичной ноды, чтобы поставить число перед ней
                while (current.nextOwner != null && !(node.T + node.S < current.nextOwner.S + current.nextOwner.T)){
                    current = current.nextOwner;
                }
                // ставим меньшее значение перед нодой
                node.nextOwner = current.nextOwner;
                current.nextOwner = node;
            }
        }
        // теперь головой является newFirst
        first = newFirst;
    }

    public static LinkedQueue createList(int[][] source){
        LinkedQueue list = new LinkedQueue(new OwnerInQueue(source[0][0],source[1][0],source[2][0]));
        int currS;
        int currT;
        int currN;

        for (int i = 1; i < source[0].length; i++) {
            currS = 0;
            currT = 0;
            currN = 0;
            for (int j = 0; j < 3; j++) {
                if(j == 0){
                    currS = source[j][i];
                }
                if(j == 1){
                    currT = source[j][i];
                }
                if(j == 2){
                    currN = source[j][i];
                }
            }
            list.addOwner(currS,currT,currN);
        }
        return list;
    }

    @Override
    public String toString() {
        return "LinkedQueue{" +
                "first=" + first +
                '}';
    }

    public static class OwnerInQueue {
        private int S;
        private int T;
        private int N;// кол-во товаров, так же и время
        private OwnerInQueue nextOwner;

        public OwnerInQueue(int S,int T,int N,OwnerInQueue nextOwner){
            this.S = S;
            this.T = T;
            this.N = N;
        }

        public OwnerInQueue(int S,int T,int N){
            this.S = S;
            this.T = T;
            this.N = N;
            this.nextOwner = null;
        }

        @Override
        public String toString() {
            return "OwnerInQueue{" +
                    "S=" + S +
                    ", T=" + T +
                    ", N=" + N +
                    ", nextOwner=" + nextOwner +
                    '}';
        }
    }
}
