package br.com.agendamento.financeiro.exception;

public class TransferenciaException extends RuntimeException {

    public TransferenciaException(String message) {
        super(message);
    }

    public TransferenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}
