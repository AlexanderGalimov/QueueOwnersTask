package ru.vsu.cs.galimov.tasks;

public class Main {

    /*(*) Смоделировать работу магазина. Отсчет времени начинается с 0, все время дискретно
            (т.е. события случаются только в «целочисленные» моменты времени, длительность
                    любого действия также «целочисленна»). Покупатели приходят в магазин, набирают
    товары и идут на кассу. Для каждого покупателя известно, когда он пришел в магазин
            (S), сколько по времени выбирал товары (T), сколько товаров выбрал (N, будем считать,
             что время обслуживания на кассе пропорционально кол-ву набранных товаров и также
             составляет N). Когда покупатель подходит к кассе, если касса обслуживает другого
    покупателя, то подошедший становится в очередь. Если к кассе одновременно подходят
    несколько покупателей, то они могут встать в очередь в случайном порядке.
    Для указанных входных данных для каждого покупателя найти момент времени, когда
    он покинет (может покинуть) магазин.*/

    public static void main(String[] args) {
        new TableForm();
    }
}
