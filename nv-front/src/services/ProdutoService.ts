import axios from 'axios';

import Categoria from '@/model/Categoria';

const api = 'http://localhost:8081';

class ProdutoService {
    constructor() {

    }
    delete(id: number) {
        return axios.delete(`${api}/categoria/${id}`);
    }
    get() {
        return axios.get(`${api}/categoria/`);

    }
    add(categoria: Categoria) {
        return axios.post(`${api}/categoria/`, categoria);
    }
    update(categoria: Categoria) {
        return axios.put(`${api}/categoria/`, categoria );
    }
    save(categoria:Categoria) {
        return axios.post(`${api}/categoria/`, categoria);
    }
  
}

export const produtoService = new ProdutoService();
