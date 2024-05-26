package estudando.api.estudando.dtos.remedio;

public record ListagemComPaginacao(
    ListaDadosRemedio remedios,
    int nextPage,
    int currentPage,
    int lastPage,
    long total 
) {
    
    public ListagemComPaginacao(  ListaDadosRemedio remedios,
    int nextPage,
    int currentPage,
    int lastPage,
    long total){
        this.remedios = remedios;
        this.total = total;
        this.currentPage =currentPage;
        this.nextPage = nextPage;
        this.lastPage = lastPage;
    }
    
}
