package com.ifmo.lesson2;

public class LuckyTickets {
    /*
    В городе N проезд в трамвае осуществляется по бумажным отрывным билетам. Каждую
    неделю трамвайное депо заказывает в местной типографии рулон билетов с номерами от
    000001 до 999999. «Счастливым» считается билетик у которого сумма первых трёх цифр
    номера равна сумме последних трёх цифр, как, например, в билетах с номерами 003102 или
    567576. Трамвайное депо решило подарить сувенир обладателю каждого счастливого билета
    и теперь раздумывает, как много сувениров потребуется. С помощью программы подсчитайте
    сколько счастливых билетов в одном рулоне?
     */
    public static void main(String[] args) {
        System.out.println(luckyTickets());
    }

    public static int luckyTickets() {
        int luckyTickets = 0;
        for (int i = 1; i <= 999999; i++) {
            String str = String.valueOf(i);
            String ticketNumber = buildStringTicketNumber(str);
            if (sumDigit(ticketNumber.charAt(0), ticketNumber.charAt(1), ticketNumber.charAt(2))
                    == sumDigit(ticketNumber.charAt(3), ticketNumber.charAt(4), ticketNumber.charAt(5))) {
                luckyTickets++;
            }
        }
        return luckyTickets;
    }

    private static String buildStringTicketNumber(String str) {
        int i;
        if ((i = 6 - str.length()) > 0) {
            StringBuilder builder = new StringBuilder();
            while (i > 0) {
                builder.append(0);
                i--;
            }
            str = builder.append(str).toString();
        }
        return str;
    }

    private static int sumDigit(char... chars) {
        int sum = 0;
        for (char item : chars) {
            sum += convertCharToInt(item);
        }
        return sum;

    }

    private static int convertCharToInt(char item) {
        return Integer.valueOf(String.valueOf(item));
    }
}
