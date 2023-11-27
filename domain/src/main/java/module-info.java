module domain{
    exports domain.modelo;
    requires lombok;
    opens domain.modelo;
    exports domain.errores;
}