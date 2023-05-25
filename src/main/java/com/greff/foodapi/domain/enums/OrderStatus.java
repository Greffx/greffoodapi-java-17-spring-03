package com.greff.foodapi.domain.enums;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {

    CREATED,
    CONFIRMED(CREATED), //from created can go to confirmed 'canGoToThis(fromThis)'
    DELIVERED(CONFIRMED),
    CANCELED(CREATED /*it is a list because in the future could have more enum in here*/);

    private final List<OrderStatus> lastStatus;

    OrderStatus(OrderStatus... lastStatus) { //O "..." (é um varargs do java) indica que o parâmetro é variádico,
        // o que significa que pode receber um número variável de argumentos do tipo
        this.lastStatus = Arrays.asList(lastStatus); //passando um array como lista pq pode ser mais de um valor, assim sendo uma lista
    }

    //método para verificar se NÃO pode alterar para um novo status
    public boolean cantAlterTo(OrderStatus newStatus, OrderStatus currentStatus) {
        return !newStatus.lastStatus.contains(currentStatus);
    }
}
