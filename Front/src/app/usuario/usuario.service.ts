import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { Pessoa } from '../core/models';

export class PessoaFiltro {
  nome: string | undefined;
  tipo: string = 'PF';
  cpfcnpj: string = '';
  pagina: number = 0;
  itensPorPagina: number = 5;
}

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  pessoasUrl: string ='';

  constructor(private http: HttpClient) {
    this.pessoasUrl = `${environment.apiUrl}/pessoas`
  }

  pesquisar(filtro: PessoaFiltro): Promise<any> {

    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

    let params = new HttpParams()
      .set('page', filtro.pagina)
      .set('size', filtro.itensPorPagina);

    if (filtro.nome) {
      params = params.set('nome', filtro.nome);
    }

    if (filtro.tipo) {
      params = params.set('tipo', filtro.tipo);
    }

    if (filtro.cpfcnpj) {
      params = params.set('cpfcnpj', filtro.cpfcnpj);
    }

    return this.http.get(`${this.pessoasUrl}`, { headers, params })
      .toPromise()
      .then((response: any) => {
        const pessoas = response['content'];

        const resultado = {
          pessoas,
          total: response['totalElements']
        };

        return resultado;
      });
  }

  listarTodas(): Promise<any> {
    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

    return this.http.get(this.pessoasUrl, { headers })
      .toPromise()
      .then((response: any) => response['content']);
  }

  excluir(codigo: number): Promise<void> {
    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

    return this.http.delete<void>(`${this.pessoasUrl}/${codigo}`, { headers }).toPromise();
  }

  mudarStatus(codigo: number, ativo: boolean): Promise<void> {
    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==')
      .append('Content-type','application/json');

    return this.http.put<void>(`${this.pessoasUrl}/${codigo}/ativo`, ativo,{ headers })
    .toPromise();
  }

  adicionar(pessoa: Pessoa): Promise<Pessoa|undefined> {
    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==')
      .append('Content-Type', 'application/json');

    return this.http.post<Pessoa>(this.pessoasUrl, pessoa, { headers })
      .toPromise();
  }

  atualizar(pessoa: Pessoa): Promise<Pessoa|undefined> {
    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==')
      .append('Content-Type', 'application/json');

    return this.http.put<Pessoa>(`${this.pessoasUrl}/${pessoa.codigo}`, pessoa, { headers })
      .toPromise();
  }

  buscarPorCodigo(codigo: number): Promise<Pessoa|undefined> {
    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

    return this.http.get<Pessoa>(`${this.pessoasUrl}/codigo/${codigo}`, { headers })
      .toPromise();
  }
}
