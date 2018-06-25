import { Produto } from '@/model/Produto';
import axios from 'axios';

import Categoria from '@/model/Categoria';
import Produto from '@/model/Produto';

const api = 'http://localhost:8081';

class CategoriaService {
    constructor() {
    }
    delete(id: number) {
        return axios.delete(`${api}/produto/${id}`);
    }
    add(produto: Produto) {
        return axios.post(`${api}/categoria/`, produto);
    }
    update(produto: Produto) {
        return axios.put(`${api}/categoria/`, produto );
    }
    save(id:number,produto: Produto) {
        return axios.post(`${api}/categoria/`, produto,{params:{id:id}} );
    }
  
}

export const categoriaService = new CategoriaService();
