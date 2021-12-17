package az.developia.transferer.gateway;

public interface Registerer<T extends Registrable> {
    void register(T registrable);
}
