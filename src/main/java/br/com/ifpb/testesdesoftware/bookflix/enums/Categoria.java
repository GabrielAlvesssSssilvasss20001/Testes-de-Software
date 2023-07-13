package br.com.ifpb.testesdesoftware.bookflix.enums;

public enum Categoria {
    BRONZE(5),
    PRATA(3),
    OURO(2),
    ;

    private int prazoDeEntrega;

    Categoria(int prazoDeEntrega) {
        this.prazoDeEntrega = prazoDeEntrega;
    }

    public int getPrazoDeEntrega() {
        return prazoDeEntrega;
    }
}
