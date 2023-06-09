package br.ufma.egressos.service.exceptions;

public class ServiceRuntimeException extends RuntimeException {
    public ServiceRuntimeException(String msg) {
        super(msg);
    }
}
