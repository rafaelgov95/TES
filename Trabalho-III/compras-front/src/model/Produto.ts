export default class Produto {
    id!:number;
    nome:string;
    preco:number;
    quantidade:number;
    constructor() {
        this.id!=null;
        this.nome="";
        this.preco=0.0;
        this.quantidade=0;
    }
    public toString = () : string => {
        return this.nome;
    }
}
