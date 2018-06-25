import Produto  from '@/model/Produto';
import axios from 'axios';


const api = 'http://localhost:8081';

class ProdutoService {

    constructor() {
    }
    delete(id: number) {
        return axios.delete(`${api}/produto/${id}`);
    }
    add(produto: Produto) {
        return axios.post(`${api}/produto/`, produto);
    }
    update(id:number,produto: Produto) {
        return axios.put(`${api}/produto/`, produto,{params:{id:id}} );
    }
    save(id:number,produto: Produto) {
        return axios.post(`${api}/produto/`, produto,{params:{id:id}} );
    }
  
}

export const produtoService = new ProdutoService();
